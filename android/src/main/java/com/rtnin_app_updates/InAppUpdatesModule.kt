package com.rtnin_app_updates

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability


class InAppUpdatesModule(reactContext: ReactApplicationContext) :
    NativeInAppUpdatesSpec(reactContext) {
    private val appUpdateManager = AppUpdateManagerFactory.create(reactContext)

    private val task = appUpdateManager.appUpdateInfo

    override fun getName() = NAME


    override fun startFlexibleUpdate(promise: Promise) {
        if (appropriateUpdateExist(task.result)) {

            val listener = InstallStateUpdatedListener { state ->
                if (state.installStatus() == InstallStatus.DOWNLOADED) {
                   promise.resolve(true)

                }

            }
            appUpdateManager.registerListener(listener)

            currentActivity?.let {
                appUpdateManager.startUpdateFlowForResult(
                    task.result,
                    AppUpdateType.FLEXIBLE,
                    it,
                    42139
                )
            }
        }
    }

    override fun installFlexibleUpdate(promise: Promise) {
        if (task.result.installStatus() === InstallStatus.DOWNLOADED) {
           val updateTask = appUpdateManager.completeUpdate()
            updateTask.addOnFailureListener { err: Exception ->
                promise.reject(
                    "Exception",
                    err.toString()
                )
            }

            updateTask.addOnSuccessListener  {

                promise.resolve(true)
            }


        } else {
            promise.reject("Exception", "no download updates exists")
        }
    }

    override fun checkForUpdate(promise: Promise) {

        task.addOnFailureListener { err: Exception ->
            promise.reject(
                "Exception",
                err.toString()
            )
        }

        println("REQUEST UPDATE !!!!!!!!!!!!! 111")
        task.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                promise.resolve("update_downloaded")
            } else if (appropriateUpdateExist(appUpdateInfo)) {
                promise.resolve("update_exists")
            } else {
                promise.resolve("no_updates_available")
            }
        }


    }

    companion object {
        const val NAME = "RTNInAppUpdates"
    }
}

fun appropriateUpdateExist(appUpdateInfo: AppUpdateInfo): Boolean {
    return (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            && (appUpdateInfo.clientVersionStalenessDays() ?: Int.MAX_VALUE) >= 2
            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE))
}

