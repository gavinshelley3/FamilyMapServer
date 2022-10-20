package Service;

import Request.ClearRequest;
import Result.ClearResult;

public class ClearService {
    //Deletes ALL data from the database, including user, authtoken, person, and event data
    /**
     * Clears all data from the database
     * @param request
     * @return success
     */

    private String message;
    private Boolean success;
    public ClearService() {

    }
    public Boolean clear(ClearRequest request) {
        // Clear all data from database
        // Return success
        return success;
    }



}
