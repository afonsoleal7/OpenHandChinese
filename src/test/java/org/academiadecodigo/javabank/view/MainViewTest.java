package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.MainController;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;


public class MainViewTest {

    private MainController mCtrl;
    private MainView mView;
    private PrintStream out;
    private Prompt prompt;

    @Before
    public void setup() {

        prompt = mock(Prompt.class);
        out = mock(PrintStream.class);
        System.setOut(out);

        mCtrl = mock(MainController.class);
        mView = new MainView();
        mView.setMainController(mCtrl);
        mView.setPrompt(prompt);


    }

    @Test
    public void testShow() {

        int fakeOption = 999;
        when(prompt.getUserInput(any(InputScanner.class))).thenReturn(fakeOption);

        mView.show();

        verify(mCtrl).onMenuSelection(fakeOption);
    }
}
