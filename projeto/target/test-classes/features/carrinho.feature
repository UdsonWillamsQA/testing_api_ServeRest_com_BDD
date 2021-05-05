 #language: pt

 @tmsLink=LoginTest
 Funcionalidade: Gerenciamento do carrinho na ServeRest

   @CadastraProduto
   @criaELogaUsuario
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

   @deletaProduto
   @excluirUsuario
   Cenario:  Cancelar uma compra
     Dado    que o usuario desista de fazer uma compra
     Quando  o usuario tenha cancelado a compra
     Entao   deve receber uma mensagem de carrinho nao encontrado para esse usuario