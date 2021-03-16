package com.sjzb.demo.service.StatisticsEntity;

import com.sjzb.demo.Repository.Node.IndicatorsNodeRepository;
import com.sjzb.demo.model.IndicatorsNodeEntity;
import com.sjzb.demo.util.lxTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author: chenlx
 * @Date: 2021-03-11 14:21:22
 * @Params: null
 * @Return
 * @Description: 通用统计访问次数功能-服务层
 */
@Service
public class GeneralRedisServiceImpl {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private IndicatorsNodeRepository idRe;

    private lxTool lxtool = new lxTool();

    private Map<String,Integer> redisData ;

//    public void redisMissionAllocation(String missionName,String[] args){
//        Map<String, Integer> record = getRedisValue();
//        if(missionName.equals("AddRedis")){
//            InsertOrUpdateRequestNodeCount(args[0]);
//        }else if(missionName.equals("InsertToNeo4j")){
//            InsertOrUpdateRequestNodeCountToNeo4j();
//        }
//
//
//    }

    public Map<String, Integer> getRedisValue() {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisData = (Map<String, Integer>) redisTemplate.opsForValue().get("IndicatorsEntityCountMap");
        return redisData;
    }

    public void setRedisValue(Map<String,Integer> v){
        redisData = v;
    }

    /**
     * @Author: chenlx
     * @Date: 2021-03-12 11:01:14
     * @Params: 指标节点的中文名
     * @Return
     * @Description: 访问节点时，记录或更新访问的次数（有效期1小时）
     */
    public Map<String, Integer> InsertOrUpdateRequestNodeCount(String IndicatorsName) {
        // 设置redisTemplate对象key的序列化方式
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 从redis缓存中获取总人数
//        Long allStudentCount = (Long) redisTemplate.opsForValue().get("allStudentCount");
//        Map<String, Integer> record = (Map<String, Integer>) redisTemplate.opsForValue().get("IndicatorsEntityCountMap");
        Map<String, Integer> record = getRedisValue();
        Integer count;
        if (record == null) {
            count = 0;
            record = new HashMap<>();
        } else {
            count = record.get(IndicatorsName);
        }
        if (count == null || count.equals(0)) count = 0;
        record.put(IndicatorsName, ++count);
        System.out.println("recordCount:");
        System.out.println(record);
        redisTemplate.opsForValue().set("IndicatorsEntityCountMap", record, 65, TimeUnit.MINUTES);

        return record;
    }

    public void InsertOrUpdateRequestNodeCountToNeo4j() {
        IndicatorsNodeEntity idnode;
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
        System.out.println("开始处理缓存数据...");
        Map<String, Integer> records = getRedisValue();
        // 设置redisTemplate对象key的序列化方式
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 从redis缓存中获取总人数
//        Long allStudentCount = (Long) redisTemplate.opsForValue().get("allStudentCount");
        if (records == null) {
            System.out.println("暂无缓存");
            return;
        }
        for (String redisName : records.keySet()) {
            idnode = idRe.findIndicatorsNodeEntityByNm(redisName).get(0);
            if (idnode.getCnt() == null) {
                idRe.setNewCount(redisName, records.get(redisName));
            }

        }
        lxtool.soutLog("Redis处理", "/", "Redis缓存数据已写入数据库");
//        清除redis的信息
        redisTemplate.delete("IndicatorsEntityCountMap");
        lxtool.soutLog("Redis处理", "/", "Redis缓存已清除");
    }

    public void goRun(Integer runningIntervalSeconds) {
        runningIntervalSeconds = runningIntervalSeconds * 2000; //换算为分钟
        TimerTask timerTask = new TimerTask() {
            public void run() {
//                System.out.println("任务启动" + new Date());
                InsertOrUpdateRequestNodeCountToNeo4j();
            }
        };
        Timer timer = new Timer();
        //每10秒执行一次
        timer.schedule(timerTask, 10, runningIntervalSeconds);
    }

}
