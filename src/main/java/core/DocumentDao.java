package core;

import java.util.List;

public interface DocumentDao {
    // Retrieve all documents
    List<Documents> getAllDocuments() throws Exception;

}
