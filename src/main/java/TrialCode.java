import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrialCode {
  @AfterMethod
    public void after1() {
      System.out.println("v");
  }

    @Test
    public void test12() {
        System.out.println("x");
    }

    @Test
    public void test2() {
        System.out.println("c");
    }

    @BeforeMethod
    public void after2() {
        System.out.println("z");
    }
}
