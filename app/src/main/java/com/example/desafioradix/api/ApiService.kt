package com.example.desafioradix.api

import com.example.desafioradix.api.model.DetalhesDataResponse
import com.example.desafioradix.api.model.RepositorioDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    val GSON: String
        get() = "{\n" +
                "  \"login\": \"art2202\",\n" +
                "  \"id\": 55219618,\n" +
                "  \"node_id\": \"MDQ6VXNlcjU1MjE5NjE4\",\n" +
                "  \"avatar_url\": \"https://avatars1.githubusercontent.com/u/55219618?v=4\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/art2202\",\n" +
                "  \"html_url\": \"https://github.com/art2202\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/art2202/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/art2202/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/art2202/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/art2202/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/art2202/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/art2202/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/art2202/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/art2202/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/art2202/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": null,\n" +
                "  \"company\": null,\n" +
                "  \"blog\": \"\",\n" +
                "  \"location\": null,\n" +
                "  \"email\": null,\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": null,\n" +
                "  \"public_repos\": 6,\n" +
                "  \"public_gists\": 0,\n" +
                "  \"followers\": 0,\n" +
                "  \"following\": 0,\n" +
                "  \"created_at\": \"2019-09-12T01:42:56Z\",\n" +
                "  \"updated_at\": \"2020-04-26T17:05:36Z\"\n" +
                "}"

    @GET
    suspend fun getRepositorios(@Url url : String) : Response<List<RepositorioDataResponse>>

    @GET
    suspend fun getDetalhes(@Url url : String) : Response<DetalhesDataResponse>
}