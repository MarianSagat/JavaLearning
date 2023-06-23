package designPatterns.factory.approachOne;

public class RobotFactory
{
    static public IRobot create(ERobot robot)
    {
        return robot.robotImpl;
    }

    static public IRobot create(String robot)
    {
        try
        {
            return ERobot.valueOf(robot.toUpperCase()).robotImpl;
        }
        catch (IllegalArgumentException exception)
        {
            return null;
        }
    }
}
