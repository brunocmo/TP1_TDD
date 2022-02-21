package com.unb;

import java.util.LinkedList;
import java.util.List;

public class CalculoIRPF {

    private float rendimentoTotal;
    private List<Rendimentos> rendimentos;

    public CalculoIRPF() {
        rendimentos = new LinkedList<Rendimentos>();
    }

    public void cadastrarRendimento(String nomeDoRendimento, float rendimentoTotal) throws DescricaoEmBrancoException {

        if( nomeDoRendimento == "" || nomeDoRendimento == null) {
            throw new DescricaoEmBrancoException();
        }

        if( rendimentoTotal <= 0 ) {
            throw new ValorRendimentoInvalidoException();
        }

        Rendimentos novoRendimento = new Rendimentos(nomeDoRendimento, rendimentoTotal);
        this.rendimentos.add(novoRendimento);
        this.rendimentoTotal += rendimentoTotal;
    }

    public float getRendimentoTotal() {
        return rendimentoTotal;
    }

}
