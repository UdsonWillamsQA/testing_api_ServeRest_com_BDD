 #language: pt

 Funcionalidade: Gerenciamento do carrinho na ServeRest

   Cenario:  Cadastrar um usuario
     Dado    que se tenha um serviço de cadastro de usuários
     Quando  se enviar os dados para cadastramento de usuario
     Entao   deve realizar o cadastro de usuario

   Cenario:  Logar com o usuario
     Dado    que o usuario estiver cadastrado
     Quando  é fornecido um login correto
     Então   deve retornar status code 200 OK

   Cenario:  Cadastrar um produto
     Dado    que o usuario tem acesso ao sistema
     Quando  os dados para cadastramento de produto forem enviados
     Entao   o sistema deve conseguir realizar o cadastro de um produto

   Cenario:  Cria um novo carrinho
     Dado    que o usuario tem acesso ao sistema
     Quando  os dados forem enviados para o cadastramento do carrinho
     Entao   deve realizar o cadastro de um carrinho
     E       a quantidade de carrinhos deve ser 2

   Cenario:  Pegar um carrinho pelo Id
     Dado    que o usuario queira um carrinho pelo seu id
     Quando  seja fornecido o id especifico do carrinho
     Entao   o usuario deve conseguir um carrinho

   Cenario:  Concluir uma compra
     Dado    que o usuario finalizou suas buscas por produtos
     Quando  o usuario tenha concluido a compra
     Entao   deve receber uma mensagem de exclusao com sucesso

   Cenario:  Cancelar uma compra
     Dado    que o usuario desista de fazer uma compra
     Quando  o usuario tenha cancelado a compra
     Entao   deve receber uma mensagem de carrinho nao encontrado para esse usuario

   @excluirUsuario
   Cenario:  Excluir produto
     Dado    que o produto esteja cadastrado
     Quando  o usuario solicitar que um produto seja deletado pelo id
     Entao   o sistema deve conseguir deletar o produto