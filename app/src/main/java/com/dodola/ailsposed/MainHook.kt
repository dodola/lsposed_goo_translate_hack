package com.dodola.ailsposed

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodReplacement
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.google.android.apps.translate") return

        XposedBridge.log("Loaded Google Translate Hook for package: " + lpparam.packageName)

        try {
            // Hook oag.dp() and return true
            XposedHelpers.findAndHookMethod(
                "oag",
                lpparam.classLoader,
                "dp",
                XC_MethodReplacement.returnConstant(true)
            )
            XposedBridge.log("Successfully hooked oag.dp to return true")
        } catch (t: Throwable) {
            XposedBridge.log("Error hooking oag.dp: " + t.message)
            XposedBridge.log(t)
        }
    }
}
