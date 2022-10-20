package Service;

import Request.FillRequest;
import Result.FillResult;

public class FillService {
    //Populates the server's database with generated data for the specified username. The required "username" parameter must be a user already registered with the server. If there is any data in the database already associated with the given username, it is deleted.
    //The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
    //More details can be found in the earlier section titled “Family History Information Generation”
    /**
     * Fills the database with generated data for the specified username
     * @param request
     * @return FillResult
     */
    private String message;
    private Boolean success;
    private FillResult result;
    public FillService() {

    }
    public Boolean fill(FillRequest request) {
        // Fill database with data for the specified user's ancestors
        // Return success
        return success;
    }



}
