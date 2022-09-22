package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.domain.Category;
import ywphsm.ourneighbor.domain.dto.CategoryDTO;
import ywphsm.ourneighbor.domain.dto.StoreDTO;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.service.CategoryService;
import ywphsm.ourneighbor.service.StoreService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final CategoryService categoryService;

    @ModelAttribute("offDays")
    public Map<String, String> offDays() {
        Map<String, String> offDays = new LinkedHashMap<>();

        offDays.put("일", "일요일");
        offDays.put("월", "월요일");
        offDays.put("화", "화요일");
        offDays.put("수", "수요일");
        offDays.put("목", "목요일");
        offDays.put("금", "금요일");
        offDays.put("토", "토요일");

        return offDays;
    }

    @GetMapping("/{storeId}")
    public String storeDetail(@PathVariable Long storeId, Model model) {
        Store store = storeService.findOne(storeId);

        StoreDTO.Detail storeDetailDTO = new StoreDTO.Detail(store);

        log.info("store={}", storeDetailDTO.getMenuList());
        log.info("store={}", store.getMenuList());
        log.info("store.review={}", store.getReviewList());

        model.addAttribute("store", storeDetailDTO);
        return "store/detail";
    }

    @GetMapping("/add")
    public String addStore(Model model) {
        List<CategoryDTO> all = categoryService.findAll();

        log.info("all={}", all);

        model.addAttribute("all", all);
        model.addAttribute("store", new StoreDTO.Add());
        return "store/add_form";
    }

    @PostMapping("/add")
    public String addStore(@ModelAttribute StoreDTO.Add storeAddDTO, @RequestParam Long categoryId) {
        Category category = categoryService.findById(categoryId);
        log.info("category={}", category.getCategoryOfStoreList());

        storeService.saveStore(storeAddDTO, category);

        return "redirect:/map";
    }

}