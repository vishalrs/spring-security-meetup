package com.sogeti.meetups.springsec.customauthstrategy.security;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sogeti.meetups.springsec.customauthstrategy.model.ApiKeyUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ApiKeyAuthProvider implements AuthenticationProvider {
    @Value("classpath:users.csv")
    Resource resourceFile;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final ApiKeyAuthToken tokenContainer = (ApiKeyAuthToken) auth;
        final String apiKey = tokenContainer.getApiKey();
        Optional<String[]> record = null;
        try {
            List<String[]> data = getDataFromCSV();
            record = getUserDetails(data, apiKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!record.isEmpty()) {
            String[] userRecord = record.get();
            ApiKeyUser user = new ApiKeyUser(userRecord[0],userRecord[1],userRecord[2],userRecord[3],
                    List.of(new SimpleGrantedAuthority(userRecord[4])));
            return new ApiKeyAuthToken(user);
        }
        else {
            throw new BadCredentialsException("No user found for token - " + apiKey);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //this class is only responsible for AuthTokenContainers
        return ApiKeyAuthToken.class.isAssignableFrom(authentication);
    }

    private List<String[]> getDataFromCSV() throws IOException {
        FileReader filereader = new FileReader(resourceFile.getFile());
        // create csvReader object and skip first Line
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();
        return csvReader.readAll();
    }

    private Optional<String[]> getUserDetails(List<String[]> data, String apiKey){
        return data.stream()
                   .filter(row-> row[3].trim().equals(apiKey))
                   .findFirst() ;
    }
}
