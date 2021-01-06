package base;

import org.apache.commons.lang3.StringUtils;

public class BaseSteps {
    private static final String DEFAULT_HOST = "https://openweathermap.org";
    private static final String STAGING_HOST = "https://openweathermap.org/";
    private static final String QA_HOST = "https://openweathermap-qa.org/";
    private static final String STAGING = "staging";
    private static final String QA = "qa";

    protected String getHostByEnvironment() {
        String env = StringUtils.defaultString(System.getProperty("environment"));
        switch (env) {
            case STAGING:
                return STAGING_HOST;
            case QA:
                return QA_HOST;
            default:
                return DEFAULT_HOST;
        }
    }


}

