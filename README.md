# Sistema de Gerenciamento de Estoque

## Visão Geral

Este é um sistema de gerenciamento de estoque desenvolvido em Java, projetado para simplificar o controle de produtos em estoque de uma empresa. Ele permite a leitura de um arquivo CSV contendo informações detalhadas sobre os produtos, como ID, nome, quantidade, categoria e preço. Os dados são armazenados em um banco de dados SQL para fácil acesso e manipulação. Além disso, o sistema oferece a capacidade de gerar relatórios úteis sobre o estoque atual, ajudando os usuários a tomar decisões informadas sobre o gerenciamento de inventário.

## Funcionalidades Principais

- **Importação de Dados:** O sistema é capaz de ler um arquivo CSV formatado especificamente contendo informações sobre os produtos em estoque.
  
- **Armazenamento em Banco de Dados:** As informações lidas do arquivo CSV são armazenadas de forma persistente em um banco de dados SQL, proporcionando uma maneira confiável de acessar e gerenciar os dados.

- **Relatórios Detalhados:** O sistema gera relatórios úteis sobre o estoque atual, incluindo:
  - Quantidade de categorias de produtos.
  - Quantidade de produtos por categoria.
  - Valor médio dos produtos.
  - Lista de produtos em estoque baixo (com quantidade menor que 3 unidades).

## Requisitos de Instalação

- **Java Development Kit (JDK):** É necessário ter o JDK instalado para compilar e executar o código Java.
  
- **MySQL Server:** Um servidor MySQL deve estar configurado e em execução para armazenar os dados do sistema.

- **Driver JDBC do MySQL:** O driver JDBC do MySQL deve ser baixado e configurado no classpath do projeto para permitir a conexão com o banco de dados.

- **Arquivo CSV de Dados:** Um arquivo CSV contendo os detalhes dos produtos em estoque deve estar disponível para importação.

## Utilização

1. **Configuração Inicial:**
   - Certifique-se de ter o JDK instalado em seu sistema.
   - Configure um servidor MySQL e crie um banco de dados para o sistema.

2. **Configuração do Projeto:**
   - Clone este repositório em seu ambiente local.
   - Baixe o driver JDBC do MySQL e adicione-o ao classpath do projeto.

3. **Compilação e Execução:**
   - Compile os arquivos Java utilizando o comando `javac`.
   - Execute o programa principal com o comando `java`.

4. **Interagindo com o Sistema:**
   - Siga as instruções exibidas no console para importar os dados do arquivo CSV e gerar relatórios sobre o estoque.
