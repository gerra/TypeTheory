package lambdaexpression.structure;

import java.util.HashSet;
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
    protected Expression normalizeInner() {
        return new Abstraction(variable, expression.normalize());
    }

    @Override
    protected Expression weakNormalizeInner() {
        return new Abstraction(variable, expression.weakNormalize());
    }

    @Override
    protected Set<Variable> getFreeVariablesInner() {
        Set<Variable> variables = expression.getFreeVariables();
        variables.remove(variable);
        return variables;
    }

    @Override
    protected Expression substituteInner(Variable subVariable, Expression subExpression, boolean isFreeVariable) {
        isFreeVariable = isFreeVariable && !variable.equals(subVariable);
        return new Abstraction(variable, expression.substituteInner(subVariable, subExpression, isFreeVariable));
    }

    @Override
    protected Expression substituteNormalized(Variable subVariable, Expression subExpression) {
        Set<Variable> freeVariables = expression.getFreeVariables();
        if (variable.equals(subVariable) || !freeVariables.contains(subVariable)) {
            return this;
        }
        Set<Variable> substitutingFreeVariables = subExpression.getFreeVariables();
        if (!substitutingFreeVariables.contains(variable)) {
            return new Abstraction(variable, expression.substituteNormalized(subVariable, subExpression));
        }
        Set<Variable> allVariables = new HashSet<>(substitutingFreeVariables);
        allVariables.addAll(freeVariables);

        Variable newVariable = new Variable("a");
        while (allVariables.contains(newVariable)) {
            newVariable = new Variable(newVariable.getName() + "'");
        }
        Expression newExpression = expression.substituteNormalized(subVariable, subExpression);
        return new Abstraction(newVariable, newExpression);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Abstraction that = (Abstraction) o;

        return variable.equals(that.variable) && expression.equals(that.expression);

    }

//    @Override
//    public int hashCode() {
//        int result = variable.hashCode();
//        result = 31 * result + expression.hashCode();
//        return result;
//    }
}
