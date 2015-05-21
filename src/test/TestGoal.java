import org.junit.Assert;
import org.junit.Test;

/**
 * Created by User on 21.05.2015.
 */
public class TestGoal {

    @Test
    public void testCheckNameCondition1(){
        Goal goal = new Goal();
        Assert.assertTrue(goal.checkNameCondition("qweasd"));
    }

    @Test
    public void testCheckNameCondition2(){
        Goal goal = new Goal();
        Assert.assertTrue(goal.checkNameCondition("qweasd asdqwe asdqwe"));
    }

    @Test
    public void testCheckNameCondition3(){
        Goal goal = new Goal();
        Assert.assertFalse(goal.checkNameCondition("qfgch"));
    }

    @Test
    public void testCheckNameCondition4(){
        Goal goal = new Goal();
        Assert.assertFalse(goal.checkNameCondition("qweasd asdqwe asdqwe "));
    }

}
