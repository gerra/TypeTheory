package lambdaexpression;

import lambdaexpression.parser.LambdaExpressionParser;
import lambdaexpression.structure.Expression;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by root on 24.06.16.
 */
public class Task4 {
    public static void main(String[] args) throws IOException {
        try (LambdaExpressionParser parser = new LambdaExpressionParser();
             FileWriter fileWriter = new FileWriter("task4.out")
        ) {
            Expression expression = parser.parse(new FileInputStream("task4.in"));
//            fileWriter.write(expression.toString() + "\n");
//            fileWriter.write(Arrays.toString(expression.getFreeVariables().toArray()) + "\n");
//            fileWriter.write(expression.substitute("y", expression).toString() + "\n");
            System.out.println(expression.normalize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
