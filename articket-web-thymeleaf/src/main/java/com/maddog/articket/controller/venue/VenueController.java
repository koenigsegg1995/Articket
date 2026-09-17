package com.maddog.articket.controller.venue;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 場館 Controller
 */
@Controller
@RequestMapping("/venue")
public class VenueController {

    /**
     * 前端場館介紹頁面
     *
     * @return venueIntroduction.html
     */
    @GetMapping("/venueIntroduction")
    public String getVenueRentalFrontPage() {
    	return "/front-end/venue/venueIntroduction";
    }
    
}
