package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ywphsm.ourneighbor.controller.form.ReviewForm;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    @GetMapping("/createReview")
    public String createReview(@ModelAttribute ReviewForm reviewForm) {
        return "review/createReview";
    }
}
