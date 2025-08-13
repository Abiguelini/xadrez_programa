# Sistema de Xadrez em Java ♟️

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
> Status: Projeto Concluído ✔️

### Tabela de Conteúdos

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades](#funcionalidades)
* [Demonstração](#demonstração)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Como Executar](#como-executar-o-projeto)
* [Autor](#autor)

---

### Descrição do Projeto

Este projeto é a implementação de um jogo de xadrez completo, projetado para rodar diretamente no terminal. Foi desenvolvido como um exercício prático para aplicar e aprofundar os conceitos de **Programação Orientada a Objetos (POO)** em Java.

O sistema implementa toda a lógica do jogo, incluindo a movimentação de peças, regras especiais, e a lógica de xeque e xeque-mate, oferecendo uma experiência de jogo funcional via console.

---

### Funcionalidades

-   **Renderização do Tabuleiro:** O tabuleiro e as peças são exibidos de forma clara no console.
-   **Movimentação de Peças:** Sistema de coordenadas (ex: a1, b2) para selecionar e mover as peças.
-   **Validação de Movimentos:** O programa valida se a jogada de origem pertence ao jogador da vez e se o destino é uma jogada válida para a peça.
-   **Destaque de Movimentos Possíveis:** Ao selecionar uma peça, o sistema destaca no tabuleiro todas as casas para as quais ela pode se mover.
-   **Lógica de Xeque e Xeque-mate:** O jogo identifica e avisa quando um rei está em xeque e encerra a partida em caso de xeque-mate.
-   **Tratamento de Exceções:** Uso de exceções customizadas (`ChessException`) para lidar com jogadas inválidas ou erros de input do usuário.

---

### Demonstração



![Demonstração do Jogo no Console](URL_DA_SUA_IMAGEM_AQUI)

---

### Tecnologias Utilizadas

-   **Java 17**
-   **Programação Orientada a Objetos (POO)**

---

### Como Executar o Projeto

Para executar este projeto em sua máquina local, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Abiguelini/xadrez_programa.git](https://github.com/Abiguelini/xadrez_programa.git)
    ```

2.  **Abra em sua IDE:**
    -   Abra o projeto em uma IDE de sua preferência (Eclipse, IntelliJ IDEA, VS Code, etc.).
    -   Aguarde a IDE carregar as dependências do Java.

3.  **Execute a classe principal:**
    -   Localize o arquivo `Program.java` no pacote `application`.
    -   Execute o método `main()` contido nesta classe.
    -   O jogo iniciará no console da sua IDE.

---

### Autor

<a href="https://github.com/Abiguelini">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/105739509?v=4" width="100px;" alt=""/>
 <br />
 <sub><b>Alvaro Biguelini</b></sub></a> <a href="https://github.com/Abiguelini" title="GitHub">🚀</a>


[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/alvaro-biguelini/)
