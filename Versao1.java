import java.util.Scanner;
import java.util.*;
import java.lang.Thread;
import java.time.temporal.IsoFields;

public class Versao1 {
    public static int tamanho,x,y,choice;
    public static Posicao matPosicao[][];
    public static String testetipo, fantasma="vivo",sentido="",estado;
    public static char movimentaçao[];
    public static int mov;
    public static Random generador = new Random();
    private static Scanner tc = new Scanner(System.in);
    private static char lebronjames;

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
        choice=generador.nextInt(3);
        if (choice==0) {
            matPosicao[0][0].setAmbiente("agente");
        }
        else if (choice==1) {
            matPosicao[0][tamanho-1].setAmbiente("agente");
        }
        else if (choice==2) {
            matPosicao[tamanho-1][0].setAmbiente("agente");
        }
        else if (choice==3) {
            matPosicao[tamanho-1][tamanho-1].setAmbiente("agente");
        }
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (matPosicao[i][j].getAmbiente().equals("")) {
                    conte++;
                }
            }
            System.out.println();
        }
        conte=0;
        System.out.println("Cont: "+conte);
        int onepiece=6;
        
        x=generador.nextInt(tamanho);
        y=generador.nextInt(tamanho);
        while (!matPosicao[x][y].getAmbiente().equals("")){
            x=generador.nextInt(tamanho);
            y=generador.nextInt(tamanho);
            System.out.println("to preso");
        }
        matPosicao[x][y].setAmbiente("ouro");
        System.out.println("passou");
        if (x==0 && y==0) {
            onepiece=0;
        }
        else if (x==0 && y==tamanho-1) {
            onepiece=1;
        }
        else if (x==tamanho-1 && y==0) {
            onepiece=2;
        }
        else if (x==tamanho-1 && y==tamanho-1) {
            onepiece=3;
        }
        x=generador.nextInt(tamanho);
        y=generador.nextInt(tamanho);

        while(teste4){
            if (matPosicao[x][y].getAmbiente().equals("agente")){
                x=generador.nextInt(tamanho);
                y=generador.nextInt(tamanho);
            }
            else{
            
                if (matPosicao[x][y].getAmbiente().equals("ouro")){
                    System.out.println("entrou");
                    matPosicao[x][y].setAmbiente("ouro|monstro");
                    teste4=false;
                }
                else{
                    matPosicao[x][y].setAmbiente("monstro");
                    teste4=false;
                }
            }
        }
        int pocos=0;
        while(pocos!=3) {
            Boolean chega=true;
            x=generador.nextInt(tamanho);
            y=generador.nextInt(tamanho);
            while (chega){
            
                if (choice==0||onepiece==0){
                    if((x==0 && y==1)||(x==1 && y==0)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else{
                        chega=false;
                    }
                }
                if (choice==1||onepiece==1){
                    if((x==0 && y==tamanho-2)||(x==1 && y==tamanho-1)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else{
                        chega=false;
                    }
                }
                if (choice==2||onepiece==2){
                    if((x==tamanho-2 && y==0)||(x==tamanho-1 && y==1)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else{
                        chega=false;
                    }
                }
                if (choice==3||onepiece==3){
                    if((x==tamanho-1 && y==tamanho-2)||(x==tamanho-2 && y==tamanho-1)){
                        x=generador.nextInt(tamanho);
                        y=generador.nextInt(tamanho);
                    }
                    else{
                        chega=false;
                    }
                }
            }
            if(matPosicao[x][y].getAmbiente().equals("")){
                matPosicao[x][y].setAmbiente("poço");
                pocos++;
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
    public static char direcao(Boolean direcao, String tipo, int i, int j) {
          
        if (tipo.equals("canto")) {
            while (direcao) {
                if (i==tamanho-1 && j==0) {
                    movimentaçao=new char[] {'N','L'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                else if (i==0 && j==0) {
                    movimentaçao=new char[] {'S','L'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                else if (i==0 && j==tamanho-1) {
                    movimentaçao=new char[] {'S','O'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                else if (i==tamanho-1 && j==tamanho-1) {
                    movimentaçao=new char[] {'N','O'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                direcao=false;
            }
        } 
        else if (tipo.equals("parede")) {
            while (direcao) {
                if (i<tamanho-1 && i>0 && j==0) {
                    movimentaçao=new char[] {'N','S','L'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                else if (i==0 && j>0 && j<tamanho-1) {
                    movimentaçao=new char[] {'S','O','L'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                else if (i==tamanho-1 && j>0 && j<tamanho-1) {
                    movimentaçao=new char[] {'N','O','L'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                else if (i>0 && i<tamanho-1 && j==tamanho-1) {
                    movimentaçao=new char[] {'N','S','O'};
                    mov=generador.nextInt(movimentaçao.length);
                }
                direcao=false;
            }
        }
        else {
            movimentaçao=new char[] {'N','S','O','L'};
            mov=generador.nextInt(movimentaçao.length);
        }
        
        lebronjames=movimentaçao[mov];
        return lebronjames;
    }
    public static String atirar(){
        String acao[]=new String[] {"atirar","nao atirar"};
        int dec = generador.nextInt(acao.length);
        String shotgun=acao[dec];
        return shotgun;
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
                    if (matPosicao[i][j+1].getAmbiente().equals("ouro|monstro")||matPosicao[i][j+1].getAmbiente().equals("ouro")) {
                        temouro=true;
                    }
                }
                if (j>0) {
                    if(matPosicao[i][j-1].getAmbiente().equals("monstro")||matPosicao[i][j-1].getAmbiente().equals("ouro|monstro")){
                        temBixo=true;
                    }
                    if(matPosicao[i][j-1].getAmbiente().equals("poço")){
                        tempoco=true;
                    }
                    if (matPosicao[i][j-1].getAmbiente().equals("ouro|monstro")||matPosicao[i][j-1].getAmbiente().equals("ouro")) {
                        temouro=true;
                    }
                }
                if (i<tamanho-1) {
                    if(matPosicao[i+1][j].getAmbiente().equals("monstro")||matPosicao[i+1][j].getAmbiente().equals("ouro|monstro")){
                        temBixo=true;
                    }
                    if(matPosicao[i+1][j].getAmbiente().equals("poço")){
                        tempoco=true;
                    }
                    if (matPosicao[i+1][j].getAmbiente().equals("ouro|monstro")||matPosicao[i+1][j].getAmbiente().equals("ouro")) {
                        temouro=true;
                    }
                }
                if (i>0) {
                    if(matPosicao[i-1][j].getAmbiente().equals("monstro")||matPosicao[i-1][j].getAmbiente().equals("ouro|monstro")){
                        temBixo=true;
                    }
                    if(matPosicao[i-1][j].getAmbiente().equals("poço")){
                        tempoco=true;
                    }
                    if (matPosicao[i-1][j].getAmbiente().equals("ouro|monstro")||matPosicao[i-1][j].getAmbiente().equals("ouro")) {
                        temouro=true;
                    }
                }
                if (temBixo&&tempoco) {
                    matPosicao[i][j].setSentido("fedor|brisa");
                }
                else if(tempoco){
                    matPosicao[i][j].setSentido("brisa");
                }
                else if(temBixo){
                    matPosicao[i][j].setSentido("fedor");
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
                else{
                    matPosicao[i][j].setSentido("seguro");
                }
            }
        }
    }
    

    public static void main(String[] args) {
        System.gc();
        limparConsole();
        System.out.print("Tamanho: ");
	    tamanho = tc.nextInt();
        matPosicao = new Posicao [tamanho][tamanho];
        criarambiente();
        criarsentido();
        printarmatrizambiente();
        printarmatrizsentido();
        int rodadas=0;
        int morte=0,pegouOouro=0,sobreviveu=0,queda=0,terror=0,matouobixo2=0,inicio=0,agente=0;
        String M[][]=new String [tamanho][tamanho];
        for (int t = 0; t < matPosicao.length; t++) {
            for (int q = 0; q < matPosicao.length; q++) {
                if (matPosicao[t][q].getAmbiente().equals("agente")) {
                    //M[i][j]='A';
                    inicio=t;
                    agente=q;
                }
            }
        }
        while (rodadas<3) {
            
            for (int t = 0; t < matPosicao.length; t++) {
                for (int q = 0; q < matPosicao.length; q++) {
                    if (matPosicao[t][q].getAmbiente().equals("agente")) {
                        M[t][q]="A";
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("monstro")) {
                        M[t][q]="M";
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                        M[t][q]="P";
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("ouro")) {
                        M[t][q]="O";
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("ouro|monstro")) {
                        M[t][q]="O|M";
                    }
                    else{
                        M[t][q]=" ";
                    }
                }
            }
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){

            }
            limparConsole();
            for (int i = 0; i < M.length; i++) {
                for (int j = 0; j < M.length; j++) {
                    System.out.print("["+M[i][j]+"]");
                }
                System.out.print("\n");
            }
            int shot=1;
            Boolean ouro=false, jogo=true;
            char indo;
            String tipo,glock="";
            int t=0,q=0;
            t=inicio;
            q=agente;
            shot=1;
            //mov='C';
            ouro=false;
            jogo=true;
            estado="Vivo";   
            System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            while (jogo) {
                
                Boolean first=true;
                System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                
                if (matPosicao[t][q].getSentido().equals("fedor|brisa")||matPosicao[t][q].getSentido().equals("fedor")) {
                    tipo=matPosicao[t][q].getTipo();
                    indo=direcao(jogo,tipo,t,q);
                    glock=atirar();
                    if (glock.equals("atirar")) {
                        System.out.println("Atira para o: "+indo);
                        System.out.println("Indo para direcao: "+indo);
                        shot=0;
                    }
                    else{
                        System.out.println("Nao atirou");
                        System.out.println("Indo para direcao: "+indo);
                    }
                }
                else if(matPosicao[t][q].getSentido().equals("brilho")||matPosicao[t][q].getSentido().equals("brilho|brisa")||matPosicao[t][q].getSentido().equals("brilho|fedor|brisa")||matPosicao[t][q].getSentido().equals("brilho|fedor")){
                    if(first){
                        if(M[t][q]=="O"){ 
                            ouro=true;
                            M[t][q]=" ";
                            System.out.println("Pegou o ouro");
                            first=false;
                        }
                    }
                    tipo=matPosicao[t][q].getTipo();
                    indo=direcao(jogo,tipo,t,q);
                    System.out.println("Indo para direcao: "+indo);
                }
                else{
                    tipo=matPosicao[t][q].getTipo();
                    indo=direcao(jogo,tipo,t,q);
                    System.out.println("Indo para direcao: "+indo);
                }
                if (indo=='N') {
                    M[t][q]=" ";
                    t=t-1;
                    M[t][q]="A";
                    if (matPosicao[t][q].getAmbiente().equals("monstro")){
                        if (glock.equals("nao atirar")) {
                            estado="morto";
                            terror++;
                            M[t][q]="M";
                            System.out.println("O monstro te matou GAME OVER!");
                        }
                        else{
                            matouobixo2++;
                        }
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                        estado="morto";
                        queda++;
                        System.out.println("Caiu no poço GAME OVER!");
                    }
                    if (estado.equals("Vivo")) {
                        System.out.println("Jogo continua");
                    }
                }
                if (indo=='S') {
                    M[t][q]=" ";
                    t=t+1;
                    M[t][q]="A";
                    if (matPosicao[t][q].getAmbiente().equals("monstro")){
                        if (glock.equals("nao atirar")) {
                            estado="morto";
                            terror++;
                            M[t][q]="M";
                            System.out.println("O monstro te matou GAME OVER!");
                        }
                        else{
                            matouobixo2++;
                        }
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                        estado="morto";
                        queda++;
                        System.out.println("Caiu no poço GAME OVER!");
                    }
                    if (estado.equals("Vivo")) {
                        System.out.println("Jogo continua");
                    }
                }
                if (indo=='O') {
                    M[t][q]=" ";
                    q=q-1;
                    M[t][q]="A";
                    if (matPosicao[t][q].getAmbiente().equals("monstro")){
                        if (glock.equals("nao atirar")) {
                            estado="morto";
                            terror++;
                            M[t][q]="M";
                            System.out.println("O monstro te matou GAME OVER!");
                        }
                        else{
                            matouobixo2++;
                        }
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                        estado="morto";
                        queda++;
                        System.out.println("Caiu no poço GAME OVER!");
                    }
                    if (estado.equals("Vivo")) {
                        System.out.println("Jogo continua");
                    }
                }
                if (indo=='L') {
                    M[t][q]=" ";
                    q=q+1;
                    M[t][q]="A";
                    if (matPosicao[t][q].getAmbiente().equals("monstro")){
                        if (glock.equals("nao atirar")) {
                            estado="morto";
                            terror++;
                            M[t][q]="M";
                            System.out.println("O monstro te matou GAME OVER!");
                        }
                        else{
                            matouobixo2++;
                        }
                    }
                    else if (matPosicao[t][q].getAmbiente().equals("poço")) {
                        estado="morto";
                        queda++;
                        System.out.println("Caiu no poço GAME OVER!");
                    }
                    if (estado.equals("Vivo")) {
                        System.out.println("Jogo continua");
                    }
                }
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){

                }
                limparConsole();
                for (int i = 0; i < M.length; i++) {
                    for (int j = 0; j < M.length; j++) {
                        System.out.print("["+M[i][j]+"]");
                    }
                    System.out.print("\n");
                }
                if (ouro) {
                    String gg=matPosicao[inicio][agente].getAmbiente();
                    pegouOouro++;
                    if (matPosicao[t][q].getAmbiente().equals(gg)) {
                        System.out.println("Parabens,Ganhou o Jogo");
                        sobreviveu++;
                        jogo=false;
                    }
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){

                    }
                }
                if (estado.equals("morto")) {
                    morte++;
                    jogo=false;
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){

                    }
                }
            }
            rodadas++;
        }  
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        printarmatrizambiente();
        System.out.print("Relatorio: \nMorreu: "+morte+" vezes\nPegou o ouro: "+pegouOouro+" vezes\nMatou o monstro: "+matouobixo2+" vezes\nO monstro matou: "+terror+" vezes\nCaiu no poço: "+queda+" vezes\n");
        if (sobreviveu>1) {
            System.out.println("Sobreviveu "+sobreviveu+" vezes");
        }
        if (sobreviveu==1) {
            System.out.println("Sobreviveu "+sobreviveu+" vez");
        }
        if (sobreviveu<1) {
            System.out.println("Sobreviveu nenhuma vez");
        }
         
    }
}