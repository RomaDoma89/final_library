package team2.spring.library;

import org.apache.log4j.Logger;


public class Log {

    public static void info (String className, String msg) {
        Logger LOG = Logger.getLogger(className);
        LOG.info(msg);
    }

    public static void debug (String className, String msg) {
        Logger LOG = Logger.getLogger(className);
        LOG.debug(msg);
    }

    public static void fatal (String className, String msg) {
        Logger LOG = Logger.getLogger(className);
        LOG.fatal(msg);
    }

    public static void fatal (String className, String msg, Throwable t) {
        Logger LOG = Logger.getLogger(className);
        LOG.fatal(msg, t);
    }

    public static void error (String className, String msg) {
        Logger LOG = Logger.getLogger(className);
        LOG.error(msg);
    }

    public static void error (String className, String msg, Throwable t) {
        Logger LOG = Logger.getLogger(className);
        LOG.error(msg, t);
    }

    public static void warn (String className, String msg) {
        Logger LOG = Logger.getLogger(className);
        LOG.warn(msg);
    }

    public static void warn (String className, String msg, Throwable t) {
        Logger LOG = Logger.getLogger(className);
        LOG.warn(msg, t);
    }
}
