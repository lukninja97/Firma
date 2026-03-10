# Firma

## Descrição do Projeto

O projeto **Firma** é uma aplicação Android desenvolvida em Kotlin, projetada para gerenciar informações de funcionários. Ele oferece funcionalidades CRUD (Criar, Ler, Atualizar, Deletar) para registros de funcionários, além de um sistema de persistência de dados robusto que combina um banco de dados local (Room) com a capacidade de importar e exportar dados através de arquivos de texto (`.txt`). A aplicação visa proporcionar uma interface intuitiva para a administração de dados de pessoal, sendo um excelente exemplo de desenvolvimento Android moderno com foco em boas práticas de arquitetura e persistência de dados.

## Screenshots

<table>
  <tr>
    <th>Tela Inicial</th>
    <th>Cadastro de Funcionário</th>
    <th>Lista de Funcionários</th>
    <th>Detalhes do Funcionário</th>
  </tr>
  <tr>
    <td><img width="195" height="400" alt="image" src="https://github.com/user-attachments/assets/661e97ca-c2bb-4a19-8ce7-e9e1bb572f7c" /></td>
    <td><img width="195" height="400" alt="image" src="https://github.com/user-attachments/assets/15e963f3-f81a-4260-ba03-7cec981c2010" /></td>
    <td><img width="195" height="400" alt="image" src="https://github.com/user-attachments/assets/9ca5e6a5-c758-465e-ad7e-5888bbcc27fd" /></td>
    <td><img width="195" height="400" alt="image" src="https://github.com/user-attachments/assets/e409f093-3d49-419f-86cd-9b841a7d7f3d" /></td>
  </tr>
  <tr>
    <th colspan="2">Persistencia em .txt</th>
    <th colspan="2">Importar em .txt</th>
  </tr>
  <tr>
    <td colspan="2"><img width="1093" height="993" alt="image" src="https://github.com/user-attachments/assets/b515ed2c-6726-4019-9963-9a8ba0d75d18" /></td>
    <td colspan="2"><img width="270" height="600" alt="image" src="https://github.com/user-attachments/assets/08d79b0f-8faf-410b-89f7-a8d43345d4c1" /></td>
  </tr>
</table>


## Funcionalidades

*   **Gerenciamento de Funcionários (CRUD):** Permite adicionar, visualizar, editar e remover registros de funcionários.
*   **Persistência de Dados Local:** Utiliza o Room Persistence Library para armazenar dados de forma eficiente e segura no dispositivo.
*   **Importação de Dados:** Capacidade de importar dados de funcionários a partir de arquivos de texto (`.txt`) formatados com valores separados por ponto e vírgula.
*   **Exportação de Dados:** Os dados dos funcionários são automaticamente atualizados em um arquivo de texto local, refletindo as operações de CRUD.
*   **Interface de Usuário Intuitiva:** Navegação clara entre a lista de funcionários e as telas de detalhes/edição.

## Tecnologias Utilizadas

O projeto **Firma** foi construído utilizando as seguintes tecnologias e bibliotecas:

*   **Kotlin:** Linguagem de programação principal para o desenvolvimento Android.
*   **Android SDK:** Ferramentas e APIs para o desenvolvimento de aplicativos Android.
*   **Gradle:** Sistema de automação de build.
*   **View Binding:** Para interagir com as views de forma segura e concisa.
*   **Android Architecture Components:**
    *   **ViewModel:** Gerencia dados relacionados à UI de forma consciente ao ciclo de vida.
    *   **LiveData:** Observables que são conscientes ao ciclo de vida.
    *   **Room Persistence Library:** Camada de abstração sobre SQLite para persistência de dados local.
    *   **Navigation Components:** Gerencia a navegação entre os destinos do aplicativo.
*   **Kotlin Coroutines:** Para programação assíncrona e não bloqueante.
*   **Material Design:** Componentes de UI para uma experiência de usuário moderna e consistente.

## Arquitetura

O projeto segue o padrão de arquitetura **MVVM (Model-View-ViewModel)**, promovendo a separação de responsabilidades e facilitando a manutenção e testabilidade do código. 

*   **Model:** Representado por `FuncionarioModel` e as classes de repositório (`FuncionarioRepository`, `FirmaDataBase`, `FuncionarioDAO`, `ArquivoTxt`). Responsável pela lógica de negócios e persistência de dados.
*   **View:** Implementada pelos `Fragment`s (`AllFuncionariosFragment`, `FuncionarioFragment`) e seus respectivos layouts. Responsável por exibir a interface do usuário e enviar eventos para o ViewModel.
*   **ViewModel:** (`AllFuncionariosViewModel`, `FuncionarioViewModel`) Atua como um intermediário entre a View e o Model, expondo dados para a View e manipulando a lógica de interação com o Model.

## Estrutura do Projeto

A estrutura do projeto segue as convenções padrão de um aplicativo Android, com uma organização clara dos pacotes:

```
Firma/
├── app/
│   ├── src/
│   │   ├── androidTest/ (Testes de instrumentação)
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/ (Código-fonte Kotlin)
│   │   │   │   └── com/example/firma/
│   │   │   │       ├── service/ (Lógica de negócio, modelos, repositórios)
│   │   │   │       │   ├── constants/
│   │   │   │       │   ├── listener/
│   │   │   │       │   ├── model/ (FuncionarioModel)
│   │   │   │       │   └── repository/ (FuncionarioRepository, ArquivoTxt, local/)
│   │   │   │       ├── view/ (Fragmentos, adaptadores, viewholders)
│   │   │   │       │   ├── adapter/
│   │   │   │       │   ├── viewholder/
│   │   │   │       │   └── AllFuncionariosFragment.kt, FuncionarioFragment.kt, MainActivity.kt
│   │   │   │       └── viewmodel/ (AllFuncionariosViewModel, FuncionarioViewModel)
│   │   │   └── res/ (Recursos: layouts, drawables, valores, navegação)
│   │   └── test/ (Testes unitários)
│   └── build.gradle (Configurações de build do módulo app)
├── build.gradle (Configurações de build do projeto)
├── gradle/
└── settings.gradle
```

## Como Rodar o Projeto

Para configurar e executar o projeto **Firma** em seu ambiente de desenvolvimento, siga os passos abaixo:

1.  **Pré-requisitos:**
    *   Android Studio instalado (versão Arctic Fox ou superior é recomendada).
    *   SDK do Android configurado (API Level 29 ou superior).
    *   Java Development Kit (JDK) 8 ou superior.

2.  **Clonar o Repositório:**
    ```bash
    git clone https://github.com/lukninja97/Firma.git
    cd Firma
    ```

3.  **Abrir no Android Studio:**
    *   Abra o Android Studio.
    *   Selecione `File > Open` e navegue até o diretório `Firma` que você clonou.
    *   Aguarde o Android Studio sincronizar o projeto e baixar as dependências do Gradle.

4.  **Executar o Aplicativo:**
    *   Conecte um dispositivo Android físico ao seu computador ou inicie um emulador Android.
    *   Selecione o dispositivo ou emulador na barra de ferramentas do Android Studio.
    *   Clique no botão `Run 'app'` (ícone de play verde) para instalar e executar o aplicativo.

## Importação de Dados

O aplicativo permite a importação de dados de funcionários a partir de um arquivo de texto (`.txt`). O formato esperado para cada linha do arquivo é:

`id;nome;complemento;reservado1;reservado2`

Exemplo:

```
1;João Silva;Operador;10;20
2;Maria Souza;Mecanico;15;25
```

Para importar, utilize o botão de importação na tela principal do aplicativo e selecione o arquivo `.txt` desejado.

## Contribuição

Contribuições são bem-vindas! Se você deseja contribuir para o projeto, por favor, siga estes passos:

1.  Faça um fork do repositório.
2.  Crie uma nova branch (`git checkout -b feature/sua-feature`).
3.  Faça suas alterações e commit-as (`git commit -am 'Adiciona nova feature'`).
4.  Envie para a branch (`git push origin feature/sua-feature`).
5.  Abra um Pull Request.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## Contato

Para dúvidas ou sugestões, entre em contato com lukninja97.
