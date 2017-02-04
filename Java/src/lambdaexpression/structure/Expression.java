package lambdaexpression.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Expression {
    private Set<Variable> freeVariables;
    protected static Map<Expression, Expression> cache = new HashMap<>();
    protected static Map<Expression, Expression> weakCache = new HashMap<>();

    public final Set<Variable> getFreeVariables() {
        if (freeVariables == null) {
            freeVariables = new HashSet<>();
            getFreeVariablesInner(new HashMap<>(), freeVariables);
        }
        return freeVariables;
    }

    public final Set<Variable> getFreeVariablesLegacy() {
        if (freeVariables == null) {
            freeVariables = getFreeVariablesInnerLegacy();
        }
        return freeVariables;
    }

    public final Expression normalize() {
        if (!cache.containsKey(this)) {
            cache.put(this, normalizeInner());
        }
        return cache.get(this);
    }

    public final Expression weakNormalize() {
        if (!weakCache.containsKey(this)) {
            weakCache.put(this, weakNormalizeInner());
        }
        return weakCache.get(this);
    }

    protected abstract Expression normalizeInner();
    protected abstract Expression weakNormalizeInner();
    protected abstract void getFreeVariablesInner(Map<Variable, Integer> counter, Set<Variable> answer);
    protected abstract Expression substitute(Variable subVariable, Expression expression);

    protected abstract Set<Variable> getFreeVariablesInnerLegacy();

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
