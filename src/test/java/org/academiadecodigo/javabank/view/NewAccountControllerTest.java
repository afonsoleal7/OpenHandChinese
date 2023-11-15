package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.NewAccountController;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class NewAccountControllerTest {

    private NewAccountView naView;
    private NewAccountController naCtrl;
    private PrintStream out;

    @Before
    public void setup() {

        out = mock(PrintStream.class);
        System.setOut(out);

        naView = new NewAccountView();
        naCtrl = mock(NewAccountController.class);

        naView.setNewAccountController(naCtrl);
    }

    @Test
    public void testShow() {

        when(naCtrl.getNewAccountId()).thenReturn(1);

        naView.show();

        verify(naCtrl).getNewAccountId();

    }
}
