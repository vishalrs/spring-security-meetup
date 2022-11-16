package com.sogeti.meetups.springsec;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.io.IOException;

public class DelegatingProxyApplication {

	private static final int PORT = 8080;

	public static void main(String[] ars) throws Exception {
		//configure and start tomcat
		String appBase = ".";
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(createTempDir());
		tomcat.setPort(PORT);
		tomcat.getHost().setAppBase(appBase);
		Context ctx = tomcat.addWebapp("", appBase);
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
				additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
	}

	private static String createTempDir() {
		try {
			File tempDir = File.createTempFile("tomcat.", "." + PORT);
			tempDir.delete();
			tempDir.mkdir();
			tempDir.deleteOnExit();
			return tempDir.getAbsolutePath();
		} catch (IOException ex) {
			throw new RuntimeException(
					"Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
					ex
			);
		}
	}

}
