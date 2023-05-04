package javaExternalLibraries;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;

public class GitlabInterpreter implements Interpreter<EGitlabTaskState> {
    static final BiMap<EGitlabTaskState, ETaskState> gitlabInterpreter = HashBiMap.create(
            Map.of(EGitlabTaskState.COMPLETED, ETaskState.COMPLETED,
                    EGitlabTaskState.QUEUED, ETaskState.WAITING,
                    EGitlabTaskState.RUNNING, ETaskState.IN_PROGRESS
            ));

    @Override
    public EGitlabTaskState interpret(ETaskState state) {
        return gitlabInterpreter.inverse().getOrDefault(state, EGitlabTaskState.QUEUED);
    }

    @Override
    public ETaskState interpret(EGitlabTaskState state) {
        return gitlabInterpreter.getOrDefault(state, ETaskState.NOT_ASSIGNED);
    }

    @Override
    public ETaskState interpret(String state) {

        try
        {
            return gitlabInterpreter.getOrDefault(EGitlabTaskState.valueOf(state),ETaskState.NOT_ASSIGNED );
        }
        catch(Exception e)
        {
            return ETaskState.NOT_ASSIGNED;
        }
    }
}
