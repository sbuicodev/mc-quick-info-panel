package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.WeatherProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeatherTest {
    private static class MockWeatherProvider implements WeatherProvider {
        public static final String CLEAR = "clear";
        public static final String RAIN = "rain";
        public static final String THUNDER = "thunder";
        public static final String SNOW = "snow";
        public static final String THUNDER_SNOW = "thunder:snow";
        public static final String UNKNOWN = "unknown";

        private String weather = CLEAR;

        public void setWeather(String weather) {
            this.weather = weather;
        }

        @Override
        public String getWeather() {
            return weather;
        }
    }

    @Test
    void testWeather() {
        MockWeatherProvider weatherProvider = new MockWeatherProvider();
        Weather weather = new Weather(weatherProvider);

        weatherProvider.setWeather(MockWeatherProvider.CLEAR);
        Assertions.assertEquals(MockWeatherProvider.CLEAR, weather.getCurrent());

        weatherProvider.setWeather(MockWeatherProvider.RAIN);
        Assertions.assertEquals(MockWeatherProvider.RAIN, weather.getCurrent());

        weatherProvider.setWeather(MockWeatherProvider.THUNDER);
        Assertions.assertEquals(MockWeatherProvider.THUNDER, weather.getCurrent());

        weatherProvider.setWeather(MockWeatherProvider.SNOW);
        Assertions.assertEquals(MockWeatherProvider.SNOW, weather.getCurrent());

        weatherProvider.setWeather(MockWeatherProvider.THUNDER_SNOW);
        Assertions.assertEquals(MockWeatherProvider.THUNDER_SNOW, weather.getCurrent());

        weatherProvider.setWeather("whatever this is");
        Assertions.assertEquals(MockWeatherProvider.UNKNOWN, weather.getCurrent());

        weatherProvider.setWeather(null);
        Assertions.assertEquals(MockWeatherProvider.UNKNOWN, weather.getCurrent());
        
    }
}
