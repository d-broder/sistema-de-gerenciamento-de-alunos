# Sistema de Gerenciamento de Alunos e Histórico de Peso

Este é um projeto desenvolvido em Java que utiliza o SGBD MySQL para gerenciar informações de alunos e seus históricos de peso.

## Funcionalidades

### Aluno

- CRUD de aluno: permite realizar operações de criação, leitura, atualização e exclusão de alunos. Os atributos de cada aluno incluem CPF, nome, data de nascimento, peso e altura.
- Métodos: foram implementados os métodos `inserir`, `excluir`, `atualizar` e `consultar` para manipular os dados dos alunos.
- Interface de usuário: uma tela interativa foi criada para facilitar a interação com o usuário.
- Getters, setters e constructor: foram implementados os métodos de acesso aos atributos dos alunos, bem como um construtor para inicializar os objetos.

### Histórico de Peso

- CRUD de históricos de pesos do aluno: permite realizar operações de criação, leitura, atualização e exclusão de registros de pesos do aluno. Cada registro inclui a data e o peso do aluno naquela data.
- Um aluno pode ter vários registros de peso em diferentes datas.
- Método para calcular o índice IMC: é calculado o índice de massa corporal (IMC) com base no peso e altura do aluno e gravado em um arquivo de texto. O arquivo contém a data do cálculo, o CPF, o nome do aluno, o índice IMC e uma interpretação do resultado. Novas inserções não sobrescrevem o arquivo existente.
- Ao excluir um aluno, os registros de peso relacionados a ele também são excluídos.

## Estrutura do Projeto

- **Pacotes**: o projeto utiliza o padrão de pacotes contendo: `factory`, `modelo`, `dao` e `gui`.
- **Interfaces amigáveis**: as interfaces foram desenvolvidas visando a facilidade de uso para o usuário.

## Como utilizar

1. Clone o repositório para a sua máquina local.
2. Importe o projeto em sua IDE de preferência.
3. Na pasta "res" do projeto, insira o arquivo "config.properties".
4. Abra o arquivo "config.properties" e configure-o com as informações do seu banco de dados MySQL, seguindo o exemplo abaixo:

    ```
    url=jdbc:mysql://localhost/nome_banco_dados
    username=root
    password=senha123
    ```

    Certifique-se de substituir "url", "username" e "password" pelos valores correspondentes ao seu ambiente de desenvolvimento.

5. Execute a aplicação e utilize a interface de usuário para gerenciar os alunos e seus históricos de peso.
