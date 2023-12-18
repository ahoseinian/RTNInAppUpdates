package com.rtnin_app_updates;

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class InAppUpdatesPackage : TurboReactPackage() {
  override fun getModule(name: String?, reactContext: ReactApplicationContext): NativeModule? =
  if (name == InAppUpdatesModule.NAME) {
    InAppUpdatesModule(reactContext)
  } else {
    null
  }

  override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
    mapOf(
      InAppUpdatesModule.NAME to ReactModuleInfo(
        InAppUpdatesModule.NAME,
        InAppUpdatesModule.NAME,
        false, // canOverrideExistingModule
        false, // needsEagerInit
        true, // hasConstants
        false, // isCxxModule
        true // isTurboModule
      )
    )
  }
}