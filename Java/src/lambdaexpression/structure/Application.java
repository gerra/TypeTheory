package lambdaexpression.structure;

import java.util.HashSet;
import java.util.Map;
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

    private Expression normalizeApplication(boolean weakNormalize) {
        Expression res;
        Expression leftWeakNormalized = left.weakNormalize();
        if (leftWeakNormalized instanceof Abstraction) {
            Abstraction leftAbstraction = (Abstraction) leftWeakNormalized;
            Variable subVariable = leftAbstraction.getVariable();
            Expression expression = leftAbstraction.getExpression();
            Expression substitutedExpression = expression.substitute(subVariable, right);
            if (weakNormalize) {
                res = substitutedExpression.weakNormalize();
//                saveWeak(res);
            } else {
                res = substitutedExpression.normalize();
//                save(res);
            }
        } else {
            if (weakNormalize) {
                res = new Application(leftWeakNormalized, right);
//                saveWeak(res);
            } else {
                res = new Application(leftWeakNormalized.normalize(), right.normalize());
//                save(res);
            }
        }
        return res;
    }

    @Override
    public Expression normalizeInner() {
        return normalizeApplication(false);
//        return normalizeApplication();
    }

    @Override
    protected Expression weakNormalizeInner() {
        return normalizeApplication(true);
//        return normalizeApplication();
    }

    @Override
    protected void getFreeVariablesInner(Map<Variable, Integer> counter, Set<Variable> answer) {
        left.getFreeVariablesInner(counter, answer);
        right.getFreeVariablesInner(counter, answer);
    }

    @Override
    protected Expression substitute(Variable subVariable, Expression subExpression) {
        Expression newLeft = left.substitute(subVariable, subExpression);
        Expression newRight = right.substitute(subVariable, subExpression);
        return new Application(newLeft, newRight);
    }

    @Override
    protected Set<Variable> getFreeVariablesInnerLegacy() {
        Set<Variable> res = new HashSet<>(left.getFreeVariablesLegacy());
        res.addAll(right.getFreeVariablesLegacy());
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        return left.equals(that.left) && right.equals(that.right);

    }

    @Override
    public int hashCode() {
        int result = left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
