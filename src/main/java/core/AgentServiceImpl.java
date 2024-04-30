package core;

public class AgentServiceImpl implements AgentService {
    // This could be a database access object or any persistence mechanism
    private final AgentDAO agentDAO;

    public AgentServiceImpl(AgentDAO agentDAO) {
        this.agentDAO = agentDAO;
    }

    @Override
    public void addAgent(Agent agent) {
        agentDAO.addAgent(agent);
    }

    @Override
    public Agent getAgentById(int agentId) {
        return agentDAO.getAgentById(agentId);
    }

    @Override
    public void updateAgent(Agent agent) {
        agentDAO.updateAgent(agent);
    }

    @Override
    public void deleteAgent(int agentId) {
        agentDAO.deleteAgent(agentId);
    }
}
