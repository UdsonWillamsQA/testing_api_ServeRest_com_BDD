#language: pt

@tmsLink=LoginTest
Funcionalidade: Efetuar Login na ServeRest
  Contexto:
    Dado   que se tenha um serviço de cadastro de usuários
    Quando se enviar os dados para cadastramento de usuario

  @excluirUsuario
  Cenario: Efetuar login com sucesso
    Quando é fornecido um login correto
    Então  deve retornar status code 200 OK
    E      devo receber uma mensagem de sucesso

  @excluirUsuario
  Cenario: Não efetuar login na api ServRest com dados invalidos
    Quando é fornecido um login incorreto
    Entao  devo receber uma mensagem de erro na resposta
    E      o sistema deve retornar status code 401 UNAUTHORIZED
