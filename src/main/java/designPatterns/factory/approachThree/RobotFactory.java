package designPatterns.factory.approachThree;

import designPatterns.factory.approachOne.IRobot;
import designPatterns.factory.approachOne.SixElbowRobot;
import designPatterns.factory.approachOne.ThreeElbowsRobot;
import designPatterns.factory.approachTwo.ERobot;

import java.util.Map;

public class RobotFactory
{
    private static final Map<ERobot, IRobot> mapping = Map.of
            (
                    ERobot.SIX_ELBOW, new SixElbowRobot(),
                    ERobot.THREE_ELBOW, new ThreeElbowsRobot()
            );

    static final public IRobot create(ERobot robotName)
    {
        try
        {
            return mapping.get(robotName);
        }
        catch (NullPointerException exception)
        {
            return null;
        }
    }
}
