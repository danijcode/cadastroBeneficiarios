# Cadastro de Beneficiarios API - Desafio Técnico
Projeto Backend Java para solução do Critário abaixo:

Critério: Temos um produto e recebemos o desafio de gerar uma lista de pessoas aptas a ganhar uma casa popular do governo, tendo como única exigência que essa lista esteja ordenada de forma mais justa possível com um somatório de pontos, analisando aspectos das famílias que estão participando. São considerados os seguintes critérios para a construção das pontuações:
- Renda total da família até 900 reais - valendo 5 pontos;
- Renda total da família de 901 à 1500 reais - valendo 3 pontos;
- Famílias com 3 ou mais dependentes  (lembrando que dependentes maiores de 18 anos não contam) - valendo 3 pontos;
- Famílias com 1 ou 2 dependentes  (lembrando que dependentes maiores de 18 anos não contam) - valendo 2 pontos.

O código que faz essa análise hoje é totalmente procedural, repleto de blocos if/else pra analisar e montar as classificações e nos dá muito custo de manutenção, nos motivando a procurar uma nova solução para este problema. Seu desafio é justamente isso, codificar uma forma melhor de chegar no mesmo resultado usando e abusando da orientação à objetos, criando classes e métodos de uma forma que fique tudo mais legível e fácil de manter e estender. Falando sobre estender, o cliente já deixou um alerta ligado de que irão incluir novos critérios na avaliação das famílias em breve, assim que forem aprovados pelo governador, então temos que ter uma solução preparada para adicioná-los sem muito custo de implementação.

## Servidor Local
- Instale o [Java 17] (ou 11) (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html), [Maven] (https://maven.apache.org/download.cgi) o banco de dados é o [H2] (https://www.h2database.com/html/main.html) e não necessita de instalação pois ja esta como dependencia do projeto e vai subir em memoria.
- Configure a variavel de ambiente `JAVA_HOME` para a pasta de instalação da sua versão do JDK.

## Rodando Aplicação (Desenvolvimento - Intellij)
- Após a IDE baixar todas as dependências, para rodar o projeto: clicar com o botão esquerdo em `CadBeneficiarioApplication.Java` e depois em: "`Run CadBeneficiarioApplication.main`" 


## Exemplos de Payloads para cadastro dos beneficiários e listagem.

 - POST (http://localhost:8080/api/beneficiario)
```
{    
    "nome": "Gloria Benedita",
    "rendaFamiliarTotal": 700,
    "dependentes": [
    {
        "nome": "raul Benedito Adulto",
        "idade": 18	
    },
    {
        "nome": "Darcio Benedito Adulto",
        "idade": 13
    }
    ]
}
```

 - GET - RESPOSTA (http://localhost:8080/api/beneficiario)
```
[
    {
        "id": 3,
        "nome": "Gloria PONTUA Benedita",
        "rendaFamiliarTotal": 899,
        "dependentes": [
            {
                "id": 7,
                "nome": "raul Benedito Adulto",
                "idade": 18
            },
            {
                "id": 8,
                "nome": "Darcio Benedito Adulto",
                "idade": 13
            },
            {
                "id": 9,
                "nome": "Daniel Benedito Adulto",
                "idade": 14
            }
        ],
        "pontuacao": 8
    },
    {
        "id": 4,
        "nome": "Gloria PONTUA Benedita",
        "rendaFamiliarTotal": 700,
        "dependentes": [
            {
                "id": 10,
                "nome": "raul Benedito Adulto",
                "idade": 18
            },
            {
                "id": 11,
                "nome": "Darcio Benedito Adulto",
                "idade": 13
            }
        ],
        "pontuacao": 7
    }
]
```

### Testes Unitários
Os testes unitários rodam junto com o build da aplicação, porém a titulo de consulta os mesmos encontram-se no diretório padrão do Spring `\src\test`

