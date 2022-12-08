package com.sankuai.inf.leaf.server.service;

import com.sankuai.inf.leaf.snowflake.WorkIDGen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

/**
 * @author http://gaozhi.online
 * @version 1.0
 * @description: TODO Eureka服务ID获取
 * @date 2022/11/5 11:00
 */
@Service("IPWorkerIDGen")
@Slf4j
public class IPWorkerIDGen implements WorkIDGen {
    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient;
    @Value("${spring.application.name}")
    private String serverId;
    @Value("${server.port}")
    private int port;
    @Value("${spring.cloud.client.ip-address}")
    private String ip;

    @Override
    public long get() {
        int workerID = 0;
        List<ServiceInstance> serviceInstances = eurekaDiscoveryClient.getInstances(serverId);
        serviceInstances.sort(Comparator.comparing(o -> (o.getHost() + o.getPort())));
        for (ServiceInstance serviceInstance : serviceInstances) {
            String curIp = serviceInstance.getHost();
            int curPort = serviceInstance.getPort();
            if (port == curPort && ip.equals(curIp)) {
                workerID++;
            }
            log.debug("比对：{},当前：{} => worker-id:{}", ip + ":" + port, curIp + ":" + curPort, workerID);
        }
        return workerID;
    }
}
