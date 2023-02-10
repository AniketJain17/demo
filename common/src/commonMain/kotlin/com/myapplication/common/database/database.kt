package com.myapplication.common.database

import com.myapplication.common.ContextProvider
import com.myapplication.common.CaPortalDatabase

expect fun getDatabase(contextProvider: com.myapplication.common.ContextProvider): CaPortalDatabase