package com.jd.joyqueue.broker.jmq2.command;

import com.jd.joyqueue.broker.jmq2.JMQ2CommandType;
import com.jd.joyqueue.broker.jmq2.network.JMQ2Payload;

import java.util.Arrays;
import java.util.List;

/**
 * 获取集群应答
 */
public class GetClusterAck extends JMQ2Payload {
    // 集群应答
    protected List<BrokerCluster> clusters;
    // 客户端所在数据中心
    protected byte dataCenter;
    // 单次调用，生产消息的消息体合计最大大小
    protected int maxSize;
    // 更新集群间隔时间
    protected int interval;
    // TopicConfig序列化字符串
    protected String allTopicConfigStrings;

    protected byte[] cacheBody;

    @Override
    public int type() {
        return JMQ2CommandType.GET_CLUSTER_ACK.getCode();
    }

    public GetClusterAck dataCenter(byte dataCenter) {
        setDataCenter(dataCenter);
        return this;
    }

    public GetClusterAck clusters(final List<BrokerCluster> clusters) {
        setClusters(clusters);
        return this;
    }

    public GetClusterAck clusters(final BrokerCluster... clusters) {
        if (clusters != null) {
            setClusters(Arrays.asList(clusters));
        }
        return this;
    }

    public GetClusterAck interval(final int interval) {
        setInterval(interval);
        return this;
    }

    public GetClusterAck maxSize(final int maxSize) {
        setMaxSize(maxSize);
        return this;
    }

    public GetClusterAck allTopicConfigStrings(final String allTopicConfigStrings) {
        setAllTopicConfigStrings(allTopicConfigStrings);
        return this;
    }

    public List<BrokerCluster> getClusters() {
        return this.clusters;
    }

    public void setClusters(List<BrokerCluster> clusters) {
        this.clusters = clusters;
    }

    public byte getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(byte dataCenter) {
        this.dataCenter = dataCenter;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getAllTopicConfigStrings() {
        return allTopicConfigStrings;
    }

    public void setAllTopicConfigStrings(String allTopicConfigStrings) {
        this.allTopicConfigStrings = allTopicConfigStrings;
    }


    public byte[] getCacheBody() {
        return cacheBody;
    }

    public void setCacheBody(byte[] cacheBody) {
        this.cacheBody = cacheBody;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetClusterAck{");
        sb.append("clusters=").append(clusters);
        sb.append(", dataCenter=").append(dataCenter);
        sb.append(", maxSize=").append(maxSize);
        sb.append(", interval=").append(interval);
        sb.append(", allTopicConfigStrings=").append(allTopicConfigStrings);
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

        GetClusterAck that = (GetClusterAck) o;

        if (dataCenter != that.dataCenter) {
            return false;
        }
        if (interval != that.interval) {
            return false;
        }
        if (maxSize != that.maxSize) {
            return false;
        }
        if (clusters != null ? !clusters.equals(that.clusters) : that.clusters != null) {
            return false;
        }
        if (!allTopicConfigStrings.equals(that.allTopicConfigStrings)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (clusters != null ? clusters.hashCode() : 0);
        result = 31 * result + (int) dataCenter;
        result = 31 * result + maxSize;
        result = 31 * result + interval;
        return result;
    }
}