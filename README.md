# Avaliação Técnica - Projeto ViaCep

Elaborar uma aplicação para ajudar um usuário a fazer a consulta de um CEP.
Onde ao informar um CEP a tela apresentará informações de endereço como
bairro, município e logradouro. Para tal crie uma um api rest com integração com o
serviço ViaCEP e uma aplicação web.

No backend deverá ser feito um cache das consultas realizadas pelo usuário.
Após uma consulta ser feita na api do viaCEP, as informações de endereço devem
ser armazenadas em um banco de dado e nas consultas subsequentes do mesmo
CEP, estas informações devem ser retornadas sem buscar novamente no serviço
viaCEP.

O frontend deve possuir uma tela com um campo para informar o CEP, um botão
para realizar a busca, e após clicar no botão deverá ser apresentado as
informações do endereço.

### Como rodar o projeto

O projeto pode ser executado atraves de um .apk localizado em: `projeto-ViaCep\app\build\outputs\apk\debug\app-debug.apk`
ou aberto na IDE android studio.
