package master_design_patterns;

public final class Application {

    public interface AbstractOperation {
        void operate();
    }

    public static class NullOperation implements AbstractOperation {
        @Override
        public void operate() {
            System.out.println("process null");
        }
    }

    public static class RealOperation implements AbstractOperation {
        @Override
        public void operate() {
            System.out.println("process value");
        }
    }

    public static AbstractOperation getOperationByValue(
        String value
    ) {
        return value == null
            ? new NullOperation()
            : new RealOperation();
    }

    public static void main(String[] args) {
        final AbstractOperation operation =
            getOperationByValue(args[0]);
        operation.operate();
    }
}

