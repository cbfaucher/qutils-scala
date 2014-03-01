package com.quartz.qutilities.logging;

/**
 * Constant class
 */
final class LoggingConstants
{
    /**
     * Default log4j config file name = log4j.xml
     */
    static final String DEFAULT_LOG4J_CONFIG            = "/src/test/config/log4j.xml";
    /**
     * Default log filename = axa.log
     */
    static final String DEFAULT_LOG_FILE_NAME            = "quartz.log";
    /**
     * System property to check for log4j file path =  log4j.config
     */
    static final String SYSPROP_OVERRIDE_LOG4J_CONFIG   = "log4j.config";
    /**
     * System property name for Tomcat home = catalina.home
     */
    static final String SYSPROP_CATALINA_HOME           = "catalina.home";
    /**
     * System property for logging directory = axa.log.directory
     */
    static final String SYSPROP_LOG_FILE_DIRECTORY       = "quartz.log.directory";
    /**
     * System property for logg filename = axa.log.name
     */
    static final String USER_LOG_FILE_NAME            = "quartz.log.name";
    
    /**
     * Disallow instanciation
     */
    private LoggingConstants()
    {
        super();
    }
}
