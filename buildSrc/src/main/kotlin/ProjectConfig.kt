object ProjectConfig {

    const val applicationId = "com.yuriisurzhykov.kmmtranslator"

    object Android {
        const val minVersion = 24
        const val targetVersion = 33
        const val compileVersion = 33
        const val applicationId = "${ProjectConfig.applicationId}.android"
    }

    object IOS {
        const val targetVersion = "14.1"
        const val applicationId = "${ProjectConfig.applicationId}.ios"
    }
}