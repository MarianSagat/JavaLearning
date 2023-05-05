package javaExternalLibraries;

//NOTE: This file was created with help AI tabinene Starter completion
public enum EGitlabTaskState {
    QUEUED("queued"),
    RUNNING("running"),
    COMPLETED("completed");

    private final String state;

    EGitlabTaskState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
}
