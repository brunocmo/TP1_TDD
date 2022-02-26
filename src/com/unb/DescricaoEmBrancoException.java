package com.unb;

public class DescricaoEmBrancoException extends NullPointerException {
    public DescricaoEmBrancoException() {
        super("Digite um dado válido por favor.");
    }
}
