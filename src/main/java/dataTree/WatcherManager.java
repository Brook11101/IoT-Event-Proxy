package dataTree;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class WatcherManager {
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Watcher>> watchTable = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Watcher, CopyOnWriteArrayList<String>> watch2Paths = new ConcurrentHashMap<>();

    public void addWatcher(String path, Watcher watcher) {
        watchTable.computeIfAbsent(path, k -> new CopyOnWriteArrayList<>()).add(watcher);
        watch2Paths.computeIfAbsent(watcher, k -> new CopyOnWriteArrayList<>()).add(path);
    }

    public void removeWatcher(String path, Watcher watcher) {
        watchTable.getOrDefault(path, new CopyOnWriteArrayList<>()).remove(watcher);
        watch2Paths.getOrDefault(watcher, new CopyOnWriteArrayList<>()).remove(path);
    }

    public List<Watcher> getWatchers(String path) {
        return watchTable.getOrDefault(path, new CopyOnWriteArrayList<>());
    }

    public boolean containsWatcher(String path, Watcher watcher) {
        return watchTable.containsKey(path) && watchTable.get(path).contains(watcher);
    }

    public void notifyWatchers(String path, EventType eventType) {
        List<Watcher> pathWatchers = watchTable.get(path);
        if (pathWatchers != null) {
            for (Watcher watcher : pathWatchers) {
                watcher.onEvent(eventType);
                // 如果 Watcher 应该是一次性的，这里可以移除它
                // pathWatchers.remove(watcher);
            }
        }
    }
}
