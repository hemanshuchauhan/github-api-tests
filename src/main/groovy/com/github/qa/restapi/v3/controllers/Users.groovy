package com.github.qa.restapi.v3.controllers

import com.github.qa.restapi.v3.enums.ContentType
import groovy.util.logging.Log4j


/**
 * Users REST Controller: https://developer.github.com/v3/users
 */
@Singleton(lazy = true)
@Log4j
class Users implements RestController {

    /**
     * Get a single user
     * @see: {@linktourl https://developer.github.com/v3/users/#get-a-single-user}
     * @param username (i.e.: viastakhov)
     * @return publicly available information about someone with a GitHub account.
     */
    def getSingleUser(String username) {
        log.info "getSingleUser >> username = ${username}"

        def response = restClient.get(
                path: "/users/$username",
                headers: [
                        Accept       : ContentType.VND_GITHUB_V3_JSON,
                        Authorization: "token $access_token",
                        'User-Agent' : user_agent
                ]
        )

        assert response.status == 200

        log.info "getSingleUser << ${response.data}"
        return response.data
    }


    /**
     * Get the authenticated user
     * @see: {@linktourl https://developer.github.com/v3/users/#get-the-authenticated-user}
     * @return profile information when authenticated through access token
     */
    def getAuthenticatedUser() {
        log.info "getAuthenticatedUser >>"

        def response = restClient.get(
                path: "/user",
                headers: [
                        Accept       : ContentType.VND_GITHUB_V3_JSON,
                        Authorization: "token $access_token",
                        'User-Agent' : user_agent
                ]
        )

        assert response.status == 200

        log.info "getAuthenticatedUser << ${response.data}"
        return response.data
    }


    /**
     * Update the authenticated user
     * @see: {@linktourl https://developer.github.com/v3/users/#update-the-authenticated-user}
     * @return profile information when authenticated through access token
     */
    def updateAuthenticatedUser(Map parameters) {
        log.info "updateAuthenticatedUser >> parameters = $parameters"

        def response = restClient.patch(
                path: "/user",
                headers: [
                        Accept       : ContentType.VND_GITHUB_V3_JSON,
                        Authorization: "token $access_token",
                        'User-Agent' : user_agent
                ],
                body: parameters,
                contentType: ContentType.JSON
        )

        assert response.status == 200

        log.info "updateAuthenticatedUser << ${response.data}"
        return response.data
    }


    /**
     * Get contextual information about a user
     * @see: {@linktourl https://developer.github.com/v3/users/#get-contextual-information-about-a-user}
     * @param username (i.e.: viastakhov)
     * @param subject_type - identifies which additional information you'd like to receive about the person's hovercard
     * @param subject_id - uses the ID for the subject_type you specified
     * @return hovercard information when authenticated through basic auth or OAuth with the repo scope
     */
    def getHovercardInfo(String username, String subject_type, String subject_id) {
        log.info "getHovercardInfo >> username = $username, subject_type = $subject_type, subject_id = $subject_id"

        def response = restClient.get(
                path: "/users/$username/hovercard",
                headers: [
                        Accept       : ContentType.VND_GITHUB_HAGAR_PREVIEW_JSON,
                        Authorization: "token $access_token",
                        'User-Agent' : user_agent
                ],
                query: [
                        subject_type: subject_type,
                        subject_id  : subject_id
                ]
        )

        assert response.status == 200

        log.info "getHovercardInfo << ${response.data}"
        return response.data
    }


    /**
     * Get all users
     * @see: {@linktourl https://developer.github.com/v3/users/#get-all-users}
     * @param since
     * @return lists all users, in the order that they signed up on GitHub
     */
    def getAllUsers(String since) {
        log.info "getAllUsers >> since = ${since}"

        def response = restClient.get(
                path: "/users",
                headers: [
                        Accept       : ContentType.VND_GITHUB_V3_JSON,
                        Authorization: "Bearer $access_token",
                        'User-Agent' : user_agent
                ],
                query: ["since": since]
        )
        assert response.status == 200

        log.info "getAllUsers << ${response.data}"
        return response.data
    }
}