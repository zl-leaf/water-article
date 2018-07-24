package me.yipzale.water.article.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

    private AtomicInteger atomic = new AtomicInteger(0);

    private int slaveCount = 0;

    @Override
    protected Object determineCurrentLookupKey() {
        String lookupKey = DbType.MASTER.toString();
        DbType dbType = DbContextHolder.getDbType();
        if (dbType == DbType.SLAVE) {
            int index = atomic.getAndUpdate(new IntUnaryOperator() {
                @Override
                public int applyAsInt(int operand) {
                    return operand + 1 % slaveCount;
                }
            });
            lookupKey = DbType.SLAVE + String.valueOf(index);
        }
        return lookupKey;
    }

    public void setSlaveCount(int slaveCount) {
        this.slaveCount = slaveCount;
    }
}
