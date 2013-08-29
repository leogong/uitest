package com.qunar.autotest.uitest.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: lin.gong
 * Date: 12-8-19
 * Time: 下午2:02
 */
public class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setAwayFromLeaveURL(rs.getInt("awayFromLeaveURL"));
        user.setAwayFromSign(rs.getInt("awayFromSign"));
        user.setEmail(rs.getString("email"));
        user.setLeaveURL(rs.getBoolean("isLeaveURL"));
        user.setSign(rs.getBoolean("isSign"));
        user.setType(rs.getString("type"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setTotalMessage(rs.getInt("totalMessage"));
        return user;
    }
}
