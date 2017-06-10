package dlei.forkme.datastore;

/**
 * Custom Exception for when database returns more rows than expected.
 */
public class TooMuchDataException extends Exception {
    public TooMuchDataException(String s) {
        super(s);
    }
}