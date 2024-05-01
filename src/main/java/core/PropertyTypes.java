package core;

public class PropertyTypes {
    private int typeId; // Primary key
    private String typeName; // Nullable field, corresponds to VARCHAR(45)

    // Default constructor
    public PropertyTypes() {}

    // Parameterized constructor for initialization
    public PropertyTypes(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    // Getters and setters for encapsulation
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}