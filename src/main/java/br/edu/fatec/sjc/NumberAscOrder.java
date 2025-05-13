package br.edu.fatec.sjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

public class NumberAscOrder<T extends Number & Comparable<T>> {

    private final CustomStack<T> stack;

    public NumberAscOrder(CustomStack<T> stack) {
        if (stack == null) {
            throw new EmptyStackException();
        }
        this.stack = stack;
    }

    public List<T> sort() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        List<T> orderedNumbers = new ArrayList<>();

        try {
            while (!stack.isEmpty()) {
                orderedNumbers.add(stack.pop());
            }
        } catch (StackEmptyException ex) {
            throw new IllegalStateException("Falha ao processar a pilha.", ex);
        }

        Collections.sort(orderedNumbers);
        return orderedNumbers;
    }
}