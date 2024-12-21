package concurrency.experiment;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 读取 device.csv、rules.txt，解析后使用 FreeMarker 生成 Main.java
 * @author 魏浩东
 */

public class GenerateConcurrency {

    public static void main(String[] args) {
        try {
            // 1. 解析 device.csv
            List<DeviceInfo> deviceList = parseDeviceFile("src/main/java/concurrency/experiment/data/device.txt");

            // 2. 解析 rules.txt (行中先读name,triggerCount,actionCount，再根据数量读触发/动作设备)
            List<RuleInfo> ruleList = parseRulesFile("src/main/java/concurrency/experiment/data/rules.txt");

            // 3. 初始化 FreeMarker
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);

            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            cfg.setDefaultEncoding("UTF-8");

            // 4. 获取模板
            Template template = cfg.getTemplate("main.ftl");

            // 5. 构建数据模型
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("devices", deviceList);
            dataModel.put("rules", ruleList);

            // 6. 生成 Main.java
            File outFile = new File("src/main/java/concurrency/Main.java");
            try (Writer out = new FileWriter(outFile)) {
                template.process(dataModel, out);
            }

        } catch (IOException | TemplateException e) {
            System.err.println("生成 Main.java 失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 解析 device.csv
     * 格式:
     * name,uuidVar
     * Wemo Smart Plug,plug
     * ...
     */
    private static List<DeviceInfo> parseDeviceFile(String filePath) throws IOException {
        List<DeviceInfo> deviceList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String header = br.readLine(); // 跳过表头 (name,uuidVar)
            String line;
            while ((line = br.readLine()) != null) {
                // 逗号分隔
                String[] columns = line.split(",");
                // [0] = 设备名, [1] = 变量名
                if (columns.length >= 2) {
                    String name = columns[0].trim();
                    String uuidVar = columns[1].trim();
                    System.out.println("New device detected: " + name + ", " + uuidVar);
                    deviceList.add(new DeviceInfo(name, uuidVar));
                }
            }
        }
        return deviceList;
    }

    /**
     * 解析 rules.txt
     * 格式:
     * name,triggerCount,actionCount,triggerList,actionList
     * rule1,1,2,plug,bulb,camera
     * rule2,1,1,bulb,plug
     * ...
     *
     * columns[0] = name
     * columns[1] = triggerCount
     * columns[2] = actionCount
     * 后续 columns[...] = 触发设备 + 动作设备
     */
    private static List<RuleInfo> parseRulesFile(String filePath) throws IOException {
        List<RuleInfo> ruleList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            // 第一行是表头 "name,triggerCount,actionCount,triggerList,actionList"
            br.readLine(); // 跳过即可

            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length < 3) {
                    // 至少要 name, triggerCount, actionCount
                    continue;
                }
                // 1) 基本信息
                String name = columns[0].trim();
                int triggerCount = Integer.parseInt(columns[1].trim());
                int actionCount  = Integer.parseInt(columns[2].trim());

                RuleInfo rule = new RuleInfo(name, triggerCount, actionCount);

                // 2) 解析触发设备
                int index = 3; // 从 columns[3] 开始
                for (int i = 0; i < triggerCount; i++) {
                    if (index + i < columns.length) {
                        rule.getTriggers().add(columns[index + i].trim());
                    }
                }
                // 3) 解析动作设备
                index = 3 + triggerCount;
                for (int i = 0; i < actionCount; i++) {
                    if (index + i < columns.length) {
                        rule.getActions().add(columns[index + i].trim());
                    }
                }

                ruleList.add(rule);
            }
        }
        return ruleList;
    }
}
