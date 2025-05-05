package com.emrekizil.list

import com.emrekizil.core.testing.TestResponseEnum
import com.emrekizil.core.testing.TestSpaceWatchRepository
import com.emrekizil.core.testing.satelliteList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
class ListViewModelTest {

    private lateinit var viewModel: ListViewModel
    private lateinit var repository: TestSpaceWatchRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = TestSpaceWatchRepository()
        viewModel = ListViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun uiState_whenInitialized_thenShowLoading() = runTest {
        val initialState = viewModel.listUiState.value
        assertTrue(initialState is ListUiState.Loading)
    }

    @Test
    fun uiState_whenGetNewSatellites_thenShowError() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.listUiState.collect() }

        repository.setRequest(TestResponseEnum.ERROR)
        advanceUntilIdle()

        assertTrue(viewModel.listUiState.value is ListUiState.Error)
    }


    @Test
    fun uiState_whenGetNewSatellites_thenShowSatellites() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.listUiState.collect() }

        repository.setRequest(TestResponseEnum.SUCCESS)
        advanceUntilIdle()

        assertEquals(
            expected = ListUiState.Success(satelliteList()) ,
            actual = viewModel.listUiState.value
        )
    }

    @Test
    fun uiState_whenGetNewSatellitesNewState_thenShowUpdatedState() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.listUiState.collect() }

        assertTrue(
            viewModel.listUiState.value is ListUiState.Loading
        )

        repository.setRequest(TestResponseEnum.SUCCESS)
        advanceUntilIdle()

        assertEquals(
            expected = ListUiState.Success(satelliteList()) ,
            actual = viewModel.listUiState.value
        )
    }

    @Test
    fun query_whenQueryUpdated_thenShowUpdatedQuery() = runTest {
        viewModel.updateQuery("Starship")

        assertEquals(
            expected = "Starship",
            actual = viewModel.query
        )
    }

}

