
package core;

public interface AgentService {
    void addAgent(Agent agent);
    Agent getAgentById(int agentId);
    void updateAgent(Agent agent);
    void deleteAgent(int agentId);
}
