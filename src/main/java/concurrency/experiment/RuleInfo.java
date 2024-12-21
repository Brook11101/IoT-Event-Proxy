package concurrency.experiment;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于存储 rules.txt 解析结果
 */
public class RuleInfo {
    private String name;          // 规则名称
    private int triggerCount;     // 触发设备个数
    private int actionCount;      // 动作设备个数

    private List<String> triggers;  // 解析到的触发设备名称
    private List<String> actions;   // 解析到的动作设备名称

    public RuleInfo(String name, int triggerCount, int actionCount) {
        this.name = name;
        this.triggerCount = triggerCount;
        this.actionCount = actionCount;
        this.triggers = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getTriggerCount() {
        return triggerCount;
    }

    public int getActionCount() {
        return actionCount;
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
                "name='" + name + '\'' +
                ", triggerCount=" + triggerCount +
                ", actionCount=" + actionCount +
                ", triggers=" + triggers +
                ", actions=" + actions +
                '}';
    }
}
