package com.mainpage.shuttlereservation.domains;

import com.mainpage.shuttlereservation.domains.models.User;
import com.mainpage.shuttlereservation.domains.managers.UserManager;

public class AppBeanFactory
{
    private User user;
    private UserManager userManager;

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
}
