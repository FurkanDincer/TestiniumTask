package utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Log {
        // LogManager üzerinden Logger nesnesini başlatıyoruz
        private static final Logger logger = LogManager.getLogger(Log.class);

        // Bilgilendirme logları için
        public static void info(String message) {
            logger.info(message);
        }
        // Hata durumlarını belirtmek için
        public static void error(String message) {
            logger.error(message);
        }

        // Uyarılar için
        public static void warn(String message) {
            logger.warn(message);
        }
}
