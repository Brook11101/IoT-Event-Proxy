package concurrency.experiment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Date: 2025/1/5
 * @Author: 魏浩东
 * @Description: 解析规则日志，并转换为 JSON（使用嵌套数组表示不同轮次）
 */
public class StaticRuleParser {

    /**
     * 解析规则日志并转换为 JSON
     * @param logFilePath 规则日志文件路径
     * @param outputJsonPath 输出 JSON 文件路径
     */
    public static void parseRuleLogToJson(String logFilePath, String outputJsonPath) {
        List<List<RuleInfo>> allRounds = new ArrayList<>();  // 存储所有轮次
        List<RuleInfo> currentRound = new ArrayList<>();     // 当前轮次

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(logFilePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // 如果是空行，表示新轮次
                if (line.isEmpty()) {
                    if (!currentRound.isEmpty()) {
                        allRounds.add(new ArrayList<>(currentRound));  // 存储当前轮次
                        currentRound.clear();  // 清空，准备下一轮
                    }
                    continue;
                }

                // 处理规则行
                RuleInfo rule = processLogLine(line, gson);
                if (rule != null) {
                    currentRound.add(rule);
                }
            }

            // 存储最后一轮数据（如果存在）
            if (!currentRound.isEmpty()) {
                allRounds.add(currentRound);
            }

        } catch (IOException e) {
            System.err.println("读取日志文件失败: " + e.getMessage());
            e.printStackTrace();
        }

        // 写入 JSON 文件
        writeRulesToJson(allRounds, outputJsonPath, gson);
    }

    /**
     * 解析单行日志，转换为 RuleInfo 对象
     * @param logLine 日志行
     * @param gson Gson 实例
     * @return 解析后的 RuleInfo（若解析失败，返回 null）
     */
    private static RuleInfo processLogLine(String logLine, Gson gson) {
        try {
            // 清理单引号，确保 JSON 格式正确
            String cleanedLine = logLine.replace("'", "\"");

            // 解析 JSON 数据
            Map<String, Object> ruleData = gson.fromJson(cleanedLine, Map.class);
            if (ruleData == null || !ruleData.containsKey("id") || !ruleData.containsKey("description")) {
                System.err.println("解析失败: 规则缺少必要字段 " + logLine);
                return null;
            }

            int id = ((Double) ruleData.get("id")).intValue();
            String description = ((String) ruleData.get("description")).replace("\\&", "&");

            List<String> triggers = extractTriggers(ruleData);
            List<String> actions = extractActions(ruleData);

            // 创建 RuleInfo 并返回
            RuleInfo rule = new RuleInfo(id, description);
            rule.getTriggers().addAll(triggers);
            rule.getActions().addAll(actions);
            return rule;

        } catch (Exception e) {
            System.err.println("解析日志行失败: " + logLine + "，错误信息: " + e.getMessage());
            return null;
        }
    }

    /**
     * 提取触发设备
     * @param ruleData 规则数据
     * @return 触发设备列表
     */
    private static List<String> extractTriggers(Map<String, Object> ruleData) {
        List<String> triggers = new ArrayList<>();
        if (ruleData.containsKey("Trigger") && ruleData.get("Trigger") instanceof List) {
            triggers.add((String) ((List<?>) ruleData.get("Trigger")).get(0));
        }
        return triggers;
    }

    /**
     * 提取动作设备
     * @param ruleData 规则数据
     * @return 动作设备列表
     */
    private static List<String> extractActions(Map<String, Object> ruleData) {
        List<String> actions = new ArrayList<>();
        if (ruleData.containsKey("Action") && ruleData.get("Action") instanceof List) {
            List<List<?>> actionList = (List<List<?>>) ruleData.get("Action");
            for (List<?> action : actionList) {
                actions.add(action.get(0).toString());
            }
        }
        return actions;
    }

    /**
     * 将规则列表写入 JSON 文件
     * @param allRounds 嵌套数组格式的规则列表
     * @param outputJsonPath 输出 JSON 文件路径
     * @param gson Gson 实例
     */
    private static void writeRulesToJson(List<List<RuleInfo>> allRounds, String outputJsonPath, Gson gson) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputJsonPath), StandardCharsets.UTF_8))) {
            gson.toJson(allRounds, writer);
        } catch (IOException e) {
            System.err.println("写入 JSON 文件失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String logFilePath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Detector\\Synchronizer\\Monitor\\Data\\static_logs.txt";
        String outputJsonPath = "E:\\研究生信息收集\\论文材料\\IoT-Event-Proxy\\src\\main\\java\\concurrency\\experiment\\data\\StaticRules.json";

        parseRuleLogToJson(logFilePath, outputJsonPath);
        System.out.println("规则解析完成");
    }
}
