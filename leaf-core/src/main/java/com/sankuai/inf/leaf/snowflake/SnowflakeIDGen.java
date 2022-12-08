package com.sankuai.inf.leaf.snowflake;

import com.sankuai.inf.leaf.IDGen;

/**
* @description: TODO 雪花算法
* @author http://gaozhi.online
* @date 2022/11/5 14:11
* @version 1.0
*/
public interface SnowflakeIDGen extends IDGen {
    /**
    * @description: 修改雪花算法的WorkId字段
    * @param workerId
    * @author http://gaozhi.online
    * @date: 2022/11/5 14:12
    */
    void updateWorkerId(long workerId);
}
