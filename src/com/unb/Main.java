package com.unb;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner ler = new Scanner(System.in);

        float rendimentoBruto, valorTotalDeducoes, valorPensaoAlimenticia, valorOutrasDedudoes, previdenciaOficial;
        int numeroDependentes, numeroOutrasDeducoes, numeroRendimentos;
        String nomeRendimento, outrasDeducoes;

        CalculoIRPF c = new CalculoIRPF();

        System.out.println("Calculadora IRPF");
        System.out.println("");

        String strA0 = JOptionPane.showInputDialog("Informe o número de Rendimentos que deseja cadastrar:");
        numeroRendimentos = Integer.parseInt(strA0);

        for (int i = 0; i < numeroRendimentos; i++) {

            String strRb0 = JOptionPane.showInputDialog("Informe o nome do seu do seu rendimento bruto:");
            nomeRendimento = strRb0;

            String strRA1 = JOptionPane.showInputDialog("Informe o valor do seu do seu rendimento bruto:");
            rendimentoBruto = Float.parseFloat(strRA1);

            c.cadastrarRendimento(nomeRendimento, rendimentoBruto);
        }

        String nPrev = JOptionPane.showInputDialog("Informe o número de Previdencias Oficiais que deseja cadastrar:");
        numeroRendimentos = Integer.parseInt(nPrev);

        for (int j = 0; j < numeroRendimentos; j++) {

            nomeRendimento = JOptionPane.showInputDialog("Informe o nome da sua previdencia oficial:");

            String wow = JOptionPane.showInputDialog("Informe o valor da sua previdencia oficial:");
            previdenciaOficial = Float.parseFloat(wow);

            c.cadastrarPrevidenciaOficial(nomeRendimento, previdenciaOficial);
        }

        String strDA0 = JOptionPane.showInputDialog("Informe o numero de dependentes:");
        numeroDependentes = Integer.parseInt(strDA0);

        String nomeDependente, dataNascimento;

        for (int i = 0; i < numeroDependentes; i++) {
            String strDA1 = JOptionPane.showInputDialog("Informe o nome do seu Dependente:");
            nomeDependente = strDA1;

            String strDA2 = JOptionPane.showInputDialog("Informe a data de nascimento do dependente:");
            dataNascimento = strDA2;

            c.cadastrarDependentes(nomeDependente, dataNascimento);
        }

        String strEA0 = JOptionPane.showInputDialog("Informe o valor gasto com Pensão Alimentícia:");
        valorPensaoAlimenticia = Float.parseFloat(strEA0);

        c.cadastrarPensaoAlimenticia(valorPensaoAlimenticia);

        String strEA1 = JOptionPane.showInputDialog("Informe quantas outras deduções ainda deseja declarar:");
        numeroOutrasDeducoes = Integer.parseInt(strEA1);

        for (int i = 0; i < numeroOutrasDeducoes; i++) {
            outrasDeducoes = JOptionPane.showInputDialog("Informe o nome do outro tipo de Dedução:");
            String strJA2 = JOptionPane.showInputDialog("Informe o valor dessa dedução:");
            valorOutrasDedudoes = Float.parseFloat(strJA2);

            c.cadastrarOutrasDeducoes(outrasDeducoes, valorOutrasDedudoes);
        }

        c.calcularImposto();

        float valorSegundaFaixa = c.getSegundaFaixa();
        float valorTerceiraFaixa = c.getTerceiraFaixa();
        float valorQuartaFaixa = c.getQuartaFaixa();
        float valorQuintaFaixa = c.getQuintaFaixa();
        float valorImposto = c.getImpostoTotal();
        float aliquotaEfetiva = c.getCalculoAliquotaEfetiva();

        System.out.println("==========================================");
        System.out.printf("Valor total de rendimentos: R$ %.2f%n", c.getRendimentoTotal());
        System.out.println("==========================================");
        System.out.printf("Valor total de deduções: R$ %.2f%n", c.getDeducaoTotal());
        System.out.println("==========================================");
        System.out.printf("Base de calculo: R$ %.2f%n", c.calcularBase() );
        System.out.println("==========================================");
        System.out.println("Imposto pago Primeira Faixa:  R$ 0,00");
        System.out.printf("Imposto pago Segunda Faixa:   R$ %.2f%n", valorSegundaFaixa);
        System.out.printf("Imposto pago Terceira Faixa:  R$ %.2f%n", valorTerceiraFaixa);
        System.out.printf("Imposto pago Quarta Faixa:    R$ %.2f%n", valorQuartaFaixa);
        System.out.printf("Imposto pago Quinta Faixa:    R$ %.2f%n", valorQuintaFaixa);
        System.out.println("==========================================");
        System.out.printf("Imposto : R$ %.2f%n", valorImposto);
        System.out.printf("Aliquota Efetiva: %.2f %%", aliquotaEfetiva * 100);
    }
}
