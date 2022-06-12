package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.w3c.dom.Text;

import java.util.List;

public class Repository
{
    // This Class will abstract access to multiple data sources.

    private UserDAO userDAO;
    private PostDAO postDAO;
    private ReactionDAO reactionDAO;

    private LiveData<List<User>> allUsers;

    public Repository(Application application)
    {
        RoomDatabase database = RoomDatabase.getDatabase(application);
        userDAO = database.userDAO();
        postDAO = database.postDAO();
        reactionDAO = database.reactionDAO();


        allUsers = userDAO.getAlphabetizedUsers();
    }



    // Returns all users, alphabetized
    LiveData<List<User>> getAllUsers()
    {
        return allUsers;
    }

    // Checks whether a user with the given ID already exists
    public boolean listContainsUserID(String ID)
    {
        if (userDAO.getUserMatchingID(ID).getValue().size() == 1)
            return true;
        return false;
    }

    // TODO: Implement Threading with the DAO statements:

    // Defining the User.Insert SQL statement.
    void insert(User user)
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> {userDAO.insertUser(user);});
    }
}
