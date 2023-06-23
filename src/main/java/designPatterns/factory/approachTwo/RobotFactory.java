package designPatterns.factory.approachTwo;

import designPatterns.factory.approachTwo.ERobot;
import designPatterns.factory.approachOne.IRobot;

public class RobotFactory
{
    static final public IRobot create(ERobot robotName)
    {
        return robotName.getImplementation().get();
    }
}
