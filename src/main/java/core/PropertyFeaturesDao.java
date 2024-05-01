package core;

import java.util.List;

public interface PropertyFeaturesDao {
    List<PropertyFeatures> getAllPropertyFeatures() throws Exception;
    PropertyFeatures getPropertyFeaturesById(int featureId) throws Exception;
    void addPropertyFeatures(PropertyFeatures propertyFeatures) throws Exception;
    void updatePropertyFeatures(PropertyFeatures propertyFeatures) throws Exception;
    void deletePropertyFeatures(int featureId) throws Exception;
}
