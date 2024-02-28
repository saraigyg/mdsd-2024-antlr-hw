package statemodel.exceptions;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class StateModelExceptionHandler extends BaseErrorListener {
    private final ArrayList<StateModelException> exceptions = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);

        exceptions.add(new StateModelException(line, charPositionInLine, msg));
    }

    public List<StateModelException> getExceptions() {
        return exceptions;
    }

    public void addException(StateModelException exception) {
        exceptions.add(exception);
    }

    public void addException(ParserRuleContext ctx, String message) {
        StateModelException exception = new StateModelException(ctx.start.getLine(), ctx.start.getCharPositionInLine(), message);
        addException(exception);
    }

    public boolean isValid() {
        return exceptions.isEmpty();
    }
}
