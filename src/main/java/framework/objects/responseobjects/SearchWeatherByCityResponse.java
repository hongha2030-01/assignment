package framework.objects.responseobjects;

import framework.objects.commons.CityWeather;

import java.util.List;

public class SearchWeatherByCityResponse {

    private String message;
    private String cod;
    private Long count;

    private List<CityWeather> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<CityWeather> getList() {
        return list;
    }

    public void setList(List<CityWeather> list) {
        this.list = list;
    }
}
