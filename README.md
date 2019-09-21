# GitHub REST API test project
Spock tests for GitHub [REST API v3](https://developer.github.com/v3/).

## Usage

### Setup test environment
* Create new file (i.e. *.env*) in the root folder of the project
* Set following variables in that file:
    ```properties
    # Enviroment variables

    ## GitHub REST API URL
    GITHUB_REST_API_URL = https://api.github.com

    ## Your personal access token [https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line]
    GITHUB_ACCESS_TOKEN = 
   ```
     
### Run tests
* To run all tests:
    ```cmd
    gradle test
    ```

## License  
Copyright © 2019 Vladimir Astakhov [viastakhov@mail.ru]

Distributed under the Eclipse Public License
