#language: pt

@tmsLink=LoginTest
Funcionalidade: Gerenciamento dos produtos da ServeRest

  @criaELogaUsuario
  Esquema do Cenario: Cadastrar novos produtos
    Dado    que o usuario tem acesso ao sistema
    Quando  os <produtos> forem enviados para cadastramento com a <quantidade> e o <valor>
    Entao deve realizar o cadastro de produtos
    E  deve retornar a lista com o <total> de produtos

    Exemplos: Produtos para cadastramento
      | produtos   | quantidade | valor | total |
      | tv         | 30         | 1500  | 3     |
      | computador | 10         | 4500  | 4     |
      | fone       | 10         | 200   | 5     |
      | estante    | 5          | 700   | 6     |
      | Bola       | 3          | 50    | 7     |

  Cenario:  Listar Produtos cadastrados
    Dado    que existam produtos cadastrados
    Quando  o usuario requisita a lista de produtos
    Entao   deve conseguir uma lista de 7 produtos

  Cenario:  editar um produto cadastrado
    Dado    que o produto esteja cadastrado
    Quando  o usuario requisita a edicao do produto
    Entao   o sistema deve conseguir atualizar o produto

  Esquema do Cenario: Deletar produtos cadastrados
    Dado    que o sistema possua produtos cadastrados
    Quando  o usuario solicitar que os <produtos> sejam deletados
    Entao   o sistema deve conseguir deletar os produtos
    E       deve retornar a lista com o <totalDelete> de produtos

    Exemplos: Produtos para exclusão
      | produtos   | totalDelete |
      | tv         | 6           |
      | computador | 5           |
      | fone       | 4           |
      | estante    | 3           |
      | BolaFurada | 2           |

  @excluirUsuario
  Cenario: Excluir dados
    Entao exclua os dados de usuario

