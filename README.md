# Simulador-de-Acesso-a-Memoria-Cache

### A função main está em src/trabalhopratico1/TrabalhoPratico1.java

Projeto desenvolvido para a disciplina de Organização e Arquitetura de Computadores do curso de Engenharia da Computação do IFSULDEMINAS - Campus Poços de Caldas. O objetivo era desenvolver um simulador de acesso à memória cache com todos os mapeamentos implementados, FIFO, LRU, LFU, etc.

![Tela inicial do simulador de cache com tema do Mario](https://github.com/Danbr23/Simulador-de-Acesso-a-Memoria-Cache/blob/main/ImagensSimulador/Tela-Inicial.png)

Ao clicar no botão iniciar, o simulador abre a tela onde é possível escolher o arquivo de teste, contendo todas as posições da memória que vão ser acessadas, e também qual tipo de mapeamento será utilizado.

![Tela de Escolha](https://github.com/Danbr23/Simulador-de-Acesso-a-Memoria-Cache/blob/main/ImagensSimulador/Tela-De-Escolha.png)

Na tela de resultados, o campo de acertos mostra quantos dos endereços requisitados, que estão no arquivo de teste, estavam na memória cache. Lembrando que inicialmente a memória cache está vazia, então toda primeira requisição de endereço a cada n posições constará como uma falha (a cada n posições pois a linha da cache guarda até n blocos, definido na classe do mapeamento). O campo falha apresenta todas as vezes que o endereço requisitado não estava na memória cache e foi preciso puxar da memória principal. O campo "Resultado" apresenta a razão entre acertos e falhas. A diferença entre cada mapeamento é o algoritmo de substituição de página, utilzado para subistitur os blocos da memória cache uma vez que ela estiver cheia, o que interfere na taxa de acertos e falhas, de acordo com os endereços buscados.

![Tela de resultados](https://github.com/Danbr23/Simulador-de-Acesso-a-Memoria-Cache/blob/main/ImagensSimulador/Tela-Resultados.png)
