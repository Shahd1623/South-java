
package core;

import java.util.List;

public interface OfferDao {
    // Retrieve all offers, potentially throwing an exception
    List<Offer> getAllOffers() throws Exception;
}
