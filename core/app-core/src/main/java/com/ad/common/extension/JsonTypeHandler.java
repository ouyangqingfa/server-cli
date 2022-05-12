package com.ad.common.extension;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CoderYoung
 */
public class JsonTypeHandler extends BaseTypeHandler<JSON> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSON parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, this.toJson(parameter));
    }

    @Override
    public JSON getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return this.parse(json);
    }

    @Override
    public JSON getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return this.parse(json);
    }

    @Override
    public JSON getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return this.parse(json);
    }

    protected JSON parse(String json) {
        if (StringUtils.isNotBlank(json)) {
            if (json.trim().startsWith("[")) {
                return JSONArray.parseArray(json);
            } else {
                return JSONObject.parseObject(json);
            }
        }
        return null;
    }

    protected String toJson(JSON obj) {
        return obj != null ? obj.toJSONString() : "";
    }

}
