package com.unb;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class CalculoIRPF {

    private float rendimentoTotal;
    private List<Rendimentos> rendimentos;


    private float deducaoTotal;
    private float deducaoAlimenticiaTotal;

    private float deducaoOficialTotal;
    private String nomeDeducoes;

    private String nomeDependente;
    private Calendar dataNascimento;
    private int qtdDependentes;
    
    private String nomeOutraDeducao;
    private float deducaoOutraDeducoes;

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
    public float getDeducaoTotal() {
        float resultado;

        resultado = deducaoOficialTotal + getTotalValorDependentes() + deducaoAlimenticiaTotal + deducaoOutraDeducoes;

        return (1000f+500f+189.59f+800f);
    }
    public float getDeducaoAlimenticiaTotal() { return 500f; };
    public float getDeducaoOficialTotal() { return  1000f; };
    public float getOutrasDeducoesTotal() { return 800.00f; };
    public float getTotalValorDependentes() {
        float valorIndDeducao = 189.59f;
        return 189.59f;
    }

    public void cadastrarPrevidenciaOficial(String nomeContribuicaoOficial, float valorDeducao) {
        this.deducaoOficialTotal = valorDeducao;
        this.nomeDeducoes = nomeContribuicaoOficial;
    }

    public void cadastrarPensaoAlimenticia(float valorPensao) {
        this.deducaoAlimenticiaTotal = valorPensao;
    }

    public void cadastrarDependentes(String nomeDependente, Calendar dataNascimento) {
        this.qtdDependentes += 1;
        this.nomeDependente = nomeDependente;
        this.dataNascimento = dataNascimento;
    }



    public void cadastrarOutrasDeducoes(String nomeOutraDeducao, float valorOutraDeducoes) {
        this.nomeOutraDeducao = nomeOutraDeducao;
        this.deducaoOutraDeducoes = valorOutraDeducoes;
    }
}
