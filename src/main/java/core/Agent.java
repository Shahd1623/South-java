package core;

public class Agent{
    private int agentId; // Primary key
    private String agentName; // Can be nullable
    private String contactInformation; // Nullable
    private String email; // Nullable

    // Default constructor
    public Agent() {}

    // Parameterized constructor
    public Agent(int agentId, String agentName, String contactInformation, String email) {
        this.agentId = agentId;
        this.agentName = agentName;
        this.contactInformation = contactInformation;
        this.email = email;
    }

    // Getters and setters for all fields
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
