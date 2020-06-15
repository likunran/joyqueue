package com.jd.joyqueue.broker.jmq2.command;

/**
 * 权限
 */
public enum Permission {
    /**
     * 无读写权限
     */
    NONE,
    /**
     * 只读
     */
    READ,
    /**
     * 只写
     */
    WRITE,
    /**
     * 读写权限
     */
    FULL;

    public static Permission valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
    }

    /**
     * 包含指定的权限
     *
     * @param permission 权限
     * @return
     */
    public boolean contain(Permission permission) {
        return this == permission || this == FULL && (permission != NONE);
    }


    /**
     * 添加写权限
     *
     * @return 返回修改后的权限
     */
    public Permission addWrite() {
        if (this == null || this == Permission.NONE) {
            return WRITE;
        } else if (this == READ) {
            return FULL;
        }
        return this;
    }

    /**
     * 删除写权限
     *
     * @return 返回修改后的权限
     */
    public Permission removeWrite() {
        if (this == null) {
            return NONE;
        }
        if (this == Permission.NONE || this == READ) {
            return this;
        } else if (this == WRITE) {
            return NONE;
        }
        return READ;
    }

    /**
     * 添加读权限
     *
     * @return 返回修改后的权限
     */
    public Permission addRead() {
        if (this == null || this == Permission.NONE) {
            return READ;
        } else if (this == WRITE) {
            return FULL;
        }
        return this;
    }

    /**
     * 删除读权限
     *
     * @return 返回修改后的权限
     */
    public Permission removeRead() {
        if (this == null) {
            return NONE;
        }
        if (this == Permission.NONE || this == WRITE) {
            return this;
        } else if (this == READ) {
            return NONE;
        }
        return WRITE;
    }

    public boolean canRead(){
        return this.contain(Permission.READ);
    }

    public boolean canWrite(){
        return this.contain(Permission.WRITE);
    }

}