package cc.ass5.storagenodes.cloudstoragenode.errorhandling;

public class ValueObjectAlreadyExistException extends RuntimeException {
    public ValueObjectAlreadyExistException(int key) {
        super("ValueObject with key  " + key + " already exists");
    }
}
