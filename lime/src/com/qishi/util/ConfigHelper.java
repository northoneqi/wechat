package com.qishi.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ConfigHelper {
	static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";

	static private boolean java1 = false;

	static private boolean ignoreTCL = false;

	static public URL getResource(String resource) {
		ClassLoader classLoader = null;
		URL url = null;

		try {
			if (!java1) {
				classLoader = getTCL();
				if (classLoader != null) {
					url = classLoader.getResource(resource);
					if (url != null) {
						return url;
					}
				}
			}

			classLoader = ConfigHelper.class.getClassLoader();
			if (classLoader != null) {
				url = classLoader.getResource(resource);
				if (url != null) {
					return url;
				}
			}
		} catch (Throwable t) {
		}

		return ClassLoader.getSystemResource(resource);
	}

	static public InputStream getResourceAsStream(String resource) {
		ClassLoader classLoader = null;
		InputStream is = null;
		Resource rs = new ClassPathResource(resource);
		
		try {
			if (!java1) {
				classLoader = getTCL();
				if (classLoader != null) {
//					is = classLoader.getResourceAsStream(resource);
					is = rs.getInputStream();
					if (is != null) {
						return is;
					}
				}
			}

			classLoader = ConfigHelper.class.getClassLoader();
			if (classLoader != null) {
//				is = classLoader.getResourceAsStream(resource);
				is = rs.getInputStream();
				if (is != null) {
					return is;
				}
			}
		} catch (Throwable t) {
		}

		return ClassLoader.getSystemResourceAsStream(resource);
	}

	public static InputStream getResourceFromPath(String file) {
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
	}

	public static InputStream getResourceFromFile(File file) {
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;

	}

	public static boolean isJava1() {
		return java1;
	}

	private static ClassLoader getTCL() throws IllegalAccessException,
			InvocationTargetException {

		// Are we running on a JDK 1.2 or later system?
		Method method = null;
		try {
			method = Thread.class.getMethod("getContextClassLoader",
					new Class[0]);
		} catch (NoSuchMethodException e) {
			// We are running on JDK 1.1
			return null;
		}

		return (ClassLoader) method.invoke(Thread.currentThread(),
				new Object[0]);
	}

	static public Class loadClass(String clazz) throws ClassNotFoundException {
		if (java1 || ignoreTCL) {
			return Class.forName(clazz);
		} else {
			try {
				return getTCL().loadClass(clazz);
			} catch (Throwable e) {
				return Class.forName(clazz);
			}
		}
	}

}
