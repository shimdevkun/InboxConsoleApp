package org.shimabuku;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.shimabuku.model.Email;

public class EmailTest {

    // setTitle()
    @Test(expected = NullPointerException.class)
    public void setTitle_Null_ThrowsException() {
        Email email = new Email();
        email.setTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setTitle_EmptyString_ThrowsException() {
        Email email = new Email();
        email.setTitle("");
    }

    @Test
    public void setTitle_ValidTitle_TitleIsSet() {
        Email email = new Email();
        String title = "First Email!";
        email.setTitle(title);

        Assert.assertEquals(title, email.getTitle());
    }

    // setMessage()
    @Test(expected = NullPointerException.class)
    public void setMessage_Null_ThrowsException() {
        Email email = new Email();
        email.setMessage(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMessage_EmptyString_ThrowsException() {
        Email email = new Email();
        email.setMessage("");
    }

    @Test
    public void setMessage_ValidTitle_TitleIsSet() {
        Email email = new Email();
        String message = "Hello, How have you been doing? c:";
        email.setMessage(message);

        Assert.assertEquals(message, email.getMessage());
    }
}
