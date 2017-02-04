package lambdaexpression;


import lambdaexpression.parser.ExpressionParser;
import lambdaexpression.structure.Expression;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by root on 24.06.16.
 */
public class Task4 {
    public static void main(String[] args) throws IOException {
        try (ExpressionParser parser = new ExpressionParser();
             FileWriter fileWriter = new FileWriter("task4.out")
        ) {
            Expression expression = parser.parse(new FileInputStream("task4.in"));
//            fileWriter.write(expression.toString() + "\n");
//            fileWriter.write(Arrays.toString(expression.getFreeVariables().toArray()) + "\n");
//            fileWriter.write(expression.substitute("y", expression).toString() + "\n");
            System.out.println(expression.normalize());
//            System.out.println(Expression.normalForm(expression));

//            Expression inEx   = parser.parse(new ByteArrayInputStream("((\\l0.((\\l1.((\\l2.((\\l3.((\\l4.((\\l5.((\\l6.((\\l7.((\\l8.((\\l9.((\\l10.((\\l11.((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) l11)) l10))) l10)) l10))))) l10))) l10)) l10)) l10))) l10))) l10)))) l10)) l10)) l10))) l10)) l10)))) l10)))) l10))) l10)) l10)) l10)) l10)) l10))) l10)) l10))) l10)) l10)))) l10))))))))) l10)) l10)) l10)))))) l10))) l10)) l10))))) l10))) l10)))))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)))))) l10))) l10)) l10))) l10)) l10))) l10)) l10)) l10))) l10))) l10))))) l10)) l10))) l10)) l10)) l10))) l10)))) l10)) l10))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)))) l10)) l10))) l10)) l10))) l10))) l10)) l10))) l10))) l10))) l10))) l10))) l10)))))) l10)) l10))))) l10)) l10)) l10))) l10)) l10)) l10)) l10))) l10)))) l10)) l10)) l10)) l10)) l10))) l10))) l10)) l10)))) l10))) l10)) l10))) l10))) l10))) l10)) l10)) l10))) l10))) l10))) l10))) l10))) l10)))) l10)) l10)))) l10)))))))))))) l10))) l10))) l10))) l10)) l10))))) l10))) l10))) l10))))) l10)))))))) l10)))))))) l10))) l10))))) l10)) l10)) l10))))) l10))) l10)) l10)) l10)) l10)) l10))) l10)) l10)) l10)) l10))) l10))))))) l10)))) l10))) l10)) l10)) l10)))) l10)) l10)) l10)) l10)) l10))))) l10)))) l10)) l10)) l10)) l10))) l10))) l10)) l10)))) l10)) l10)) l10)))) l10))) l10)) l10)) l10)))) l10)))) l10)) l10)) l10))) l10))) l10)) l10))) l10))) l10)))) l10)))) l10)) l10))))) l10))) l10))))) l10)) l10))) l10)) l10)))) l10))) l10)))))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10))))))) l10)) l10)) l10))))) l10)) l10)) l10)) l10)) l10)) l10)))))) l10)) l10))) l10))))) l10)) l10)) l10)) l10))) l10)) l10))) l10)) l10))) l10)) l10)) l10))) l10)) l10)))))))) l10)) l10))) l10)) l10))) l10)) l10))))) l10))) l10)))) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10)) l10))) l10)))))) l10))) l10))) l10))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10)) l10)))) l10)) l10)))))) l10))) l10))))) l10)) l10)) l10))) l10)) l10))) l10)) l10))) l10))) l10)))) l10)))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10))) l10)) l10)) l10)) l10)) l10)) l10)) l10))))) l10)) l10))))) l10)))))) l10)) l10))) l10))) l10)) l10)) l10)))) l10)))) l10)) l10)) l10))) l10)) l10)))) l10)) l10)))) l10)) l10)) l10))))))))))) l10))))) l10)) l10))) l10))) l10)) l10)) l10)) l10)) l10))) l10)))) l10))) l10))))) l10))))))) l10))) l10))) l10)) l10))) l10)))) l10)) l10))) l10))) l10)) l10)) l10)) l10))))) l10)) l10))))))) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10))) l10))))) l10)) l10)) l10)))) l10)))) l10)) l10))))) l10))))))) l10)) l10))) l10)))) l10))))) l10)))) l10))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)) l10)) l10)))))) l10)))) l10))) l10)) l10))) l10)) l10)) l10)) l10))))) l10)))) l10)))) l10)) l10)) l10)) l10))) l10))) l10))))))) l10)))) l10))) l10)) l10)) l10))) l10))) l10)) l10)))) l10)) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)))) l10)) l10)) l10))) l10))) l10)) l10))) l10)) l10))))))))) l10)) l10)) l10)) l10))) l10))))) l10)) l10))))) l10)) l10))) l10))) l10)) l10))) l10)) l10)) l10))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10))) l10)) l10)) l10)) l10)))) l10))))))))))) l10))) l10)) l10)) l10)))) l10))) l10)))))) l10)) l10)) l10)))) l10)))))) l10)) l10)) l10))))) l10))) l10))) l10)) l10)) l10)) l10))) l10)))) l10)) l10)) l10))) l10)))))) l10)) l10)) l10))) l10)) l10)) l10)) l10))))))) l10)) l10)))) l10)))) l10))) l10)) l10))) l10)) l10)))) l10))) l10))) l10))) l10)) l10)) l10)) l10)))))))) l10)) l10)) l10))) l10)))) l10)) l10)) l10)) l10)) l10))) l10)))) l10)))))))) l10)) l10)) l10))) l10)) l10))))))) l10)) l10)) l10))) l10))) l10)) l10)) l10)) (\\l11.l11))) ((\\l10.(l10 l10)) (\\l10.(l10 l10))))) (l0 (\\l9.(\\l10.(\\l11.((\\l12.((\\l13.(((l1 l12) l13) (((l1 l13) l12) ((l9 (l4 l10)) (l4 l11))))) (l8 l11))) (l8 l10)))))))) (\\l8.((l8 (\\l9.l3)) l2)))) (\\l7.(\\l8.((l8 l4) l7))))) (\\l6.(\\l7.((l6 l5) l7))))) (\\l5.(\\l6.(\\l7.((l5 l6) (l6 l7))))))) (\\l4.(\\l5.(\\l6.(((l4 (\\l7.(\\l8.(l8 (l7 l5))))) (\\l7.l6)) (\\l7.l7))))))) (\\l3.(\\l4.l4)))) (\\l2.(\\l3.l2)))) (\\l1.(\\l2.(\\l3.((l1 l2) l3)))))) (\\l0.((\\l1.(l0 (l1 l1))) (\\l1.(l0 (l1 l1))))))".getBytes()));
//            Expression serAns = parser.parse(new ByteArrayInputStream("((\\l0. ((\\l1. ((\\l2. ((\\l3. ((\\l4. ((\\l5. ((\\l6. ((\\l7. ((\\l8. ((\\l9. ((\\l10. ((\\l11. ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l3 l10) ((l2 ((l3 l10) ((l2 ((l3 l10) ((l2 ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) ((l3 l10) ((l3 l10) ((l2 ((l2 ((l3 l10) ((l2 ((l3 l10) l11)) l10))) l10)) l10))))) l10))) l10)) l10)) l10))) l10))) l10)))) l10)) l10)) l10))) l10)) l10)))) l10)))) l10))) l10)) l10)) l10)) l10)) l10))) l10)) l10))) l10)) l10)))) l10))))))))) l10)) l10)) l10)))))) l10))) l10)) l10))))) l10))) l10)))))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)))))) l10))) l10)) l10))) l10)) l10))) l10)) l10)) l10))) l10))) l10))))) l10)) l10))) l10)) l10)) l10))) l10)))) l10)) l10))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)))) l10)) l10))) l10)) l10))) l10))) l10)) l10))) l10))) l10))) l10))) l10))) l10)))))) l10)) l10))))) l10)) l10)) l10))) l10)) l10)) l10)) l10))) l10)))) l10)) l10)) l10)) l10)) l10))) l10))) l10)) l10)))) l10))) l10)) l10))) l10))) l10))) l10)) l10)) l10))) l10))) l10))) l10))) l10))) l10)))) l10)) l10)))) l10)))))))))))) l10))) l10))) l10))) l10)) l10))))) l10))) l10))) l10))))) l10)))))))) l10)))))))) l10))) l10))))) l10)) l10)) l10))))) l10))) l10)) l10)) l10)) l10)) l10))) l10)) l10)) l10)) l10))) l10))))))) l10)))) l10))) l10)) l10)) l10)))) l10)) l10)) l10)) l10)) l10))))) l10)))) l10)) l10)) l10)) l10))) l10))) l10)) l10)))) l10)) l10)) l10)))) l10))) l10)) l10)) l10)))) l10)))) l10)) l10)) l10))) l10))) l10)) l10))) l10))) l10)))) l10)))) l10)) l10))))) l10))) l10))))) l10)) l10))) l10)) l10)))) l10))) l10)))))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10))))))) l10)) l10)) l10))))) l10)) l10)) l10)) l10)) l10)) l10)))))) l10)) l10))) l10))))) l10)) l10)) l10)) l10))) l10)) l10))) l10)) l10))) l10)) l10)) l10))) l10)) l10)))))))) l10)) l10))) l10)) l10))) l10)) l10))))) l10))) l10)))) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10)) l10))) l10)))))) l10))) l10))) l10))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10)) l10)))) l10)) l10)))))) l10))) l10))))) l10)) l10)) l10))) l10)) l10))) l10)) l10))) l10))) l10)))) l10)))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10))) l10)) l10)) l10)) l10)) l10)) l10)) l10))))) l10)) l10))))) l10)))))) l10)) l10))) l10))) l10)) l10)) l10)))) l10)))) l10)) l10)) l10))) l10)) l10)))) l10)) l10)))) l10)) l10)) l10))))))))))) l10))))) l10)) l10))) l10))) l10)) l10)) l10)) l10)) l10))) l10)))) l10))) l10))))) l10))))))) l10))) l10))) l10)) l10))) l10)))) l10)) l10))) l10))) l10)) l10)) l10)) l10))))) l10)) l10))))))) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10))) l10))))) l10)) l10)) l10)))) l10)))) l10)) l10))))) l10))))))) l10)) l10))) l10)))) l10))))) l10)))) l10))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)) l10)) l10)))))) l10)))) l10))) l10)) l10))) l10)) l10)) l10)) l10))))) l10)))) l10)))) l10)) l10)) l10)) l10))) l10))) l10))))))) l10)))) l10))) l10)) l10)) l10))) l10))) l10)) l10)))) l10)) l10)) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)))) l10)) l10)) l10))) l10))) l10)) l10))) l10)) l10))))))))) l10)) l10)) l10)) l10))) l10))))) l10)) l10))))) l10)) l10))) l10))) l10)) l10))) l10)) l10)) l10))) l10)))) l10)) l10)) l10)) l10)) l10)) l10)))) l10))) l10)) l10))) l10)) l10)) l10)) l10)))) l10))))))))))) l10))) l10)) l10)) l10)))) l10))) l10)))))) l10)) l10)) l10)))) l10)))))) l10)) l10)) l10))))) l10))) l10))) l10)) l10)) l10)) l10))) l10)))) l10)) l10)) l10))) l10)))))) l10)) l10)) l10))) l10)) l10)) l10)) l10))))))) l10)) l10)))) l10)))) l10))) l10)) l10))) l10)) l10)))) l10))) l10))) l10))) l10)) l10)) l10)) l10)))))))) l10)) l10)) l10))) l10)))) l10)) l10)) l10)) l10)) l10))) l10)))) l10)))))))) l10)) l10)) l10))) l10)) l10))))))) l10)) l10)) l10))) l10))) l10)) l10)) l10)) (\\l11. l11))) ((\\l10. (l10 l10)) (\\l10. (l10 l10))))) (l0 (\\l9. (\\l10. (\\l11. ((\\l12. ((\\l13. (((l1 l12) l13) (((l1 l13) l12) ((l9 (l4 l10)) (l4 l11))))) (l8 l11))) (l8 l10)))))))) (\\l8. ((l8 (\\l9. l3)) l2)))) (\\l7. (\\l8. ((l8 l4) l7))))) (\\l6. (\\l7. ((l6 l5) l7))))) (\\l5. (\\l6. (\\l7. ((l5 l6) (l6 l7))))))) (\\l4. (\\l5. (\\l6. (((l4 (\\l7. (\\l8. (l8 (l7 l5))))) (\\l7. l6)) (\\l7. l7))))))) (\\l3. (\\l4. l4)))) (\\l2. (\\l3. l2)))) (\\l1. (\\l2. (\\l3. ((l1 l2) l3)))))) (\\l0. ((\\l1. (l0 (l1 l1))) (\\l1. (l0 (l1 l1))))))".getBytes()));
//            System.out.println(inEx.toString());
//            System.out.println(serAns.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
