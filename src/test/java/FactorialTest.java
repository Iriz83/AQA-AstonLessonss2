import org.example.Factorial;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FactorialTest {

    @Test
    public void testFactorialOfPositiveNumber() {
        Factorial factorial = new Factorial();
        long result = factorial.calculateFactorial(5);
        Assert.assertEquals(result, 120, "Факториал 5 должен быть равен 120");
    }

    @Test
    public void testFactorialOfZero() {
        Factorial factorial = new Factorial();
        long result = factorial.calculateFactorial(0);
        Assert.assertEquals(result, 1, "Факториал 0 должен быть равен 1");
    }

    @Test
            (expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        Factorial factorial = new Factorial();
        factorial.calculateFactorial(-1);
    }
}
