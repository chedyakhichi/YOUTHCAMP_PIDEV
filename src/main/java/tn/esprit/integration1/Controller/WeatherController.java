package tn.esprit.integration1.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.integration1.Services.WeatherService;

@RestController
@RequestMapping("weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/forecast/{city}")
    public ResponseEntity<?> weatherForecastAverage(@PathVariable("city") String city) {
        return weatherService.weatherForecastAverage(city);
    }
}
