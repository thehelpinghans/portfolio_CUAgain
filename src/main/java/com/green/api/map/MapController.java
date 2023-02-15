package com.green.api.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {


    @GetMapping("/admin/mapinfo")
    public String map2() {
        return "admin/map/mapinfo";
    }
}
 