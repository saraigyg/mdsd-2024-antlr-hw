package statemodel.exceptions;

public class StateModelException {
    public int line;
    public int column;
    public String message;

    public StateModelException(int line, int column, String message) {
        this.line = line;
        this.column = column;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                "line %d, column %d: %s", line, column, message);
    }
}
