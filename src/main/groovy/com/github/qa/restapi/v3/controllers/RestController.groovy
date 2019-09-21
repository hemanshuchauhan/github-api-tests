package com.github.qa.restapi.v3.controllers

import com.github.qa.restapi.v3.constants.Environment
import groovyx.net.http.RESTClient


trait RestController {
    RESTClient restClient = new RESTClient(Environment.GITHUB_REST_API_URL)
    String access_token = Environment.GITHUB_ACCESS_TOKEN
    String user_agent = Environment.GITHUB_USER_AGENT
}