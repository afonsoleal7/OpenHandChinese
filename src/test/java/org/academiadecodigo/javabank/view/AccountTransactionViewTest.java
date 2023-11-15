package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.InputScanner;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.transaction.AccountTransactionController;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountTransactionViewTest {

    private AccountTransactionView view;
    private AccountTransactionController atc;
    private PrintStream out;
    private Prompt prompt;

    @Before
    public void setup() {

        view = new AccountTransactionView();
        atc = mock(AccountTransactionController.class);
        out = mock(PrintStream.class);
        prompt = mock(Prompt.class);

        // wire mocks
        view.setTransactionController(atc);
        view.setPrompt(prompt);
        System.setOut(out);

    }

    @Test
    public void testShowNoAccounts() {

        Set<Integer> ids = new HashSet<>();
        when(atc.getAccountIds()).thenReturn(ids);

        view.show();

        // verify that submit transaction was never called
        verify(atc, never()).submitTransaction(anyInt(), anyDouble());

    }

    @Test
    public void testShowAccounts() {

        int accountId = 1;
        double amount = 100;

        // create fake account id list
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(2);

        // return the fake account list when needed
        when(atc.getAccountIds()).thenReturn(ids);

        // simulate user input. first inputs 1, then inputs 100
        when(prompt.getUserInput(any(InputScanner.class))).thenReturn(accountId, amount);

        // call the method under test
        view.show();

        // verify that submit transaction was called with the correct values
        verify(atc).submitTransaction(accountId, amount);

    }
}
