package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.LoginController;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoginViewTest {

    private LoginView loginView;
    private Prompt prompt;
    private LoginController loginController;
    private PrintStream out;

    @Before
    public void setup() {

        loginView = new LoginView();
        prompt = mock(Prompt.class);
        loginController = mock(LoginController.class);

        // mock System.out
        out = mock(PrintStream.class);
        System.setOut(out);

        loginView.setPrompt(prompt);
        loginView.setLoginController(loginController);

    }

    @Test
    public void showLoginPrompt() {

        // fake user id
        int userId = 1;

        // when prompt asks for user input, return fake userId
        when(prompt.getUserInput(any(InputScanner.class))).thenReturn(userId);

        // show the login screen..
        loginView.show();

        // verify that getUserInput was called
        verify(prompt).getUserInput(any(InputScanner.class));

        // verify that onLogic was called with the fake user id
        verify(loginController).onLogin(userId);

        // verify that println was not called with the error message
        verify(out, never()).println(Messages.VIEW_LOGIN_ERROR);

    }

}
