# Banco de dados

## Setup

Este projeto utiliza Liquibase para gerenciar as mudanças e atualizações do banco de dados. Para configurar e utilizar corretamente, siga os passos abaixo:

1. Instale o Java - JDK 17 - ou superior.

2. Preencha o arquivo liquibase.properties com as informações do seu banco de dados. Este arquivo contém as configurações de conexão com o banco de dados, como URL, nome de usuário e senha.

3. Execute o arquivo `init.sh` para aplicar as atualizações no banco de dados. Este script irá iniciar o Liquibase e aplicar todas as mudanças pendentes no seu banco de dados.

Lembre-se de que todas as alterações no banco de dados devem ser gerenciadas através do Liquibase para garantir a consistência e a rastreabilidade das alterações.

## Changelog

Para adicionar novas alterações ao banco de dados, você deve criar um novo arquivo na pasta changelog. O nome do arquivo deve seguir o padrão YYYYMMDD-HHMM-descrição-da-alteração.sql, onde:

- YYYYMMDD é a data em que a alteração foi criada.
- HHMM é a hora em que a alteração foi criada.
- descrição-da-alteração é uma breve descrição do que a alteração faz.

Cada arquivo de changelog deve conter uma ou mais "changesets", que são blocos de alterações que o Liquibase pode aplicar ao banco de dados. Cada changeset deve ter um identificador único e comandos de rollback para desfazer as alterações, se necessário.


