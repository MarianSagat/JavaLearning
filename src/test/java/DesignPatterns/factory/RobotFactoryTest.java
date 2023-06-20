package DesignPatterns.factory;

import designPatterns.factory.approachOne.RobotFactory;
import designPatterns.factory.approachOne.SixElbowRobot;
import designPatterns.factory.approachOne.ThreeElbowsRobot;
import designPatterns.factory.approachTwo.ERobot;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RobotFactoryTest
{
    @Test
    public void test()
    {
        assertEquals(RobotFactory.create("ThreeElbow") instanceof ThreeElbowsRobot, true);
        assertEquals(designPatterns.factory.approachTwo.RobotFactory.create(ERobot.THREE_ELBOW) instanceof ThreeElbowsRobot, true);
        assertEquals(designPatterns.factory.approachThree.RobotFactory.create(ERobot.THREE_ELBOW) instanceof ThreeElbowsRobot, true);
    }

    @Test
    public void test2()
    {
        assertEquals(RobotFactory.create("SixElbow") instanceof SixElbowRobot, true);
        assertEquals(designPatterns.factory.approachTwo.RobotFactory.create(ERobot.SIX_ELBOW) instanceof SixElbowRobot, true);
        assertEquals(designPatterns.factory.approachThree.RobotFactory.create(ERobot.SIX_ELBOW) instanceof SixElbowRobot, true);
    }
}
