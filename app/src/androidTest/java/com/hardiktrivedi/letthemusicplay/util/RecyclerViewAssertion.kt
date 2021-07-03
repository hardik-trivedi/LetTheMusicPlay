package com.hardiktrivedi.letthemusicplay.util

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.EspressoException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert.assertEquals

fun hasViewWithIdAndTextAtPosition(
    position: Int = 0,
    viewId: Int,
    expectedValue: CharSequence
) = ViewAssertion { view, _ ->
    if (view is RecyclerView) {
        view.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(viewId)
            ?.let {
                assertEquals(expectedValue, it.text)
            }
            ?: throw RecyclerViewAssertionException("The view holder hasn't got a view with the specified ID, $viewId.")
    } else {
        throw RecyclerViewAssertionException("The view is not a RecyclerView.")
    }
}


fun hasViewWithIdAndContentDescriptionAtPosition(
    position: Int = 0,
    viewId: Int,
    expectedValue: CharSequence
) = ViewAssertion { view, _ ->
    if (view is RecyclerView) {
        view.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<View>(viewId)
            ?.let {
                assertEquals(expectedValue, it.contentDescription)
            }
            ?: throw RecyclerViewAssertionException("The view holder hasn't got a view with the specified ID, $viewId.")
    } else {
        throw RecyclerViewAssertionException("The view is not a RecyclerView.")
    }
}

class RecyclerViewAssertionException(message: String) : RuntimeException(message), EspressoException