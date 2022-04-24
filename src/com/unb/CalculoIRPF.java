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

    private float primeiraFaixa;
    private float segundaFaixa;
    private float terceiraFaixa;
    private float quartaFaixa;
    private float quintaFaixa;

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
        if( nomeContribuicaoOficial == "" || nomeContribuicaoOficial == null) {
            throw new DescricaoEmBrancoException();
        }
        if( valorDeducao <= 0 ) {
            throw new ValorDeducaoInvalidoException();
        }
        PrevidenciaOficial novaPrevidencia = new PrevidenciaOficial(nomeContribuicaoOficial, valorDeducao);

        this.previdenciaOficial.add(novaPrevidencia);
        this.deducaoOficialTotal += valorDeducao;

    }

    public void cadastrarPensaoAlimenticia(float valorPensao) {
        if( valorPensao < 0 ) {
            throw new ValorDeducaoInvalidoException();
        }
        this.deducaoAlimenticiaTotal += valorPensao;
    }

    public void cadastrarDependentes(String nomeDependente, String dataNascimento) {

        if( nomeDependente == "" && dataNascimento == "") {
            this.qtdDependentes += 0;
        }else if ( nomeDependente == "" ) {
            throw new NomeEmBrancoException();
        } else {

            this.qtdDependentes += 1;
            this.nomeDependente = nomeDependente;
            this.dataNascimento = dataNascimento;
        }

    }

    public float getTotalValorDependentes() {
        float valorIndDeducao = 189.59f;
        return valorIndDeducao * this.qtdDependentes;
    }

    public void cadastrarOutrasDeducoes(String nomeOutraDeducao, float valorOutraDeducoes) {
        if( nomeOutraDeducao == "" || nomeOutraDeducao == null) {
            throw new DescricaoEmBrancoException();
        }
        if( valorOutraDeducoes <= 0 ) {
            throw new ValorDeducaoInvalidoException();
        }
        OutrasDeducoes novaDeducao = new OutrasDeducoes(nomeOutraDeducao, valorOutraDeducoes);
        outrasDeducoes.add(novaDeducao);
        this.deducaoOutraDeducoesTotal += valorOutraDeducoes;
    }

    public float calcularBase() {
        return getRendimentoTotal() - getDeducaoTotal();
    }

    public void calcularImposto() {
        new CalcularImposto(this).calcularFaixas();
    }

    public float getPrimeiraFaixa() {
        return 0;
    }

    public void setPrimeiraFaixa(float primeiraFaixa) {
        this.primeiraFaixa = primeiraFaixa;
    }

    public float getSegundaFaixa() {
        return this.segundaFaixa*0.075f;
    }

    public void setSegundaFaixa(float segundaFaixa) {
        this.segundaFaixa = segundaFaixa;
    }

    public float getTerceiraFaixa() {
        return this.terceiraFaixa*0.15f;
    }

    public void setTerceiraFaixa(float terceiraFaixa) {
        this.terceiraFaixa = terceiraFaixa;
    }

    public float getQuartaFaixa() {
        return this.quartaFaixa * 0.225f;
    }

    public void setQuartaFaixa(float quartaFaixa) {
        this.quartaFaixa = quartaFaixa;
    }

    public float getQuintaFaixa() {
        return this.quintaFaixa * 0.275f;
    }

    public void setQuintaFaixa(float quintaFaixa) {
        this.quintaFaixa = quintaFaixa;
    }

    public float getImpostoTotal() {
        return (getSegundaFaixa() + getTerceiraFaixa() + getQuartaFaixa() + getQuintaFaixa());
    }

    public float getCalculoAliquotaEfetiva() {
        return getImpostoTotal()/getRendimentoTotal();
    }
}
