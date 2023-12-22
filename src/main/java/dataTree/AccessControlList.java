package dataTree;

import java.util.*;

public class AccessControlList {
    private final Map<String, Set<Permission>> acl;

    public AccessControlList() {
        this.acl = new HashMap<>();
    }

    public synchronized void addPermission(String principal, Permission permission) {
        //这里的写法是如果没有就创建一个新的Set
        acl.computeIfAbsent(principal, k -> new HashSet<>()).add(permission);
    }

    public synchronized void removePermission(String principal, Permission permission) {
        if (acl.containsKey(principal)) {
            acl.get(principal).remove(permission);
            if (acl.get(principal).isEmpty()) {
                acl.remove(principal); // 移除空的权限集合
            }
        }
    }

    public synchronized Set<Permission> getPermissions(String principal) {
        return acl.getOrDefault(principal, Collections.emptySet());
    }

    public synchronized boolean hasPermission(String principal, Permission permission) {
        return acl.containsKey(principal) && acl.get(principal).contains(permission);
    }

    // 其他方法，例如移除权限等
}