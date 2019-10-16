# datalorePlot JS library.

## Building

* build project with Gradle (`./gradlew build`)

* take library files in `js-package/build/dist` directory


## Publishing in Bintray CDN

* set `bintray_user` and `bintray_api_key` in `gradle.properties` with your Bintray credentials

* run `:js-package:bintrayUpload` gradle task (`./gradlew :js-package:bintrayUpload`) 