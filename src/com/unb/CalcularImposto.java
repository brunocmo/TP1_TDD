package com.unb;

public class CalcularImposto  {

    CalculoIRPF fonte;
    
    float[] faixas = {0f, 0f, 0f, 0f, 0f};
    float baseDeCalculo;

    public CalcularImposto (CalculoIRPF fonte){
        this.fonte = fonte;
    }

    public void calcularFaixas() {
        baseDeCalculo = fonte.calcularBase();

        if (baseDeCalculo <= 1903.98f) {
            faixas[0] = baseDeCalculo;

        } else {
            if (baseDeCalculo > 1903.98f && baseDeCalculo <= 2826.65f) {
                faixas[0] = 1903.98f;
                faixas[1] = baseDeCalculo - faixas[0];

            } else {
                if (baseDeCalculo > 2826.65f && baseDeCalculo <= 3751.05f) {
                    faixas[0] = 1903.98f;
                    faixas[1] = 922.67f;
                    faixas[2] = baseDeCalculo - (faixas[0] + faixas[1]);

                } else {
                    if (baseDeCalculo > 3751.05f && baseDeCalculo <= 4664.68f) {
                        faixas[0] = 1903.98f;
                        faixas[1] = 922.67f;
                        faixas[2] = 924.40f;
                        faixas[3] = baseDeCalculo - (faixas[0] + faixas[1] + faixas[2]);
                    } else {
                        faixas[0] = 1903.98f;
                        faixas[1] = 922.67f;
                        faixas[2] = 924.40f;
                        faixas[3] = 913.63f;
                        faixas[4] = baseDeCalculo - (faixas[0] + faixas[1] + faixas[2] + faixas[3]);
                    }

                }
            }

        }
        fonte.setPrimeiraFaixa(faixas[0]);
        fonte.setSegundaFaixa(faixas[1]);
        fonte.setTerceiraFaixa(faixas[2]); 
        fonte.setQuartaFaixa(faixas[3]);
        fonte.setQuintaFaixa(faixas[4]); 
    }
}
