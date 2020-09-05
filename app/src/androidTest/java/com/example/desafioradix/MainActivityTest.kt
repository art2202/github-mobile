import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.desafioradix.R
import com.example.desafioradix.ui.InformacoesActivity
import com.example.desafioradix.ui.MainActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java, false, true
    )

    @Test
    fun verifica_editText_e_buttom_esta_visivel(){
        onView(withId(R.id.edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.botao_buscar)).check(matches(isDisplayed()))
    }

    @Test
    fun insere_nome_e_busca(){
        onView(withId(R.id.edit_text)).perform(typeText("art2202"))
        closeSoftKeyboard()
        onView(withId(R.id.botao_buscar)).perform(click())
    }

    @Test
    fun verifica_editText_esta_vazio(){
        onView(withId(R.id.edit_text)).perform(typeText(""))
        closeSoftKeyboard()
        onView(withId(R.id.botao_buscar)).perform(click())
    }

    @Test
    fun testa_intent(){

        Intents.init();
        onView(withId(R.id.edit_text)).perform(typeText("art2202"))
        closeSoftKeyboard()

        val matcher: Matcher<Intent> = hasComponent(InformacoesActivity::class.java.name)

        val result = ActivityResult(Activity.RESULT_OK, null)
        intending(matcher).respondWith(result)

        onView(withId(R.id.botao_buscar)).perform(click())
        intended(matcher)
        Intents.release()
    }


}