import javaExternalLibraries.EGitlabTaskState;
import javaExternalLibraries.ETaskState;
import javaExternalLibraries.GitlabInterpreter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GitlabInterpreterTest {
    @Test
    public void testInterpreter() {
        Assert.assertEquals(new GitlabInterpreter().interpret(""), ETaskState.NOT_ASSIGNED);
        Assert.assertEquals(new GitlabInterpreter().interpret(EGitlabTaskState.COMPLETED), ETaskState.COMPLETED);
        Assert.assertEquals(new GitlabInterpreter().interpret(ETaskState.IN_PROGRESS), EGitlabTaskState.RUNNING);
    }
}
