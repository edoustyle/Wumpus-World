import java.util.*;
import java.lang.Thread;

public class Versao2 {
    public static int tamanho,x,y,t,q,choice,mov,morte=0,pegouOouro=0,sobreviveu=0,queda=0,terror=0,matouobixo2=0,inicio=0,agente=0,rodadas=0,quantidadePocos,quantidadeWumpos;
    public static Posicao matPosicao[][];
    public static String testetipo, fantasma="vivo",sentido="",estado, Mapeamento[][],M[][];
    public static char movimentaçao[],indo=' ',perigoatual='N';
    public static Random generador = new Random();
    private static Scanner tc = new Scanner(System.in);
    private static char lebronjames,perigo;
    public static Boolean glock=false,teste21=true;
    public static Trajeto trajeto;

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
    public static char direcao(String tipo, int i, int j, char situacao, int linhaANT, int colunaANT) {
        verificacao(i,j);
        t=i;
        q=j;
        if (situacao=='F'||situacao=='B'||situacao=='D') {
            if (linhaANT>i) {
                lebronjames='S';
            }
            else if (linhaANT<i) {
                lebronjames='N';
            }
            else if (colunaANT>j) {
                lebronjames='L';
            }
            else if (colunaANT<j) {
                lebronjames='O';
            }
        }
        else {
            if (tipo.equals("canto")) {
                
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
            } 
            else if (tipo.equals("parede")) {
                
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
            }
            else {
                movimentaçao=new char[] {'N','S','O','L'};
                mov=generador.nextInt(movimentaçao.length);
            }
            lebronjames=movimentaçao[mov];
            if (lebronjames=='N') {
                if (Mapeamento[i-1][j].equals("poço")) {
                    System.out.println("entrou");
                    direcao(tipo, i, j, situacao, linhaANT, colunaANT);
                }
            }
            else if (lebronjames=='S') {
                if (Mapeamento[i+1][j].equals("poço")) {
                    System.out.println("entrou");
                    direcao(tipo, i, j, situacao, linhaANT, colunaANT);
                }
            }
            else if (lebronjames=='O') {
                if (Mapeamento[i][j-1].equals("poço")) {
                    System.out.println("entrou");
                    direcao(tipo, i, j, situacao, linhaANT, colunaANT);
                }
            }
            else if (lebronjames=='L') {
                if (Mapeamento[i][j+1].equals("poço")) {
                    System.out.println("entrou");
                    direcao(tipo, i, j, situacao, linhaANT, colunaANT);
                }
            }
        }
        return lebronjames;
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
    public static void cauteloso(String tipo, int i, int j, String sentidoAtual){
        int contw=0, contp=0,t,q;
        
        if (tipo.equals("canto")) {

            if (sentidoAtual.equals("fedor")) {
                if (i==tamanho-1 && j==0) {
                    //{'N','L'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoMonstro";
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoMonstro";
                    }
                }
                else if (i==0 && j==tamanho-1) {
                    //{'S','O'};
                    if(!Mapeamento[i+1][j].equals("safe")){
                        Mapeamento[i+1][j]="RiscoMonstro";
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoMonstro";
                    }
                }
                else if (i==tamanho-1 && j==tamanho-1) {
                    //{'N','O'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoMonstro";
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoMonstro";
                    }
                }
            }
            else if (sentidoAtual.equals("brisa")) {
                if (i==tamanho-1 && j==0) {
                    //{'N','L'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoPoço";
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoPoço";
                    }
                }
                else if (i==0 && j==tamanho-1) {
                    //{'S','O'};
                    if(!Mapeamento[i+1][j].equals("safe")){
                        Mapeamento[i+1][j]="RiscoPoço";
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoPoço";
                    }
                }
                else if (i==tamanho-1 && j==tamanho-1) {
                    //{'N','O'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoPoço";
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoPoço";
                    }
                }
            }
        } 
        else if (tipo.equals("parede")) {
            if (sentidoAtual.equals("fedor")) {
                if (i<tamanho-1 && i>0 && j==0) {
                    //{'N','S','L'}Esquerda;
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoMonstro";
                        contw++;
                        t=i;
                        q=j;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoMonstro";
                        contw++;
                        t=i;
                        q=j;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoMonstro";
                        contw++;
                        t=i;
                        q=j;
                    }
                }
                else if (i==0 && j>0 && j<tamanho-1) {
                    //{'S','O','L'}Cima;
                    if(!Mapeamento[i][j-1].equals("safe")){
                        Mapeamento[i][j-1]="RiscoMonstro";
                        contw++;
                        t=i;
                        q=j;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoMonstro";
                        contw++;
                        t=i;
                        q=j;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoMonstro";
                        contw++;
                        t=i;
                        q=j;
                    }
                }
                else if (i==tamanho-1 && j>0 && j<tamanho-1) {
                    //{'N','O','L'}Inferior;
                    if(!Mapeamento[i][j-1].equals("safe")){
                        Mapeamento[i][j-1]="RiscoMonstro";
                        contw++;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoMonstro";
                        contw++;
                    }
                    if (!Mapeamento[i-1][j].equals("safe")) {
                        Mapeamento[i-1][j]="RiscoMonstro";
                        contw++;
                    }
                }
                else if (i>0 && i<tamanho-1 && j==tamanho-1) {
                    //{'N','S','O'}Direita;
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoMonstro";
                        contw++;
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoMonstro";
                        contw++;
                    }
                    if (!Mapeamento[i-1][j].equals("safe")) {
                        Mapeamento[i-1][j]="RiscoMonstro";
                        contw++;
                    }
                }
                System.out.println("Possivel monstro detectado");
            }
            else if (sentidoAtual.equals("brisa")){
                if (i<tamanho-1 && i>0 && j==0) {
                    //{'N','S','L'};
                    if(Mapeamento[i-1][j]!="safe"){
                        Mapeamento[i-1][j]="RiscoPoço";
                        contp++;
                    }
                    if (Mapeamento[i][j+1]!="safe") {
                        Mapeamento[i][j+1]="RiscoPoço";
                        contp++;
                    }
                    if (Mapeamento[i+1][j]!="safe") {
                        Mapeamento[i+1][j]="RiscoPoço";
                        contp++;
                    }
                }
                else if (i==0 && j>0 && j<tamanho-1) {
                    //{'S','O','L'};
                    if(!Mapeamento[i][j-1].equals("safe")){
                        Mapeamento[i][j-1]="RiscoPoço";
                        contp++;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoPoço";
                        contp++;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoPoço";
                        contp++;
                    }
                }
                else if (i==tamanho-1 && j>0 && j<tamanho-1) {
                    //{'N','O','L'};
                    if(!Mapeamento[i][j-1].equals("safe")){
                        Mapeamento[i][j-1]="RiscoPoço";
                        contp++;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoPoço";
                        contp++;
                    }
                    if (!Mapeamento[i-1][j].equals("safe")) {
                        Mapeamento[i-1][j]="RiscoPoço";
                        contp++;
                    }
                }
                else if (i>0 && i<tamanho-1 && j==tamanho-1) {
                    //{'N','S','O'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoPoço";
                        contp++;
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoPoço";
                        contp++;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoPoço";
                        contp++;
                    }
                }
                System.out.println("Possivel poço detectados.");
            }
            else if (sentidoAtual.equals("fedor|brisa")) {
                if (i<tamanho-1 && i>0 && j==0) {
                    //{'N','S','L'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                }
                else if (i==0 && j>0 && j<tamanho-1) {
                    //{'S','O','L'};
                    if(!Mapeamento[i][j-1].equals("safe")){
                        Mapeamento[i][j-1]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                }
                else if (i==tamanho-1 && j>0 && j<tamanho-1) {
                    //{'N','O','L'};
                    if(!Mapeamento[i][j-1].equals("safe")){
                        Mapeamento[i][j-1]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i][j+1].equals("safe")) {
                        Mapeamento[i][j+1]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i-1][j].equals("safe")) {
                        Mapeamento[i-1][j]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                }
                else if (i>0 && i<tamanho-1 && j==tamanho-1) {
                    //{'N','S','O'};
                    if(!Mapeamento[i-1][j].equals("safe")){
                        Mapeamento[i-1][j]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i][j-1].equals("safe")) {
                        Mapeamento[i][j-1]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                    if (!Mapeamento[i+1][j].equals("safe")) {
                        Mapeamento[i+1][j]="RiscoDuplo";
                        contp++;
                        contw++;
                    }
                }
                System.out.println("Possivel monstro e poços detectados.");
            }
        }
        else {
            //{'N','S','O','L'};

            if (sentidoAtual.equals("fedor")) {
                if (!Mapeamento[i][j+1].equals("safe")){
                    Mapeamento[i][j+1]="RiscoMonstro";
                    contw++;
                }
                if (!Mapeamento[i][j-1].equals("safe")) {
                    Mapeamento[i][j-1]="RiscoMonstro";
                    contw++;
                }
                if (!Mapeamento[i+1][j].equals("safe")) {
                    Mapeamento[i+1][j]="RiscoMonstro";
                    contw++;
                }
                if (!Mapeamento[i-1][j].equals("safe")) {
                    Mapeamento[i-1][j]="RiscoMonstro";
                    contw++;
                }
                System.out.println("Possivel monstro detectado");
            }
            else if (sentidoAtual.equals("brisa")) {
                if (!Mapeamento[i][j-1].equals("safe")){
                    Mapeamento[i][j-1]="RiscoPoço";
                    contp++;
                }
                if (!Mapeamento[i][j+1].equals("safe")) {
                    Mapeamento[i][j+1]="RiscoPoço";
                    contp++;
                }
                if (!Mapeamento[i+1][j].equals("safe")) {
                    Mapeamento[i+1][j]="RiscoPoço";
                    contp++;
                }
                if (!Mapeamento[i-1][j].equals("safe")) {
                    Mapeamento[i-1][j]="RiscoPoço";
                    contp++;
                }
                System.out.println("Possivel poço detectados.");
            }
            else if (sentidoAtual.equals("fedor|brisa")) {
                if (!Mapeamento[i][j-1].equals("safe")){
                    Mapeamento[i][j-1]="RiscoDuplo";
                    contp++;
                    contw++;
                }
                if (!Mapeamento[i][j+1].equals("safe")) {
                    Mapeamento[i][j+1]="RiscoDuplo";
                    contp++;
                    contw++;
                }
                if (!Mapeamento[i+1][j].equals("safe")) {
                    Mapeamento[i+1][j]="RiscoDuplo";
                    contp++;
                    contw++;
                }
                if (!Mapeamento[i-1][j].equals("safe")) {
                    Mapeamento[i-1][j]="RiscoDuplo";
                    contp++;
                    contw++;
                }
                System.out.println("Possivel monstro e poços detectados.");
            }
        }
        if (sentidoAtual.equals("fedor")) {
            perigo='F';
        }
        else if (sentidoAtual.equals("brisa")) {
            perigo='B';
        }
        else if (sentidoAtual.equals("fedor|brisa")) {
            perigo='D';
        }
        else if (sentidoAtual.equals("seguro")){
            perigo='N';
        }
    }
    public static void verificacao(int i, int j){
        int contpoço=0,contwumpus=0;
        if (!Mapeamento[i][j].equals("safe")) {
            //paredes
            if (i==0 && j>0 && j<tamanho-1) { //parede superior
                if (Mapeamento[i][j+1].equals("Riscopoço")||Mapeamento[i][j-1].equals("Riscopoço")||Mapeamento[i+1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j+1].equals("RiscoMonstro")||Mapeamento[i][j-1].equals("RiscoMonstro")||Mapeamento[i+1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j+1].equals("RiscoDuplo")||Mapeamento[i][j-1].equals("RiscoDuplo")||Mapeamento[i+1][j].equals("RiscoDuplo")) 
                {   contwumpus++;
                    contpoço++;
                }
            }
            else if (i>0 && j==0 && i<tamanho-1) { //parede esquerda

                if (Mapeamento[i][j+1].equals("Riscopoço")||Mapeamento[i-1][j].equals("Riscopoço")||Mapeamento[i+1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j+1].equals("RiscoMonstro")||Mapeamento[i-1][j].equals("RiscoMonstro")||Mapeamento[i+1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j+1].equals("RiscoDuplo")||Mapeamento[i-1][j].equals("RiscoDuplo")||Mapeamento[i+1][j].equals("RiscoDuplo"))
                {   contwumpus++;
                    contpoço++;
                }
            }
            else if (i==tamanho-1 && j>0 && j<tamanho-1) { //parede inferior
                
                if (Mapeamento[i][j+1].equals("Riscopoço")||Mapeamento[i][j-1].equals("Riscopoço")||Mapeamento[i-1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j+1].equals("RiscoMonstro")||Mapeamento[i][j-1].equals("RiscoMonstro")||Mapeamento[i-1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j+1].equals("RiscoDuplo")||Mapeamento[i][j-1].equals("RiscoDuplo")||Mapeamento[i-1][j].equals("RiscoDuplo"))
                {   contwumpus++;
                    contpoço++;
                }
            }
            else if (j==tamanho-1 && i>0 && i<tamanho-1) { //parede direita
                
                if (Mapeamento[i-1][j].equals("Riscopoço")||Mapeamento[i][j-1].equals("Riscopoço")||Mapeamento[i+1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i-1][j].equals("RiscoMonstro")||Mapeamento[i][j-1].equals("RiscoMonstro")||Mapeamento[i+1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i-1][j].equals("RiscoDuplo")||Mapeamento[i][j-1].equals("RiscoDuplo")||Mapeamento[i+1][j].equals("RiscoDuplo"))
                {   
                    contwumpus++;
                    contpoço++; 
                }
            }
            //centro
            else if (matPosicao[i][j].getTipo().equals("centro")) {
                if (Mapeamento[i][j+1].equals("Riscopoço")||Mapeamento[i][j-1].equals("Riscopoço")||Mapeamento[i+1][j].equals("Riscopoço")||Mapeamento[i-1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j+1].equals("RiscoMonstro")||Mapeamento[i][j-1].equals("RiscoMonstro")||Mapeamento[i+1][j].equals("RiscoMonstro")||Mapeamento[i-1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j+1].equals("RiscoDuplo")||Mapeamento[i][j-1].equals("RiscoDuplo")||Mapeamento[i+1][j].equals("RiscoDuplo")||Mapeamento[i-1][j].equals("RiscoDuplo"))
                {   contwumpus++;
                    contpoço++;
                }
            }
            //cantos
            else if (j==tamanho-1 && i==0) { //canto superior direito
                if (Mapeamento[i][j-1].equals("Riscopoço")||Mapeamento[i+1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j-1].equals("RiscoMonstro")||Mapeamento[i+1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j-1].equals("RiscoDuplo")||Mapeamento[i+1][j].equals("RiscoDuplo"))
                {   contwumpus++;
                    contpoço++;
                }
            }
            else if (j==0 && i==tamanho-1) { //canto inferior esquerdo
                if (Mapeamento[i][j+1].equals("Riscopoço")||Mapeamento[i-1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j+1].equals("RiscoMonstro")||Mapeamento[i-1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j+1].equals("RiscoDuplo")||Mapeamento[i-1][j].equals("RiscoDuplo"))
                {   contwumpus++;
                    contpoço++;
                }
            }
            else if (j==tamanho-1 && i==tamanho-1) { //canto inferior direito
                if (Mapeamento[i][j-1].equals("Riscopoço")||Mapeamento[i-1][j].equals("Riscopoço")) contpoço++;
                else if (Mapeamento[i][j-1].equals("RiscoMonstro")||Mapeamento[i-1][j].equals("RiscoMonstro")) contwumpus++;
                else if (Mapeamento[i][j-1].equals("RiscoDuplo")||Mapeamento[i-1][j].equals("RiscoDuplo"))
                {   contwumpus++;
                    contpoço++;
                }
            }
            if (contpoço>=2) { //poço identificado
                Mapeamento[i][j]="poço";
                System.out.print("Poço confirmado na "+"["+i+"]"+"["+j+"]");
                contpoço=0;
            }
        }
        if (contwumpus>=2) {
            Mapeamento[i][j]="monstro";
            System.out.print("Monstro confirmado na "+"["+i+"]"+"["+j+"]");
            contwumpus=0;
        }
        if (contpoço>=2) {
            Mapeamento[i][j]="poço";
            System.out.print("Poço confirmado na "+"["+i+"]"+"["+j+"]");
            contpoço=0;
        }
        if (contwumpus>=2) {
            Mapeamento[i][j]="monstro";
            System.out.print("Monstro confirmado na "+"["+i+"]"+"["+j+"]");
            contwumpus=0;
        }
        perigo='N';
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
        while (rodadas<1) {
            trajeto = new Trajeto();
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
            int shot=quantidadeWumpos;
            String tipo;
            t=inicio;
            q=agente;
            shot=1;
            mov='C';
            Boolean ouro=false, jogo=true;
            estado="Vivo";   
            System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            Mapeamento[t][q]="safe";
            int linhaANT=t, colunaANT=q;
            Boolean first=true;
            while (jogo) {
                Coordenadas tafarel=new Coordenadas(t, q);
                trajeto.addCaminho(tafarel);
                Mapeamento[t][q]=matPosicao[t][q].getSentido();

                System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                if (matPosicao[t][q].getSentido().equals("safe")) {
                    tipo=matPosicao[t][q].getTipo();
                    if (matPosicao[t][q].getPersonalidade()=='c') {
                        cauteloso(tipo, t, q,matPosicao[t][q].getSentido());
                    }
                    indo=direcao(tipo,t,q, perigo,linhaANT,colunaANT);
                    System.out.println("Indo para direcao: "+indo);
                }
                else if (matPosicao[t][q].getSentido().equals("fedor|brisa")||matPosicao[t][q].getSentido().equals("fedor")) {
                    tipo=matPosicao[t][q].getTipo();
                    if (matPosicao[t][q].getPersonalidade()=='c') {
                        cauteloso(tipo, t, q,matPosicao[t][q].getSentido());
                    }
                    indo=direcao(tipo,t,q,perigo,linhaANT,colunaANT);
                    if (glock) {
                        System.out.println("Atira para o: "+indo);
                        System.out.println("Indo para direcao: "+indo);
                        if (shot>0) {
                            shot--;
                        }
                    }
                    else{
                        System.out.println("Nao atirou");
                        System.out.println("Indo para direcao: "+indo);
                    }
                }
                else if(matPosicao[t][q].getSentido().equals(("brisa"))){
                    tipo=matPosicao[t][q].getTipo();
                    if (matPosicao[t][q].getPersonalidade()=='c') {
                        cauteloso(tipo, t, q,matPosicao[t][q].getSentido());
                    }
                    indo=direcao(tipo,t,q,perigo,linhaANT,colunaANT);
                }
                else if(matPosicao[t][q].getSentido().equals("brilho")||matPosicao[t][q].getSentido().equals("brilho|brisa")||matPosicao[t][q].getSentido().equals("brilho|fedor|brisa")||matPosicao[t][q].getSentido().equals("brilho|fedor")){
                    //System.out.print("entrou");
                    if(first){
                        //System.out.print("entrou");
                        if(!M[t][q].equals("O")){ 
                            //System.out.print("entrou");
                            //ouro=true;
                            M[t][q]=" ";
                            System.out.println("Pegou o ouro");
                            pegouOouro++;
                            voltar(t, q);
                            jogo=false;
                            first=false;
                        }
                    }
                    // tipo=matPosicao[t][q].getTipo();
                    // if (matPosicao[t][q].getPersonalidade()=='c') {
                    //     cauteloso(tipo, t, q,matPosicao[t][q].getSentido());
                    // }
                    // indo=direcao(tipo,t,q, perigo,linhaANT,colunaANT);
                    // System.out.println("Indo para direcao: "+indo);
                }
                if (indo=='N') {
                M[t][q]=" ";
                t=t-1;
                M[t][q]="A";
                
                linhaANT=t+1;
                colunaANT=q;
                if (matPosicao[t][q].getAmbiente().equals("monstro")){
                    if (!glock) {
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
                
                linhaANT=t-1;
                colunaANT=q;
                if (matPosicao[t][q].getAmbiente().equals("monstro")){
                    if (!glock) {
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
                
                linhaANT=t;
                colunaANT=q+1;
                if (matPosicao[t][q].getAmbiente().equals("monstro")){
                    if (!glock) {
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
                
                linhaANT=t;
                colunaANT=q-1;
                if (matPosicao[t][q].getAmbiente().equals("monstro")){
                    if (!glock) {
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
                System.out.println(indo);
                
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){

                }
                limparConsole();
                printarjogo();
                if (estado.equals("morto")) {
                    morte++;
                    jogo=false;
                }
            }
            rodadas++;
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
        matPosicao=new Posicao [tamanho][tamanho];
        Mapeamento=new String[tamanho][tamanho];
        criarambiente();
        criarsentido();
        printarmatrizambiente();
        printarmatrizsentido();
        M = new String [tamanho][tamanho];

        for (int i = 0; i < Mapeamento.length; i++) {
            for (int j = 0; j < Mapeamento.length; j++) {
                Mapeamento[i][j]="Desc";
            }
        }
        inicio=0;
        agente=0;

        System.out.println("Quer mudar a personalidade do agente? '1' para sim ou '2' para nao");
        int pers = tc.nextInt();
        if (pers==1){
            System.out.println("Digite: 1 para ultima aposta, digite 2 para cauteloso");
            int qualfase = tc.nextInt();
            if (qualfase==1) {
                for (int i = 0; i < matPosicao.length; i++) {
                    for (int j = 0; j < matPosicao.length; j++) {
                        matPosicao[i][j].setPersonalidade('u');
                    }
                }
            }
            else{
                for (int i = 0; i < matPosicao.length; i++) {
                    for (int j = 0; j < matPosicao.length; j++) {
                        matPosicao[i][j].setPersonalidade('c');
                    }
                }
            }
        }
        System.out.print("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        printarmatrizambiente();
        printarmatrizsentido();
        iniciarjogo();



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