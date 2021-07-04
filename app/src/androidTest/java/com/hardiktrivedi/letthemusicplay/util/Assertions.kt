package com.hardiktrivedi.letthemusicplay.util

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers

fun onRecyclerView(recyclerViewId: Int, assertion: () -> ViewAssertion) {
    onView(ViewMatchers.withId(recyclerViewId))
        .check(assertion())
}