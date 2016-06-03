# Automação de Teste - WebService #

## Tecnologia usada ##

* Java 1.7
* JUnit 4.12
* Gson 2.4
* HTTPClient 4.5

## Pré-condições para executar ##

* Ter o Maven instalado na máquina - [Tutorial instalação Maven](http://blog.cvinicius.com.br/2012/09/instalacao-e-configuracao-maven.html)
* Ter o Java 1.7 instalado na máquina

## Como executar o teste automatizado ##

* Necessário atender as pré-condições citadas anteriormente
* Baixe o projeto de testes deste repositório

### Execução via linha de comando ###

* Via linha de comando acesse o diretório onde está o projeto
* execute a seguinte instrução: mvn site
* Pronto, o teste será executado

### Execução via eclipse ###

* Importe o projeto no eclipse
* Com o botão direito, clique no projeto e faça um Update Project do Maven - ( Maven > Update Project > Ok )
* Com o botão direito, clique no projeto e faça um Build... pelo Maven - ( Run as > Maven Build... > Informe a Goal site > Ok )
* Pronto, o teste será executado

### Resultado do Teste ###

* Analise o resultado do teste no relatório HTML após o término da execução de teste.
* Acesse o relatório pelo link file:///<diretório do projeto>/teste-rest/target/site/surefire-report.html
