# Descrição:

Repositório criado no intuito de fazer os testes automatizados da API ServeRest passada no desafio de QA.
Foi desenvolvida com Java, JUnit e RestAssured.

# Usado no projeto:

Java (versão 17)
Maven (versão 3.9.4)
JUnit (versão 5.10.0)
Rest Assured (versão 5.3.2)

# Como Executar:

Verifique se você tem o Java JDK instalado em sua máquina.
Clone o repositório para que ele fique disponível localmente pra você.
Abra o projeto em sua IDE de preferência.
Verifique se as dependências do projeto foram baixadas devidamente.
Navegue até a o package onde se encontram as classes de teste, no caso deste projeto, o caminho "src/test/java/com.dbserver.serverest".
Execute a classe de teste clicando em cima dela com o botão direito do mouse em "Run", podendo variar de acordo com sua IDE. 

# Sobre o projeto: 

 Foi criada uma model para definir o modelo de usuário nos testes

# Sobre os Testes:

Foi criado um Stub para definir os dados dos usuários nos testes

Foram feitos os seguintes testes na requisição GET:

- Teste que retorna uma lista de usuários
- Teste que falha quando é buscado um endpoint inexistente na api
- Cria um usúario e busca por ID e ainda compara email, senha e ID

Foram feitos os seguintes testes na requisição POST:

- Teste que cadastra um novo usuário
- Teste que cria o mesmo usuário duas vezes e retorna um erro de cadastro com emial existente


Foram feitos os seguintes testes na requisição PUT:

- Teste que cria um usuário, atualiza ele e compara nome, email, password e administrador 

Foram feitos os seguintes testes na requisição DELETE:

- Teste que cria um usuário e deleta ele
- Teste que não exclui um usuário que não exista