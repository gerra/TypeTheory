package lambdaexpression.structure;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 24.06.16.
 */
public abstract class Expression {
    private Set<Variable> freeVariables;
    protected static Map<Expression, Expression> cache = new HashMap<>();
    protected static Map<Expression, Expression> weakCache = new HashMap<>();

    public Set<Variable> getFreeVariables() {
        if (freeVariables == null) {
            freeVariables = getFreeVariablesInner();
        }
        return freeVariables;
    }

    public Expression substitute(String variableName, Expression expression) {
        return substituteInner(new Variable(variableName), expression, true);
    }

    public Expression normalize() {
//        try {
            if (!cache.containsKey(this)) {
                cache.put(this, this);
                Expression normalized = normalizeInner();
                cache.put(this, normalized);
            }
            return cache.get(this);
//        } catch (StackOverflowError e) {
//            System.out.println(this.toString());
//            System.exit(1);
//            return null;
//        }
//        return normalizeInner();
    }

    public Expression weakNormalize() {
        try {
            if (!weakCache.containsKey(this)) {
                weakCache.put(this, this);
                Expression weakNormalized = weakNormalizeInner();
                weakCache.put(this, weakNormalized);
            }
            return weakCache.get(this);
        } catch (StackOverflowError e) {
            System.out.println(this.toString());
            System.exit(1);
            return null;
        }
//        return weakNormalizeInner();
    }

    protected abstract Expression normalizeInner();
    protected abstract Expression weakNormalizeInner();
    protected abstract Set<Variable> getFreeVariablesInner();
    protected abstract Expression substituteInner(Variable subVariable, Expression subExpression, boolean isFreeVariable);
    protected abstract Expression substituteNormalized(Variable subVariable, Expression subExpression);

    public static String withBrackets(Expression expression) {
        if (expression instanceof Variable) {
            return expression.toString();
        }
        return "(" + expression.toString() + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
