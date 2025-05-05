package com.emrekizil.detail

import com.emrekizil.core.testing.TestResponseEnum
import com.emrekizil.core.testing.TestSpaceWatchRepository
import com.emrekizil.core.testing.satelliteDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private lateinit var repository: TestSpaceWatchRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = TestSpaceWatchRepository()
        viewModel = DetailViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        val initialState = viewModel.detailUiState.value
        assertTrue(initialState is DetailUiState.Loading)
    }

    @Test
    fun uiState_whenGetSatelliteDetail_thenShowError() = runTest {
        viewModel.getSatelliteDetail(1)

        repository.setRequest(TestResponseEnum.ERROR)
        advanceUntilIdle()

        assertTrue(viewModel.detailUiState.value is DetailUiState.Error)
    }


    @Test
    fun uiState_whenGetNewSatellites_thenShowSatellites() = runTest {
        viewModel.getSatelliteDetail(1)
        repository.setRequest(TestResponseEnum.SUCCESS)
        advanceUntilIdle()

        assertEquals(
            expected = DetailUiState.Success(satelliteDetail().toSatelliteUiModel()) ,
            actual = viewModel.detailUiState.value
        )
    }

    @Test
    fun uiState_whenGetNewSatellitesNewState_thenShowUpdatedState() = runTest {
        viewModel.getSatelliteDetail(1)

        assertTrue(
            viewModel.detailUiState.value is DetailUiState.Loading
        )

        repository.setRequest(TestResponseEnum.SUCCESS)
        advanceUntilIdle()

        assertEquals(
            expected = DetailUiState.Success(satelliteDetail().toSatelliteUiModel()) ,
            actual = viewModel.detailUiState.value
        )
    }
}