package framework.objects.commons;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties
public class Main {
    private Long temp;

    @JsonAlias(value = "feels_like")
    private Long feelsLike;

    public Long getTempMin() {
        return tempMin;
    }

    public void setTempMin(Long tempMin) {
        this.tempMin = tempMin;
    }

    @JsonAlias(value = "temp_min")
    private Long tempMin;

    @JsonAlias(value = "temp_max")
    private Long tempMax;

    private Long pressure;
    private Long humidity;

    public Long getTemp() {
        return temp;
    }

    public void setTemp(Long temp) {
        this.temp = temp;
    }

    public Long getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Long feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Long getTempMax() {
        return tempMax;
    }

    public void setTempMax(Long tempMax) {
        this.tempMax = tempMax;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }
}
