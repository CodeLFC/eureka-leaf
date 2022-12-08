package com.sankuai.inf.leaf.server.service;

import com.sankuai.inf.leaf.common.PropertyFactory;
import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.ZeroIDGen;
import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.exception.InitException;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGen;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGenImpl;
import com.sankuai.inf.leaf.snowflake.WorkIDGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service("SnowflakeService")
public class SnowflakeService implements BeanFactoryAware {

    private Logger logger = LoggerFactory.getLogger(SnowflakeService.class);

    private SnowflakeIDGen idGen;
    private WorkIDGen workIDGen;

    public SnowflakeService() {

    }

    private void init(BeanFactory beanFactory) throws InitException {
        Properties properties = PropertyFactory.getProperties();
        //是否启动
        boolean flag = Boolean.parseBoolean(properties.getProperty(Constants.LEAF_SNOWFLAKE_ENABLE, "false"));
        if (flag) {
            //workID提供者
            String workIDSupplier = properties.getProperty(Constants.LEAF_SNOWFLAKE_WORKER_ID_SUPPLIER);
            workIDGen = (WorkIDGen) beanFactory.getBean(workIDSupplier);
            idGen = new SnowflakeIDGenImpl(workIDGen);
            if (idGen.init()) {
                logger.info("Snowflake Service Init Successfully");
            } else {
                throw new InitException("Snowflake Service Init Fail");
            }
        } else {
            idGen = new ZeroIDGen();
            logger.info("Zero ID Gen Service Init Successfully");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        try {
            init(beanFactory);
        } catch (InitException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public void updateWorkerId() {
        idGen.updateWorkerId(workIDGen.get());
    }
}
