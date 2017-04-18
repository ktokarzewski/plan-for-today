package pl.com.tokarzewski.api;

public interface DayFinalizerService {
    String EVERY_FIVE_MINUTES = "0 */5 * ? * *";
    String EVERY_TWENTY_MINUTES = "0 */20 * ? * *";
    String EVERY_DAY_AT_MIDNIGHT = "0 0 0 ? * *";

    void finalizeDay();
}
