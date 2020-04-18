package edu.aeon.sim;

import edu.illinois.cs.cogcomp.core.utilities.configuration.ResourceManager;

import java.util.Properties;

public class ResourceManagerFactory {

    private static final String WN_PATH_KEY = "wnPath";

    public static ResourceManager get(String wnPath) {
        Properties props = new Properties();
        props.setProperty(WN_PATH_KEY, wnPath);
        return new ResourceManager(props);
    }

}
