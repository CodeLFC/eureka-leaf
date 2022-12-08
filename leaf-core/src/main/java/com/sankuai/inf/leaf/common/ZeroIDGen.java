package com.sankuai.inf.leaf.common;

import com.sankuai.inf.leaf.snowflake.SnowflakeIDGen;

public class ZeroIDGen implements SnowflakeIDGen {
    @Override
    public Result get(String key) {
        return new Result(0, Status.SUCCESS);
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public void updateWorkerId(long workerId) {

    }
}
