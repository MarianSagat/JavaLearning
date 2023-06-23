package designPatterns.factory.approachTwo;

import designPatterns.factory.approachOne.IRobot;
import designPatterns.factory.approachOne.SixElbowRobot;
import designPatterns.factory.approachOne.ThreeElbowsRobot;

import java.util.function.Supplier;

//doesnt work lobok anotation i tryed to enable Annotation processors, also restart IDE
/*@RequiredArgsConstructor
@Getter*/
public enum ERobot
{
    THREE_ELBOW(ThreeElbowsRobot::new),
    SIX_ELBOW(SixElbowRobot::new);

    private final Supplier<IRobot> implementation;

    ERobot(Supplier<IRobot> implementation)
    {
        this.implementation = implementation;
    }

    public Supplier<IRobot> getImplementation()
    {
        return implementation;
    }
}
