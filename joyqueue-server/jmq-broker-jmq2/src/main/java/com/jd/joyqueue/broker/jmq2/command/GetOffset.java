package com.jd.joyqueue.broker.jmq2.command;

import com.google.common.base.Preconditions;
import com.jd.joyqueue.broker.jmq2.JMQ2CommandType;
import com.jd.joyqueue.broker.jmq2.network.JMQ2Payload;

/**
 * 获取复制偏移量
 */
public class GetOffset extends JMQ2Payload {
    // 起始偏移量
    private long offset;
    // 优化
    private boolean optimized;

    public GetOffset optimized(final boolean optimized) {
        setOptimized(optimized);
        return this;
    }

    public GetOffset offset(final long offset) {
        setOffset(offset);
        return this;
    }

    public long getOffset() {
        return this.offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public boolean isOptimized() {
        return this.optimized;
    }

    public void setOptimized(boolean optimized) {
        this.optimized = optimized;
    }

    @Override
    public void validate() {
        super.validate();
        Preconditions.checkArgument(offset > 0, "offset must be greater than or equal 0");
    }

    @Override
    public int type() {
        return JMQ2CommandType.GET_OFFSET.getCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetOffset{");
        sb.append("Offset=").append(offset);
        sb.append(", optimized=").append(optimized);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        GetOffset getOffset = (GetOffset) o;

        if (offset != getOffset.offset) {
            return false;
        }
        if (optimized != getOffset.optimized) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (offset ^ (offset >>> 32));
        result = 31 * result + (optimized ? 1 : 0);
        return result;
    }
}