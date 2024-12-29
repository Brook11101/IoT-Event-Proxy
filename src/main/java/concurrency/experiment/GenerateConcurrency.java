package concurrency.experiment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 读取 device.csv、rules.txt，解析后使用 FreeMarker 生成 Main.java
 * @author 魏浩东
 */

public class GenerateConcurrency {

    public static void main(String[] args) {
        try {
            // 1. 解析 device.csv
            List<DeviceInfo> deviceList = parseDeviceFile("src/main/java/concurrency/experiment/RealUser/RealUserDevice.txt");

            // 2. 解析 rules.txt (行中先读name,triggerCount,actionCount，再根据数量读触发/动作设备)
            List<RuleInfo> ruleList = parseRulesLog("E:\\研究生信息收集\\论文材料\\IoT-Event-Detector\\detector\\matcher\\RealUser\\SynchronizationComparison\\synclogs.txt");

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

    private static List<RuleInfo> parseRulesLog(String filePath) throws IOException {
        List<RuleInfo> ruleList = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setLenient() // 启用宽松模式，允许非标准 JSON 格式
                .create();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Map<String, Object> ruleData = gson.fromJson(line, Map.class);

                // 提取规则 ID 和描述
                int id = ((Double) ruleData.get("id")).intValue(); // Gson 解析数字为 Double
                String description = (String) ruleData.get("description");
                RuleInfo rule = new RuleInfo(id, description.replace("\\&", "&"));

                // 提取 triggers：只取设备名称
                List<String> triggers = new ArrayList<>();
                triggers.add((String) ((List<?>) ruleData.get("Trigger")).get(0));
                rule.getTriggers().addAll(triggers);

                // 提取 actions：只取设备名称
                List<String> actions = ((List<List<?>>) ruleData.get("Action"))
                        .stream()
                        .map(action -> action.get(0).toString()) // 仅取设备名称
                        .collect(Collectors.toList());
                rule.getActions().addAll(actions);

                ruleList.add(rule);
            }
        }
        return ruleList;
    }

}
