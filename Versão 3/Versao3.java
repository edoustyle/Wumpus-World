import java.util.*;
import java.lang.Thread;
//import AG.*;

public class Versao3 {
    public static int tamanho,x,y,t,q,choice,mov,morte=0,pegouOouro=0,sobreviveu=0,queda=0,terror=0,matouobixo2=0,inicio=0,agente=0,rodadas=-1,quantidadePocos,quantidadeWumpos;
    public static Posicao matPosicao[][];
    public static String testetipo, fantasma="vivo",sentido="",estado, Mapeamento[][],M[][];
    public static char movimentaçao[],indo=' ',perigoatual='N';
    public static Random generador = new Random();
    private static Scanner tc = new Scanner(System.in);
    public static Boolean glock=false,teste21=true,bateu=false;
    public static Trajeto trajeto;
    public static int geracao=1,calcul=10;
    private static final int TAMANHO_POPULACAO = 100;
    private static final int NUMERO_MAX_GERACOES = 100;
    public static ListaPopulacao ListaP;
    public static AG_MW testecal;
    public static Populacao cidadao;

    public static void limparConsole(){
        try{
            String nomeSistema = System.getProperty("os.name");

            if(nomeSistema.contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                return;
            }else{
                for (int i = 0; i < 100; i++){
                    System.out.println();
                }
            }
        }catch(Exception e){
            return;
        }
    }
    public static void criarambiente() {
        quantidadePocos = (int)((tamanho*tamanho)*0.2);
        quantidadeWumpos = (int)(((int)((tamanho*tamanho)*0.2))*0.4);

        for (int i = 0; i < matPosicao.length; i++) {
            for (int j = 0; j < matPosicao.length; j++) {
                Posicao poze3= new Posicao("centro", "","");
                matPosicao[i][j]=poze3;
            }
        }
        int conte=0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                Posicao poze = new Posicao("", "","");
                if (i==0 && j > 0 && j < tamanho-1) {
                    poze.setTipo("parede");
                    matPosicao[i][j]=poze;
                }
                else if (j==0 && i > 0 && i < tamanho-1) {
                    poze.setTipo("parede");
                    matPosicao[i][j]=poze;
                }
                else if (i==tamanho-1 && j > 0 && j < tamanho-1) {
                    poze.setTipo("parede");
                    matPosicao[i][j]=poze;
                }
                else if (j == tamanho-1 && i > 0 && i < tamanho-1) {
                    poze.setTipo("parede");
                    matPosicao[i][j]=poze;
                }
                if (j == tamanho-1 && i == tamanho-1) {
                    poze.setTipo("canto");
                    matPosicao[i][j]=poze;
                }
                if (j == 0 && i == 0) {
                    poze.setTipo("canto");
                    matPosicao[i][j]=poze;
                }
                if (j == 0 && i == tamanho-1) {
                    poze.setTipo("canto");
                    matPosicao[i][j]=poze;
                }
                if (i == 0 && j == tamanho-1) {
                    poze.setTipo("canto");
                    matPosicao[i][j]=poze;
                }
            }
        }
        boolean teste4=true;
        //posicao agente
        matPosicao[0][0].setAmbiente("agente");
        //poosicao ouro
        int onepiece=6;
        x=generador.nextInt(tamanho);
        y=generador.nextInt(tamanho);
        while (!matPosicao[x][y].getAmbiente().equals("")){
            x=generador.nextInt(tamanho);
            y=generador.nextInt(tamanho);
        }
        matPosicao[x][y].setAmbiente("ouro");

        if (x==0 && y==0) onepiece=0;
        else if (x==0 && y==tamanho-1) onepiece=1;
        else if (x==tamanho-1 && y==0) onepiece=2;
        else if (x==tamanho-1 && y==tamanho-1) onepiece=3;
        x=generador.nextInt(tamanho);
        y=generador.nextInt(tamanho);
        
        int criaW=0;
        System.out.println("Quantidade de poços: "+quantidadePocos);
        System.out.println("Quantidade de wumpus: "+quantidadeWumpos);

        while(criaW!=quantidadeWumpos){
            if ((x==0 && y==0)||(x==1&&y==0)||(x==0&&y==1)){
                x=generador.nextInt(tamanho);
                y=generador.nextInt(tamanho);
            }
            else{
                if (matPosicao[x][y].getAmbiente().equals("ouro")){matPosicao[x][y].setAmbiente("ouro|monstro");}
                else{matPosicao[x][y].setAmbiente("monstro");}
            }
            x=generador.nextInt(tamanho);
            y=generador.nextInt(tamanho);
            criaW++;
        }
        int criaP=0;
        while(criaP!=quantidadePocos) {
            Boolean chega=true;
            x=generador.nextInt(tamanho);
            y=generador.nextInt(tamanho);
            while (chega){
            
                if (choice==0||onepiece==0){
                    if((x==0 && y==1)||(x==1 && y==0)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else chega=false;
                }
                if (choice==1||onepiece==1){
                    if((x==0 && y==tamanho-2)||(x==1 && y==tamanho-1)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else chega=false;
                }
                if (choice==2||onepiece==2){
                    if((x==tamanho-2 && y==0)||(x==tamanho-1 && y==1)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else chega=false;
                }
                if (choice==3||onepiece==3){
                    if((x==tamanho-1 && y==tamanho-2)||(x==tamanho-2 && y==tamanho-1)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else chega=false;
                }
            }
            if(matPosicao[x][y].getAmbiente().equals("")){
                matPosicao[x][y].setAmbiente("poço");
                criaP++;
            }
        }
    }
    public static void printarmatrizambiente(){
        Posicao tafarel;
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tafarel=matPosicao[i][j];
                testetipo=tafarel.getAmbiente();
                System.out.print("["+testetipo+"]");
            }
            System.out.println();
        }
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }
    public static void printarmatrizsentido(){
        Posicao tafarel;
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tafarel=matPosicao[i][j];
                testetipo=tafarel.getSentido();
                System.out.print("["+testetipo+"]");
            }
            System.out.println();
        }
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }
    public static void direcao(String tipo, int i, int j, char gene){

        if (tipo.equals("canto")) {
            
            if (i==tamanho-1 && j==0) {
                if (gene=='S'||gene=='O'){
                    bateu=true;
                }
            }
            else if (i==0 && j==0) {
                if (gene=='N'||gene=='O'){
                    bateu=true;
                }
            }
            else if (i==0 && j==tamanho-1) {
                if (gene=='N'||gene=='L'){
                    bateu=true;
                }
            }
            else if (i==tamanho-1 && j==tamanho-1) {
                if (gene=='S'||gene=='L'){
                    bateu=true;
                }
            }
        } 
        else if (tipo.equals("parede")) {
            
            if (i<tamanho-1 && i>0 && j==0) {
                if (gene=='O'){
                    bateu=true;
                }
            }
            else if (i==0 && j>0 && j<tamanho-1) {
                if (gene=='N'){
                    bateu=true;
                }
            }
            else if (i==tamanho-1 && j>0 && j<tamanho-1) {
                if (gene=='S'){
                    bateu=true;
                }
            }
            else if (i>0 && i<tamanho-1 && j==tamanho-1) {
                if (gene=='L'){
                    bateu=true;
                }
            }
        }
        else{
            bateu=false;
        }
    }
    public static void criarsentido(){

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                Boolean temBixo=false,tempoco=false,temouro=false;
                if (j<tamanho-1) {
                    if (matPosicao[i][j+1].getAmbiente().equals("monstro")||matPosicao[i][j+1].getAmbiente().equals("ouro|monstro")) {
                        temBixo=true;
                    }
                    if (matPosicao[i][j+1].getAmbiente().equals("poço")) {
                        tempoco=true;
                    }
                }
                if (j>0) {
                    if(matPosicao[i][j-1].getAmbiente().equals("monstro")||matPosicao[i][j-1].getAmbiente().equals("ouro|monstro")){
                        temBixo=true;
                    }
                    if(matPosicao[i][j-1].getAmbiente().equals("poço")){
                        tempoco=true;
                    }
                }
                if (i<tamanho-1) {
                    if(matPosicao[i+1][j].getAmbiente().equals("monstro")||matPosicao[i+1][j].getAmbiente().equals("ouro|monstro")){
                        temBixo=true;
                    }
                    if(matPosicao[i+1][j].getAmbiente().equals("poço")){
                        tempoco=true;
                    }
                }
                if (i>0) {
                    if(matPosicao[i-1][j].getAmbiente().equals("monstro")||matPosicao[i-1][j].getAmbiente().equals("ouro|monstro")){
                        temBixo=true;
                    }
                    if(matPosicao[i-1][j].getAmbiente().equals("poço")){
                        tempoco=true;
                    }
                }
                if (matPosicao[i][j].getAmbiente().equals("ouro")) {
                    temouro=true;
                }
                if (temBixo&&tempoco) {
                    matPosicao[i][j].setSentido("fedor|brisa");
                }
                else if (temBixo&&temouro&&tempoco) {
                    matPosicao[i][j].setSentido("brilho|fedor|brisa");
                }
                else if (temBixo&&temouro) {
                    matPosicao[i][j].setSentido("brilho|fedor");
                }
                else if (tempoco&&temouro) {
                    matPosicao[i][j].setSentido("brilho|brisa");
                }
                else if (matPosicao[i][j].getAmbiente().equals("ouro")||matPosicao[i][j].getAmbiente().equals("ouro|monstro")) {
                    matPosicao[i][j].setSentido("brilho");
                }
                else if(tempoco){
                    matPosicao[i][j].setSentido("brisa");
                }
                else if(temBixo){
                    matPosicao[i][j].setSentido("fedor");
                }
                else{
                    matPosicao[i][j].setSentido("safe");
                }
            }
        }
    }
    public static void printarmapa(){
        String tafarel;
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tafarel=M[i][j];
                System.out.print("["+tafarel+"]");
            }
            System.out.println();
        }
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }
    public static void printarjogo() {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                System.out.print("["+M[i][j]+"]");
            }
            System.out.print("\n");
        }
    }
    public static void iniciarjogo(){
        while (rodadas<NUMERO_MAX_GERACOES) {
            morte=0;
            pegouOouro=0;
            matouobixo2=0;
            terror=0;
            queda=0;
            for (int t = 0; t < matPosicao.length; t++) {
                for (int q = 0; q < matPosicao.length; q++) {
                    if (matPosicao[t][q].getAmbiente().equals("agente")) M[t][q]="A";
                    else if (matPosicao[t][q].getAmbiente().equals("monstro")) M[t][q]="M";
                    else if (matPosicao[t][q].getAmbiente().equals("poço")) M[t][q]="P";
                    else if (matPosicao[t][q].getAmbiente().equals("ouro")) M[t][q]="O";
                    else if (matPosicao[t][q].getAmbiente().equals("ouro|monstro")) M[t][q]="O|M";
                    else M[t][q]=" ";
                }
            }
            // try{
            //     Thread.sleep(1000);
            // }catch(InterruptedException e){

            // }
            // limparConsole();
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    System.out.print("["+M[i][j]+"]");
                }
                System.out.print("\n");
            }
            int ind=0;
            Individuo zidane;
            while (ind<TAMANHO_POPULACAO) {
                //System.out.println("Resetou. Individuo: "+ind);
                int fitness=0;
                zidane=cidadao.getIndividuo(ind);
                int indicedogene=0;
                int shot=quantidadeWumpos;
                String tipo;
                t=inicio;
                q=agente;
                Boolean ouro=false;
                estado="Vivo";   
                System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                Boolean first=true, jogo=true;
                while (jogo) {
                    if (indicedogene<=calcul) {
                        indo = zidane.getGene(indicedogene);
                    }
                    //System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    if (matPosicao[t][q].getSentido().equals("safe")) {
                        tipo=matPosicao[t][q].getTipo();
                        direcao(tipo,t,q,indo);
                        //System.out.println("Indo para direcao: "+indo);
                    }
                    else if (matPosicao[t][q].getSentido().equals("fedor|brisa")||matPosicao[t][q].getSentido().equals("fedor")) {
                        tipo=matPosicao[t][q].getTipo();
                        direcao(tipo,t,q,indo);
                        if (glock) {
                            //System.out.println("Atira para o: "+indo);
                            //System.out.println("Indo para direcao: "+indo);
                            if (shot>0) {
                                shot--;
                            }
                        }
                        else{
                            //System.out.println("Nao atirou");
                            //System.out.println("Indo para direcao: "+indo);
                        }
                    }
                    else if(matPosicao[t][q].getSentido().equals(("brisa"))){
                        tipo=matPosicao[t][q].getTipo();
                        direcao(tipo,t,q,indo);
                    }
                    else if(matPosicao[t][q].getSentido().equals("brilho")||matPosicao[t][q].getSentido().equals("brilho|brisa")||matPosicao[t][q].getSentido().equals("brilho|fedor|brisa")||matPosicao[t][q].getSentido().equals("brilho|fedor")){
                        //System.out.print("entrou");
                        tipo=matPosicao[t][q].getTipo();
                        direcao(tipo,t,q,indo);
                        if(first){
                            //System.out.print("entrou");
                            if(!M[t][q].equals("O")){ 
                                M[t][q]=" ";
                                System.out.println("Pegou o ouro");
                                pegouOouro++;
                                ouro=true;
                                fitness+=2000;
                                //zidane.setFitness(zidane.getFitness()+2000);
                                first=false;
                            }
                        }
                        else{
                            fitness-=10;
                            //zidane.setFitness(zidane.getFitness()-10);
                        }
                    }
                    if(bateu==false){
                        //System.out.println("Entrou, indo:["+indo+"]\n");
                        if (indo=='N') {
                            M[t][q]=" ";
                            t=t-1;
                            M[t][q]="A";
                            if (matPosicao[t][q].getAmbiente().contains("monstro")){
                                if (!glock) {
                                    estado="morto";
                                    terror++;
                                    M[t][q]="M";
                                    fitness-=500;
                                    //zidane.setFitness(zidane.getFitness()-500);
                                    //System.out.println("O monstro te matou GAME OVER!");
                                }
                                else{
                                    fitness+=1500;
                                    //zidane.setFitness(zidane.getFitness()+1500);
                                    matouobixo2++;
                                }
                            }
                            else if (matPosicao[t][q].getAmbiente().contains("poço")) {
                                estado="morto";
                                queda++;
                                //zidane.setFitness(zidane.getFitness()-500);
                                fitness-=500;
                                //System.out.println("Caiu no poço GAME OVER!");
                            }
                            if (estado.contains("Vivo")) {
                                fitness+=20;
                                //zidane.setFitness(zidane.getFitness()+20);
                                //System.out.println("Jogo continua");
                            }
                        }
                        if (indo=='S') {
                            M[t][q]=" ";
                            t=t+1;
                            M[t][q]="A";
                            if (matPosicao[t][q].getAmbiente().equals("monstro")){
                                if (!glock) {
                                    estado="morto";
                                    fitness-=500;
                                    //zidane.setFitness(zidane.getFitness()-500);
                                    terror++;
                                    M[t][q]="M";
                                    //System.out.println("O monstro te matou GAME OVER!");
                                }
                                else{
                                    fitness-=50;
                                    //zidane.setFitness(zidane.getFitness()-50);
                                    matouobixo2++;
                                }
                            }
                            else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                                estado="morto";
                                fitness-=500;
                                //zidane.setFitness(zidane.getFitness()-500);
                                queda++;
                                //System.out.println("Caiu no poço GAME OVER!");
                            }
                            if (estado.equals("Vivo")) {
                                fitness+=20;
                                //zidane.setFitness(zidane.getFitness()+20);
                                //System.out.println("Jogo continua");
                            }
                        }
                        if (indo=='O') {
                            M[t][q]=" ";
                            q=q-1;
                            M[t][q]="A";
                            if (matPosicao[t][q].getAmbiente().equals("monstro")){
                                if (!glock) {
                                    estado="morto";
                                    fitness-=500;
                                    //zidane.setFitness(zidane.getFitness()-500);
                                    terror++;
                                    M[t][q]="M";
                                    //System.out.println("O monstro te matou GAME OVER!");
                                }
                                else{
                                    fitness+=1500;
                                    //zidane.setFitness(zidane.getFitness()+1500);
                                    matouobixo2++;
                                }
                            }
                            else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                                estado="morto";
                                fitness-=500;
                                //zidane.setFitness(zidane.getFitness()-500);
                                queda++;
                               //System.out.println("Caiu no poço GAME OVER!");
                            }
                            if (estado.equals("Vivo")) {
                                fitness+=20;
                                //zidane.setFitness(zidane.getFitness()+20);
                                //System.out.println("Jogo continua");
                            }
                        }
                        if (indo=='L') {
                            M[t][q]=" ";
                            q=q+1;
                            M[t][q]="A";
                            if (matPosicao[t][q].getAmbiente().equals("monstro")){
                                if (!glock) {
                                    estado="morto";
                                    fitness-=500;
                                    //zidane.setFitness(zidane.getFitness()-500);
                                    terror++;
                                    M[t][q]="M";
                                    //System.out.println("O monstro te matou GAME OVER!");
                                }
                                else{
                                    fitness+=1500;
                                    //zidane.setFitness(zidane.getFitness()+1500);
                                    matouobixo2++;
                                }
                            }
                            else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                                estado="morto";
                                fitness-=500;
                                //zidane.setFitness(zidane.getFitness()-500);
                                queda++;
                                //System.out.println("Caiu no poço GAME OVER!");
                            }
                            if (estado.equals("Vivo")) {
                                fitness+=20;
                                //zidane.setFitness(zidane.getFitness()+20);
                                //System.out.println("Jogo continua");
                            }
                        }
                    }
                    else{
                        fitness-=10;
                        //zidane.setFitness(zidane.getFitness()-10);
                        bateu=false;
                    }
                        
                    // try{
                    //     Thread.sleep(500);
                    // }catch(InterruptedException e){

                    // }
                    // limparConsole();
                    // printarjogo();
                    indicedogene++;
                    //System.out.println("Individuo: "+ind+" Fitness atual: "+fitness);
                    if (estado.equals("morto")||indicedogene==calcul) {
                        morte++;
                        jogo=false;
                        ind++;
                        zidane.setFitness(fitness);
                    }
                    if (ouro) {
                        String gg=matPosicao[inicio][agente].getAmbiente();
                        if (matPosicao[t][q].getAmbiente().equals(gg)) {
                            //System.out.println("Parabens,Ganhou o Jogo");
                            sobreviveu++;
                            jogo=false;
                        }
                        // try{
                        //     Thread.sleep(500);
                        // }catch(InterruptedException e){
    
                        // }
                    }
                }
                
            }
            System.out.print("\nRelatorio: \nGeracao " +geracao+ "\nMorreu: "+morte+" vezes\nPegou o ouro: "+pegouOouro+" vezes\nMatou o monstro: "+matouobixo2+" vezes\nO monstro matou: "+terror+" vezes\nCaiu no poço: "+queda+" vezes\n");
            if (sobreviveu>1) {
                System.out.println("Sobreviveu "+sobreviveu+" vezes");
            }
            if (sobreviveu==1) {
                System.out.println("Sobreviveu "+sobreviveu+" vez");
            }
            if (sobreviveu<1) {
                System.out.println("Sobreviveu nenhuma vez");
            }
            cidadao = testecal.evolverPopulacao(cidadao,ListaP);
            geracao++;
            rodadas++;
            
        }
        System.out.println("\n");
        for(int i=0; i<ListaP.listasize()-1; i++){
            cidadao=ListaP.getLista(i);
            System.out.println("Geracao: "+(i+1));
            for (int j = 0; j < TAMANHO_POPULACAO; j++) {
                if(cidadao.getIndividuo(j).getFitness()>0) System.out.println("Fitness: "+cidadao.getIndividuo(j).getFitness());
            }
            System.out.println("\n");
        }
    }
    public static void voltar(int lin, int col) {
        Coordenadas voltar;
        for (int i = trajeto.size()-1; i >= 0; i--) {
            voltar=trajeto.getCaminho(i);
            M[lin][col]=" ";
            M[voltar.getLinha()][voltar.getColuna()]="A";
            lin=voltar.getLinha();
            col=voltar.getColuna();
            printarjogo();
            System.out.println("===================================");
        }
        sobreviveu++;
    }
    public static void main(String[] args) {
        System.gc();
        limparConsole();
        System.out.print("Tamanho: ");
	    tamanho=tc.nextInt();
        calcul=tamanho/2+tamanho;
        matPosicao=new Posicao [tamanho][tamanho];
        criarambiente();
        criarsentido();
        printarmatrizambiente();
        printarmatrizsentido();
        M = new String [tamanho][tamanho];
        inicio=0;
        agente=0;
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        printarmatrizambiente();
        printarmatrizsentido();
        
        testecal = new AG_MW(calcul);
        // Inicialização da população
        cidadao = new Populacao(TAMANHO_POPULACAO, true,calcul);
        
        ListaP = new ListaPopulacao();
        ListaP.addLista(cidadao);
        iniciarjogo();
    }
}