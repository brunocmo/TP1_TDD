package com.unb;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CalculoIRPF {

    private float rendimentoTotal;
    private List<Rendimentos> rendimentos;
    private float[] previdenciaOficial;
    private String[] descricaoPrevidenciaOficial;
    private int qtdDependentes;

    public CalculoIRPF() {
        rendimentos = new LinkedList<Rendimentos>();
        previdenciaOficial = new float[0];
        descricaoPrevidenciaOficial = new String[0];
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

    public void cadastrarPrevidenciaOficial(String tipoPrevidenciaOficial, float v) {
        float tempValor[] = new float[previdenciaOficial.length + 1];
        String tempDescricao[] = new String[descricaoPrevidenciaOficial.length + 1];

        for (int i = 0; i < previdenciaOficial.length; i++){
            tempValor[i] = this.previdenciaOficial[i];
            tempDescricao[i] = this.descricaoPrevidenciaOficial[i];
        }

        tempValor[previdenciaOficial.length] = v;
        tempDescricao[previdenciaOficial.length] = tipoPrevidenciaOficial;

        this.descricaoPrevidenciaOficial = tempDescricao;
        this.previdenciaOficial = tempValor;


    }

    public float getTotalDeducaoPrevidenciaOficial() {
        float totalPrevidenciaOficial = 0;

        for (float v : previdenciaOficial){
            totalPrevidenciaOficial += v;
        }
        totalPrevidenciaOficial += getTotalValorDependentes();

        return totalPrevidenciaOficial;
    }

    public void cadastrarDependentes(int qtd) {
        this.qtdDependentes = qtd;
    }

    public float getTotalValorDependentes() {
        float valorIndDeducao = 189.59f;
        return valorIndDeducao * this.qtdDependentes;
    }
}
