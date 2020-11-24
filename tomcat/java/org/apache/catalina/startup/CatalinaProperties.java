/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina.startup;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


/**
 * Utility class to read the bootstrap Catalina configuration.
 *
 * @author Remy Maucherat
 */
public class CatalinaProperties {

//    private static final Log log = LogFactory.getLog(CatalinaProperties.class);


    private static Properties properties = new Properties();


    static {
        try {
            properties.load(
                    new ByteArrayInputStream(CatalinaProperties.CATALINA_PROPERTIES_TEXT.getBytes())
            );
        } catch (IOException e) {
            throw new AssertionError();
        }
        loadProperties();
    }


    /**
     * @param name The property name
     * @return specified property value
     */
    public static String getProperty(String name) {
        return properties.getProperty(name);
    }


    /**
     * Load properties.
     */
    private static void loadProperties() {

//        InputStream is = null;
//        String fileName = "catalina.properties";
//
//        try {
//            String configUrl = System.getProperty("catalina.config");
//            if (configUrl != null) {
//                if (configUrl.indexOf('/') == -1) {
//                    // No '/'. Must be a file name rather than a URL
//                    fileName = configUrl;
//                } else {
//                    is = (new URL(configUrl)).openStream();
//                }
//            }
//        } catch (Throwable t) {
//            handleThrowable(t);
//        }
//
//        if (is == null) {
//            try {
//                File home = new File(Bootstrap.getCatalinaBase());
//                File conf = new File(home, "conf");
//                File propsFile = new File(conf, fileName);
//                is = new FileInputStream(propsFile);
//            } catch (Throwable t) {
//                handleThrowable(t);
//            }
//        }
//
//        if (is == null) {
//            try {
//                is = CatalinaProperties.class.getResourceAsStream
//                    ("/org/apache/catalina/startup/catalina.properties");
//            } catch (Throwable t) {
//                handleThrowable(t);
//            }
//        }
//
//        if (is != null) {
//            try {
//                properties = new Properties();
//                properties.load(is);
//            } catch (Throwable t) {
//                handleThrowable(t);
//                log.warn(t);
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException ioe) {
//                    log.warn("Could not close catalina properties file", ioe);
//                }
//            }
//        }
//
//        if ((is == null)) {
//            // Do something
//            log.warn("Failed to load catalina properties file");
//            // That's fine - we have reasonable defaults.
//            properties = new Properties();
//        }

        // Register the properties as system properties
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = properties.getProperty(name);
            if (value != null) {
                System.setProperty(name, value);
            }
        }
    }


    // Copied from ExceptionUtils since that class is not visible during start
    /*private static void handleThrowable(Throwable t) {
        if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        }
        if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        }
        // All other instances of Throwable will be silently swallowed
    }*/


    private static final String CATALINA_PROPERTIES_TEXT = "# Licensed to the Apache Software Foundation (ASF) under one or more\n" +
            "# contributor license agreements.  See the NOTICE file distributed with\n" +
            "# this work for additional information regarding copyright ownership.\n" +
            "# The ASF licenses this file to You under the Apache License, Version 2.0\n" +
            "# (the \"License\"); you may not use this file except in compliance with\n" +
            "# the License.  You may obtain a copy of the License at\n" +
            "#\n" +
            "#     http://www.apache.org/licenses/LICENSE-2.0\n" +
            "#\n" +
            "# Unless required by applicable law or agreed to in writing, software\n" +
            "# distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
            "# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
            "# See the License for the specific language governing permissions and\n" +
            "# limitations under the License.\n" +
            "\n" +
            "#\n" +
            "# List of comma-separated packages that start with or equal this string\n" +
            "# will cause a security exception to be thrown when\n" +
            "# passed to checkPackageAccess unless the\n" +
            "# corresponding RuntimePermission (\"accessClassInPackage.\"+package) has\n" +
            "# been granted.\n" +
            "package.access=sun.,org.apache.catalina.,org.apache.coyote.,org.apache.jasper.,org.apache.tomcat.\n" +
            "#\n" +
            "# List of comma-separated packages that start with or equal this string\n" +
            "# will cause a security exception to be thrown when\n" +
            "# passed to checkPackageDefinition unless the\n" +
            "# corresponding RuntimePermission (\"defineClassInPackage.\"+package) has\n" +
            "# been granted.\n" +
            "#\n" +
            "# by default, no packages are restricted for definition, and none of\n" +
            "# the class loaders supplied with the JDK call checkPackageDefinition.\n" +
            "#\n" +
            "package.definition=sun.,java.,org.apache.catalina.,org.apache.coyote.,\\\n" +
            "org.apache.jasper.,org.apache.naming.,org.apache.tomcat.\n" +
            "\n" +
            "#\n" +
            "#\n" +
            "# List of comma-separated paths defining the contents of the \"common\"\n" +
            "# classloader. Prefixes should be used to define what is the repository type.\n" +
            "# Path may be relative to the CATALINA_HOME or CATALINA_BASE path or absolute.\n" +
            "# If left as blank,the JVM system loader will be used as Catalina's \"common\"\n" +
            "# loader.\n" +
            "# Examples:\n" +
            "#     \"foo\": Add this folder as a class repository\n" +
            "#     \"foo/*.jar\": Add all the JARs of the specified folder as class\n" +
            "#                  repositories\n" +
            "#     \"foo/bar.jar\": Add bar.jar as a class repository\n" +
            "#\n" +
            "# Note: Values are enclosed in double quotes (\"...\") in case either the\n" +
            "#       ${catalina.base} path or the ${catalina.home} path contains a comma.\n" +
            "#       Because double quotes are used for quoting, the double quote character\n" +
            "#       may not appear in a path.\n" +
            "common.loader=\"${catalina.base}/lib\",\"${catalina.base}/lib/*.jar\",\"${catalina.home}/lib\",\"${catalina.home}/lib/*.jar\"\n" +
            "\n" +
            "#\n" +
            "# List of comma-separated paths defining the contents of the \"server\"\n" +
            "# classloader. Prefixes should be used to define what is the repository type.\n" +
            "# Path may be relative to the CATALINA_HOME or CATALINA_BASE path or absolute.\n" +
            "# If left as blank, the \"common\" loader will be used as Catalina's \"server\"\n" +
            "# loader.\n" +
            "# Examples:\n" +
            "#     \"foo\": Add this folder as a class repository\n" +
            "#     \"foo/*.jar\": Add all the JARs of the specified folder as class\n" +
            "#                  repositories\n" +
            "#     \"foo/bar.jar\": Add bar.jar as a class repository\n" +
            "#\n" +
            "# Note: Values may be enclosed in double quotes (\"...\") in case either the\n" +
            "#       ${catalina.base} path or the ${catalina.home} path contains a comma.\n" +
            "#       Because double quotes are used for quoting, the double quote character\n" +
            "#       may not appear in a path.\n" +
            "server.loader=\n" +
            "\n" +
            "#\n" +
            "# List of comma-separated paths defining the contents of the \"shared\"\n" +
            "# classloader. Prefixes should be used to define what is the repository type.\n" +
            "# Path may be relative to the CATALINA_BASE path or absolute. If left as blank,\n" +
            "# the \"common\" loader will be used as Catalina's \"shared\" loader.\n" +
            "# Examples:\n" +
            "#     \"foo\": Add this folder as a class repository\n" +
            "#     \"foo/*.jar\": Add all the JARs of the specified folder as class\n" +
            "#                  repositories\n" +
            "#     \"foo/bar.jar\": Add bar.jar as a class repository\n" +
            "# Please note that for single jars, e.g. bar.jar, you need the URL form\n" +
            "# starting with file:.\n" +
            "#\n" +
            "# Note: Values may be enclosed in double quotes (\"...\") in case either the\n" +
            "#       ${catalina.base} path or the ${catalina.home} path contains a comma.\n" +
            "#       Because double quotes are used for quoting, the double quote character\n" +
            "#       may not appear in a path.\n" +
            "shared.loader=\n" +
            "\n" +
            "# Default list of JAR files that should not be scanned using the JarScanner\n" +
            "# functionality. This is typically used to scan JARs for configuration\n" +
            "# information. JARs that do not contain such information may be excluded from\n" +
            "# the scan to speed up the scanning process. This is the default list. JARs on\n" +
            "# this list are excluded from all scans. The list must be a comma separated list\n" +
            "# of JAR file names.\n" +
            "# The list of JARs to skip may be over-ridden at a Context level for individual\n" +
            "# scan types by configuring a JarScanner with a nested JarScanFilter.\n" +
            "# The JARs listed below include:\n" +
            "# - Tomcat Bootstrap JARs\n" +
            "# - Tomcat API JARs\n" +
            "# - Catalina JARs\n" +
            "# - Jasper JARs\n" +
            "# - Tomcat JARs\n" +
            "# - Common non-Tomcat JARs\n" +
            "# - Test JARs (JUnit, Cobertura and dependencies)\n" +
            "tomcat.util.scan.StandardJarScanFilter.jarsToSkip=\\\n" +
            "annotations-api.jar,\\\n" +
            "ant-junit*.jar,\\\n" +
            "ant-launcher.jar,\\\n" +
            "ant.jar,\\\n" +
            "asm-*.jar,\\\n" +
            "aspectj*.jar,\\\n" +
            "bootstrap.jar,\\\n" +
            "catalina-ant.jar,\\\n" +
            "catalina-ha.jar,\\\n" +
            "catalina-ssi.jar,\\\n" +
            "catalina-storeconfig.jar,\\\n" +
            "catalina-tribes.jar,\\\n" +
            "catalina.jar,\\\n" +
            "cglib-*.jar,\\\n" +
            "cobertura-*.jar,\\\n" +
            "commons-beanutils*.jar,\\\n" +
            "commons-codec*.jar,\\\n" +
            "commons-collections*.jar,\\\n" +
            "commons-daemon.jar,\\\n" +
            "commons-dbcp*.jar,\\\n" +
            "commons-digester*.jar,\\\n" +
            "commons-fileupload*.jar,\\\n" +
            "commons-httpclient*.jar,\\\n" +
            "commons-io*.jar,\\\n" +
            "commons-lang*.jar,\\\n" +
            "commons-logging*.jar,\\\n" +
            "commons-math*.jar,\\\n" +
            "commons-pool*.jar,\\\n" +
            "dom4j-*.jar,\\\n" +
            "easymock-*.jar,\\\n" +
            "ecj-*.jar,\\\n" +
            "el-api.jar,\\\n" +
            "geronimo-spec-jaxrpc*.jar,\\\n" +
            "h2*.jar,\\\n" +
            "hamcrest-*.jar,\\\n" +
            "hibernate*.jar,\\\n" +
            "httpclient*.jar,\\\n" +
            "icu4j-*.jar,\\\n" +
            "jasper-el.jar,\\\n" +
            "jasper.jar,\\\n" +
            "jaspic-api.jar,\\\n" +
            "jaxb-*.jar,\\\n" +
            "jaxen-*.jar,\\\n" +
            "jdom-*.jar,\\\n" +
            "jetty-*.jar,\\\n" +
            "jmx-tools.jar,\\\n" +
            "jmx.jar,\\\n" +
            "jsp-api.jar,\\\n" +
            "jstl.jar,\\\n" +
            "jta*.jar,\\\n" +
            "junit-*.jar,\\\n" +
            "junit.jar,\\\n" +
            "log4j*.jar,\\\n" +
            "mail*.jar,\\\n" +
            "objenesis-*.jar,\\\n" +
            "oraclepki.jar,\\\n" +
            "oro-*.jar,\\\n" +
            "servlet-api-*.jar,\\\n" +
            "servlet-api.jar,\\\n" +
            "slf4j*.jar,\\\n" +
            "taglibs-standard-spec-*.jar,\\\n" +
            "tagsoup-*.jar,\\\n" +
            "tomcat-api.jar,\\\n" +
            "tomcat-coyote.jar,\\\n" +
            "tomcat-dbcp.jar,\\\n" +
            "tomcat-i18n-*.jar,\\\n" +
            "tomcat-jdbc.jar,\\\n" +
            "tomcat-jni.jar,\\\n" +
            "tomcat-juli-adapters.jar,\\\n" +
            "tomcat-juli.jar,\\\n" +
            "tomcat-util-scan.jar,\\\n" +
            "tomcat-util.jar,\\\n" +
            "tomcat-websocket.jar,\\\n" +
            "tools.jar,\\\n" +
            "websocket-api.jar,\\\n" +
            "wsdl4j*.jar,\\\n" +
            "xercesImpl.jar,\\\n" +
            "xml-apis.jar,\\\n" +
            "xmlParserAPIs-*.jar,\\\n" +
            "xmlParserAPIs.jar,\\\n" +
            "xom-*.jar\n" +
            "\n" +
            "# Default list of JAR files that should be scanned that overrides the default\n" +
            "# jarsToSkip list above. This is typically used to include a specific JAR that\n" +
            "# has been excluded by a broad file name pattern in the jarsToSkip list.\n" +
            "# The list of JARs to scan may be over-ridden at a Context level for individual\n" +
            "# scan types by configuring a JarScanner with a nested JarScanFilter.\n" +
            "tomcat.util.scan.StandardJarScanFilter.jarsToScan=\\\n" +
            "log4j-taglib*.jar,\\\n" +
            "log4j-web*.jar,\\\n" +
            "log4javascript*.jar,\\\n" +
            "slf4j-taglib*.jar\n" +
            "\n" +
            "# String cache configuration.\n" +
            "tomcat.util.buf.StringCache.byte.enabled=true\n" +
            "#tomcat.util.buf.StringCache.char.enabled=true\n" +
            "#tomcat.util.buf.StringCache.trainThreshold=500000\n" +
            "#tomcat.util.buf.StringCache.cacheSize=5000\n";


}
