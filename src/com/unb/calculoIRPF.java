package com.unb;

public class calculoIRPF {

    private float rendimentoTotal;
    private String nomeDoRendimento;

    public void cadastrarRendimento(String nomeDoRendimento, float rendimentoTotal) {
        this.nomeDoRendimento = nomeDoRendimento;
        this.rendimentoTotal = rendimentoTotal;
    }

    public float getRendimentoTotal() {
        return rendimentoTotal;
    }

}
