package dlei.forkme.datastore;

/**
 * Custom Exception for when database is expected to return some data and returns nothing.
 */
public class NoDataException extends Exception {
    public NoDataException(String s) {
        super(s);
    }
}
