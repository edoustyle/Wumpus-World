import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;

public class AG_MW {
    private static final int TAMANHO_POPULACAO = 100;
    private static final double TAXA_MUTACAO = 0.5;
    private static final int NUMERO_MAX_GERACOES = 100;
    private static char mov2[]=new char[] {'N','S','O','L'};
    private static int tamanho=10,calcul=10;

    // Evolução da população
    public static Populacao evolverPopulacao(Populacao populacao, ListaPopulacao ListaP) {
        Populacao novaPopulacao = new Populacao(populacao.getTamanhoPopulacao(), false,calcul);

        // Manter o melhor indivíduo da população atual
        novaPopulacao.setIndividuo(0, populacao.getMelhorIndividuo());

        // Cruzamento da população atual para criar a nova população
        int indiceInicio = 1;
        while (indiceInicio < novaPopulacao.getTamanhoPopulacao()) {
            Individuo pai1 = selecaoRoleta(populacao);
            Individuo pai2 = selecaoRoleta(populacao);

            Individuo filho = cruzamento(pai1, pai2);

            novaPopulacao.setIndividuo(indiceInicio, filho);
            mutacao(novaPopulacao.getIndividuo(indiceInicio));
            indiceInicio++;
        }
        ListaP.addLista(novaPopulacao);
        return novaPopulacao;
    }

    // Seleção de torneio para escolher um indivíduo
    public static Individuo selecaoTorneio(Populacao populacao) {
        Random random = new Random();
        Populacao populacaoTorneio = new Populacao(TAMANHO_POPULACAO, false,calcul);

        for (int i = 0; i < TAMANHO_POPULACAO; i++) {
            int indiceAleatorio = random.nextInt(populacao.getTamanhoPopulacao());
            populacaoTorneio.setIndividuo(i, populacao.getIndividuo(indiceAleatorio));
        }

        return populacaoTorneio.getMelhorIndividuo();
    }

    // Seleção por roleta
    public static Individuo selecaoRoleta(Populacao populacao) {
        Random random = new Random();
        double somaFitness = 0.0;

        // Calcula a soma total do fitness da população
        for (int i = 0; i < populacao.getTamanhoPopulacao(); i++) {
            somaFitness += populacao.getIndividuo(i).getFitness();
        }

        // Gera um valor aleatório entre 0 e a soma total do fitness
        double valorRoleta = random.nextDouble() * somaFitness;

        double somaParcial = 0.0;
        for (int i = 0; i < populacao.getTamanhoPopulacao(); i++) {
            somaParcial += populacao.getIndividuo(i).getFitness();

            // Se a soma parcial atingir o valor da roleta, retorna o indivíduo correspondente
            if (somaParcial >= valorRoleta) {
                return populacao.getIndividuo(i);
            }
        }

        // Caso não tenha sido selecionado nenhum indivíduo, retorna o último indivíduo
        return populacao.getIndividuo(populacao.getTamanhoPopulacao() - 1);
    }
    
    // Seleção por classificação (Rank Selection)
    public static Individuo selecaoRank(Populacao populacao) {
        Random random = new Random();
        double somaRank = 0.0;
        double somaProbabilidades = 0.0;

        // Ordena os indivíduos por fitness (em ordem decrescente)
        Arrays.sort(populacao.getIndividuos(), Comparator.comparingInt(Individuo::getFitness).reversed());

        // Calcula a soma dos ranks
        for (int i = 0; i < populacao.getTamanhoPopulacao(); i++) {
            somaRank += i + 1;
        }

        // Calcula as probabilidades de seleção para cada indivíduo com base no rank
        for (int i = 0; i < populacao.getTamanhoPopulacao(); i++) {
            double probabilidade = (i + 1) / somaRank;
            somaProbabilidades += probabilidade;
            populacao.getIndividuo(i).setProbabilidadeSelecao(probabilidade);
        }

        // Gera um valor aleatório entre 0 e a soma das probabilidades
        double valorSelecao = random.nextDouble() * somaProbabilidades;

        double somaParcial = 0.0;
        for (int i = 0; i < populacao.getTamanhoPopulacao(); i++) {
            somaParcial += populacao.getIndividuo(i).getProbabilidadeSelecao();

            // Se a soma parcial atingir o valor de seleção, retorna o indivíduo correspondente
            if (somaParcial >= valorSelecao) {
                return populacao.getIndividuo(i);
            }
        }

        // Caso não tenha sido selecionado nenhum indivíduo, retorna o último indivíduo
        return populacao.getIndividuo(populacao.getTamanhoPopulacao() - 1);
    }
    
    // Cruzamento de dois indivíduos
    public static Individuo cruzamento(Individuo pai1, Individuo pai2) {
        Random random = new Random();
        Individuo filho = new Individuo(calcul);

        // Realiza o cruzamento dos genes dos pais para gerar o filho
        for (int i = 0; i < pai1.getTamanhoGenes(); i++) {
            if (random.nextDouble() <= 0.5) {
                filho.setGene(i, pai1.getGene(i));
            } else {
                filho.setGene(i, pai2.getGene(i));
            }
        }

        return filho;
    }

    // Mutação de um indivíduo
    public static void mutacao(Individuo individuo) {
        Random random = new Random();
        //Realiza a mutação dos genes do indivíduo
        for (int i = 0; i < individuo.getTamanhoGenes(); i++) {
            if (random.nextDouble() <= TAXA_MUTACAO) {
                int nGene = random.nextInt(mov2.length);
                char NovoG=mov2[nGene];
                individuo.setGene(i,NovoG);
            }
        }
        //individuo.calcularFitness();
    }
}

class FitnessComparator implements Comparator<Individuo> {
    @Override
    public int compare(Individuo individuo1, Individuo individuo2) {
        // Comparação baseada no fitness dos indivíduos
        return Integer.compare(individuo1.getFitness(), individuo2.getFitness());
    }
}
