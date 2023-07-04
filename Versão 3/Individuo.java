import java.util.Arrays;
import java.util.Random;

public class Individuo {
    private char[] genes;
    private int fitness;
    private double probabilidadeSelecao;
    public Trajeto trajeto;
    public int indice2;
    public char mov2[];

    public Individuo(int tamanhogene) {
        //tamanhogene=10;
        genes = new char[tamanhogene];
        fitness = 0;
    }

    public double getProbabilidadeSelecao() {
        return probabilidadeSelecao;
    }

    public void setProbabilidadeSelecao(double probabilidadeSelecao) {
        this.probabilidadeSelecao = probabilidadeSelecao;
    }

    public void setindice(int indice){
        this.indice2=indice;
    }

    public char getGene(int indice) {
        return genes[indice];
    }

    public void setGene(int indice, char valor) {
        genes[indice] = valor;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int valor) {
        fitness = valor;
    }

    public int getTamanhoGenes() {
        return genes.length;
    }

    public void gerarGenesAleatorios() {
        Random random = new Random();
        for (int i = 0; i < genes.length; i++) {
            mov2=new char[] {'N','S','L','O'};
            int teste=random.nextInt(mov2.length);
            genes[i]=mov2[teste];
        }
    }


    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
