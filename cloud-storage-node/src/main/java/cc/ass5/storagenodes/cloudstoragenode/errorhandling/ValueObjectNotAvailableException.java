package cc.ass5.storagenodes.cloudstoragenode.errorhandling;

public class ValueObjectNotAvailableException extends RuntimeException {
    public ValueObjectNotAvailableException(int key) {
        super("ValueObject with key  " + key + " not available");
    }
}
