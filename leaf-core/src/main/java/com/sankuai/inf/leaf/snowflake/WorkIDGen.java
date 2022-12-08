package com.sankuai.inf.leaf.snowflake;

/**
 * @author http://gaozhi.online
 * @version 1.0
 * @description: TODO 获取workID
 * @date 2022/11/5 10:38
 */
public interface WorkIDGen {
    /**
     * @return long 返回本机ID
     * @description: 获取本机服务ID
     * @author http://gaozhi.online
     * @date: 2022/11/5 10:51
     */
    long get();
}
