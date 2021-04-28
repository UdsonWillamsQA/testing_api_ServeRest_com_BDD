#language: pt

  Funcionalidade: Gerenciamento dos produtos da ServeRest

    Cenario:  Cadastrar um usuario
      Dado    que se tenha um serviço de cadastro de usuários
      Quando  se enviar os dados para cadastramento de usuario
      Entao   deve realizar o cadastro de usuario

    Cenario:  Fazer Login
      Dado    que o usuario estiver cadastrado
      Quando  é fornecido um login correto
      Então   deve retornar status code 200 OK

    Cenario:  Cadastrar um novo produto
      Dado    que o usuario tem acesso ao sistema
      Quando  os dados para cadastramento de produto forem enviados
      Entao   o sistema deve conseguir realizar o cadastro de um produto

    Cenario:  Listar Produtos cadastrados
      Dado    que existam produtos cadastrados
      Quando  o usuario requisita a lista de produtos
      Entao   deve conseguir uma lista de 3 produtos

    Cenario:  editar um produto cadastrado
      Dado    que o produto esteja cadastrado
      Quando  o usuario requisita a edicao do produto
      Entao   o sistema deve conseguir atualizar o produto

    @excluirUsuario
    Cenario:  Deletar um novo produto
      Dado    que o produto esteja cadastrado
      Quando  o usuario solicitar que um produto seja deletado pelo id
      Entao   o sistema deve conseguir deletar o produto