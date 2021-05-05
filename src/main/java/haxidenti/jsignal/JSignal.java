package haxidenti.jsignal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class JSignal<T> {

    List<JSignalTask<T>> tasks = Collections.synchronizedList(new ArrayList<>(64));
    List<JSignalTask<T>> onceTask = Collections.synchronizedList(new ArrayList<>(64));

    public void connect(JSignalTask<T> task) {
        tasks.add(task);
    }

    public void emit(T data) {
        for (JSignalTask<T> task : onceTask) {
            task.call(data);
        }
        onceTask.clear();
        for (JSignalTask<T> task : tasks) {
            task.call(data);
        }
    }

    public void clear() {
        tasks.clear();
    }

    public void connectOnce(JSignalTask<T> task) {
        onceTask.add(task);
    }

    public void connectFiltered(JSignalTask<T> task, Predicate<T> filter) {
        JSignalTask<T> subTask = data -> {
            if (filter.test(data)) {
                task.call(data);
            }
        };
        tasks.add(task);
    }
}
