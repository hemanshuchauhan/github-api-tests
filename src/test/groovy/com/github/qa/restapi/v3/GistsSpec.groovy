package com.github.qa.restapi.v3

import com.github.qa.restapi.v3.constants.Environment
import spock.lang.*


@Title('REST API v3: Gists')
@See('https://developer.github.com/v3/gists')
@Issue([])
@Timeout(240)
@Requires({ Environment.GITHUB_ACCESS_TOKEN })
class GistsSpec extends Specification {

    @PendingFeature
    def 'List all public gists'() {
        when: 'I request the list all public gists'
        def response = GitHubClient.gistsController.getAllPublicGists(since)

        then: 'I retrieve expected list sorted by most recently updated'
        verifyAll {
            true
        }

        where:
        since << ['2010-04-14T02:15:15Z', '', '2100-04-14T02:15:15Z']

        gist << [
                [],
                [],
                []
        ]
    }
}