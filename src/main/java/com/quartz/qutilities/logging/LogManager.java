package com.quartz.qutilities.logging;

import java.io.File;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Provide logging facilities
 * <p>
 * <b>Usage : </b>
 * </p>
 * 
 * <pre><code>
 * 
 *  private static final ILog log = LogManager.getLogger(Class c);
 *   [...]
 *   log.debug(message);
 *  
 * </code></pre>
 * 
 * <p>
 * Priority levels are :
 * </p>
 * Level.DEBUG, <br/>Level.INFO, <br/>Level.WARN, <br/>Level.ERROR, <br/>Level.FATAL, <br/>
 */
public final class LogManager
{
    private static final Map LOGGERS = new Hashtable();
    private static final String ROOT_LOG4J_CONFIG = "/log4j.xml";

    private static File loggingDirectory;

    static
    {
        configureLogging();
    }

    public static void configureLogging()
    {
        final String overrideLog4jXml = System.getProperty(LoggingConstants.SYSPROP_OVERRIDE_LOG4J_CONFIG);
        final String logFileDirectory = System.getProperty(LoggingConstants.SYSPROP_LOG_FILE_DIRECTORY);
        final String logFileName = System.getProperty(LoggingConstants.USER_LOG_FILE_NAME);

        //  check if override log4j is specified
        if (overrideLog4jXml == null)
        {
            displayNote("You can override default log4j.xml file by specifying -D"
                + LoggingConstants.SYSPROP_OVERRIDE_LOG4J_CONFIG + "=<full path to log4j.xml>");
        }

        //  specify logging directory, guess it if not specified.
        if (logFileDirectory != null)
        {
            loggingDirectory = new File(logFileDirectory);
        }
        else
        {
            displayNote("You can override the default location directory for log files by specifying -D"
                + LoggingConstants.SYSPROP_LOG_FILE_DIRECTORY + "=<log directory>.");
            loggingDirectory = guessLoggingDirectory();
        }

        // specify log name, default otherwise
        final File loggingFile;
        if (logFileName != null)
        {
            loggingFile = new File(loggingDirectory, logFileName);
        }
        else
        {
            displayNote("You can override the default log filename for log files by specifying -D"
                + LoggingConstants.USER_LOG_FILE_NAME + "=<log filename>.");
            loggingFile = new File(loggingDirectory, LoggingConstants.DEFAULT_LOG_FILE_NAME);
        }

        displayNote("Log Directory: " + loggingDirectory.getAbsolutePath());
        displayNote("Log Filename: " + loggingFile.getAbsolutePath());

        //  a configurator tweaker to replace certain variables specified.
        final DOMConfigurator domConfigurator = createDOMConfigurator(loggingDirectory, loggingFile);

        //  configure
        if (overrideLog4jXml == null)
        {
            System.out.println("Check if /log4j.xml exists");
            URL log4jURL = LogManager.class.getResource(ROOT_LOG4J_CONFIG);
            if (log4jURL == null)
            {
                System.out.println(ROOT_LOG4J_CONFIG + " is NOT found..  Looking for default");
                log4jURL = LogManager.class.getResource(LoggingConstants.DEFAULT_LOG4J_CONFIG);
            }

            if (log4jURL == null)
            {
                throw new LoggingInitialisationException("Default log4j.xml file not found!");
            }

            System.out.println("Using Log4j XML Configuration: " + log4jURL.toString());

            domConfigurator.doConfigure(log4jURL, org.apache.log4j.LogManager.getLoggerRepository());
        }
        else
        {
            displayNote("Using OVERRIDE for log4j.xml: " + overrideLog4jXml);
            domConfigurator.doConfigure(overrideLog4jXml, org.apache.log4j.LogManager.getLoggerRepository());
        }

        // default logger
        final ILog defaultLogger = new Log4jImpl(LogManager.class);
        LogManager.LOGGERS.put(LogManager.class, defaultLogger);

        defaultLogger.info("PROPERTY -Duser.name=" + System.getProperty("user.name"));
        defaultLogger.info("PROPERTY -D" + LoggingConstants.SYSPROP_OVERRIDE_LOG4J_CONFIG + "=" + overrideLog4jXml);
        defaultLogger.info("PROPERTY -D" + LoggingConstants.SYSPROP_LOG_FILE_DIRECTORY + "=" + logFileDirectory);
        defaultLogger.info("PROPERTY -D" + LoggingConstants.USER_LOG_FILE_NAME + "=" + logFileName);
        defaultLogger.info("PROPERTY -D" + LoggingConstants.SYSPROP_CATALINA_HOME + "="
            + System.getProperty(LoggingConstants.SYSPROP_CATALINA_HOME));
    }

    private LogManager()
    {
        super();
    }

    public static File getLoggingdirectory()
    {
        return loggingDirectory;
    }

    /**
     * Creates a {@link org.apache.log4j.xml.DOMConfigurator}that replaces the ${{@link LoggingConstants#SYSPROP_LOG_FILE_DIRECTORY}}
     * variable with the appropriate value.
     * <p>
     * Usually that would be done using System Properties, but security checks prevent J2EE application to change'em.
     * 
     * @param pConfigurationDirectory
     *            The directory where to store Java-Excel logging files
     * @return A {@link org.apache.log4j.xml.DOMConfigurator}to use to configure our Log4J.
     */
    private static DOMConfigurator createDOMConfigurator(final File pConfigurationDirectory,
        final File pConfigurationFile)
    {
        return new DOMConfigurator()
        {
            /**
             * Overriden version so that we can trap the logging.directory variable.
             */
            protected String subst(final String value)
            {
                final String loggingDirVar = "${" + LoggingConstants.SYSPROP_LOG_FILE_DIRECTORY + "}";
                final String loggingFileVar = "${" + LoggingConstants.USER_LOG_FILE_NAME + "}";
                final StringBuffer substitution = new StringBuffer(value);

                if (value.indexOf(loggingDirVar) != -1)
                {

                    substitution.replace(substitution.indexOf(loggingDirVar), substitution.indexOf(loggingDirVar)
                        + loggingDirVar.length(), pConfigurationDirectory.getAbsolutePath());
                }

                if (value.indexOf(loggingFileVar) != -1)
                {

                    substitution.replace(substitution.indexOf(loggingFileVar), substitution.indexOf(loggingFileVar)
                        + loggingFileVar.length(), pConfigurationFile.getName());
                }

                return super.subst(substitution.toString());
            }
        };
    }

    /**
     * If the logging directory is not specified using {@link LoggingConstants#SYSPROP_LOG_FILE_DIRECTORY}, this method takes a guess to
     * where Java-Excel log files should be stored.
     * 
     * @return A directory where to store the file
     * @throws LoggingInitialisationException
     *             If no such directory can be "guessed"
     */
    private static File guessLoggingDirectory() throws LoggingInitialisationException
    {
        final String catalinaHome = System.getProperty(LoggingConstants.SYSPROP_CATALINA_HOME);
        if (catalinaHome != null)
        {
            final File logDir = new File(catalinaHome, "logs");
            if (isValidLoggingDirectory(logDir))
            {
                return logDir;
            }
        }

        //  ${catalina.home}/logs didn't work
        //  Try ${user.home}/logs instead
        final String userHome = System.getProperty("user.home");
        if (userHome != null)
        {
            final File logDir = new File(userHome);
            if (isValidLoggingDirectory(logDir))
            {
                return logDir;
            }
        }

        throw new LoggingInitialisationException("No directory could be found for writing the log files!");
    }

    /**
     * Verifies if the directory stored in the file is a valid directory for log files.
     * 
     * @return true if valid, false otherwise.
     */
    private static boolean isValidLoggingDirectory(final File pLogDir)
    {
        return pLogDir.exists() && pLogDir.canWrite();
    }

    /**
     * Displays a note on the screen
     * 
     * @param pMsg
     *            The note's message.
     */
    private static void displayNote(final String pMsg)
    {
        System.out.print("<<< LogManager Notice >>> ");
        System.out.print(pMsg);
        System.out.println();
    }

    /**
     * Return the Log wrapper associated with the class c
     * 
     * @param c
     *            A class (usually current class)
     * @return a Log object taht provide logging methods
     */
    public static ILog getLogger(final Class c)
    {
        if (c == null)
        {
            return (ILog) LOGGERS.get(LogManager.class);
        }
        Object o = LOGGERS.get(c);
        if (o == null)
        {
            o = new Log4jImpl(c);
            LOGGERS.put(c, o);
        }
        return (ILog) o;
    }

    /**
     * Return the Logger associated with the logger name of the configuration file
     * 
     * @param pLoggerName
     *            String representing the logger name defined in the configuration file
     * @return A Log object that provide logging methods
     */
    public static ILog getLogger(final String pLoggerName)
    {
        if (pLoggerName == null)
        {
            return (ILog) LOGGERS.get(LogManager.class);
        }
        Object o = LOGGERS.get(pLoggerName);
        if (o == null)
        {
            o = new Log4jImpl(pLoggerName);
            LOGGERS.put(pLoggerName, o);
        }
        return (ILog) o;
    }

    /**
     * Sends a DEBUG message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     */
    public static void debug(final Class c, final Object o)
    {
        Logger.getLogger(c).debug(o);
    }

    /////////////////////////////// STATIC DEBUG METHODS ///////////////////////////////

    /**
     * Sends a DEBUG message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     */
    public static void debug(final String loggerName, final Object o)
    {
        Logger.getLogger(loggerName).debug(o);
    }

    /**
     * Sends a DEBUG message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void debug(final Class c, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(c).debug(o, pThrowable);
    }

    /**
     * Sends a DEBUG message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void debug(final String loggerName, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(loggerName).debug(o, pThrowable);
    }

    /////////////////////////////// STATIC INFO METHODS ///////////////////////////////

    /**
     * Sends a INFO message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     */
    public static void info(final Class c, final Object o)
    {
        Logger.getLogger(c).info(o);
    }

    /**
     * Sends a INFO message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     */
    public static void info(final String loggerName, final Object o)
    {
        Logger.getLogger(loggerName).info(o);
    }

    /**
     * Sends a INFO message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void info(final Class c, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(c).info(o, pThrowable);
    }

    /**
     * Sends a INFO message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void info(final String loggerName, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(loggerName).info(o, pThrowable);
    }

    /////////////////////////////// STATIC WARN METHODS ///////////////////////////////

    /**
     * Sends a WARN message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     */
    public static void warn(final Class c, final Object o)
    {
        Logger.getLogger(c).info(o);
    }

    /**
     * Sends a WARN message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     */
    public static void warn(final String loggerName, final Object o)
    {
        Logger.getLogger(loggerName).info(o);
    }

    /**
     * Sends a WARN message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void warn(final Class c, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(c).info(o, pThrowable);
    }

    /**
     * Sends a WARN message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void warn(final String loggerName, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(loggerName).info(o, pThrowable);
    }

    /////////////////////////////// STATIC ERROR METHODS ///////////////////////////////

    /**
     * Sends a ERROR message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     */
    public static void error(final Class c, final Object o)
    {
        Logger.getLogger(c).info(o);
    }

    /**
     * Sends a ERROR message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     */
    public static void error(final String loggerName, final Object o)
    {
        Logger.getLogger(loggerName).info(o);
    }

    /**
     * Sends a ERROR message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void error(final Class c, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(c).info(o, pThrowable);
    }

    /**
     * Sends a ERROR message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void error(final String loggerName, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(loggerName).info(o, pThrowable);
    }

    /////////////////////////////// STATIC FATAL METHODS ///////////////////////////////

    /**
     * Sends a FATAL message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     */
    public static void fatal(final Class c, final Object o)
    {
        Logger.getLogger(c).info(o);
    }

    /**
     * Sends a FATAL message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     */
    public static void fatal(final String loggerName, final Object o)
    {
        Logger.getLogger(loggerName).info(o);
    }

    /**
     * Sends a FATAL message
     * 
     * @param c
     *            Current class name for which to get a configured logger
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void fatal(final Class c, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(c).info(o, pThrowable);
    }

    /**
     * Sends a FATAL message
     * 
     * @param loggerName
     *            The name of the logger to use
     * @param o
     *            Object to log
     * @param pThrowable
     *            Cause to send for logging
     */
    public static void fatal(final String loggerName, final Object o, final Throwable pThrowable)
    {
        Logger.getLogger(loggerName).info(o, pThrowable);
    }

    /**
     * Sets the level for the given logger.  If new level is invalid, it sets it to DEBUG.
     *
     * @param pLoggerName Logger name
     * @param pLevel New level, one value of "DEBUG", "INFO", "WARN", "ERROR", "FATAL", or "OFF".
     */
    public static void setLevel(final String pLoggerName, final String pLevel)
    {
        Logger.getLogger(pLoggerName).setLevel(Level.toLevel(pLevel));
    }
}