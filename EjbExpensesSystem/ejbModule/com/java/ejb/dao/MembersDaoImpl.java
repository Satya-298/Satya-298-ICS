package com.java.ejb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.ejb.model.GroupMembers;
import com.java.ejb.model.Groups;
import com.java.ejb.model.Users;
import com.java.ejb.util.ConnectionHelper;

public class MembersDaoImpl implements MembersDao{

    // 1. Get admin user by email and password
	@Override
    public Users getAdminByCredentials(String email, String password) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionHelper.getConnection();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Users user = new Users();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        }
        return null;
    }

    // 2. Check if user is admin of the group
	@Override
    public boolean isAdminOfGroup(int adminId, int groupId) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionHelper.getConnection();
        String sql = "SELECT * FROM group_s WHERE id = ? AND adminId = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, groupId);
        pst.setInt(2, adminId);
        ResultSet rs = pst.executeQuery();

        return rs.next();
    }

    // 3. Add a member to the group
	@Override
    public String addMemberToGroup(GroupMembers members) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionHelper.getConnection();
        String sql = "INSERT INTO group_members (group_id, user_id, advance) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, members.getGroups().getId());
        pst.setInt(2, members.getUsers().getId());
        pst.setInt(3, members.getAdvance());
        pst.executeUpdate();
        return "Member added to group successfully.";
    }
}
