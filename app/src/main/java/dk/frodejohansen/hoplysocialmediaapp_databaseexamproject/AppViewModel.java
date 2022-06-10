package dk.frodejohansen.hoplysocialmediaapp_databaseexamproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {
    MutableLiveData<String> user = new MutableLiveData<String>();

    public void setName(String name){
        user.setValue(name);
    }

    public MutableLiveData<String> getName(){
        return user;
    }

}
