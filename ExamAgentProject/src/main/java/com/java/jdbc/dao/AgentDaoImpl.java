package com.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.jdbc.model.Agent;
import com.java.jdbc.model.Gender;
import com.java.jdbc.util.ConnectionHelper;

public class AgentDaoImpl implements AgentDao{
	
	Connection connection;
	PreparedStatement psmt;

	@Override
	public List<Agent> showAgentDao() throws ClassNotFoundException, SQLException {
			List<Agent> agentList = new ArrayList<Agent>();
			Agent agent = null;
			connection = ConnectionHelper.getConnection();
			String cmd = "select * from agent";
			psmt = connection.prepareStatement(cmd);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				agent = new Agent();
				agent.setAgentId(rs.getInt("AgentId"));
				agent.setName(rs.getString("name"));
				agent.setCity(rs.getString("city"));
				agent.setGender(Gender.valueOf(rs.getString("gender")));
				agent.setMaritalstatus(rs.getInt("Maritalstatus"));
				agent.setPremium(rs.getDouble("premium"));
				agentList.add(agent);
			}
			return agentList;
		}

	@Override
	public Agent searchAgentDao(int agentId) throws ClassNotFoundException, SQLException {
		Agent agent = null;
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from agent where empno = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setInt(1, agentId);
		ResultSet rs = psmt.executeQuery();
		if(rs.next()) {
			agent = new Agent();
			agent.setAgentId(rs.getInt("agentId"));
			agent.setName(rs.getString("name"));
			agent.setCity(rs.getString("city"));
			agent.setGender(Gender.valueOf(rs.getString("gender")));
			agent.setMaritalstatus(rs.getInt("maritalstatus"));
			agent.setPremium(rs.getDouble("premium"));
		}
		return agent;
	}
	@Override
	public String addAgentDao(Agent agent) throws ClassNotFoundException, SQLException {
		String cmd = "Insert into agent(AgentId,name,city,gender,Maritalstatus,premium) values(?,?,?,?,?,?)";
		connection = ConnectionHelper.getConnection();
		psmt = connection.prepareStatement(cmd);
		psmt.setInt(1, agent.getAgentId());
		psmt.setString(2, agent.getName());
		psmt.setString(4, agent.getCity());
		psmt.setString(3, agent.getGender().toString());
		psmt.setInt(5, agent.getMaritalstatus());
		psmt.setDouble(6, agent.getPremium());
		psmt.executeUpdate();
		return "agent Record Inserted...";
	}

	@Override
	public String deleteAgentDao(int agentId) throws ClassNotFoundException, SQLException {
		Agent agentFound = searchAgentDao(agentId);;
		if (agentFound !=null) {
			String cmd = "Delete from agent where AgentId = ?";
			connection = ConnectionHelper.getConnection();
			psmt = connection.prepareStatement(cmd);
			psmt.setInt(1, agentId);
			psmt.executeUpdate();
			return "agent Record Deleted...";
		}
		return "agent Record Not Found...";
	}


	@Override
	public String updateAgentDao(Agent agentUpdated) throws ClassNotFoundException, SQLException {
		Agent agentFound = searchAgentDao(agentUpdated.getAgentId());
		if (agentFound==null) {
			return "agent Record Not Found...";
		}
		String cmd = "Update agent set Name = ?, city = ?, Gender = ?, Maritalstatus = ?, premium = ? Where AgentId = ?";
		connection = ConnectionHelper.getConnection();
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, agentUpdated.getName());
		psmt.setString(3, agentUpdated.getCity());
		psmt.setString(2, agentUpdated.getGender().toString());
		psmt.setInt(4, agentUpdated.getMaritalstatus());
		psmt.setDouble(5, agentUpdated.getPremium());
		psmt.setInt(6, agentUpdated.getAgentId());
		psmt.executeUpdate();
		return "agent Record Updated...";
	}

}
