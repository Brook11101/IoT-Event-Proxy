package dataTree;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class WatcherManager {
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Watcher>> watchTable = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Watcher, CopyOnWriteArrayList<String>> watch2Paths = new ConcurrentHashMap<>();

    public void addWatcher(String path, Watcher watcher) {
        watchTable.computeIfAbsent(path, k -> new CopyOnWriteArrayList<>()).add(watcher);
        //注意，需要确保一个watcher被加到多个节点上时这里的watch2Paths也能够及时收录
        watch2Paths.computeIfAbsent(watcher, k -> new CopyOnWriteArrayList<>()).add(path);
    }

    public void removeWatcher(String path, Watcher watcher) {
        // 从特定路径移除Watcher
        CopyOnWriteArrayList<Watcher> watchersAtGivenPath = watchTable.getOrDefault(path, new CopyOnWriteArrayList<>());
        watchersAtGivenPath.remove(watcher);
        if (watchersAtGivenPath.isEmpty()) {
            watchTable.remove(path);
        }

        // 从watch2Paths中移除Watcher，并获取所有相关联的路径
        CopyOnWriteArrayList<String> paths = watch2Paths.getOrDefault(watcher, new CopyOnWriteArrayList<>());
        paths.remove(path);
        if (paths.isEmpty()) {
            watch2Paths.remove(watcher);
        } else {
            // 这里进行了优化：对于Watcher关联的其他路径，也需要进行移除操作
            for (String p : paths) {
                CopyOnWriteArrayList<Watcher> watchers = watchTable.getOrDefault(p, new CopyOnWriteArrayList<>());
                watchers.remove(watcher);
                if (watchers.isEmpty()) {
                    watchTable.remove(p);
                }
            }
            watch2Paths.remove(watcher); // 移除watch2Paths中的Watcher记录
        }
    }

    public List<Watcher> getWatchers(String path) {
        return watchTable.getOrDefault(path, new CopyOnWriteArrayList<>());
    }

    public boolean containsWatcher(String path, Watcher watcher) {
        return watchTable.containsKey(path) && watchTable.get(path).contains(watcher);
    }

    public void triggerWatchers(String path, EventType eventType) {
        List<Watcher> pathWatchers = watchTable.get(path);
        if (pathWatchers != null) {
            for (Watcher watcher : pathWatchers) {
                watcher.onEvent(eventType);
                //这里暂时不对Watchers作一次性处理
                //removeWatcher(path, watcher);
            }
        }
    }
}
