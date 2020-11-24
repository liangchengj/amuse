package com.meyoustu.amuse.gous.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author Liangcheng Juves
 * Created at 2020/04/30 14:50
 */
public final class Resource {

    private Resource() {
    }

    private final ClassLoader classLoader = Resource.class.getClassLoader();

    private static ClassLoader getClassLoader() {
        return new Resource().classLoader;
    }

    public static InputStream getAsStream(String name) {
        return getClassLoader().getResourceAsStream(name);
    }

    public static InputStream getAsStream(Class<?> clazz, String name) {
        return clazz.getResourceAsStream(name);
    }

    public static URL getAsURL(String name) {
        return getClassLoader().getResource(name);
    }

    public static String getAsURLPath(String name) {
        return getAsURL(name).getPath();
    }

    public static Enumeration<URL> getAsURLs(String name)
            throws IOException {
        return getClassLoader().getResources(name);
    }

}
