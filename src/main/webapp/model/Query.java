package main.webapp.model;

import com.sun.istack.internal.NotNull;
import main.webapp.exception.WLException;
import main.webapp.exception.WebLabException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Query implements Serializable {
    private static final long serialVersionUID = 8442671044445353433L;

    @NotNull
    private final Double x;

    @NotNull
    private final Double y;

    @NotNull
    private final Double r;

    private String result;

    private Long executionTime;

    private final String creationTime;

    public Query(@NotNull String x, @NotNull String y, @NotNull String r) {
        try {
            this.x = Double.parseDouble(x);
        } catch (NumberFormatException e){
            throw new WebLabException(WLException.INVALID_POINT_X_FORMAT);
        }
        try {
            this.y = Double.parseDouble(y);
        } catch (NumberFormatException e){
            throw new WebLabException(WLException.INVALID_POINT_Y_MESSAGE);
        }
        try {
            this.r = Double.parseDouble(r);
        } catch(NumberFormatException e){
            throw new WebLabException(WLException.INVALID_POINT_R_FORMAT);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");

        creationTime = LocalDateTime.now(ZoneId.of("UTC+3")).format(formatter);

        validate();
    }

    public String getX() {
        return String.format("%.2f", this.x);
    }

    public String getY() {
        return String.format("%.2f", this.y);
    }

    public String getR() {
        return String.format("%.2f", this.r);
    }

    public String getResult() {
        if (this.result == null) {
            this.executionTime = System.currentTimeMillis();
            this.result = isInsideArea() ? "Yes" : "No";
            this.executionTime = System.currentTimeMillis() - this.executionTime;
        }
        return this.result;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public String getCreationTime() {
        return creationTime;
    }

    private void validate() {
        if (this.x < -3 || this.x > 5) throw new WebLabException(WLException.INVALID_POINT_X_MESSAGE);
        if (this.y < -2 || this.y > 2) throw new WebLabException(WLException.INVALID_POINT_Y_MESSAGE);
        if (this.r < 1 || this.r > 5) throw new WebLabException((WLException.INVALID_RADIUS_MESSAGE));
    }

    private boolean isInsideArea() {
        return (isInsideCircle() || isInsideRectangle() || isInsideTriangle());
    }

    private boolean isInsideCircle() {
        return (this.x >= 0 && this.y >= 0 && this.x * this. x + this.y * this.y <= this.r * this.r / 4.0);
    }
    private boolean isInsideRectangle() {
        return (this.x >= -this.r && this.x <= 0 && this.y >= 0 && this.y <= this.r);
    }
    private boolean isInsideTriangle() {
        return (this.y <= 0 && this.x >= 0 &&  this.y >= this.x * 2 - this.r);
    }
}
