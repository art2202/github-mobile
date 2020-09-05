package com.example.desafioradix

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafioradix.ui.InformacoesActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers.not


@RunWith(AndroidJUnit4::class)
class InformacoesActivityTest{


    //comentar a variavel nome que pega da intent e descomentar a que tem nome estatico
    //para realizar teste nessa activity

    @get:Rule
    var activityRule: ActivityTestRule<InformacoesActivity> = ActivityTestRule(
        InformacoesActivity::class.java, false, true
    )



    @Test
    fun verifica_se_tudo_ta_visivel(){
        onView(withId(R.id.botao_voltar)).check(matches(isDisplayed()))
        onView(withId(R.id.text_repositorios)).check(matches(isDisplayed()))
        onView(withId(R.id.nome_usuario)).check(matches(isDisplayed()))
        onView(withId(R.id.shimmer)).check(matches(isDisplayed()))
        onView(withId(R.id.layout_informacoes)).check(matches(isDisplayed()))
    }

    @Test
    fun verifica_recycler_invisivel(){
        onView(withId(R.id.recycler_repositorios)).check(matches(not(isDisplayed())))
    }
    @Test
    fun verifica_texto(){
        onView(withId(R.id.text_repositorios)).perform(typeText("Repositórios"))
    }
    @Test
    fun testa_botao_voltar(){
        onView(withId(R.id.botao_voltar)).perform(click())

    }

}