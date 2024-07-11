package baksakcci.shoppingmall.order.mock;

import java.time.LocalDateTime;

public class ClockHolder {

    private final LocalDateTime currentTime;

    public ClockHolder() {
        currentTime = LocalDateTime.now();
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }
}
