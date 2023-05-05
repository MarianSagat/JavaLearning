package javaExternalLibraries;

public interface Interpreter<T> {
    T interpret(ETaskState state);
    ETaskState interpret(T state);
    ETaskState interpret(String state);
}
