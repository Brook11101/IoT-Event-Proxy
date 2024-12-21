package concurrency.experiment;

/**
 * 用于存储 device.csv 解析结果
 */
public class DeviceInfo {
    private String name;      // 设备名称
    private String uuidVar;   // 对应在 Java 代码里用的变量名

    public DeviceInfo(String name, String uuidVar) {
        this.name = name;
        this.uuidVar = uuidVar;
    }

    public String getName() {
        return name;
    }

    public String getUuidVar() {
        return uuidVar;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "name='" + name + '\'' +
                ", uuidVar='" + uuidVar + '\'' +
                '}';
    }
}
