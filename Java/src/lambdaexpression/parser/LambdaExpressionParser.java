package lambdaexpression.parser;

import lambdaexpression.structure.Abstraction;
import lambdaexpression.structure.Application;
import lambdaexpression.structure.Expression;
import lambdaexpression.structure.Variable;

import java.io.IOException;
import java.io.InputStream;

import static lambdaexpression.parser.LambdaExpressionLexer.TokenType.*;

/**
 * Created by root on 24.06.16.
 */
public class LambdaExpressionParser implements AutoCloseable {

    private LambdaExpressionLexer lexer;
    private LambdaExpressionLexer.Token token;

    public Expression parse(InputStream is) throws IOException {
        lexer = new LambdaExpressionLexer(is);
        token = lexer.processNextToken();
        return parseExpression();
    }

    private Expression parseExpression() throws IOException {
        if (token.tokenType == LAMBDA_SIGN) {
            return parseAbstraction();
        } else {
            Expression application = parseApplication();
            if (token.tokenType == LAMBDA_SIGN) {
                return new Application(application, parseAbstraction());
            } else {
                return application;
            }
        }
    }

    private Expression parseApplication() throws IOException {
        Expression application = parseAtom();
        Expression atom;
        while ((atom = parseAtom()) != null) {
            application = new Application(application, atom);
        }
        return application;
    }

    private Expression parseAtom() throws IOException {
        if (token.tokenType == LBRACKET) {
            token = lexer.processNextToken();
            Expression expression = parseExpression();
            assert token.tokenType == RBRACKET;
            token = lexer.processNextToken();
            return expression;
        } else if (token.tokenType == NAME) {
            Variable variable = new Variable(token.str);
            token = lexer.processNextToken();
            return variable;
        } else {
            return null;
        }
    }

    private Abstraction parseAbstraction() throws IOException {
        assert token.tokenType == LAMBDA_SIGN;
        token = lexer.processNextToken();
        Variable variable = parseVariable();
        assert token.tokenType == POINT;
        token = lexer.processNextToken();
        Expression expression = parseExpression();
        return new Abstraction(variable, expression);
    }

    private Variable parseVariable() throws IOException {
        assert token.tokenType == NAME;
        Variable variable = new Variable(token.str);
        token = lexer.processNextToken();
        return variable;
    }

    @Override
    public void close() throws Exception {
        lexer.close();
    }
}
