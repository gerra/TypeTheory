package lambdaexpression.structure;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by root on 24.06.16.
 */
public class Abstraction extends Expression {
    private Variable variable;
    private Expression expression;

    public Abstraction(Variable variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public Variable getVariable() {
        return variable;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "\\" + variable.toString() + ". " + Expression.withBrackets(expression);
    }

    @Override
    public Expression normalizeInner() {
        return new Abstraction(variable, expression.normalize());
    }

    @Override
    protected Expression weakNormalizeInner() {
        return this;
    }

    @Override
    protected void getFreeVariablesInner(Map<Variable, Integer> counter, Set<Variable> answer) {
        counter.putIfAbsent(variable, 0);
        Integer oldValue = counter.get(variable);
        counter.put(variable, oldValue + 1);
        expression.getFreeVariablesInner(counter, answer);
        counter.put(variable, oldValue);
        if (oldValue == 0) {
            counter.remove(variable);
        }
    }

    @Override
    protected Expression substitute(Variable subVariable, Expression subExpression) {
//        Set<Variable> freeVariables = expression.getFreeVariables();
        Set<Variable> freeVariables = expression.getFreeVariablesLegacy();
        if (variable.equals(subVariable) || !freeVariables.contains(subVariable)) {
            return this;
        }
//        Set<Variable> substitutingFreeVariables = subExpression.getFreeVariables();
        Set<Variable> substitutingFreeVariables = subExpression.getFreeVariablesLegacy();
        if (!substitutingFreeVariables.contains(variable)) {
            return new Abstraction(variable, expression.substitute(subVariable, subExpression));
        }
        Set<Variable> allVariables = new HashSet<>(substitutingFreeVariables);
        allVariables.addAll(freeVariables);

        Variable newVariable = new Variable("a");
        while (allVariables.contains(newVariable)) {
            newVariable = new Variable(newVariable.getName() + "'");
        }
//        Expression newExpression = expression.substitute(subVariable, newVariable);
//        return new Abstraction(variable, newExpression.substitute(newVariable, subExpression));

        Expression newExpression = expression.substitute(variable, newVariable);
        return new Abstraction(newVariable, newExpression.substitute(subVariable, subExpression));
    }

    @Override
    protected Set<Variable> getFreeVariablesInnerLegacy() {
        Set<Variable> res = new HashSet<>(expression.getFreeVariablesLegacy());
        res.remove(variable);
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Abstraction that = (Abstraction) o;

        return variable.equals(that.variable) && expression.equals(that.expression);

    }

    @Override
    public int hashCode() {
        int result = variable.hashCode();
        result = 31 * result + expression.hashCode();
        return result;
    }
}
