package core;

public class AgentProxy implements AgentService {
    private final AgentService agentService;

    public AgentProxy(AgentService agentService) {
        this.agentService = agentService;
    }

    @Override
    public void addAgent(Agent agent) {
        System.out.println("Logging: Adding agent with ID: " + agent.getAgentId());
        agentService.addAgent(agent); // Forward to the real implementation
    }

    @Override
    public Agent getAgentById(int agentId) {
        System.out.println("Logging: Getting agent by ID: " + agentId);
        return agentService.getAgentById(agentId);
    }

    @Override
    public void updateAgent(Agent agent) {
        System.out.println("Logging: Updating agent with ID: " + agent.getAgentId());
        agentService.updateAgent(agent);
    }

    @Override
    public void deleteAgent(int agentId) {
        System.out.println("Logging: Deleting agent with ID: " + agentId);
        agentService.deleteAgent(agentId);
    }
}
