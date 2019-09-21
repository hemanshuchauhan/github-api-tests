package com.github.qa.restapi.v3.controllers

import com.github.qa.restapi.v3.enums.ContentType
import groovy.util.logging.Log4j


/**
 * Gists REST Controller: https://developer.github.com/v3/gists
 */
@Singleton(lazy = true)
@Log4j
class Gists implements RestController {

    def getAllPublicGists(String since) {
        log.info "getAllPublicGists >> since = ${since}"

        def response = restClient.get(
                path: "/gists/public",
                headers: [
                        Accept       : ContentType.VND_GITHUB_V3_JSON,
                        Authorization: "Bearer $access_token",
                        'User-Agent' : user_agent
                ],
                query: ["since": since]
        )
        assert response.status == 200

        log.info "getAllPublicGists << ${response.data}"
        return response.data
    }
}