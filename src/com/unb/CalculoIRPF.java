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
    private List<PrevidenciaOficial> previdenciaOficial;

    private String nomeDependente;
    private String dataNascimento;
    private int qtdDependentes;
    
    private float deducaoOutraDeducoesTotal;
    private List<OutrasDeducoes> outrasDeducoes;

    public CalculoIRPF() {
        rendimentos = new LinkedList<Rendimentos>();
        previdenciaOficial = new LinkedList<PrevidenciaOficial>();
        outrasDeducoes = new LinkedList<OutrasDeducoes>();

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

        resultado = deducaoOficialTotal + getTotalValorDependentes() + deducaoAlimenticiaTotal + deducaoOutraDeducoesTotal;

        return resultado;
    }

    public float getDeducaoAlimenticiaTotal() { return deducaoAlimenticiaTotal; };
    public float getDeducaoOficialTotal() { return  deducaoOficialTotal; };
    public float getOutrasDeducoesTotal() { return deducaoOutraDeducoesTotal; };

    public void cadastrarPrevidenciaOficial(String nomeContribuicaoOficial, float valorDeducao) {
        PrevidenciaOficial novaPrevidencia = new PrevidenciaOficial(nomeContribuicaoOficial, valorDeducao);

        this.previdenciaOficial.add(novaPrevidencia);
        this.deducaoOficialTotal += valorDeducao;

    }

    public void cadastrarPensaoAlimenticia(float valorPensao) {
        this.deducaoAlimenticiaTotal += valorPensao;
    }

    public void cadastrarDependentes(String nomeDependente, String dataNascimento) {
        this.qtdDependentes += 1;
        this.nomeDependente = nomeDependente;
        this.dataNascimento = dataNascimento;

    }

    public float getTotalValorDependentes() {
        float valorIndDeducao = 189.59f;
        return valorIndDeducao * this.qtdDependentes;
    }

    public void cadastrarOutrasDeducoes(String nomeOutraDeducao, float valorOutraDeducoes) {
        OutrasDeducoes novaDeducao = new OutrasDeducoes(nomeOutraDeducao, valorOutraDeducoes);
        outrasDeducoes.add(novaDeducao);
        this.deducaoOutraDeducoesTotal += valorOutraDeducoes;
    }
}
