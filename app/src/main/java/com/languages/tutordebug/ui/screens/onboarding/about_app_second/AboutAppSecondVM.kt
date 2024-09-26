package com.languages.tutordebug.ui.screens.onboarding.about_app_second

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.android.play.core.review.ReviewManagerFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutAppSecondVM @Inject constructor(

) : ViewModel() {

    fun launchAppReviewFlow(
        activity: Activity, userRatedAppCallBack: () -> Unit,
    ) {
        val manager = ReviewManagerFactory.create(activity)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val flow = manager.launchReviewFlow(activity, task.result)
                flow.addOnCompleteListener { _ ->
                    userRatedAppCallBack.invoke()
                }
            }
        }
    }
}