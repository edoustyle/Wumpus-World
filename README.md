# Wumpus-World
![1_wSgWu87_-miKnsmhddNUSg](https://github.com/edoustyle/Wumpus-World/assets/134174991/36cdf16b-fbcd-4ab7-9b43-958f1d757fdb)

>Status: Developed ✅
### Versão 1.0

    Regras:
* Escolher aleatoriamente o caminho entre os possiveis (somente entre os possiveis para maximizar o tempo)
* Nao pode haver um obstaculo aos lados adjançante ao canto (ao mesmo tempo) se o agente (caçador) estiver naquele canto (vale pro ouro tbm),
ex: se caçador[0][0] então posicao[1][0] e posicao[0][1] não podem ter obstaculos, mesma coisa pro ouro (no lugar do caçador)
* Somente 1 tiro (por enquanto) escolha aleatoria, porém quando atirar segue a direcao na qual atirou
* Objetivo pegar o ouro e voltar para posicao inicial (vivo)
matar o wumpus nao é obrigacao (mas pode ser dependendo da situaçao)
nao tem memoria
* Cada obstaculos causa uma percepcao às posicoes adjacentes (nao precisa fazer para as que tem poço)
* O wumpus é fixo, não anda durante o jogo (por enquanto)
* Os poços são fixos durante o jogo (obvio)
* Os obstaculos e a posicao do agente sao decididos aleatoriamente
* O agente só possui uma vida (por enquanto)
* Não tem limite de tempo (por enquanto)
* O ouro pode cair aleatoriamente onde está o wumpus mas não onde está o agente e onde está um poço (porque senao nao tem graça)
### Assistir o video da versão 1 ➡️ https://github.com/edoustyle/Wumpus-World/assets/134174991/e7a75167-d561-492e-aa48-3c57ba4a36c9

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
#
    OBS: O código da versão 2.0 não está perfeito, o nosso agente está mais para 'medroso' do que 'cauteloso' 😅
