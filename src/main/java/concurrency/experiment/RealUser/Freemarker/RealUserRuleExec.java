package concurrency.experiment.RealUser.Freemarker;

import concurrency.scheduling.RuleTree;
import concurrency.scheduling.TaskNode;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class RealUserRuleExec {
public static void main(String[] args) throws InterruptedException {
RuleTree ruleTree = new RuleTree();

ExecutorService executorService = Executors.newFixedThreadPool(1);

// ============ 生成设备部分 ============
    UUID Smoke = UUID.randomUUID();
    ruleTree.createDevice("Smoke", Smoke);
    UUID Location = UUID.randomUUID();
    ruleTree.createDevice("Location", Location);
    UUID WaterLeakage = UUID.randomUUID();
    ruleTree.createDevice("WaterLeakage", WaterLeakage);
    UUID MijiaCurtain1 = UUID.randomUUID();
    ruleTree.createDevice("MijiaCurtain1", MijiaCurtain1);
    UUID MijiaCurtain2 = UUID.randomUUID();
    ruleTree.createDevice("MijiaCurtain2", MijiaCurtain2);
    UUID YeelightBulb = UUID.randomUUID();
    ruleTree.createDevice("YeelightBulb", YeelightBulb);
    UUID SmartThingsDoorSensor = UUID.randomUUID();
    ruleTree.createDevice("SmartThingsDoorSensor", SmartThingsDoorSensor);
    UUID MijiaDoorLock = UUID.randomUUID();
    ruleTree.createDevice("MijiaDoorLock", MijiaDoorLock);
    UUID RingDoorbell = UUID.randomUUID();
    ruleTree.createDevice("RingDoorbell", RingDoorbell);
    UUID iRobotRoomba = UUID.randomUUID();
    ruleTree.createDevice("iRobotRoomba", iRobotRoomba);
    UUID AlexaVoiceAssistance = UUID.randomUUID();
    ruleTree.createDevice("AlexaVoiceAssistance", AlexaVoiceAssistance);
    UUID PhilipsHueLight = UUID.randomUUID();
    ruleTree.createDevice("PhilipsHueLight", PhilipsHueLight);
    UUID MideaAirConditioner = UUID.randomUUID();
    ruleTree.createDevice("MideaAirConditioner", MideaAirConditioner);
    UUID NetatmoWeatherStation = UUID.randomUUID();
    ruleTree.createDevice("NetatmoWeatherStation", NetatmoWeatherStation);
    UUID YeelightCeilingLamp1 = UUID.randomUUID();
    ruleTree.createDevice("YeelightCeilingLamp1", YeelightCeilingLamp1);
    UUID YeelightCeilingLamp2 = UUID.randomUUID();
    ruleTree.createDevice("YeelightCeilingLamp2", YeelightCeilingLamp2);
    UUID YeelightCeilingLamp3 = UUID.randomUUID();
    ruleTree.createDevice("YeelightCeilingLamp3", YeelightCeilingLamp3);
    UUID YeelightCeilingLamp5 = UUID.randomUUID();
    ruleTree.createDevice("YeelightCeilingLamp5", YeelightCeilingLamp5);
    UUID YeelightCeilingLamp6 = UUID.randomUUID();
    ruleTree.createDevice("YeelightCeilingLamp6", YeelightCeilingLamp6);
    UUID WemoSmartPlug = UUID.randomUUID();
    ruleTree.createDevice("WemoSmartPlug", WemoSmartPlug);
    UUID WyzeCamera = UUID.randomUUID();
    ruleTree.createDevice("WyzeCamera", WyzeCamera);
    UUID SmartLifePIRmotionsensor1 = UUID.randomUUID();
    ruleTree.createDevice("SmartLifePIRmotionsensor1", SmartLifePIRmotionsensor1);
    UUID SmartLifePIRmotionsensor2 = UUID.randomUUID();
    ruleTree.createDevice("SmartLifePIRmotionsensor2", SmartLifePIRmotionsensor2);
    UUID SmartLifePIRmotionsensor3 = UUID.randomUUID();
    ruleTree.createDevice("SmartLifePIRmotionsensor3", SmartLifePIRmotionsensor3);
    UUID MijiaPurifier = UUID.randomUUID();
    ruleTree.createDevice("MijiaPurifier", MijiaPurifier);
    UUID MijiaProjector = UUID.randomUUID();
    ruleTree.createDevice("MijiaProjector", MijiaProjector);
    UUID Notification = UUID.randomUUID();
    ruleTree.createDevice("Notification", Notification);

// ============ 生成规则部分 ============
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(SmartThingsDoorSensor);

        // 遍历 actions
            actionDevices.add(Notification);

        submitTask(executorService, ruleTree, "Rule-1",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-1","if door opened(4),notify me."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

        // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-2",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-2","if rain(11), close the curtains(1,2)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

        // 遍历 actions
            actionDevices.add(YeelightBulb);

        submitTask(executorService, ruleTree, "Rule-3",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-3","If door(5) is locked, after 10sec, turn off the Yeelight Bulb(3)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

        // 遍历 actions
            actionDevices.add(WyzeCamera);
            actionDevices.add(Notification);

        submitTask(executorService, ruleTree, "Rule-4",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-4","If door(5) is unlocked between 11pm-4am, record a short vedio clip(18) and turn on notification(18)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(AlexaVoiceAssistance);

        // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-5",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-5","Tell Alexa(8) to open/close a certain Curtain(1-2)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor2);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);

        submitTask(executorService, ruleTree, "Rule-6",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-6","Every 30 minutes check PIR motion sensor(20), if 1h since last person detected, close lamp (13-14)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor3);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-7",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-7","Every 30 minutes check PIR motion sensor21, if 1h since last person detected, close lamp 15&16"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp3);

        submitTask(executorService, ruleTree, "Rule-8",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-8","If door(5) unlock, when night, then lamp(14) on"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

        // 遍历 actions
            actionDevices.add(RingDoorbell);
            actionDevices.add(AlexaVoiceAssistance);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-9",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-9","If door(5) unlock, then lights(6,8,11,17-18) switch on"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(SmartThingsDoorSensor);

        // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-10",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-10","After 7 pm, I come to the room, when I open the door, the curtain should be closed"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

        // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-11",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-11","If it is raining in winter, the curtain should be open for more light."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

        // 遍历 actions
            actionDevices.add(YeelightBulb);

        submitTask(executorService, ruleTree, "Rule-12",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-12","If it is raining after 5 pm, the bulb should be open for more light."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(SmartThingsDoorSensor);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-13",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-13","if smartThings Door Sensor (4) Any new motion ,then turn on Yeelight (12-16)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

        submitTask(executorService, ruleTree, "Rule-14",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-14","If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-15",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-15","If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

        submitTask(executorService, ruleTree, "Rule-16",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-16","If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-17",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-17","If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(YeelightBulb);

        submitTask(executorService, ruleTree, "Rule-18",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-18","When the curtain is closed, please turn on the bulb."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-19",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-19","if Curtain(1) open ,then Curtain(2) open"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(MideaAirConditioner);

        submitTask(executorService, ruleTree, "Rule-20",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-20","if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(YeelightBulb);

        // 遍历 actions
            actionDevices.add(MijiaCurtain1);

        submitTask(executorService, ruleTree, "Rule-21",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-21","When turn on the bulb, please close the curtain."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

        submitTask(executorService, ruleTree, "Rule-22",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-22","If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-23",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-23","If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-24",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-24","if Curtain(1) open ,then Curtain(2) open"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(MideaAirConditioner);

        submitTask(executorService, ruleTree, "Rule-25",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-25","if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(YeelightBulb);

        // 遍历 actions
            actionDevices.add(MijiaCurtain1);

        submitTask(executorService, ruleTree, "Rule-26",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-26","When turn on the bulb, please close the curtain."));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

        submitTask(executorService, ruleTree, "Rule-27",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-27","If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-28",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-28","If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

        // 遍历 actions
            actionDevices.add(MijiaCurtain2);

        submitTask(executorService, ruleTree, "Rule-29",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-29","if Curtain(1) open ,then Curtain(2) open"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(MideaAirConditioner);

        submitTask(executorService, ruleTree, "Rule-30",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-30","if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

        submitTask(executorService, ruleTree, "Rule-31",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-31","If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
        sleep(500);
        }
{
TreeSet<UUID> triggerDevices = new TreeSet<>();
    TreeSet<UUID> actionDevices = new TreeSet<>();

        // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

        // 遍历 actions
            actionDevices.add(MideaAirConditioner);

        submitTask(executorService, ruleTree, "Rule-32",
        triggerDevices, actionDevices,
        new TaskNode.SimpleExecFunc("Rule-32","if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
        sleep(500);
        }

        executorService.shutdown();
        }

        private static void submitTask(ExecutorService executorService,
        RuleTree ruleTree,
        String taskName,
        TreeSet<UUID> triggerDevices, TreeSet<UUID> actionDevices,
                TaskNode.SimpleExecFunc execFunc) {
                executorService.submit(() -> {
                Thread.currentThread().setName(taskName);
                ruleTree.createTask(taskName, triggerDevices, actionDevices, execFunc);
                });
                }
                }
