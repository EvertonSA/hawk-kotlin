import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.stackdriver.StackdriverConfig;
import io.micrometer.stackdriver.StackdriverMeterRegistry;

// Run: git update-index --assume-unchanged **/SampleConfig.java
// If you legitimately need to change this file, you can undo this with: git update-index --no-assume-unchanged **/SampleConfig.java
public class MetricsConfig {
    public static MeterRegistry metricsSystem() {
        // Pick a monitoring system here to use in your samples.
        return MetricsRegistries.stackdriver();
    }
}

public class MetricsRegistries {
    /**
     * @param serviceAccountJson The fully qualified path on the local file system to a service account's JSON.
     * @param projectId          The Google Cloud project id found on the dropdown at the top of the Google Cloud console.
     * @see <a href="https://cloud.google.com/monitoring/docs/reference/libraries#setting_up_authentication">Google Cloud authentication</a>
     * @return A Stackdriver registry.
     */
    public static StackdriverMeterRegistry stackdriver(String serviceAccountJson, String projectId) {
        try (InputStream credentials = new FileInputStream(new File(serviceAccountJson))) {
            return StackdriverMeterRegistry
                    .builder(new StackdriverConfig() {
                        @Override
                        public String projectId() {
                            return projectId;
                        }

                        @Override
                        public String get(String key) {
                            return null;
                        }

                        @Override
                        public Duration step() {
                            return Duration.ofSeconds(10);
                        }
                    })
                    .metricServiceSettings(() -> MetricServiceSettings.newBuilder()
                            .setCredentialsProvider(FixedCredentialsProvider.create(ServiceAccountCredentials.fromStream(credentials)))
                            .build()
                    )
                    .build();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}