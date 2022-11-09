package Service;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.EventDao;
import DataAccess.UserDao;
import Model.User;
import Request.FillRequest;
import Result.FillResult;

import java.sql.Connection;
import java.sql.SQLException;

public class FillService {
    //Populates the server's database with generated data for the specified username. The required "username" parameter must be a user already registered with the server. If there is any data in the database already associated with the given username, it is deleted.
    //The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
    //More details can be found in the earlier section titled “Family History Information Generation”
    /**
     * Fills the database with generated data for the specified username
     * @param request
     * @return FillResult
     */
    // Fill database with data for the specified user's ancestors
    // Return success

    private String message;
    private Boolean success;
    private FillResult result;
    public FillService() {

    }
    public FillResult fill(FillRequest request) {
        Database db = new Database();
        int generations = 4;

        try {
            db.openConnection();
            Connection conn = db.getConnection();
            UserDao userDao = new UserDao(conn);
            User user = userDao.find(request.getUsername());
            clear(request, conn);
            FamilyTreeGenerator familyTreeGenerator = new FamilyTreeGenerator();
            familyTreeGenerator.generateFamilyTree(request.getUsername(), user.getGender(), generations, user, conn);

            db.closeConnection(true);
            FillResult result = new FillResult();
            result.setMessage("Successfully added " + familyTreeGenerator.getPeopleCount() + " persons and " + familyTreeGenerator.getEventCount() + " events to the database.");
            result.setSuccess(true);
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            db.closeConnection(false);
            FillResult result = new FillResult("Error: " + e.getMessage(), false);
            return result;
        }
    }

    public FillResult fillGen(FillRequest request) {
        FillResult fillResult = new FillResult();
        return fillResult;
    }

    public void clear(FillRequest request, Connection conn) throws DataAccessException, SQLException {

    }

}
