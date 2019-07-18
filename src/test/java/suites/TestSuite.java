package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    FellowshipTest.class,
    GosslingatorTest.class,
    RandomTableTest.class,
    SavingsCalculatorTest.class,
    SortingHatTest.class,
    WaitForItTest.class,
    SpelleologyTest.class
})
public class TestSuite {
}
