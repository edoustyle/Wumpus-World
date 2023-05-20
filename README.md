# Wumpus-World
![header](https://www.google.com/url?sa=i&url=https%3A%2F%2Fmedium.com%2F%40cluelessrae%2Fthe-probabilistic-wumpus-world-e1bc4a90377b%3Fsource%3Drss-------1&psig=AOvVaw3TZF7wTfsjBNGUQRSwaGyu&ust=1684710996513000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCLjWpNaDhf8CFQAAAAAdAAAAABAE)
![1_wSgWu87_-miKnsmhddNUSg](https://github.com/edoustyle/Wumpus-World/assets/134174991/7c567ed6-9518-4de8-abcc-eda031716aa9)
>Status: Developing ⚠️

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

### Versão 2.0

    Nessa versão utilizaremos como base a fase anterior, na qual o jogo (mundo de wumpus) estava rodando a princípio aleatoriamente, no entanto a partir de agora o jogo tomará ainda mais forma, implementando então a memória ao agente, que aumentará o índice de assertividade em relação ao objetivo que é pegar o ouro e voltar a casa inicial, casa está que o agente iniciou no jogo. A partir de agora o agente conseguirá identificar (os perigos e o objetivo como: brisa, fedor e brilho), poderá marcar as casas que estão seguras e guardar essas informações para que o objetivo seja concluído. Dito isso, utilizamos as seguintes implementações:

+ Ao sentir uma percepção dita como perigosa (fedor, brisa ou as duas juntas), o agente volta para casa anterior e pega um caminho diferente.

+ Se ele sentir uma mesma percepção (exemplo: ele sentiu brisa, voltou e sentiu fedor e brisa no outro caminho, no caso brisa se repete) ele já identifica uma casa de perigo no seu mapa.

+ Esse método é exemplificado usando uma matriz de mapeamento que originalmente todas as casas estão marcadas como desconhecidas.

+	Cada casa identificada como potencialmente perigosa ao longo do trajeto do nosso agente são marcadas também na nossa matriz de Mapeamento, como por exemplo um potencial poço seria (Riscopoço). Uma vez que um poço foi localizado pelo agente, os outros (Riscopoço) passam a ser seguros (safe).

+	Em cada posição marcada como safe no mapa do nosso agente ele sabe que já andou por lá e pode andar com segurança “na teoria”.

+	Toda casa onde não tem percepções é marcada como safe no mapa do agente.

+	Após perceber “brilho” o agente pega o ouro e retorna à sua posição inicial seguindo o caminho de onde ele veio, caminho que é armazenado numa lista. Ao retornar a sua posição original com o ouro, o agente ganha a partida.

+	O agente só vai atirar após identificar onde está o wumpus.