package com.github.qa.restapi.v3.constants

interface Environment {
    Properties props = new Properties()

    File propsFile = new File("./.env").withInputStream {
        props.load it
    }

    static String GITHUB_ACCESS_TOKEN = props.get('GITHUB_ACCESS_TOKEN', '')
    static String GITHUB_REST_API_URL = props.get('GITHUB_REST_API_URL', 'https://api.github.com')
    static String GITHUB_USER_AGENT = props.get('GITHUB_USER_AGENT', '')
}