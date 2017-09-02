import ru.zilberg.hometask5.Assert;
import ru.zilberg.hometask5.annotation.After;
import ru.zilberg.hometask5.annotation.Before;
import ru.zilberg.hometask5.annotation.Test;
import ru.zilberg.hometask5.example.Example;

public class ExampleTest {
    Example ex;
    Example ex1;
    @Before
    public void before(){
        System.out.println("Start befor-method");
        ex = new Example();
        ex1 = new Example(1, 4);
    }

    @Test
    public void test1(){
        Assert.assertTrue("1 Test default constructor ", ex.sum() == 0);
        Assert.assertFalse("2 Test default constructor ", ex.sum() != 0);
    }
    @Test
    public void test2(){
        Assert.assertTrue("1 Test constructor Example(x,y):", ex1.sum() == 5);
        Example ex2 = new Example(5 , 5);
        Assert.assertFalse("2 Test constructor Example(x,y):", ex2.sum() != 10);
    }
    @Test
    public void test3(){
        Example example = new Example();
        //example = null;
        Assert.assertIsNotNull("Test is null", example);
    }

    @After
    public void after(){
        System.out.println("Start after-method");
        System.out.println("-----------------------------");
    }
}
