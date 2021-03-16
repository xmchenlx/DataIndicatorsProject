package com.sjzb.demo.service;

/**
 * @ProgramName: demo_youdao
 * @Author: xmche
 * @Date: 2021-03-12 14:16:53
 * @Param: 运行间隔（分钟）
 * @Description:
 */
public class TaskTimerServiceImpl {
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//    @Autowired
//    private IndicatorsNodeRepository idRe;
//
//    private lxTool lxtool = new lxTool();
//
//    public void goRun(Integer runningIntervalSeconds) {
//        runningIntervalSeconds = runningIntervalSeconds * 1000; //换算为分钟
//        TimerTask timerTask = new TimerTask() {
//            public void run() {
////                System.out.println("任务启动" + new Date());
//                processRedisData();
//            }
//        };
//        Timer timer = new Timer();
//        //每10秒执行一次
//        timer.schedule(timerTask, 10, runningIntervalSeconds);
//    }

    public void processRedisData() {
//        IndicatorsNodeEntity idnode;
//        System.out.println("开始处理缓存数据...");
//        Map<String, Integer> records = new HashMap<>();
//        // 设置redisTemplate对象key的序列化方式
////        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        // 从redis缓存中获取总人数
////        Long allStudentCount = (Long) redisTemplate.opsForValue().get("allStudentCount");
//        try {
//            records = (Map<String, Integer>) redisTemplate.opsForValue().get("IndicatorsEntityCountMap");
//            if (records == null) {
//                System.out.println("缓存为空。");
//                return;
//            }
//        } catch (Exception e) {
//            System.out.println("缓存为空-trycatch。");
//            e.printStackTrace();
//            return;
//        }
//        if (records == null) {
//            System.out.println("暂无缓存");
//            return;
//        }
//        for (String redisName : records.keySet()) {
//            idnode = idRe.findIndicatorsNodeEntityByNm(redisName).get(0);
//            if (idnode.getCnt() == null) {
//                idRe.setNewCount(redisName, records.get(redisName));
//            }
//
//        }
//        lxtool.soutLog("Redis处理", "/", "Redis缓存数据已写入数据库");
////        清除redis的信息
//        redisTemplate.delete("IndicatorsEntityCountMap");
//        lxtool.soutLog("Redis处理", "/", "Redis缓存已清除");
    }

}
