package org.shimabuku;

import org.shimabuku.service.*;
import org.shimabuku.view.EmailView;
import org.shimabuku.view.UserView;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final InMemoryInboxData inboxData = new InMemoryInboxData();

    public static void main( String[] args )
    {
        UserView userView = new UserView(inboxData);
        userView.welcomeUser();

        int userId = 0;
        while (userView.isValidUser(userId)) {
            userView.printUserNotFoundError();
            userId = 1;
        }
        userView.logIn(4);
        System.out.println();
        EmailView emailView = new EmailView(inboxData, 4);
        emailView.printActions();
    }
}
