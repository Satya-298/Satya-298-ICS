package com.java.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.jdbc.model.Agent;

public interface AgentDao {
	
	List<Agent> showAgentDao() throws ClassNotFoundException, SQLException;
	Agent searchAgentDao(int agentId) throws ClassNotFoundException, SQLException;
	String addAgentDao(Agent agent) throws ClassNotFoundException, SQLException;
	String deleteAgentDao(int agentId) throws ClassNotFoundException, SQLException;
	String updateAgentDao(Agent agentUpdated) throws ClassNotFoundException, SQLException;

}
