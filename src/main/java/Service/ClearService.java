package Service;

import DataAccess.*;
import Request.ClearRequest;
import Result.ClearResult;

public class ClearService {
    //Deletes ALL data from the database, including user,
    // authtoken, person, and event data


    public ClearService() {

    }

    public ClearResult clear(ClearRequest request) throws DataAccessException {
        Database db = new Database();
        ClearResult clearResult = new ClearResult();
        try {
            db.openConnection();
            UserDao userDao = new UserDao(db.getConnection());
            PersonDao personDao = new PersonDao(db.getConnection());
            EventDao eventDao = new EventDao(db.getConnection());
            userDao.clear();
            personDao.clear();
            eventDao.clear();
            db.closeConnection(true);
            clearResult.setMessage("Clear succeeded.");
            clearResult.setSuccess(true);
        } catch (DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
            clearResult.setMessage("Error: " + e.getMessage());
            clearResult.setSuccess(false);
        }
        return clearResult;
    }

}
