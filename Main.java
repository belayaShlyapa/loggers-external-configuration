public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("We can see me from console output only");
        log.info("We can see me from console output and logback.xml file");
    }
}