package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.domain.dto.StoreAddDTO;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.store.dto.StoreDetailDTO;
import ywphsm.ourneighbor.service.StoreService;

@Controller
@RequestMapping("/store")
@Slf4j
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/{storeId}")
    public String storeDetail(@PathVariable Long storeId, Model model) {
        Store store = storeService.findOne(storeId);

        StoreDetailDTO storeDetailDTO = new StoreDetailDTO(store);

        model.addAttribute("store", storeDetailDTO);
        return "store/detail";
    }

    @GetMapping("/addStore")
    public String addStore(Model model) {
        model.addAttribute("store", new StoreAddDTO());

        return "store/add_form";
    }

    @PostMapping("/addStore")
    public String addStore(@ModelAttribute StoreAddDTO storeAddDTO) {
        Store store = storeAddDTO.toEntity();
        storeService.saveStore(store);

        return "redirect:/map";
    }
}
