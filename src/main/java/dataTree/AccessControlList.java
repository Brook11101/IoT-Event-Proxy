package dataTree;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.Collections;

public class AccessControlList {
    private final ConcurrentHashMap<String, Set<Permission>> acl;

    public AccessControlList() {
        this.acl = new ConcurrentHashMap<>();
    }

    public void addPermission(String principal, Permission permission) {
        acl.computeIfAbsent(principal, k -> Collections.newSetFromMap(new ConcurrentHashMap<>())).add(permission);
    }

    public void removePermission(String principal, Permission permission) {
        Set<Permission> perms = acl.get(principal);
        if (perms != null) {
            perms.remove(permission);
            if (perms.isEmpty()) {
                acl.remove(principal);
            }
        }
    }

    public Set<Permission> getPermissions(String principal) {
        return acl.getOrDefault(principal, Collections.emptySet());
    }

    public boolean hasPermission(String principal, Permission permission) {
        return acl.containsKey(principal) && acl.get(principal).contains(permission);
    }
}
