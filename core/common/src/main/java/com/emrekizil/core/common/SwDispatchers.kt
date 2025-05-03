package com.emrekizil.core.common

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class Dispatcher(val sWDispatcher:SwDispatchers)

enum class SwDispatchers{
    IO
}