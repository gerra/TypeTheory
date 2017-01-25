package lambdaexpression.structure;

import java.util.Set;

/**
 * Created by root on 24.06.16.
 */
public class Application extends Expression {
    private Expression left;
    private Expression right;

    public Application(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return Expression.withBrackets(left) + " " + Expression.withBrackets(right);
    }

    private Expression normalizeApplication() {
        Expression leftNormalized = left.normalize();
        if (leftNormalized instanceof Abstraction) {
            Abstraction leftAbstraction = (Abstraction) leftNormalized;
            Expression expressionBeforeSubstitution = leftAbstraction.getExpression();
            Variable substitutionVariable = leftAbstraction.getVariable();
            Expression expressionAfterSubstitution = expressionBeforeSubstitution
                    .substituteNormalized(substitutionVariable, right);
            return expressionAfterSubstitution.normalize();
        } else {
            return new Application(leftNormalized, right.normalize());
        }
    }

    private Expression normalizeApplication(boolean weakNormalize) {
        Expression leftWeakNormalized = left.weakNormalize();
        if (leftWeakNormalized instanceof Abstraction) {
            Abstraction leftAbstraction = (Abstraction) leftWeakNormalized;
            Variable subVariable = leftAbstraction.getVariable();
            Expression expression = leftAbstraction.getExpression();
            Expression substitutedExpression = expression.substituteNormalized(subVariable, right);
            if (weakNormalize) {
                return substitutedExpression.weakNormalize();
            } else {
                return substitutedExpression.normalize();
            }
        } else {
            if (weakNormalize) {
                return new Application(leftWeakNormalized, right.weakNormalize());
            } else {
                return new Application(leftWeakNormalized.normalize(), right.normalize());
            }
        }
    }

    @Override
    protected Expression normalizeInner() {
//        return normalizeApplication(false);
        return normalizeApplication();
    }

    @Override
    protected Expression weakNormalizeInner() {
//        return normalizeApplication(true);
        return normalizeApplication();
    }

    @Override
    protected Set<Variable> getFreeVariablesInner() {
        Set<Variable> result = left.getFreeVariables();
        result.addAll(right.getFreeVariables());
        return result;
    }

    @Override
    protected Expression substituteInner(Variable subVariable, Expression subExpression, boolean isFreeVariable) {
        Expression newLeft = left.substituteInner(subVariable, subExpression, isFreeVariable);
        Expression newRight = right.substituteInner(subVariable, subExpression, isFreeVariable);
        return new Application(newLeft, newRight);
    }

    @Override
    protected Expression substituteNormalized(Variable subVariable, Expression subExpression) {
        Expression newLeft = left.substituteNormalized(subVariable, subExpression);
        Expression newRight = right.substituteNormalized(subVariable, subExpression);
        return new Application(newLeft, newRight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        return left.equals(that.left) && right.equals(that.right);

    }

//    @Override
//    public int hashCode() {
//        int result = left.hashCode();
//        result = 31 * result + right.hashCode();
//        return result;
//    }
}
