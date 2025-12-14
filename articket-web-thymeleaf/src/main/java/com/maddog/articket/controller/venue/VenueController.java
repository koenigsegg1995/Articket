package com.maddog.articket.controller.venue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/venue")
public class VenueController {
//    @Autowired
//    VenueService venueService;//暫時用不到

    // 前端場館介紹頁面的Mapping
    @GetMapping("/venueIntroduction")
    public String getVenueRentalFrontPage() {
    	return "/front-end/venue/venueIntroduction";
    }
    
}
