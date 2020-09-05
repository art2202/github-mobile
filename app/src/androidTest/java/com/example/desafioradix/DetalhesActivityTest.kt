package com.example.desafioradix

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafioradix.ui.DetalhesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetalhesActivityTest {


    @get:Rule
    var activityRule: ActivityTestRule<DetalhesActivity> = ActivityTestRule(
        DetalhesActivity::class.java, false, true
    )

    @Test
    fun verifica_se_tudo_ta_visivel(){
        onView(withId(R.id.botao_voltar)).check(matches(isDisplayed()))
        onView(withId(R.id.text_descricao)).check(matches(isDisplayed()))
        onView(withId(R.id.text_linguagem)).check(matches(isDisplayed()))
        onView(withId(R.id.text_data)).check(matches(isDisplayed()))
        onView(withId(R.id.abrir_git)).check(matches(isDisplayed()))
        onView(withId(R.id.issues)).check(matches(isDisplayed()))
        onView(withId(R.id.star)).check(matches(isDisplayed()))
        onView(withId(R.id.fork)).check(matches(isDisplayed()))
    }

    @Test
    fun verifica_botao_voltar(){
        onView(withId(R.id.botao_voltar)).perform(click())
    }

    @Test
    fun verifica_botao_git(){
        onView(withId(R.id.abrir_git)).perform(click())
    }

}