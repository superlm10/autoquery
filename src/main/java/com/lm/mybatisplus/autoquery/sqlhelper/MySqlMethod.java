package com.lm.mybatisplus.autoquery.sqlhelper;

/**
 * @author LM
 * @description:
 * @date 2019/3/12 9:47
 */
public enum MySqlMethod {

    /**
     * 自动查询
     */
    AUTO_QUERY("autoQuery", "自动查询", "<script>select %s from %s %s</script>"),
    ;

    /**
     * 方法
     */
    private final String method;
    /**
     * 描述
     */
    private final String desc;

    /**
     * sql
     */
    private final String sql;

    private MySqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return this.method;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSql() {
        return this.sql;
    }

}
