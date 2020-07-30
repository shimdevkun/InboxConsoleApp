package org.shimabuku;

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
        boolean running = true;
        while (running) {
            UserView userView = new UserView(inboxData);
            userView.welcomeUser();
            running = userView.runInbox();

            if (running) {
                System.out.println();
                EmailView emailView = new EmailView(inboxData, userView.getUserId());
                emailView.printNewEmails();
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
}
