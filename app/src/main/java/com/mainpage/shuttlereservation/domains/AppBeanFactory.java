package com.mainpage.shuttlereservation.domains;

import com.mainpage.shuttlereservation.domains.managers.DataManager;
import com.mainpage.shuttlereservation.domains.models.response.User;
import com.mainpage.shuttlereservation.domains.managers.UserManager;

public class AppBeanFactory
{
    private User user;
    private UserManager userManager;
    private DataManager dataManager;

    public User getUser()
    {
        if(user==null)
        {
            this.user = new User();
        }

        return this.user;
    }

    public UserManager getUserManager() {
        if(this.userManager==null){
            this.userManager = new UserManager();
        }

        return this.userManager;
    }

    public DataManager getDataManager() {
        if(this.dataManager==null){
            this.dataManager = new DataManager();
        }
        return this.dataManager;
    }
}
