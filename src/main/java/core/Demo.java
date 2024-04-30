// Example usage
package core;

public class Demo {
    public static void main(String[] args) {
        AgentDao agentDAO = new AgentDao(); // Assuming you have this
        AgentService agentService = new AgentServiceImpl(agentDAO);
        AgentService agentProxy = new AgentProxy(agentService);

        Agent newAgent = new Agent(1, "John Doe", "123-456-7890", "johndoe@example.com");

        agentProxy.addAgent(newAgent); // This will log the operation
    }
}
