#language: pt

@tmsLink=LoginTest
Funcionalidade: Gerenciamento de um usuario na ServeRest

  Esquema do Cenario: Cadastrar usuarios
    Dado    que se tenha um serviço de cadastro de usuários
    Quando  se enviar os nomes dos <usuarios> com <email> para o cadastramento de usuario
    Entao   deve realizar o cadastro de usuario

    Exemplos: usuarios para cadastramento
      | usuarios | email             |
      | joao     | joao@mail.com     |
      | jose     | jose@mail.com     |
      | leonardo | leonardo@mail.com |
      | julio    | julio@mail.com    |
      | Udson    | udson@gmail.com   |

  Cenario:  Listar todos os usuarios
    Dado    que a aplicação possua usuarios cadastrados
    Quando  pesquisar por usuarios
    Entao   o sistema deve receber uma lista de 6 usuarios

  Cenario:  Buscar um usuario por seu Id
    Dado    que a aplicação possua usuarios cadastrados
    Quando  é solicitado a pesquisa pelo id de um usuario
    Entao   o sistema deve receber os dados de um usuario

  Cenario:  Editar um usuario por seu Id
    Dado    que a aplicação possua usuarios cadastrados
    Quando  um usuario for editado pelo seu id
    Entao   o sistema deve conseguir receber os dados atualizados de um usuario

  Esquema do Cenario:  Deletar usuarios por seu Id
    Dado    que a aplicação possua usuarios cadastrados
    Quando  é solicitado que um <usuario> seja deletado pelo id
    Entao   o sistema deve conseguir deletar o usuario

    Exemplos: usuarios para serem deletados
      | usuario  |
      | joao     |
      | jose     |
      | leonardo |
      | julio    |
      | Udson    |