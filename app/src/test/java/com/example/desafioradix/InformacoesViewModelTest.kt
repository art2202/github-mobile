package com.example.desafioradix

import com.example.desafioradix.base.BaseUTTest
import com.example.desafioradix.di.configureTestAppComponent
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.desafioradix.api.repository.InformacoesRepository
import com.example.desafioradix.ui.viewModel.InformacoesViewModel
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Test

@RunWith(JUnit4::class)
class InformacoesViewModelTest : BaseUTTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository : InformacoesRepository

    lateinit var informacoesViewModel: InformacoesViewModel

    @Before
    fun start(){
        super.setUp()
        //Used for initiation of Mockk
        MockKAnnotations.init(this)
        //Start Koin with required dependencies
        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }


    @Test
    fun test_login_view_model_data_populates_expected_value(){

        informacoesViewModel = InformacoesViewModel(repository)
        val sampleResponse = getJson("success_resp_list.json")
        var jsonObj = Gson().fromJson(sampleResponse, AllPeople::class.java)
        //Make sure login use case returns expected response when called
        coEvery { repository.processLoginUseCase(any()) } returns jsonObj
        informacoesViewModel.response().observeForever {  }

        informacoesViewModel.requestLoginActivityData(mParam)

        assert(mLoginActivityViewModel.mAllPeopleResponse.value != null)
        assert(mLoginActivityViewModel.mAllPeopleResponse.value!!.responseRESPONSESTATUS
                == LiveDataWrapper.RESPONSESTATUS.SUCCESS)
        val testResult = mLoginActivityViewModel.mAllPeopleResponse.value as LiveDataWrapper<AllPeople>
        Assert.assertEquals(testResult.response!!.next, mNextValue)
    }

}