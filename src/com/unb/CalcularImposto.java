package com.unb;

public class CalcularImposto  {

    public static final float ZEROIMPOSTO = 0f;

    public static final float PRIMEIRAFAIXA = 1903.98f;
    public static final float SEGUNDAFAIXA = 922.67f;
    public static final float TERCEIRAFAIXA = 924.40f;
    public static final float QUARTAFAIXA = 913.63f;

    public static final float CAIUSEGUNDAFAIXA = PRIMEIRAFAIXA + SEGUNDAFAIXA;
    public static final float CAIUTERCEIRAFAIXA = PRIMEIRAFAIXA + SEGUNDAFAIXA + TERCEIRAFAIXA;
    public static final float CAIUQUARTAFAIXA = PRIMEIRAFAIXA + SEGUNDAFAIXA + TERCEIRAFAIXA + QUARTAFAIXA;


    CalculoIRPF fonte;
    
    float[] faixas = {
        ZEROIMPOSTO, 
        ZEROIMPOSTO, 
        ZEROIMPOSTO, 
        ZEROIMPOSTO, 
        ZEROIMPOSTO
    };

    float baseDeCalculo;

    public CalcularImposto (CalculoIRPF fonte){
        this.fonte = fonte;
    }

    public void calcularFaixas() {
        baseDeCalculo = fonte.calcularBase();

        if (baseDeCalculo <= PRIMEIRAFAIXA) {
            faixas[0] = baseDeCalculo;
        } else {
            if (baseDeCalculo > PRIMEIRAFAIXA && baseDeCalculo <= CAIUSEGUNDAFAIXA) {
                faixas[0] = PRIMEIRAFAIXA;
                faixas[1] = baseDeCalculo - PRIMEIRAFAIXA;
            } else {
                if (baseDeCalculo > CAIUSEGUNDAFAIXA && baseDeCalculo <= CAIUTERCEIRAFAIXA) {
                    faixas[0] = PRIMEIRAFAIXA;
                    faixas[1] = SEGUNDAFAIXA;
                    faixas[2] = baseDeCalculo - CAIUSEGUNDAFAIXA;
                } else {
                    if (baseDeCalculo > CAIUTERCEIRAFAIXA && baseDeCalculo <= CAIUQUARTAFAIXA) {
                        faixas[0] = PRIMEIRAFAIXA;
                        faixas[1] = SEGUNDAFAIXA;
                        faixas[2] = TERCEIRAFAIXA;
                        faixas[3] = baseDeCalculo - CAIUTERCEIRAFAIXA;
                    } else {
                        faixas[0] = PRIMEIRAFAIXA;
                        faixas[1] = SEGUNDAFAIXA;
                        faixas[2] = TERCEIRAFAIXA;
                        faixas[3] = QUARTAFAIXA;
                        faixas[4] = baseDeCalculo - CAIUQUARTAFAIXA;
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

