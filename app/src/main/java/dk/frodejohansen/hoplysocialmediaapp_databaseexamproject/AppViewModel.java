package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.frodejohansen.hoplysocialmediaapp_databaseexamproject.Database.Repository;

public class AppViewModel extends AndroidViewModel {
    MutableLiveData<String> user = new MutableLiveData<String>();

    Repository repository;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void setName(String name){
        user.setValue(name);
    }

    public MutableLiveData<String> getName(){
        return user;
    }


}
