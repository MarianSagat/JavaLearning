package javaExternalLibraries;

//NOTE: This file was created with help AI tabinene Starter completion
public enum EJiraTaskState {
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    private final String state;

    EJiraTaskState(String state)
    {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
