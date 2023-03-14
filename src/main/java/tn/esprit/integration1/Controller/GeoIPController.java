package tn.esprit.integration1.Controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.integration1.Entities.GeoIP;
import tn.esprit.integration1.Interfaces.GeoIPLocationService;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class GeoIPController {

    private final GeoIPLocationService geoIPLocationService;

    public GeoIPController(GeoIPLocationService geoIPLocationService) {
        this.geoIPLocationService = geoIPLocationService;
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/geoIP/")
    public GeoIP getLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {
        //String ipAddress = request.getHeader("X-Forwarded-For");
        String ipAddress ="197.27.211.57";
        if (ipAddress == null) {
           // ipAddress = request.getRemoteAddr();
         //   ipAddress ="197.27.211.57";
           ipAddress = request.getRemoteAddr();
        }
        return geoIPLocationService.getIpLocation(ipAddress,request);
    }




}





