# testing_api_ServeRest_com_BDD

Projeto de teste da api <a href="https://github.com/ServeRest/ServeRest">ServeRest</a> utilizando BDD

O projeto de testes utiliza Maven para gerenciar as dependencias.

<h2>Para rodar o ServeRest Localmente você precisa:
<h3>Ter o <a href="https://www.docker.com/">Docker</a> instalado na sua maquina.</h3>
<h3>do comando > docker run -p 3000:3000 paulogoncalvesbh/serverest:latest < </h3>
<p>Assim a API vai rodar localmente com a ajuda do docker, deixando os testes mais conclusivos.</p>
<h4>Também é possivel rodar os testes online no <a href="https://serverest.dev/">site da ServeRest</a> Mas é preciso trocar o endpoint setado na classe Configs</h4>

<h3>Tecnologias utilizadas:</h3>
<p><a href="https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html">Java 11</a></p>
<p><a href="https://cucumber.io/docs/installation/java/">Cucumber</a></p>
<p><a href="https://github.com/rest-assured/rest-assured/wiki/GettingStarted">Rest Assured</a></p>
<p><a href="https://projectlombok.org/">Lombok</a></p>
<p><a href="https://junit.org/junit4/">Junit</a></p>
<p><a href="https://github.com/FasterXML/jackson">FasterXML/jackson</a></p>
<h3>Todas são importadas com a ajuda do Maven, e estão no arquivo pom.xml</h3>

<h3>Utilize a classe Cucumber Runner para conseguir executar todos os testes.
Também é possivel executar cada feature sozinha diretamente no arquivo das features.
Mas não esqueça de SUBIR a aplicação.<h3>
  
  
