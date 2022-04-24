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

    private CalculoIRPF fonte;
    
    private float[] faixas = {
        ZEROIMPOSTO, 
        ZEROIMPOSTO, 
        ZEROIMPOSTO, 
        ZEROIMPOSTO, 
        ZEROIMPOSTO
    };

    private float baseDeCalculo;

    public CalcularImposto (CalculoIRPF fonte){
        this.fonte = fonte;
    }

    public void calcularFaixas() {
        baseDeCalculo = fonte.calcularBase();

        if (baseDeCalculo <= PRIMEIRAFAIXA) {
            caiuPrimeiraFaixa();
        } else {
            if (baseDeCalculo > PRIMEIRAFAIXA && baseDeCalculo <= CAIUSEGUNDAFAIXA) {
                caiuSegundaFaixa();
            } else {
                if (baseDeCalculo > CAIUSEGUNDAFAIXA && baseDeCalculo <= CAIUTERCEIRAFAIXA) {
                    caiuTerceiraFaixa();
                } else {
                    if (baseDeCalculo > CAIUTERCEIRAFAIXA && baseDeCalculo <= CAIUQUARTAFAIXA) {
                        caiuQuartaFaixa();
                    } else {
                        caiuQuintaFaixa();
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

    private void caiuQuintaFaixa() {
        faixas[0] = PRIMEIRAFAIXA;
        faixas[1] = SEGUNDAFAIXA;
        faixas[2] = TERCEIRAFAIXA;
        faixas[3] = QUARTAFAIXA;
        faixas[4] = baseDeCalculo - CAIUQUARTAFAIXA;
    }

    private void caiuQuartaFaixa() {
        faixas[0] = PRIMEIRAFAIXA;
        faixas[1] = SEGUNDAFAIXA;
        faixas[2] = TERCEIRAFAIXA;
        faixas[3] = baseDeCalculo - CAIUTERCEIRAFAIXA;
    }

    private void caiuTerceiraFaixa() {
        faixas[0] = PRIMEIRAFAIXA;
        faixas[1] = SEGUNDAFAIXA;
        faixas[2] = baseDeCalculo - CAIUSEGUNDAFAIXA;
    }

    private void caiuSegundaFaixa() {
        faixas[0] = PRIMEIRAFAIXA;
        faixas[1] = baseDeCalculo - PRIMEIRAFAIXA;
    }

    private void caiuPrimeiraFaixa() {
        faixas[0] = baseDeCalculo;
    }
}
