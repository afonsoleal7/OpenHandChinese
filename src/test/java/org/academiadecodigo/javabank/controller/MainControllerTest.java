package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.view.UserOptions;
import org.academiadecodigo.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MainControllerTest {

    private MainController mainController;
    private AuthService authService;
    private Controller ctrl1;
    private Controller ctrl2;
    private Customer customer;
    private View view;

    @Before
    public void setup() {

        mainController = new MainController();

        // create mocks
        authService = mock(AuthService.class);
        ctrl1 = mock(Controller.class);
        ctrl2 = mock(Controller.class);
        customer = mock(Customer.class);
        view = mock(View.class);

        // create a fake controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.DEPOSIT.getOption(), ctrl1);
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), ctrl2);

        // wire them to mainController
        mainController.setControllerMap(controllerMap);
        mainController.setAuthService(authService);
        mainController.setView(view);

    }

    @Test
    public void initTest() {

        mainController.init();
        verify(view).show();

    }

    @Test
    public void onMenuSelectionTest() {

        // select option 1
        mainController.onMenuSelection(UserOptions.DEPOSIT.getOption());

        // verify controller 1 has been inited
        verify(ctrl1).init();

        // select option 2
        mainController.onMenuSelection(UserOptions.GET_BALANCE.getOption());

        // verify controller 2 has been inited
        verify(ctrl2).init();

        // verify that view has been shown both times
        verify(view, times(2)).show();
    }

    @Test(expected = IllegalStateException.class)
    public void onMenuSelectionInvalidTest() {

        // selecting a non existing option should throw an IllegalStateException
        mainController.onMenuSelection(100);
    }

    @Test
    public void onMenuSelectionQuitTest() {

        // select quit option
        mainController.onMenuSelection(UserOptions.QUIT.getOption());

        // verify that init method has never been invoked in any controller
        verify(ctrl1, never()).init();
        verify(ctrl2, never()).init();

        // verify that show was never called
        verify(view, never()).show();
    }

    @Test
    public void getCustomerNameTest() {

        // setup a fake accessing customer
        String name = "Rui";
        when(customer.getFirstName()).thenReturn(name);
        when(authService.getAccessingCustomer()).thenReturn(customer);

        assertEquals(mainController.getCustomerName(), name);

    }
}
