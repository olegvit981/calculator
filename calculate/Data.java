package calculate;

public class Data implements Calculation {
    private Operations operation;
    private int value1;
    private int value2;
    
    public Data(Operations operation, int value1, int value2) {
        this.setOperation(operation);
        this.setValue1(value1);
        this.setValue2(value2);
    }
    
    public Operations getOperation() {
        return operation;
    }
    
    public void setOperation(Operations operation) {
        this.operation = operation;
    }
    
    public int getValue1() {
        return value1;
    }
    
    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    @Override
    public int compute() {
        if (operation.compareTo(Operations.ADDITION) == 0) {
            return value1 + value2;
        } else if (operation.compareTo(Operations.SUBTRACTION) == 0) {
            return value1 - value2;
        } else if (operation.compareTo(Operations.MULTIPLICATION) == 0) {
            return value1 * value2;
        } else if (operation.compareTo(Operations.DIVISION) == 0) {
            return value1 / value2;
        } else {
            return 0;
        }
    }

}
