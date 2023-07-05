# Wumpus-World
![1_wSgWu87_-miKnsmhddNUSg](https://github.com/edoustyle/Wumpus-World/assets/134174991/36cdf16b-fbcd-4ab7-9b43-958f1d757fdb)

>Status: Developed ✅
### Versão 1.0
![Random_walk_in2D_closeup](https://github.com/edoustyle/Wumpus-World/assets/134174991/9cce921e-c517-41c4-a820-0412628e159e)

Esse projeto representa de forma visual como se sai uma inteligencia artificial confrontada à diferentes obstáculos.
O mundo pode é composto por um agente, e vários obstaculos, nesse caso poço e wumpus(a quantidade de cada um depende do tamanho escolhido pelo usuário para o mundo).
Nessa primeira versão o nosso agente se move de forma aleatório (respeitando as direções possíveis).
O objetivo principal é andar pelo mundo a procura do ouro e voltar para a sua posição inicial (vivo).

    Regras:
* Escolher aleatoriamente o caminho entre os possiveis (somente entre os possiveis para maximizar o tempo)
* Nao pode haver um obstaculo aos lados adjançante ao canto (ao mesmo tempo) se o agente (caçador) estiver naquele canto (vale pro ouro tbm),
ex: se caçador[0][0] então posicao[1][0] e posicao[0][1] não podem ter obstaculos, mesma coisa pro ouro (no lugar do caçador)
* O agente pode atirar até 1x a quantidade de wumpus no mundo (por enquanto) escolha aleatoria, porém quando atirar segue a direcao na qual atirou
* Objetivo pegar o ouro e voltar para posicao inicial (vivo)
* matar o wumpus nao é obrigacao (mas pode ser dependendo da situaçao)
* nao tem memoria
* Cada obstaculos causa uma percepcao às posicoes adjacentes (nao precisa fazer para as que tem poço)
* O wumpus é fixo, não anda durante o jogo (por enquanto)
* Os poços são fixos durante o jogo (obvio)
* Os obstaculos e a posicao do agente sao decididos aleatoriamente
* O agente só possui uma vida (por enquanto)
* Não tem limite de tempo (por enquanto)
* O ouro pode cair aleatoriamente onde está o wumpus mas não onde está o agente e onde está um poço (porque senao nao tem graça)
### Assistir o video da versão 1 ➡️ https://github.com/edoustyle/Wumpus-World/issues/1#issue-1788416656
>Status: Developing ⚠️
### Versão 2.0

![360_F_487133202_AoYev86tqkqK6IsRjnuFWWsTbPDJtqJa](https://github.com/edoustyle/Wumpus-World/assets/134174991/b0192a8c-d21b-4ee8-8b17-119df38767f8)

Nessa versão utilizaremos como base a fase anterior, na qual o jogo (mundo de wumpus) estava rodando a princípio aleatoriamente, no entanto a partir de agora o jogo tomará ainda mais forma, implementando então a memória ao agente, que aumentará o índice de assertividade em relação ao objetivo que é pegar o ouro e voltar a casa inicial, casa está que o agente iniciou no jogo. A partir de agora o agente conseguirá identificar (os perigos e o objetivo como: brisa, fedor e brilho), poderá marcar as casas que estão seguras e guardar essas informações para que o objetivo seja concluído. Dito isso, utilizamos as seguintes implementações:

    Novas Regras:
+ Ao sentir uma percepção dita como perigosa (fedor, brisa ou as duas juntas), o agente volta para casa anterior e pega um caminho diferente.

+ Se ele sentir uma mesma percepção (exemplo: ele sentiu brisa, voltou e sentiu fedor e brisa no outro caminho, no caso brisa se repete) ele já identifica uma casa de perigo no seu mapa.

+ Esse método é exemplificado usando uma matriz de mapeamento que originalmente todas as casas estão marcadas como desconhecidas.

+	Cada casa identificada como potencialmente perigosa ao longo do trajeto do nosso agente são marcadas também na nossa matriz de Mapeamento, como por exemplo um potencial poço seria (Riscopoço). Uma vez que um poço foi localizado pelo agente, os outros (Riscopoço) passam a ser seguros (safe).

+	Em cada posição marcada como safe no mapa do nosso agente ele sabe que já andou por lá e pode andar com segurança “na teoria”.

+	Cada passo é registrado usando com o auxilio da classe 'Coordenadas', caminho percorrido é salvo numa lista (class Trajeto).

+	Toda casa onde não tem percepções é marcada como safe no mapa do agente.

+	Após perceber “brilho” o agente pega o ouro e retorna à sua posição inicial seguindo o caminho de onde ele veio, caminho que é armazenado numa lista. Ao retornar a sua posição original com o ouro, o agente ganha a partida. Usando a função 'voltar' ele percorre o seu trajeto (inverso) que está armazenado na lista da classe Trajeto.

+	O agente só vai atirar após identificar onde está o wumpus.

### Assistir o vídeo da versão 2 ➡️ https://github.com/edoustyle/Wumpus-World/issues/2#issue-1788428474
#
    OBS: O código da versão 2.0 não está perfeito, o nosso agente está mais para 'medroso' do que 'cauteloso' 😅
### Versão 3.0
![DNA](https://github.com/edoustyle/Wumpus-World/assets/134174991/df13f383-9799-4793-81b6-7c865350d108)

Nessa versão utilizaremos algoritmos genéticos para influenciar o comportamento do nosso agente, serão criados diversas gerações de individuos (representando nosso agente) que sofrerão uma mutação (de uma geração para outra). O objetivo ainda é caminhar no mundo de wumpus para achar o ouro e voltar para a posicão inical (vivo). Na primeira população, todos os genes dos indivíduos são formados de maneira aleatória, o tamanho desses genes é definido de acordo com o tamanho do mundo. Da segunda geração em diante o gene dos indivíduos serão escolhidos a partir de um cruzamento e de uma mutação originada de seus “pais”.

    Detalhando: 
- Escolha dos pais
  Há diferentes formas de escolher os dois indivíduos “pais” de um indivíduo num algoritmo genético, tais como a seleção de torneio, de roleta ou de rank. Nessa versão foi escolhida a seleção por roleta, também conhecida como seleção proporcional, é um método utilizado em algoritmos genéticos para selecionar indivíduos para reprodução com base em sua aptidão (fitness). O processo é chamado de "roleta" porque se assemelha ao girar uma roleta de cassino, onde os indivíduos com maior fitness têm uma maior probabilidade de serem selecionados. Em seguida é feito um cruzamento entre os dois “pais” para gerar um indivíduo “filho”. Após isso o filho é inserido na nova população, isso é feito várias vezes até preencher o tamanho da população.

- taxa de mutacao
- calcul do fitness
- detalhar as classes
