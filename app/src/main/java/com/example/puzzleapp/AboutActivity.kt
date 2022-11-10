package com.example.puzzleapp

import android.app.Activity
import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import android.webkit.WebView

class AboutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        actionBar?.setDisplayShowCustomEnabled(true)

        val wb = WebView(this)

        val unencodedHtml =
            """
            <html>
                <body>
                   <p>This is the about page.</p>
                </body>
            </html>
            """.trimIndent();
        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
        wb.loadData(encodedHtml, "text/html", "base64")

//        wb.loadUrl("file://assets/html/About.html")
        setContentView(wb)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                run { finish() }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}