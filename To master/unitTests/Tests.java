//import org.junit.Assert;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import unitTests.Calculator;
//import unitTests.Chocolate;
//import unitTests.IsPrime;
//import unitTests.MatrixSquare;
//
//import java.util.Arrays;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertAll;
//
//public class Tests {
//    static Calculator calculator;
//    static Chocolate choc;
//    @BeforeAll
//    public static void setup(){
//        calculator = new Calculator();
//        choc = new Chocolate();
//        System.out.println("Setup finished");
//    }
//
//
//    @DisplayName("Testing sum of two numbers")
//    @Test
//    public void firstTest() {
//        int actualValue = calculator.sumOfTwoNumbers(3, 5);
//        int expectedValue = 8;
//        Assert.assertEquals(expectedValue, actualValue);
//    }
//
//    @DisplayName("Testing sub of two numbers")
//    @Test
//    public void secondTest() {
//        int actualValue = calculator.subtractionOfTwoNumbers(10, 5);
//        int expectedValue = 5;
//        Assert.assertEquals(expectedValue, actualValue);
//    }
//    @DisplayName("Testing the entire calculator")
//    @Test
//    public void calculatorTest() {
//        assertAll(()->{
//            Assert.assertEquals("The sum of two numbers is incorrect",8, calculator.sumOfTwoNumbers(3,5 ));
//            Assert.assertEquals("The substraction of two numbers is incorrect",5, calculator.subtractionOfTwoNumbers(10,5 ));
//            Assert.assertEquals("The multiplication of two numbers is incorrect",15, calculator.multiplicationOfTwoNumbers(3,5 ));
//            Assert.assertEquals("The division of two numbers is incorrect",4, calculator.divisionOfTwoNumbers(20,5 ));
//        });
//    }
//    @DisplayName("Testing if the number is odd")
//    @Test
//    public void checkIfOdd() {
//        Assert.assertTrue("The number is not odd", Calculator.isOdd(5));
//    }
//
//    @DisplayName("Testing numbers to be odd")
//    @ParameterizedTest
//    @ValueSource(ints={1,3,5,7})
//    public void testIsOdd(int inputNumber){
//        assertThat(calculator.isOdd(inputNumber), equalTo(true));
//    }
//
//    @DisplayName("Repeated test")
//    @RepeatedTest(5)
//    public void repeatedTest() {
//        System.out.println("This is repeated test");
//    }
//    @DisplayName("Is chocolate splittable?")
//    @Test
//    public void isPossibleToCut(){
//
//        Assert.assertEquals("YES", Chocolate.chocComparison(5, 7, 13));
//    }
//    @DisplayName("Square with equal diagonal")
//    @Test
//    public void MatrixSquare(){
//
//        System.out.println(Arrays.deepToString(MatrixSquare.matrixsquare2(5)));
//    }
//
//    @DisplayName("The number is prime")
//    @Test
//    public void IsPrime(){
//
//        System.out.println(IsPrime.isPrime(5));
//    }
//
//
//    @AfterAll
//    public static void tearDown(){
//        calculator = null;
//        System.out.println("Tear down completed");
//    }
//
//
//
//}
//
//
//
////        MatcherAssert.assertThat("Is not matched:",8, comparesEqualTo(unitTests.Calculator.sumOfTwoNumbers(3, 5)));
////        MatcherAssert.assertThat("Is not matched:",expectedValue, comparesEqualTo(unitTests.Calculator.multiplicationOfTwoNumbers(3, 5)));
//
