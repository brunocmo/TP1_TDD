package com.unb;

public class ValorRendimentoInvalidoException extends IllegalArgumentException{
    public ValorRendimentoInvalidoException() {
        super("Digite um valor positivo por favor.");
    }
}
