package com.fabish.dao;

import com.fabish.models.Member;
import com.fabish.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    // Add a new member
    public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO members (name, email, phone, membership_type, join_date, age) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPhone());
            stmt.setString(4, member.getMembershipType());
            stmt.setDate(5, Date.valueOf(member.getJoinDate()));
            stmt.setInt(6, member.getAge()); // Add age parameter
            stmt.executeUpdate();
        }
    }

    // Get all members
    public List<Member> getAllMembers() throws SQLException {
        String query = "SELECT * FROM members";
        List<Member> members = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setMembershipType(rs.getString("membership_type"));
                member.setJoinDate(rs.getDate("join_date").toString());
                member.setAge(rs.getInt("age")); // Add age
                members.add(member);
            }
        }
        return members;
    }

    // Update an existing member
    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE members SET name = ?, email = ?, phone = ?, membership_type = ?, join_date = ?, age = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getPhone());
            stmt.setString(4, member.getMembershipType());
            stmt.setDate(5, Date.valueOf(member.getJoinDate()));
            stmt.setInt(6, member.getAge()); // Add age parameter
            stmt.setInt(7, member.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Member updated successfully!");
            } else {
                System.out.println("No member found with the given ID.");
            }
        }
    }

    // Delete a member by ID
    public void deleteMember(int id) throws SQLException {
        String query = "DELETE FROM members WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Member deleted successfully!");
            } else {
                System.out.println("No member found with the given ID.");
            }
        }
    }
}
