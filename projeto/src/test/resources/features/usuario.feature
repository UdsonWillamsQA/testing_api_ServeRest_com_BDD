#language: pt


Funcionalidade: Gerenciamento de um usuario na ServeRest


  Cenario:  Cadastrar um usuario
    Dado    que se tenha um serviço de cadastro de usuários
    Quando  se enviar os dados para cadastramento de usuario
    Entao   deve realizar o cadastro de usuario

  Cenario:  Listar todos os usuarios
    Dado    que a aplicação possua usuarios cadastrados
    Quando  pesquisar por usuarios
    Entao   o sistema deve receber uma lista de 2 usuarios

  Cenario:  Buscar um usuario por seu Id
    Dado    que a aplicação possua usuarios cadastrados
    Quando  é solicitado a pesquisa pelo id de um usuario
    Entao   o sistema deve receber os dados de um usuario

  Cenario:  Editar um usuario por seu Id
    Dado    que a aplicação possua usuarios cadastrados
    Quando  um usuario for editado pelo seu id
    Entao   o sistema deve conseguir receber os dados atualizados de um usuario

  Cenario:  Deletar um usuario por seu Id
    Dado    que a aplicação possua usuarios cadastrados
    Quando  é solicitado que um usuario seja deletado pelo id
    Entao   o sistema deve conseguir deletar o usuario