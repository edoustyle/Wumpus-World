import java.util.Arrays;

public class Populacao {
    private Individuo[] individuos;

    public Populacao(int tamanhoPopulacao, boolean iniciar,int calcul) {
        individuos = new Individuo[tamanhoPopulacao];
        if (iniciar) {
            for (int i = 0; i < tamanhoPopulacao; i++) {
                
                Individuo novoIndividuo = new Individuo(calcul);
                novoIndividuo.gerarGenesAleatorios();
                individuos[i] = novoIndividuo;
            }
        }
    }

    public Individuo getIndividuo(int indice) {
        return individuos[indice];
    }

    public Individuo[] getIndividuos() {
        return individuos;
    }

    public void setIndividuo(int indice, Individuo individuo) {
        individuos[indice] = individuo;
    }

    public Individuo getMelhorIndividuo() {
        Individuo melhorIndividuo = individuos[0];
        for (int i = 1; i < individuos.length; i++) {
            if (individuos[i].getFitness() > melhorIndividuo.getFitness()) {
                melhorIndividuo = individuos[i];
            }
        }
        return melhorIndividuo;
    }

    public int getTamanhoPopulacao() {
        return individuos.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(individuos);
    }
}
