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
        while (true) {
            UserView userView = new UserView(inboxData);
            userView.welcomeUser();

            System.out.println();
            EmailView emailView = new EmailView(inboxData, 4);
            while (emailView.isLoggedIn()) {
                emailView.printActions();
                System.out.println();
            }
            System.out.println();
            System.out.println();
            System.out.println("---------------");
            System.out.println();
            System.out.println();
        }
    }
}
