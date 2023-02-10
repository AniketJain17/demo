package com.myapplication.common.database

import com.myapplication.common.CaPortalDatabase
import com.myapplication.common.ContextProvider
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

actual fun getDatabase(contextProvider: ContextProvider): CaPortalDatabase {
    val dbPath = File(System.getProperty("java.io.tmpdir"), DB_NAME)
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${dbPath.absolutePath}")
        .also { CaPortalDatabase.Schema.create(it) }
    return CaPortalDatabase(driver = driver)
}
