package com.github.qa.restapi.v3

import com.github.qa.restapi.v3.controllers.Gists
import com.github.qa.restapi.v3.controllers.Users


class GitHubClient {

    static Gists getGistsController() {
        return Gists.instance
    }

    static Users getUsersController() {
        return Users.instance
    }
}
