package com.mingrn.common.redis;

import com.mingrn.common.redis.client.JedisGeoClient;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoUnit;

import java.util.Set;

@RestController
@RequestMapping
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
    }

    @GetMapping("/geoGet")
    public String geoGet() {
        GeoCoordinate geoCoordinate = JedisGeoClient.getInstance().geoPos("citys:location", "shanghai");
        return geoCoordinate.toString();
    }

    @GetMapping("/geoSet")
    public Long geoSet() {
        return JedisGeoClient.getInstance().geoAdd("citys:location", 116.4071700000, 39.9046900000, "beijing");
    }

    @GetMapping("/geoMembers")
    public Set<String> geoMembers() {
        return JedisGeoClient.getInstance().geoMembers("citys:location", 0, -1);
    }

    @GetMapping("/geoDist")
    public Double geoDist() {
        Double d = JedisGeoClient.getInstance().geoDist("citys:location","beijing","shanghai", GeoUnit.M);
        System.out.println(d);
        return d;
    }
}