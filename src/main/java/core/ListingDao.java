package core;

import java.util.List;

public interface ListingDao {
    // Retrieve all listings, potentially throwing an exception
    List<Listing> getAllListings() throws Exception;
}
