package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private AuthService authService;
    private View loginView;
    private Controller nextController;

    @Before
    public void setUp() {

        loginController = new LoginController();

        // mock auth service, next controller and view
        authService = mock(AuthService.class);
        loginView = mock(View.class);
        nextController = mock(Controller.class);

        loginController.setAuthService(authService);
        loginController.setView(loginView);
        loginController.setNextController(nextController);

    }

    @Test
    public void initTest() {

        loginController.init();
        verify(loginView).show();

    }

    @Test
    public void onLoginTest() {

        // fake user id
        int fakeId = 112319121;

        // authservice mock will return true
        when(authService.authenticate(fakeId)).thenReturn(true);

        loginController.onLogin(fakeId);

        // verify authenticate been called with the proper id
        verify(authService).authenticate(fakeId);

        // verify nextController's init method has been called
        verify(nextController).init();

        // verify that login view was never presented
        verify(loginView, never()).show();
    }

    @Test
    public void onLoginFailTest() {

        // fake user id
        int fakeId = 1231238;

        // authservice mock will return false
        when(authService.authenticate(anyInt())).thenReturn(false);

        loginController.onLogin(fakeId);

        verify(authService).authenticate(fakeId);
        verify(nextController, never()).init();
        verify(loginView).show();
    }

}
