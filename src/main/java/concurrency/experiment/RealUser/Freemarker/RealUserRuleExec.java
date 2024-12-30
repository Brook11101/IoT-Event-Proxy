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
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-1",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Netatmo Weather Station(11) is Windy, close MiJia Curtains(1,2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-2",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if i leave home, start the iRobot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartThingsDoorSensor);

            // 遍历 actions
            actionDevices.add(Notification);

            submitTask(executorService, ruleTree, "Rule-3",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(4),notify me."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-4",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if i leave home, close all lamps(12,13,14,15,16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-5",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) is locked, after 10sec, turn off the Yeelight Bulb(3)"));
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

            submitTask(executorService, ruleTree, "Rule-6",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) is unlocked between 11pm-4am, record a short vedio clip(18) and turn on notification(18)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);

            submitTask(executorService, ruleTree, "Rule-7",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor(19), if 1h since last person detected, close lamp (12-13)"));
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

            submitTask(executorService, ruleTree, "Rule-8",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor(20), if 1h since last person detected, close lamp (13-14)"));
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

            submitTask(executorService, ruleTree, "Rule-9",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor21, if 1h since last person detected, close lamp 15&16"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp3);

            submitTask(executorService, ruleTree, "Rule-10",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) unlock, when night, then lamp(14) on"));
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

            submitTask(executorService, ruleTree, "Rule-11",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) unlock, then lights(6,8,11,17-18) switch on"));
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

            submitTask(executorService, ruleTree, "Rule-12",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If weather station(11) detect wind speed rise, curtain(1,2) switch off"));
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

            submitTask(executorService, ruleTree, "Rule-13",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After 7 pm, I come to the room, when I open the door, the curtain should be closed"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-14",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When I leave the room, the curtain should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-15",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After we leave the room, about 10 pm everyday, the bulb should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-16",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After we leave the room, about 10 pm everyday, the curtain should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-17",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After we leave the room, about 10 pm everyday, the smart plug should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-18",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When I leave the room, the camera should be closed."));
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

            submitTask(executorService, ruleTree, "Rule-19",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if smartThings Door Sensor (4) Any new motion ,then turn on Yeelight (12-16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-20",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-21",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-22",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-23",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-24",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-25",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
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
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
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
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-28",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-29",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-30",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-31",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-32",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-33",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-34",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-35",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-36",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-37",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-38",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MideaAirConditioner);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-39",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if air conditioner(10) is opened, close curtains(1,2)"));
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

            submitTask(executorService, ruleTree, "Rule-40",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if rain(11), close the curtains(1,2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-41",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if motion(19) detected, open the wemo smart plug(17)."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-42",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if motion detected(19), open the camera(18)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);

            submitTask(executorService, ruleTree, "Rule-43",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(19) detects person, turn on Yeelight Ceiling Lamp (12-13)"));
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

            submitTask(executorService, ruleTree, "Rule-44",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(20) detects person, turn on Yeelight Ceiling Lamp (13-14)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-45",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(19), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor2);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-46",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(20), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-47",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If sensor(19) detect person, wyze(18) enable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor2);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-48",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR Motion Sensor(20) detects person, enable Camera(18) motion detection"));
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

            submitTask(executorService, ruleTree, "Rule-49",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining in winter, the curtain should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-50",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining after 5 pm, the bulb should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-51",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Job Complete ,then Wemo Smart Plug（17）Turn off"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-52",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-53",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-54",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-55",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-56",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-57",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-58",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, close curtain(1) and curtain(2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-59",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-60",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-61",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-62",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-63",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-64",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-65",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-66",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-67",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-68",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-69",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-70",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-71",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-72",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-73",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-74",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-75",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-76",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-77",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-78",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-79",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-80",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-81",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-82",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(5), when after 7p.m., open the lamps(12,13,14,15,16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MideaAirConditioner);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-83",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if air conditioner(10) is opened, close curtains(1,2)"));
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

            submitTask(executorService, ruleTree, "Rule-84",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(21) detects person, turn on Yeelight Ceiling Lamp (15-16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor3);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-85",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(21), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-86",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-87",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) lock, when door sensor(4) open, then wyze(18) alert"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-88",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) lock, when fri, then iRobot(7) cleaning a room"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-89",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) lock, wemo(17) and purifier(22) switch off"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-90",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-91",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-92",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(5), open the lamps(12,13,14,15,16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-93",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Mijia Door Lock(5) locked,then turn on Wyze Camera(18)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-94",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Netatmo Weather Station(11) Air pressure rises above,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-95",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Job Complete ,then Wemo Smart Plug（17）Turn off"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-96",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-97",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-98",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-99",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-100",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-101",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-102",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-103",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-104",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-105",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-106",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-107",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-108",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-109",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-110",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-111",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-112",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-113",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-114",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-115",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-116",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-117",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-118",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-119",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-120",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-121",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-122",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-123",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-124",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-125",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-126",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-127",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-128",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartThingsDoorSensor);

            // 遍历 actions
            actionDevices.add(Notification);

            submitTask(executorService, ruleTree, "Rule-129",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(4),notify me."));
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

            submitTask(executorService, ruleTree, "Rule-130",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if rain(11), close the curtains(1,2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-131",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-132",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
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

            submitTask(executorService, ruleTree, "Rule-133",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Tell Alexa(8) to open/close a certain Curtain(1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);

            submitTask(executorService, ruleTree, "Rule-134",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor(19), if 1h since last person detected, close lamp (12-13)"));
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

            submitTask(executorService, ruleTree, "Rule-135",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor(20), if 1h since last person detected, close lamp (13-14)"));
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

            submitTask(executorService, ruleTree, "Rule-136",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor21, if 1h since last person detected, close lamp 15&16"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-137",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-138",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, close curtain(1) and curtain(2)"));
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

            submitTask(executorService, ruleTree, "Rule-139",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After 7 pm, I come to the room, when I open the door, the curtain should be closed"));
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

            submitTask(executorService, ruleTree, "Rule-140",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining in winter, the curtain should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-141",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining after 5 pm, the bulb should be open for more light."));
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

            submitTask(executorService, ruleTree, "Rule-142",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if smartThings Door Sensor (4) Any new motion ,then turn on Yeelight (12-16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-143",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-144",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-145",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-146",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-147",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-148",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-149",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-150",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-151",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-152",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-153",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-154",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-155",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-156",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-157",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-158",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-159",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-160",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-161",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(RingDoorbell);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-162",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if doorbell rings(6), open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WyzeCamera);

            // 遍历 actions
            actionDevices.add(PhilipsHueLight);
            actionDevices.add(MijiaProjector);

            submitTask(executorService, ruleTree, "Rule-163",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If motion(18) is detected by Wyze Camera, turn on Philips Hue Light(9) and Mijia Projecter(23)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WyzeCamera);

            // 遍历 actions
            actionDevices.add(PhilipsHueLight);

            submitTask(executorService, ruleTree, "Rule-164",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If Camera(18) detects person, open Light(9)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(RingDoorbell);

            // 遍历 actions
            actionDevices.add(MijiaProjector);

            submitTask(executorService, ruleTree, "Rule-165",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If Doorbell(6) rings, mute Projector (23)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WyzeCamera);

            // 遍历 actions
            actionDevices.add(MijiaProjector);

            submitTask(executorService, ruleTree, "Rule-166",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If Camera(18) detect person, unmute Projector(23)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(RingDoorbell);

            // 遍历 actions
            actionDevices.add(AlexaVoiceAssistance);

            submitTask(executorService, ruleTree, "Rule-167",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Ring Doorbell（6） new ring detected,then Alexa Voice Assistance（8） sing a song"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WyzeCamera);

            // 遍历 actions
            actionDevices.add(AlexaVoiceAssistance);

            submitTask(executorService, ruleTree, "Rule-168",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Wyze Camera（18）Smoke alarm is detected ,then  Alexa Voice Assistance（8） alarm"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-169",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-170",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-171",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-172",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-173",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MideaAirConditioner);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-174",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if air conditioner(10) is opened, close curtains(1,2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-175",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) is locked, after 10sec, turn off the Yeelight Bulb(3)"));
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

            submitTask(executorService, ruleTree, "Rule-176",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) is unlocked between 11pm-4am, record a short vedio clip(18) and turn on notification(18)"));
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

            submitTask(executorService, ruleTree, "Rule-177",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Tell Alexa(8) to open/close a certain Curtain(1-2)"));
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

            submitTask(executorService, ruleTree, "Rule-178",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(21) detects person, turn on Yeelight Ceiling Lamp (15-16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor3);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-179",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(21), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-180",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp3);

            submitTask(executorService, ruleTree, "Rule-181",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) unlock, when night, then lamp(14) on"));
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

            submitTask(executorService, ruleTree, "Rule-182",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) unlock, then lights(6,8,11,17-18) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-183",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-184",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If Weather Station(11) detects rain stop, turn off Purifier(22)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-185",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-186",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-187",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Netatmo Weather Station(11) rain no longer detected,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-188",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-189",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-190",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-191",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-192",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-193",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-194",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-195",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-196",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-197",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-198",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-199",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-200",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-201",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-202",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-203",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-204",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-205",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-206",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-207",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-208",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-209",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-210",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-211",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-212",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-213",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-214",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-215",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-216",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if i leave home, start the iRobot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(RingDoorbell);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-217",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if doorbell rings(6), open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-218",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if i leave home, close all lamps(12,13,14,15,16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-219",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if motion(19) detected, open the wemo smart plug(17)."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-220",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if motion detected(19), open the camera(18)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);

            submitTask(executorService, ruleTree, "Rule-221",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(19) detects person, turn on Yeelight Ceiling Lamp (12-13)"));
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

            submitTask(executorService, ruleTree, "Rule-222",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor(20), if 1h since last person detected, close lamp (13-14)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-223",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(19), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-224",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-225",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-226",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If sensor(19) detect person, wyze(18) enable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(RingDoorbell);

            // 遍历 actions
            actionDevices.add(MijiaProjector);

            submitTask(executorService, ruleTree, "Rule-227",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If Doorbell(6) rings, mute Projector (23)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-228",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-229",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When I leave the room, the curtain should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-230",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After we leave the room, about 10 pm everyday, the bulb should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-231",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After we leave the room, about 10 pm everyday, the curtain should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-232",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After we leave the room, about 10 pm everyday, the smart plug should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(Location);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-233",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When I leave the room, the camera should be closed."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(RingDoorbell);

            // 遍历 actions
            actionDevices.add(AlexaVoiceAssistance);

            submitTask(executorService, ruleTree, "Rule-234",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Ring Doorbell（6） new ring detected,then Alexa Voice Assistance（8） sing a song"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-235",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-236",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-237",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-238",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-239",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-240",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-241",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-242",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, close curtain(1) and curtain(2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-243",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-244",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-245",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-246",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-247",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-248",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-249",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-250",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-251",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-252",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-253",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-254",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-255",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-256",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-257",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-258",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-259",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-260",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-261",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-262",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-263",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-264",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-265",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-266",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-267",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-268",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-269",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-270",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-271",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-272",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(5), when after 7p.m., open the lamps(12,13,14,15,16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MideaAirConditioner);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-273",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if air conditioner(10) is opened, close curtains(1,2)"));
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

            submitTask(executorService, ruleTree, "Rule-274",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if rain(11), close the curtains(1,2)"));
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

            submitTask(executorService, ruleTree, "Rule-275",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Tell Alexa(8) to open/close a certain Curtain(1-2)"));
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

            submitTask(executorService, ruleTree, "Rule-276",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor21, if 1h since last person detected, close lamp 15&16"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-277",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-278",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) lock, when door sensor(4) open, then wyze(18) alert"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-279",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) lock, when fri, then iRobot(7) cleaning a room"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-280",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If door(5) lock, wemo(17) and purifier(22) switch off"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-281",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
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

            submitTask(executorService, ruleTree, "Rule-282",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining in winter, the curtain should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-283",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining after 5 pm, the bulb should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);
            actionDevices.add(YeelightCeilingLamp3);
            actionDevices.add(YeelightCeilingLamp5);
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-284",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(5), open the lamps(12,13,14,15,16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaDoorLock);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-285",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Mijia Door Lock(5) locked,then turn on Wyze Camera(18)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-286",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-287",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-288",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-289",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-290",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-291",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-292",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-293",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-294",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-295",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-296",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-297",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-298",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-299",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-300",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-301",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-302",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-303",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-304",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-305",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-306",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-307",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-308",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-309",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-310",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-311",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-312",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-313",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-314",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-315",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-316",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-317",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-318",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-319",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-320",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-321",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-322",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-323",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-324",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-325",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartThingsDoorSensor);

            // 遍历 actions
            actionDevices.add(Notification);

            submitTask(executorService, ruleTree, "Rule-326",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if door opened(4),notify me."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MideaAirConditioner);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-327",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if air conditioner(10) is opened, close curtains(1,2)"));
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

            submitTask(executorService, ruleTree, "Rule-328",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if sun(12), open the curtains(1,2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-329",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if motion(19) detected, open the wemo smart plug(17)."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-330",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if motion detected(19), open the camera(18)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp1);
            actionDevices.add(YeelightCeilingLamp2);

            submitTask(executorService, ruleTree, "Rule-331",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(19) detects person, turn on Yeelight Ceiling Lamp (12-13)"));
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

            submitTask(executorService, ruleTree, "Rule-332",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(20) detects person, turn on Yeelight Ceiling Lamp (13-14)"));
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

            submitTask(executorService, ruleTree, "Rule-333",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR motion senser(21) detects person, turn on Yeelight Ceiling Lamp (15-16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-334",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(19), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor2);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-335",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(20), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor3);

            // 遍历 actions
            actionDevices.add(iRobotRoomba);

            submitTask(executorService, ruleTree, "Rule-336",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If person detected by PIR motion(21), dock robot(7)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor1);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-337",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If sensor(19) detect person, wyze(18) enable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(SmartLifePIRmotionsensor2);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-338",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If PIR Motion Sensor(20) detects person, enable Camera(18) motion detection"));
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

            submitTask(executorService, ruleTree, "Rule-339",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("After 7 pm, I come to the room, when I open the door, the curtain should be closed"));
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

            submitTask(executorService, ruleTree, "Rule-340",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if smartThings Door Sensor (4) Any new motion ,then turn on Yeelight (12-16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WemoSmartPlug);

            submitTask(executorService, ruleTree, "Rule-341",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Job Complete ,then Wemo Smart Plug（17）Turn off"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-342",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-343",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, when curtain(1) or curtain(2) is closed, open the Yeelight Bulb(3)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-344",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-345",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-346",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-347",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(WemoSmartPlug);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-348",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If wemo is open, close curtain(1) and curtain(2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-349",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-350",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-351",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-352",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-353",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-354",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-355",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-356",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-357",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-358",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-359",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-360",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-361",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-362",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-363",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-364",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-365",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-366",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
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

            submitTask(executorService, ruleTree, "Rule-367",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if rain(11), close the curtains(1,2)"));
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

            submitTask(executorService, ruleTree, "Rule-368",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("Every 30 minutes check PIR motion sensor21, if 1h since last person detected, close lamp 15&16"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-369",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(WyzeCamera);

            submitTask(executorService, ruleTree, "Rule-370",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If iRobot(7) robot started, then wyze(18) disable motion detection"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-371",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
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

            submitTask(executorService, ruleTree, "Rule-372",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining in winter, the curtain should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(NetatmoWeatherStation);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-373",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If it is raining after 5 pm, the bulb should be open for more light."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(iRobotRoomba);

            // 遍历 actions
            actionDevices.add(MijiaPurifier);

            submitTask(executorService, ruleTree, "Rule-374",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if iRobot Roomba(7) Robot Started ,then Mijia Purifier(22) Start"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-375",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-376",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-377",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-378",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaPurifier);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-379",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If open Purifier(22), close Curtain (1-2)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-380",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-381",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-382",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-383",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-384",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-385",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-386",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) off, when motion sensor(21) detect person, then Lamp(15) switch on."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-387",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If  curtain(2) off, when motion sensor(21) detect person, then Lamp(16) switch on"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightBulb);

            submitTask(executorService, ruleTree, "Rule-388",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When the curtain is closed, please turn on the bulb."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-389",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-390",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-391",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-392",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-393",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-394",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-395",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(YeelightBulb);

            // 遍历 actions
            actionDevices.add(MijiaCurtain1);

            submitTask(executorService, ruleTree, "Rule-396",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("When turn on the bulb, please close the curtain."));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp5);

            submitTask(executorService, ruleTree, "Rule-397",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(1) is open when between 8am and 5pm, turn off Yeelight(15)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-398",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain1);

            // 遍历 actions
            actionDevices.add(MijiaCurtain2);

            submitTask(executorService, ruleTree, "Rule-399",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(1) open ,then Curtain(2) open"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-400",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(YeelightCeilingLamp6);

            submitTask(executorService, ruleTree, "Rule-401",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("If curtain(2) is open when between 8am and 5pm, turn off Yeelight(16)"));
            sleep(500);
        }
        {
            TreeSet<UUID> triggerDevices = new TreeSet<>();
            TreeSet<UUID> actionDevices = new TreeSet<>();

            // 遍历 triggers
            triggerDevices.add(MijiaCurtain2);

            // 遍历 actions
            actionDevices.add(MideaAirConditioner);

            submitTask(executorService, ruleTree, "Rule-402",
                    triggerDevices, actionDevices,
                    new TaskNode.SimpleExecFunc("if Curtain(2) open ,then turn off Midea Air Conditioner(10)"));
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
