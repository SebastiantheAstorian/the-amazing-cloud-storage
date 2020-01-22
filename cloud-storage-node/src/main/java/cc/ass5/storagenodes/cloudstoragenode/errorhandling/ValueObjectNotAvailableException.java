package cc.ass5.storagenodes.cloudstoragenode.errorhandling;

import java.util.List;

/**
 * Exception that is thrown when the user tries to store image files in the database
 * whose file names are already used.
 */
public class ValueObjectNotAvailableException extends RuntimeException {
    public ValueObjectNotAvailableException(int key) {
        super("ValueObject with key  " + key + " not available");
    }
}
