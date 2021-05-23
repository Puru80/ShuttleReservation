package com.mainpage.shuttlereservation;

public class AppBeanFactory
{
    private User user;

    public User getUser()
    {
        if(user==null)
        {
            this.user = new User();
        }

        return this.user;
    }

}
