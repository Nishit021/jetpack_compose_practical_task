package com.salekart

import com.google.gson.Gson
import com.salekart.data.api.ApiServices
import com.salekart.data.datasource.ProductDataSource
import com.salekart.data.datasource.ProductDataSourceImpl
import com.salekart.data.entity.Product
import com.salekart.di.AppModule
import com.salekart.ui.repository.ProductRepository
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class UserRepositoryImplTest {

    private lateinit var repository: ProductRepository
    private lateinit var testApis: ApiServices
    private lateinit var productDataSource: ProductDataSource
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        testApis = AppModule.getNetworkApi()
        productDataSource = ProductDataSourceImpl(testApis)
        repository = ProductRepository(productDataSource = productDataSource)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `for server success, api must return with http code 200`() = runTest {
        val products = emptyList<Product>()
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(products))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = productDataSource.getListOfProduct()
        assert(actualResponse.code() == HttpURLConnection.HTTP_OK)
    }
}