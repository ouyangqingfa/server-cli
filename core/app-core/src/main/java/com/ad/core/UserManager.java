package com.ad.core;

import com.ad.core.auth.IUserManager;

/**
 * @author CoderYoung
 */
public class UserManager implements IUserManager {

    private IUserManager userManager;

    public void setUserManager(IUserManager manager) {
        this.userManager = manager;
    }


}
