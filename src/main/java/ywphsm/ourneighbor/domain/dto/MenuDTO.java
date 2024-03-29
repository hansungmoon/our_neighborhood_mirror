package ywphsm.ourneighbor.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ywphsm.ourneighbor.domain.dto.hashtag.HashtagOfMenuDTO;
import ywphsm.ourneighbor.domain.menu.Menu;
import ywphsm.ourneighbor.domain.menu.MenuFeat;
import ywphsm.ourneighbor.domain.menu.MenuType;
import ywphsm.ourneighbor.domain.store.Store;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuDTO {

    @Data
    @NoArgsConstructor
    public static class Add {

        @NotBlank
        private String name;

        @NotNull
        private Integer price;

        private MenuType type;

        private MenuFeat feature;

        @NotNull
        private Long storeId;

        private MultipartFile file;

        private String hashtag;


        @Builder
        public Add(String name, Integer price,
                   MenuType type, MenuFeat feature,
                   Long storeId, MultipartFile file, String hashtag) {
            this.name = name;
            this.price = price;
            this.type = type;
            this.feature = feature;
            this.storeId = storeId;
            this.file = file;
            this.hashtag = hashtag;
        }

        public Menu toEntity(Store store) {
            return Menu.builder()
                    .name(name)
                    .price(price)
                    .type(type)
                    .feature(feature)
                    .store(store)
                    .hashtagOfMenuList(new ArrayList<>())
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Update {

        @NotNull
        private Long id;

        @NotBlank
        private String name;

        @NotNull
        private Integer price;

        @NotNull
        private Long storeId;

        private String storedFileName;

        private String uploadImgUrl;

        private MultipartFile file;

        private int discountPrice;

        private LocalDateTime discountStart;

        private LocalDateTime discountEnd;

        @NotBlank
        private MenuType type;

        private MenuFeat feature;

        private String hashtag;

        @Builder
        public Update(Long id, String name, Integer price, Long storeId,
                      MenuType type, MenuFeat feature, String hashtag,
                      String storedFileName, String uploadImgUrl, MultipartFile file,
                      int discountPrice, LocalDateTime discountStart, LocalDateTime discountEnd) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.storeId = storeId;
            this.type = type;
            this.feature = feature;
            this.hashtag = hashtag;
            this.storedFileName = storedFileName;
            this.uploadImgUrl = uploadImgUrl;
            this.file = file;
            this.discountPrice = discountPrice;
            this.discountStart = discountStart;
            this.discountEnd = discountEnd;
        }

        @Builder
        public Update(Menu menu) {
            this.id = menu.getId();
            this.name = menu.getName();
            this.price = menu.getPrice();
            this.storeId = menu.getStore().getId();
            this.type = menu.getType();
            this.feature = menu.getFeature();
            this.storedFileName = menu.getFile().getStoredFileName();
            this.uploadImgUrl = menu.getFile().getUploadImageUrl();
            this.discountPrice = menu.getDiscountPrice();
            this.discountStart = menu.getDiscountStart();
            this.discountEnd = menu.getDiscountEnd();
        }

        public Menu toEntity() {
            return Menu.builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .discountPrice(discountPrice)
                    .discountStart(discountStart)
                    .discountEnd(discountEnd)
                    .type(type)
                    .feature(feature)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Detail {

        @NotBlank
        private String name;

        @NotNull
        private Integer price;

        @NotNull
        private int discountPrice;

        @NotBlank
        private MenuType type;

        private MenuFeat feature;

        private String storedFileName;

        private String uploadImgUrl;

        private List<HashtagOfMenuDTO.Simple> hashtagOfMenuDTOList;


        @Builder
        public Detail(String name, Integer price, int discountPrice,
                      MenuType type, MenuFeat feature,
                      List<HashtagOfMenuDTO.Simple> hashtagOfMenuDTOList,
                      String storedFileName, String uploadImgUrl) {
            this.name = name;
            this.price = price;
            this.discountPrice = discountPrice;
            this.type = type;
            this.feature = feature;
            this.hashtagOfMenuDTOList = hashtagOfMenuDTOList;
            this.storedFileName = storedFileName;
            this.uploadImgUrl = uploadImgUrl;
        }

        public static Detail of(Menu entity) {
            return Detail.builder()
                    .name(entity.getName())
                    .price(entity.getPrice())
                    .discountPrice(entity.getDiscountPrice())
                    .type(entity.getType())
                    .feature(entity.getFeature())
                    .hashtagOfMenuDTOList(entity.getHashtagOfMenuList().stream()
                            .map(HashtagOfMenuDTO.Simple::new)
                            .collect(Collectors.toList()))
                    .storedFileName(entity.getFile().getStoredFileName())
                    .uploadImgUrl(entity.getFile().getUploadImageUrl())
                    .build();
        }
    }
}