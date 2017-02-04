package lambdaexpression.structure;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 24.06.16.
 */
public class Variable extends Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public Expression normalizeInner() {
        return this;
    }

    @Override
    protected Expression weakNormalizeInner() {
        return this;
    }

    @Override
    protected void getFreeVariablesInner(Map<Variable, Integer> counter, Set<Variable> answer) {
        if (!counter.containsKey(this)) {
            answer.add(this);
        }
    }

    @Override
    protected Expression substitute(Variable subVariable, Expression subExpression) {
        if (this.equals(subVariable)) {
            return subExpression;
        }
        return this;
    }

    @Override
    protected Set<Variable> getFreeVariablesInnerLegacy() {
        Set<Variable> res = new HashSet<>();
        res.add(this);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable that = (Variable) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
