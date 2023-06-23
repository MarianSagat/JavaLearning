package designPatterns.factory.approachOne;

public enum ERobot {
    THREEELBOW(new ThreeElbowsRobot()),
    SIXELBOW(new SixElbowRobot());

    IRobot robotImpl;

    ERobot(IRobot robotImpl) {
        this.robotImpl = robotImpl;
    }
}
