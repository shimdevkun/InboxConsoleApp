package org.shimabuku;

import org.shimabuku.controller.EmailController;
import org.shimabuku.controller.UserController;
import org.shimabuku.model.Email;
import org.shimabuku.model.User;
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

        System.out.println("Welcome to the inbox app!");
        System.out.println("Please select a user: ");
        System.out.println();

        userView.logIn(4);
        System.out.println();
        EmailView emailView = new EmailView(inboxData, 4);
        emailView.printActions();
    }
}
