package statemodel;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import statemodel.exceptions.StateModelExceptionHandler;
import statemodel.generated.StateModelLexer;
import statemodel.generated.StateModelParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StateModelRunner {
    private static final StateModelExceptionHandler exceptionHandler = new StateModelExceptionHandler();

    private static final Path inputPath = Paths.get("src", "statemodel", "input.state");

    public static StateModelParser.ModelContext readModel(String source) {
        var lexer = new StateModelLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(exceptionHandler);

        var parser = new StateModelParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(exceptionHandler);

        return parser.model();
    }

    public static void main(String[] args) throws IOException {
        String source = Files.readString(inputPath);
        readModel(source);

        if (exceptionHandler.isValid()) {
            printValid();
        }
        else {
            printExceptions();
        }
    }

    private static void printValid() {
        System.out.println();
        System.out.println("***** Input is valid *****");
    }

    private static void printExceptions() {
        System.out.println();
        System.out.println("***** Input has problems *****");

        for (var exc : exceptionHandler.getExceptions()) {
            System.out.println(exc);
        }
    }
}
