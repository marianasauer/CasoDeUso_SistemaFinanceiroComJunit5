# Aplicação com Junit 5 em um Sistema Financeiro ![image](https://github.com/marianasauer/apiTestes/assets/105138712/b5a7de4f-ab0b-4392-90b9-037ed063a429)


## Testes Unitários:
É um alinhamento entre a expectativa e a realidade, já sabendo previamente o resultado correto.

## Princípio FIRST
- FAST -> Fazer as execuções com mais frequência.
- ISOLATED -> Conseguir executar cada um dos testes separadamente.
- REPEATABLE -> Um teste deve ser repetitivo (um teste deve ser executado quantas vezes for necessário).
- SELF-VALIDATED -> O próprio teste deve declarar se passou ou não.
- TIMELY / THOROUGH -> Timely: O teste deve ser excutado o quão antes possível e corretamente. Thorough: O teste deve estar completo e minocioso.

## Classes de Equivalência
Tem como objetivo reduzir o universo de testes em um subconjunto menor. Assim, em vez de testar todas as combinações de dados de teste de entrada, podemos escolher e passar qualquer um dos dados de teste de uma classe de equivalência específica para o aplicativo e assumir que o aplicativo se comportará da mesma maneira para os outros dados de teste dessa classe. 

## Identificação Falha vs Erro
- Erro: Quando o resultado do teste não é o esperado.
- Falha: Quando não é possível executar o teste, podendo causar um AssertionError.
<img width="466" alt="image" src="https://github.com/marianasauer/apiTestes/assets/105138712/349b7809-66ef-4540-8a67-323048db778f">

## Anotações do Junit
<table>
  <tr>
    <td>@BeforeEach</td>
    <td>É executado antes de cada um dos testes. Podemos utiliza-lo para trazer o banco ao estado inicial.</td>
  </tr>
  <tr>
    <td>@BeforeAll</td>
    <td>É executado uma vez a cada execução (sendo executado no momento que a classe vai ser criada). Podemos utiliza-lo para criar a estrutura do banco de dados.</td>
  </tr>
  <tr>
    <td>@AfterEach</td>
    <td>É executado depois de cada um dos testes. Podemos utiliza-lo para limpar os dados.</td>
  </tr>
  <tr>
    <td>@AfterAll</td>
    <td>É executado depois de todos os métodos, deve ser estático. Podemos utiliza-lo para fechar o recurso utilizado.</td>
  </tr>
</table>

## Onion Architecture
O domínio é uma esfera de conhecimento. Refere-se ao conhecimento de negócios que nosso software está tentando modelar. O Domain-Driven Design centra-se no modelo de domínio que tem uma compreensão rica dos processos e regras de um domínio. A arquitetura Onion implementa esse conceito e aumenta drasticamente a qualidade do código, reduz a complexidade e permite sistemas corporativos evolutivos.

!<img width="295" alt="Sem título" src="https://github.com/marianasauer/apiTestes/assets/105138712/59416112-df45-4b4c-9aca-200b07824237">

## Parameterized
Podemos escrever o código e passar uma lista de um conjunto de valores a serem utilizado. Para cada conjunto de valores será criado um novo teste, assim não terá uma lista de testes repetitivos extensa e vai facilitar caso seja necessário adicionar um novo teste, será necessário apenas adicionar uma nova linha no parameterized.

~~~~java
@ParameterizedTest
@CsvSource(value = {" ", " ", " "})
public void deveDividirCorretamente(int num, int den, double res){
  float resultado = calc.dividir(num, den);
  Assertions.assertEquals(res, resultado);
}
~~~



