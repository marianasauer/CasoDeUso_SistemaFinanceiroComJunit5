# Aplicação com Junit 5 em um Sistema Financeiro

## Testes Unitários:
É um alinhamento entre a expectativa e a realidade, já sabendo previamente o resultado correto.

## Princípio FIRST
- FAST -> Fazer as execuções com mais frequência.
- ISOLATED -> Conseguir executar cada um dos testes separadamente.
- REPEATABLE -> Um teste deve ser repetitivo (um teste deve ser executado quantas vezes for necessário).
- SELF-VALIDATED -> O próprio teste deve declarar se passou ou não.
- TIMELY / THOROUGH -> Timely: O teste deve ser excutado o quão antes possível e corretamente. Thorough: O teste deve estar completo e minocioso.

# Classes de Equivalência
Tem como objetivo reduzir o universo de testes em um subconjunto menor. Assim, em vez de testar todas as combinações de dados de teste de entrada, podemos escolher e passar qualquer um dos dados de teste de uma classe de equivalência específica para o aplicativo e assumir que o aplicativo se comportará da mesma maneira para os outros dados de teste dessa classe. 

# Identificação Falha vs Erro
- Erro: Quando o resultado do teste não é o esperado.
- Falha: Quando não é possível executar o teste, podendo causar um AssertionError.
<img width="466" alt="image" src="https://github.com/marianasauer/apiTestes/assets/105138712/349b7809-66ef-4540-8a67-323048db778f">

# Anotações do Junit
| ------------- | ------------- |
| @BeforeEach | É executado antes de cada um dos testes.  |
| @BeforeAll | É executado uma vez a cada execução.  |



