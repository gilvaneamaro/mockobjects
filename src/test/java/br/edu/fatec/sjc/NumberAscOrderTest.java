package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class NumberAscOrderTest {
    private CustomStack<Integer> mockStack;

    @BeforeEach
    void setUp() {
        mockStack = mock(CustomStack.class);
    }

    @Test
    @DisplayName("Deve ordenar corretamente os 6 números da pilha")
    public void testSortWithSixNumbers() throws StackEmptyException {
        List<Integer> simulatedStack = new ArrayList<>(Arrays.asList(45, 7, 32, 1, 60, 18));

        when(mockStack.isEmpty()).thenAnswer(invocation -> simulatedStack.isEmpty());

        when(mockStack.pop()).thenAnswer(invocation -> {
            if (simulatedStack.isEmpty()) {
                throw new StackEmptyException();
            }
            return simulatedStack.remove(simulatedStack.size() - 1);
        });

        NumberAscOrder<Integer> sorter = new NumberAscOrder<>(mockStack);
        List<Integer> result = sorter.sort();

        List<Integer> expected = Arrays.asList(1, 7, 18, 32, 45, 60);
        assertEquals(expected, result);

        verify(mockStack, atLeast(1)).isEmpty();
        verify(mockStack, times(6)).pop();
    }


    @Test
    @DisplayName("Deve lançar exceção ao tentar ordenar pilha vazia")
    public void testSortWithEmptyStack() throws StackEmptyException {
        when(mockStack.isEmpty()).thenReturn(true);

        NumberAscOrder<Integer> sorter = new NumberAscOrder<>(mockStack);

        assertThrows(
                EmptyStackException.class,
                sorter::sort,
                "Não é possível ordenar: pilha vazia."
        );

        verify(mockStack, atLeastOnce()).isEmpty();
        verify(mockStack, never()).pop();
    }

}
