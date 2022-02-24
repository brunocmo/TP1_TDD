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
        PrevidenciaOficial novaPrevidencia = new PrevidenciaOficial(nomeContribuicaoOficial, valorDeducao);

        this.previdenciaOficial.add(novaPrevidencia);
        this.deducaoOficialTotal += valorDeducao;

    }

    public void cadastrarPensaoAlimenticia(float valorPensao) {
        this.deducaoAlimenticiaTotal += valorPensao;
    }

    public void cadastrarDependentes(String nomeDependente, String dataNascimento) {

        if( nomeDependente == "" && dataNascimento == "") {
            this.qtdDependentes += 0;
        }else {

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
        OutrasDeducoes novaDeducao = new OutrasDeducoes(nomeOutraDeducao, valorOutraDeducoes);
        outrasDeducoes.add(novaDeducao);
        this.deducaoOutraDeducoesTotal += valorOutraDeducoes;
    }

    public float calcularBase() {
        return getRendimentoTotal() - getDeducaoTotal();
    }

    public void calcularImposto() {

        float baseDeCalculo = calcularBase();

        if (baseDeCalculo <= 1903.98f) {
            this.primeiraFaixa = baseDeCalculo;
            this.segundaFaixa = 0;
            this.terceiraFaixa = 0;
            this.quartaFaixa = 0;
            this.quintaFaixa = 0;
        } else {
            if (baseDeCalculo > 1903.98f && baseDeCalculo <= 2826.65f) {
                this.primeiraFaixa = 1903.98f;
                this.segundaFaixa = baseDeCalculo - 1903.98f;
                this.terceiraFaixa = 0;
                this.quartaFaixa = 0;
                this.quintaFaixa = 0;
            } else {
                if (baseDeCalculo > 2826.65f && baseDeCalculo <= 3751.05f) {
                    this.primeiraFaixa = 1903.98f;
                    this.segundaFaixa = 922.67f;
                    this.terceiraFaixa = baseDeCalculo - (1903.98f + 922.67f);
                    this.quartaFaixa = 0;
                    this.quintaFaixa = 0;
                } else {
                    if (baseDeCalculo > 3751.05f && baseDeCalculo <= 4664.68f) {
                        this.primeiraFaixa = 1903.98f;
                        this.segundaFaixa = 922.67f;
                        this.terceiraFaixa = 924.40f;
                        this.quartaFaixa = baseDeCalculo - (1903.98f + 922.67f + 924.40f);
                        this.quintaFaixa = 0;
                    } else {
                        this.primeiraFaixa = 1903.98f;
                        this.segundaFaixa = 922.67f;
                        this.terceiraFaixa = 924.40f;
                        this.quartaFaixa = 913.63f;
                        this.quintaFaixa = baseDeCalculo - (1903.98f + 922.67f + 924.40f + 913.63f);
                    }

                }
            }

        }
    }

    public float getPrimeiraFaixa() {
        return 0;
    }

    public float getSegundaFaixa() {
        return this.segundaFaixa*0.075f;
    }

    public float getTerceiraFaixa() {
        return this.terceiraFaixa*0.15f;
    }

    public float getQuartaFaixa() {
        return this.quartaFaixa*0.225f;
    }

    public float getQuintaFaixa() {
        return this.quintaFaixa*.275f;
    }

    public float getImpostoTotal() {
        return (getSegundaFaixa() + getTerceiraFaixa() + getQuartaFaixa() + getQuintaFaixa());
    }

    public float getCalculoAliquotaEfetiva() {
        return 0.0783f;
    }
}
