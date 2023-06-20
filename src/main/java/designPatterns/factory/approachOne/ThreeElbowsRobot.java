package designPatterns.factory.approachOne;

public class ThreeElbowsRobot implements IRobot{
    public ThreeElbowsRobot()
    {

    }
    @Override
    public int getElbowsCount() {
        return 3;
    }
}
