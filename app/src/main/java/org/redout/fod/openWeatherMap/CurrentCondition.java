
package org.redout.fod.openWeatherMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
    "id",
    "dt",
    "name",
    "coord",
    "main",
    "wind",
    "weather",
    "clouds",
    "rain"
})
public class CurrentCondition {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("weather")
    private List<Weather> weather = new ArrayList<Weather>();
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("rain")
    private Rain rain;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The dt
     */
    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The coord
     */
    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    /**
     * 
     * @param coord
     *     The coord
     */
    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * 
     * @return
     *     The main
     */
    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The wind
     */
    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * @return
     *     The weather
     */
    @JsonProperty("weather")
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    @JsonProperty("weather")
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The rain
     */
    @JsonProperty("rain")
    public Rain getRain() {
        return rain;
    }

    /**
     * 
     * @param rain
     *     The rain
     */
    @JsonProperty("rain")
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
