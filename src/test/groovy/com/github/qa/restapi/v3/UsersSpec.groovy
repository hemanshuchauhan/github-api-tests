package com.github.qa.restapi.v3

import com.github.qa.restapi.v3.constants.Environment
import spock.lang.*


@Title('REST API v3: Users')
@See('https://developer.github.com/v3/users')
@Issue([])
@Timeout(240)
@Requires({ Environment.GITHUB_ACCESS_TOKEN })
@SuppressWarnings(["GroovyAssignabilityCheck"])
class UsersSpec extends Specification {
    static def user_backup

    def setupSpec() {
        user_backup = GitHubClient.usersController.getAuthenticatedUser()
    }

    def cleanupSpec() {
        GitHubClient.usersController.updateAuthenticatedUser([
                name    : user_backup.name ?: '',
                email   : user_backup.email ?: '',
                blog    : user_backup.blog ?: '',
                company : user_backup.company ?: '',
                location: user_backup.location ?: '',
                hireable: user_backup.hireable ?: false,
                bio     : user_backup.bio ?: ''
        ])
    }


    def 'Get a single user'() {
        when: 'I request publicly available information about the user with a GitHub account'
        def response = GitHubClient.usersController.getSingleUser(login)

        then: 'I retrieve expected publicly available information in the response'
        verifyAll {
            response.login == user_info.login
            response.id.class == user_info.id
            response.node_id.class == user_info.node_id
            response.avatar_url.class == user_info.avatar_url
            response.gravatar_id == user_info.gravatar_id
            response.url == user_info.url
            response.html_url == user_info.html_url
            response.followers_url == user_info.followers_url
            response.following_url == user_info.following_url
            response.gists_url == user_info.gists_url
            response.starred_url == user_info.starred_url
            response.subscriptions_url == user_info.subscriptions_url
            response.organizations_url == user_info.organizations_url
            response.repos_url == user_info.repos_url
            response.events_url == user_info.events_url
            response.received_events_url == user_info.received_events_url
            response.site_admin == user_info.site_admin
            response.name == user_info.name
            response.company == user_info.company
            response.blog == user_info.blog
            response.location == user_info.location
            response.email == user_info.email
            response.hireable == user_info.hireable
            response.bio == user_info.bio
            response.public_repos.class == user_info.public_repos
            response.public_gists.class == user_info.public_gists
            response.followers.class == user_info.followers
            response.following.class == user_info.following
            Date.parse("YYYY-MM-DD'T'HH:MM:SS", response.created_at)
            Date.parse("YYYY-MM-DD'T'HH:MM:SS", response.updated_at)
        }

        where:
        login        | name                 | email                             | company         | location         | blog                                | hireable
        'josevalim'  | 'José Valim'         | 'jose.valim@plataformatec.com.br' | 'Plataformatec' | 'Kraków, Poland' | 'http://blog.plataformatec.com.br/' | null
        'richhickey' | 'Rich Hickey'        | null                              | null            | 'NY'             | 'http://clojure.org'                | null
        'viastakhov' | 'Vladimir Astakhoff' | 'viastakhov@mail.ru'              | 'GE'            | 'Moscow, Russia' | ''                                  | true

        user_info = [
                login              : login,
                id                 : Integer,
                node_id            : String,
                avatar_url         : String,
                gravatar_id        : '',
                url                : "https://api.github.com/users/$login",
                html_url           : "https://github.com/$login",
                followers_url      : "https://api.github.com/users/$login/followers",
                following_url      : "https://api.github.com/users/$login/following{/other_user}",
                gists_url          : "https://api.github.com/users/$login/gists{/gist_id}",
                starred_url        : "https://api.github.com/users/$login/starred{/owner}{/repo}",
                subscriptions_url  : "https://api.github.com/users/$login/subscriptions",
                organizations_url  : "https://api.github.com/users/$login/orgs",
                repos_url          : "https://api.github.com/users/$login/repos",
                events_url         : "https://api.github.com/users/$login/events{/privacy}",
                received_events_url: "https://api.github.com/users/$login/received_events",
                type               : 'User',
                site_admin         : false,
                name               : name,
                company            : company,
                blog               : blog,
                location           : location,
                email              : email,
                hireable           : hireable,
                bio                : null,
                public_repos       : Integer,
                public_gists       : Integer,
                followers          : Integer,
                following          : Integer
        ]
    }


    def 'Get the authenticated user'() {
        when: 'I request the public profile information when authenticated through OAuth without the user scope'
        def response = GitHubClient.usersController.getAuthenticatedUser()

        then: 'I retrieve expected public profile information in the response'
        verifyAll {
            response.login == user_info.login
            response.id.class == user_info.id
            response.node_id.class == user_info.node_id
            response.avatar_url.class == user_info.avatar_url
            response.gravatar_id == user_info.gravatar_id
            response.url == user_info.url
            response.html_url == user_info.html_url
            response.followers_url == user_info.followers_url
            response.following_url == user_info.following_url
            response.gists_url == user_info.gists_url
            response.starred_url == user_info.starred_url
            response.subscriptions_url == user_info.subscriptions_url
            response.organizations_url == user_info.organizations_url
            response.repos_url == user_info.repos_url
            response.events_url == user_info.events_url
            response.received_events_url == user_info.received_events_url
            response.site_admin == user_info.site_admin
            response.name == user_info.name
            response.company == user_info.company
            response.blog == user_info.blog
            response.location == user_info.location
            response.email == user_info.email
            response.hireable == user_info.hireable
            response.bio == user_info.bio
            response.public_repos.class == user_info.public_repos
            response.public_gists.class == user_info.public_gists
            response.followers.class == user_info.followers
            response.following.class == user_info.following
            Date.parse("YYYY-MM-DD'T'HH:MM:SS", response.created_at)
            Date.parse("YYYY-MM-DD'T'HH:MM:SS", response.updated_at)
        }

        where:
        login        | name                 | email                | company | location         | blog | hireable
        'viastakhov' | 'Vladimir Astakhoff' | 'viastakhov@mail.ru' | 'GE'    | 'Moscow, Russia' | ''   | true

        user_info = [
                login              : login,
                id                 : Integer,
                node_id            : String,
                avatar_url         : String,
                gravatar_id        : '',
                url                : "https://api.github.com/users/$login",
                html_url           : "https://github.com/$login",
                followers_url      : "https://api.github.com/users/$login/followers",
                following_url      : "https://api.github.com/users/$login/following{/other_user}",
                gists_url          : "https://api.github.com/users/$login/gists{/gist_id}",
                starred_url        : "https://api.github.com/users/$login/starred{/owner}{/repo}",
                subscriptions_url  : "https://api.github.com/users/$login/subscriptions",
                organizations_url  : "https://api.github.com/users/$login/orgs",
                repos_url          : "https://api.github.com/users/$login/repos",
                events_url         : "https://api.github.com/users/$login/events{/privacy}",
                received_events_url: "https://api.github.com/users/$login/received_events",
                type               : 'User',
                site_admin         : false,
                name               : name,
                company            : company,
                blog               : blog,
                location           : location,
                email              : email,
                hireable           : hireable,
                bio                : null,
                public_repos       : Integer,
                public_gists       : Integer,
                followers          : Integer,
                following          : Integer
        ]
    }


    def 'Update the authenticated user'() {
        when: 'I update the authenticated user through OAuth'
        def response = GitHubClient.usersController.updateAuthenticatedUser(parameters)

        then: 'I retrieve expected public profile information in the response'
        verifyAll {
            (response.name ?: '') == name
            (response.company ?: '') == company
            (response.blog ?: '') == blog
            (response.location ?: '') == location
            (response.email ?: '') == email
            (response.hireable ?: false) == hireable
            (response.bio ?: '') == bio
        }

        where:
        name                   | email                | company | location         | blog                         | hireable | bio
        'Vladimir Astakhov'    | 'viastakhov@mail.ru' | ''      | ''               | ''                           | false    | ''
        'Vladimir I. Astakhov' | 'viastakhov@mail.ru' | 'GE'    | 'Moscow, Russia' | 'https://elixir-lang.org/'   | true     | UUID.randomUUID().toString()
        ''                     | 'viastakhov@mail.ru' | ''      | 'NY, USA'        | ''                           | false    | ''
        'Vladimir Astakhov'    | ''                   | 'BHGE'  | ''               | 'http://spockframework.org/' | false    | ''
        ''                     | ''                   | ''      | ''               | ''                           | false    | ''

        parameters = [
                name    : name,
                email   : email,
                blog    : blog,
                company : company,
                location: location,
                hireable: hireable,
                bio     : bio
        ]
    }


    def 'Get contextual information about a user'() {
        when: 'I request contextual information about a user'
        def response = GitHubClient.usersController.getHovercardInfo(username, subject_type, subject_id)

        then: 'I retrieve expected hovercard  information'
        response == contexts


        where:
        username  | subject_type   | subject_id
        'octocat' | 'repository'   | '1300192'
        'octocat' | 'issue'        | '484833171'
        'mhjort'  | 'repository'   | '15944295'
        'mhjort'  | 'pull_request' | '232756032'
        'mojombo' | 'organization' | '7966854'

        contexts << [
                [
                        contexts: [
                                [
                                        message: 'Owns this repository',
                                        octicon: 'repo'
                                ],
                                [
                                        message: 'Committed to this repository',
                                        octicon: 'git-commit'
                                ]
                        ]
                ],
                [
                        contexts: [
                                [
                                        message: 'Owns this repository',
                                        octicon: 'repo'
                                ],
                                [
                                        message: 'Committed to this repository',
                                        octicon: 'git-commit'
                                ]
                        ]
                ],
                [
                        contexts: [
                                [
                                        message: 'Owns this repository',
                                        octicon: 'repo'
                                ],
                                [
                                        message: 'Committed to this repository',
                                        octicon: 'git-commit'
                                ]
                        ]
                ],
                [
                        contexts: [
                                [
                                        message: 'Opened this pull request',
                                        octicon: 'git-pull-request'
                                ],
                                [
                                        message: 'Owns this repository',
                                        octicon: 'repo'
                                ],
                                [
                                        message: 'Committed to this repository',
                                        octicon: 'git-commit'
                                ]
                        ]
                ],
                [
                        contexts: [
                                [
                                        message: 'Member of @toml-lang',
                                        octicon: 'organization'
                                ]
                        ]
                ]
        ]
    }


    def 'Get all users'() {
        when: 'I request lists all users, in the order that they signed up on GitHub'
        def response = GitHubClient.usersController.getAllUsers(since as String).first()

        then: 'I retrieve paginated list users in the order that they signed up on GitHub'
        verifyAll {
            response.login == user_info.login
            response.id == user_info.id
            response.node_id.class == user_info.node_id
            response.avatar_url.class == user_info.avatar_url
            response.gravatar_id == user_info.gravatar_id
            response.url == user_info.url
            response.html_url == user_info.html_url
            response.followers_url == user_info.followers_url
            response.following_url == user_info.following_url
            response.gists_url == user_info.gists_url
            response.starred_url == user_info.starred_url
            response.subscriptions_url == user_info.subscriptions_url
            response.organizations_url == user_info.organizations_url
            response.repos_url == user_info.repos_url
            response.events_url == user_info.events_url
            response.received_events_url == user_info.received_events_url
            response.site_admin == user_info.site_admin
        }

        where:
        since    | login        | id
        0        | 'mojombo'    | 1
        9581     | 'josevalim'  | 9582
        44951702 | 'viastakhov' | 44951703

        user_info = [
                login              : login,
                id                 : id,
                node_id            : String,
                avatar_url         : String,
                gravatar_id        : '',
                url                : "https://api.github.com/users/$login",
                html_url           : "https://github.com/$login",
                followers_url      : "https://api.github.com/users/$login/followers",
                following_url      : "https://api.github.com/users/$login/following{/other_user}",
                gists_url          : "https://api.github.com/users/$login/gists{/gist_id}",
                starred_url        : "https://api.github.com/users/$login/starred{/owner}{/repo}",
                subscriptions_url  : "https://api.github.com/users/$login/subscriptions",
                organizations_url  : "https://api.github.com/users/$login/orgs",
                repos_url          : "https://api.github.com/users/$login/repos",
                events_url         : "https://api.github.com/users/$login/events{/privacy}",
                received_events_url: "https://api.github.com/users/$login/received_events",
                type               : 'User',
                site_admin         : false
        ]
    }
}