package com.salekart.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.salekart.ui.components.Loader
import com.salekart.ui.components.ProductRowComponent
import com.salekart.ui.viewmodel.ProductViewModel
import com.salekart.utilities.ResourceState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    productViewModel: ProductViewModel = hiltViewModel()
) {

    val productResponse by productViewModel.products.collectAsState()
    val pageState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        100
    }

    VerticalPager(
        state = pageState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
        ) { page: Int ->

        when (productResponse) {
            is ResourceState.Loading -> Loader()
            is ResourceState.Success -> {
                val response = (productResponse as ResourceState.Success).data

                if (response.products.isNotEmpty()) {
                    ProductRowComponent(page, response.products[page])
                }
            }

            is ResourceState.Error -> (productResponse as ResourceState.Error)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}