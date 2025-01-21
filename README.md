# paketo-dynatrace-demo

Run with the following pack command:

```
pack build test-project \
--builder paketobuildpacks/builder-noble-java-tiny \
--run-image paketobuildpacks/run-noble-tiny \
--lifecycle-image buildpacksio/lifecycle \
--buildpack paketo-buildpacks/java-native-image \
--buildpack docker.io/paketobuildpacks/dynatrace \
--env BP_NATIVE_IMAGE=true \
--env BP_MAVEN_ACTIVE_PROFILES="native,dynatrace-native" \
--env BP_JVM_VERSION=21 \
--env BP_MAVEN_ADDITIONAL_BUILD_ARGUMENTS="-Dquarkus.native.sources-only=true -DenvironmentUrl=https://[an-environment].live.dynatrace.com -DapiToken=[your-token]" \
--env BP_NATIVE_IMAGE_BUILT_ARTIFACT=target/native-sources/*-runner.jar \
--env BP_NATIVE_IMAGE_BUILD_ARGUMENTS_FILE=target/native-sources/native-image.args \
--env BP_MAVEN_BUILT_MODULE=load-verification-webservice \
--env BP_MAVEN_BUILT_ARTIFACT="target .dynatrace"
--volume [path-to-parent-folder]/dynatrace/binding:/platform/bindings/Dynatrace
```

Substitute the `[an-environment]` with your environment for dynatrace and `[your-token]` with your api token. Replace `[path-to-parent-folder]` with the path to the home of this repo.

Open the bindings folder and update the api-token and environment-id files with the same values you have in `[an-environment]` and `[your-token]`.