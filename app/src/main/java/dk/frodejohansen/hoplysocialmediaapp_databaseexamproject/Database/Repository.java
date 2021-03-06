package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class Repository
{
    // This Class will abstract access to multiple data sources.

    private UserDAO userDAO;
    private PostDAO postDAO;
    private ReactionDAO reactionDAO;
    private CommentDAO commentDAO;

    public RoomDatabase database;



    private List<User> allUsers;
    private List<String> userIds;
    private List<String> passwordFromID;

    public Repository(Application application)
    {
        database = RoomDatabase.getDatabase(application);
        userDAO = database.userDAO();
        postDAO = database.postDAO();
        reactionDAO = database.reactionDAO();
        commentDAO = database.commentDAO();


        RoomDatabase.databaseWriteExecutor.execute(() -> { allUsers = userDAO.getAlphabetizedUsers(); });
        RoomDatabase.databaseWriteExecutor.execute(() -> { userIds = userDAO.getUserIds(); });
    }
    //--------------------
    // User Methods
    //--------------------

    public List<User> getAlphabetizedUsers()
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> { this.allUsers = userDAO.getAlphabetizedUsers(); });

        return this.allUsers;
    }

    public List<String> getUserIds()
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> { this.userIds = userDAO.getUserIds(); });

        return this.userIds;
    }

    // Defining the User.Insert SQL statement.
    public void insert(User user)
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> {userDAO.insertUser(user);});
    }

    // insert post into database.
    public void insert(Post post)
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> {postDAO.insertPost(post);});
    }

    public boolean usernameExists(String username)
    {
        RoomDatabase.databaseWriteExecutor.execute(() -> { this.userIds = userDAO.getUserIds(); });

        if(userIds.contains(username))
            return true;
        return false;
    }
    public boolean userExistsAndMatches(String userid, String hashedPassword)
    {
        for (User user:allUsers)
        {
            if (Objects.equals(user.getUser_id(), userid) && Objects.equals(user.getPassword(), hashedPassword))
                return true;
        }

        return false;
    }

    //--------------------
    // Reaction Methods
    //--------------------

    // Returns true if the given user has already reacted to the chosen post.
    public boolean hasUserReacted(Post post, User user)
    {
        // Get the list of all the postIds that the user has reacted to.
        List<Integer> userReactionPostIDs = reactionDAO.getUserReactionPostIDs(user.getUser_id()).getValue();

        if (userReactionPostIDs.contains(post.getId()))
            return true;
        return false;
    }

    // Checks whether a user with the given ID already exists
    public boolean listContainsUserID(String ID)
    {
        if (userDAO.getUserMatchingID(ID).contains(ID))
        {
            return true;
        }
        return false;
    }

    // TODO: Implement Threading with the DAO statements:


}
