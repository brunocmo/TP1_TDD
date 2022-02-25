package com.unb;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        float rendimentoBruto, valorTotalDeducoes, valorPensaoAlimenticia, valorOutrasDedudoes;
        int numeroDependentes, numeroOutrasDeducoes;
        String nomeRendimento, outrasDeducoes;

        CalculoIRPF c = new CalculoIRPF();

        System.out.println("Calculadora IRPF");

            System.out.println("Informe o nome do seu do seu rendimento bruto:");
            nomeRendimento = ler.next();

            System.out.println("Informe o valor do seu do seu rendimento bruto:");
            rendimentoBruto = ler.nextFloat();

            c.cadastrarRendimento(nomeRendimento, rendimentoBruto);

            System.out.println("Informe o numero de dependentes:");
            numeroDependentes = ler.nextInt();

            String nomeDependente, dataNascimento;

            for (int i = 0; i < numeroDependentes; i++) {

                System.out.println("Informe o nome do seu Dependente:");
                nomeDependente = ler.next();
                System.out.println("Informe a data de nascimento do seu dependente:");
                dataNascimento = ler.next();
                c.cadastrarDependentes(nomeDependente, dataNascimento);
            }

            float valorDeducaoDependentes = c.getTotalValorDependentes();

            ler.nextLine();
            System.out.printf("Valor de dedução Dependentes: R$ %.2f", valorDeducaoDependentes);
            ler.nextLine();

            System.out.println("Informe o valor gasto com Pensão Alimentícia:");
            valorPensaoAlimenticia = ler.nextFloat();

            c.cadastrarPensaoAlimenticia(valorPensaoAlimenticia);

            System.out.println("Informe quantas outras deduções ainda deseja declarar:");
            numeroOutrasDeducoes = ler.nextInt();

            for (int i = 0; i < numeroOutrasDeducoes; i++) {

                System.out.println("Informe o nome da seu outro tipo de Dedução:");
                outrasDeducoes = ler.next();
                System.out.println("Informe o valor dessa dedução:");
                valorOutrasDedudoes = ler.nextFloat();
                c.cadastrarOutrasDeducoes(outrasDeducoes, valorOutrasDedudoes);
            }

            c.calcularImposto();

            float valorSegundaFaixa = c.getSegundaFaixa();
            float valorTerceiraFaixa = c.getTerceiraFaixa();
            float valorQuartaFaixa = c.getQuartaFaixa();
            float valorQuintaFaixa = c.getQuintaFaixa();
            float valorImposto = c.getImpostoTotal();
            float aliquotaEfetiva = c.getCalculoAliquotaEfetiva();

            System.out.println("Imposto pago Primeira Faixa: R$ 0,00 ");
            System.out.printf("Imposto pago Segunda Faixa: R$ %.2f%n", valorSegundaFaixa);
            System.out.printf("Imposto pago Terceira Faixa: R$ %.2f%n", valorTerceiraFaixa);
            System.out.printf("Imposto pago Quarta Faixa: R$ %.2f%n", valorQuartaFaixa);
            System.out.printf("Imposto pago Quinta Faixa: R$ %.2f%n", valorQuintaFaixa);

            System.out.printf("Imposto : R$ %.2f%n", valorImposto);
            System.out.printf("Aliquota Efetiva: %.2f%n %%", aliquotaEfetiva * 100);








       // System.out.printf("Valor de total de deduções: R$ %.2f/n", valorTotalDeducoes);

    }
}