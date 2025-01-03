package concurrency.experiment;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于存储 rules.txt 解析结果
 */
public class RuleInfo {
    private int id;                // 规则 ID
    private String description;    // 规则描述
    private List<String> triggers; // 解析到的触发设备名称
    private List<String> actions;  // 解析到的动作设备名称

    public RuleInfo(int id, String description) {
        this.id = id;
        this.description = description;
        this.triggers = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTriggers() {
        return triggers;
    }

    public List<String> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "RuleInfo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", triggers=" + triggers +
                ", actions=" + actions +
                '}';
    }
}
