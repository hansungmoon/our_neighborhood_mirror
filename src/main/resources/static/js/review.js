import validation from "./validation.js";
import mask from "./mask.js";

var main = {
    init: async function () {
        let _this = this;

        mask.loadingWithMask();

        const reviewSaveBtn = document.getElementById("review-save");
        const reviewDeleteBtnList = document.querySelectorAll(".review-delete");
        const reviewMoreBtn = document.getElementById("review-more");
        const storeImgBtn = document.getElementById("store-image-btn");
        const mainImg = document.getElementById("main-image");

        if (reviewSaveBtn !== null) {
            reviewSaveBtn.addEventListener("click", () => {
                _this.check()
            });
        }

        if (reviewDeleteBtnList !== null) {
            reviewDeleteBtnList.forEach((btn) => {
                btn.addEventListener("click", () => {
                    _this.delete(btn.id);
                })
            })
        }

        if (reviewMoreBtn !== null) {
            reviewMoreBtn.addEventListener("click", () => {
                _this.more()
            });
        }

        if (storeImgBtn !== null) {
            if (mainImg.getAttribute("src") === null || mainImg.getAttribute("src") === "") {
                storeImgBtn.addEventListener("click", () => {
                    _this.saveMainImage();
                })
            } else {
                storeImgBtn.addEventListener("click", () => {
                    _this.updateMainImage();
                })
            }
        }

        const zoomDiv = document.getElementById("modal-content");
        let scale = 1;

        function zoom(event) {
            event.preventDefault();

            scale += event.deltaY * -0.01;

            // Restrict scale
            scale = Math.min(Math.max(.125, scale), 4);

            // Apply scale transform
            zoomDiv.style.transform = `scale(${scale})`;
        }

        if (zoomDiv !== null) {
            zoomDiv.onwheel = zoom;
        }

        mask.closeMask();

        //좋아요 버튼
        let $likeBtn =$('.icon.heart');

        $likeBtn.click(function(){
            console.log("likeBtn.src=", document.getElementById('like-img').src);
            if (document.getElementById('like-img').src === 'https://cdn-icons-png.flaticon.com/512/803/803087.png') {
                $likeBtn.addClass('active');
            }
            $likeBtn.toggleClass('active');

            if($likeBtn.hasClass('active')){
                _this.likeUpdate(true)
                $(this).find('img').attr({
                    'src': 'https://cdn-icons-png.flaticon.com/512/803/803087.png',
                    alt:'찜하기 완료'
                });
            }else{
                _this.likeUpdate(false)
                $(this).find('i').removeClass('fas').addClass('far')
                $(this).find('img').attr({
                    'src': 'https://cdn-icons-png.flaticon.com/512/812/812327.png',
                    alt:"찜하기"
                })
            }
        })
    },

    check: function () {
        const rating1 = document.getElementById("rating1");
        const rating2 = document.getElementById("rating2");
        const rating3 = document.getElementById("rating3");
        const rating4 = document.getElementById("rating4");
        const rating5 = document.getElementById("rating5");
        const content = document.getElementById("content");
        const storeId = document.getElementById("storeId").value;
        const memberId = document.getElementById("memberId").value;

        const ratingValid = document.getElementById('review-rating-valid');
        const contentValid = document.getElementById('review-content-valid');

        rating1.classList.remove("valid-custom");
        content.classList.remove("valid-custom");

        validation.removeValidation(ratingValid);
        validation.removeValidation(contentValid);

        let ratingCheck = true;

        if (rating1.value === "" || rating2.value === "" || rating3.value === "" ||
            rating4.value === "" || rating5.value === '') {
            ratingCheck = false;
        }

        if (content.value !== "" && ratingCheck === true
        && memberId !== "" && storeId !== "") {
            this.save();
        }

        if (content.value === "") {
            content.classList.add("valid-custom");
            validation.addValidation(contentValid, "리뷰 내용을 작성해주세요.");
        }

        if (ratingCheck === false) {
            validation.addValidation(ratingValid, "평점을 선택해주세요.");
        }

    },

    createDefaultImg: function (formData) {
        let file = formData.get("file");

        if (file.name === "") {
            formData.delete("file");
        }

        let defaultFile = new File(["foo"], "default.png", {
            type: "image/png"
        })

        formData.append("file", defaultFile);
    },

    save: function () {
        mask.loadingWithMask();

        const reviewForm = document.getElementById("review-add-form");
        const storeId = document.getElementById("storeId").value;

        let formData = new FormData(reviewForm);

        this.createDefaultImg(formData);

        axios({
            headers: {
                "Content-Type": "multipart/form-data",
                "Access-Control-Allow_Origin": "*"
            },
            method: "post",
            url: "/user/review",
            data: formData
        }).then((resp) => {
            alert("리뷰가 등록됐습니다.");
            window.location.href = "/store/" + storeId;
            mask.closeMask();
        }).catch((error) => {
            alert("리뷰를 등록할 수 없습니다.");
            console.log(error)
            mask.closeMask();
        });
    },

    delete: function (btnId) {
        const reviewId = btnId.substring(17);
        const storeId = document.getElementById("storeId").value;

        axios({
            method: "delete",
            url: "/review/delete/" + storeId,
            params: {
                reviewId: reviewId
            }
        }).then((resp) => {
            alert("리뷰 삭제가 완료됐습니다.");
            window.location.reload();
        }).catch((error) => {
            console.log(error);
        })
    },

    more: function () {
        let page = $("#reviewBody tr").length / 5 + 1;  //마지막 리스트 번호를 알아내기 위해서 tr태그의 length를 구함.
        let addListHtml = "";
        console.log("page", page);

        const storeId = document.getElementById("storeId").value;

        let loginMember = null;
        let memberRoleList = document.querySelectorAll(".member-role");
        loginMember = memberRoleList.item(0).value

        console.log("loginMember", loginMember);

        axios({
            method: "get",
            url: "/review/more",
            params: {
                page: page,
                storeId: storeId
            }
        }).then((resp) => {
            let data = resp.data

            if (data.last) {
                $("#review-more").remove();
            }
            for (let contentElement of data.content) {
                addListHtml += "<tr>";
                addListHtml += "<td>" + contentElement.reviewId + "</td>";
                switch (contentElement.rating) {
                    case 1:
                        addListHtml += "<td>★☆☆☆☆</td>";
                        break;
                    case 2:
                        addListHtml += "<td>★★☆☆☆</td>";
                        break;
                    case 3:
                        addListHtml += "<td>★★★☆☆</td>";
                        break;
                    case 4:
                        addListHtml += "<td>★★★★☆</td>";
                        break;
                    case 5:
                        addListHtml += "<td>★★★★★</td>";
                        break;
                }
                addListHtml += "<td>" + contentElement.content + "</td>";
                addListHtml += "<td>" + contentElement.username + "</td>";
                addListHtml += "<td>" + contentElement.createDate.substring(0, 10) + "</td>";
                addListHtml += '<td><img src="/menu/' + contentElement.storedFileName + '" width="180" height="180" alt="리뷰 사진"></td>';
                if (loginMember !== null) {
                    if (loginMember === 'ADMIN') {
                        addListHtml += '<td><button id="review-delete-btn' + contentElement.reviewId + '" type="button" class="btn btn-dark mt-4 review-delete"> 삭제 </button></td>';
                    }
                }
                addListHtml += "</tr>";
            }

            $("#reviewBody").append(addListHtml);

        }).catch((e) => {
            console.error(e);
        });
    },

    likeUpdate: function (like) {
        const storeId = document.getElementById("storeId").value;
        const memberId = document.getElementById("memberId").value;

        axios({
            method: "put",
            url: "/user/like",
            params: {
                likeStatus: like,
                memberId: memberId,
                storeId: storeId
            }
        }).then((resp) => {
            let check = resp.data;
                alert(check);
        }).catch((error) => {
            console.log(error);
        });
    },

    saveMainImage: function () {
        const storeId = document.getElementById("storeId");
        const form = document.getElementById("main-image-form");
        const formData = new FormData(form);

        axios({
            headers: {
                "Content-Type": "multipart/form-data",
                "Access-Control-Allow_Origin": "*"
            },
            method: "post",
            url: "/seller/store/edit-image/" + storeId.value,
            data: formData
        }).then((resp) => {
            alert("메인 이미지가 등록됐습니다.");
            window.location.reload();
        }).catch((error) => {
            console.log(error)
        });
    },

    updateMainImage: function () {
        const storeId = document.getElementById("storeId");
        const form = document.getElementById("main-image-form");
        const formData = new FormData(form);

        axios({
            headers: {
                "Content-Type": "multipart/form-data",
                "Access-Control-Allow_Origin": "*"
            },
            method: "put",
            url: "/seller/store/edit-image/" + storeId.value,
            data: formData
        }).then((resp) => {
            alert("메인 이미지가 수정됐습니다.");
            window.location.reload();
        }).catch((error) => {
            console.log(error)
        });
    },
};

main.init();