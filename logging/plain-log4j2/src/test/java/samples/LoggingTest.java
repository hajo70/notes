package samples;

import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.logging.Level;

/**
 * Requires: -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager
 */
public class LoggingTest {

    private static final Class<?> me = MethodHandles.lookup().lookupClass();
    private static final Logger log4jLogger = LogManager.getLogger(me);
    private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(me);
    private static final java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(me.getName());
    private static final Log jclLogger = org.apache.commons.logging.LogFactory.getLog(me);
    private static final org.apache.log4j.Logger log4j1Logger = org.apache.log4j.LogManager.getLogger(me);

    @Test
    void log() {
        log4jLogger.fatal("Fatal from Log4j2");
        log4jLogger.error("Error from Log4j2");
        log4jLogger.warn("Warning from Log4j2");
        log4jLogger.info("Info from Log4j2");
        log4jLogger.debug("Debug from Log4j2");
        log4jLogger.trace("Trace from Log4j2");

        slf4jLogger.error("Error from Slf4j");
        slf4jLogger.warn("Warning from Slf4j");
        slf4jLogger.info("Info from Slf4j");
        slf4jLogger.debug("Debug from Slf4j");
        slf4jLogger.trace("Trace from Slf4j");

        for (Level level : List.of(Level.SEVERE, Level.WARNING, Level.INFO, Level.FINE, Level.FINER, Level.FINEST, Level.ALL, Level.OFF)) {
            julLogger.log(level, level.getLocalizedName() + " from JUL");
        }

        jclLogger.fatal("Fatal from JCL");
        jclLogger.error("Error from JCL");
        jclLogger.warn("Warning from JCL");
        jclLogger.info("Info from JCL");
        jclLogger.debug("Debug from JCL");
        jclLogger.trace("Trace from JCL");

        log4j1Logger.fatal("Fatal from log4j-1");
        log4j1Logger.error("Error from log4j-1");
        log4j1Logger.warn("Warning from log4j-1");
        log4j1Logger.info("Info from log4j-1");
        log4j1Logger.debug("Debug from log4j-1");
        log4j1Logger.trace("Trace from log4j-1");
    }
}
