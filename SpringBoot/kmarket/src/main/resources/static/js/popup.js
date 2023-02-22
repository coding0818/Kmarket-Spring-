$(document).ready(function(){

    // 자바스크립트 내장함수 금액, 날짜 현지화 https://blog.munilive.com/posts/javascript-localization-with-toLocaleString.html


    // 상호명 클릭 시 - 판매자 정보 팝업 출력
    $('.latest .info .company > a').click(function(e){
        e.preventDefault();

        let company = $(this).text();

        console.log('company: ' + company);

        let jsonData = {"company": company}

        $.ajax({
            url:"/kmarket/my/company",
            type:"POST",
            data: jsonData,
            dataType: 'JSON',
            success: (data)=>{
                console.log(data);
                console.log(data.company.tel);
                $('#popSeller .level').text(data.company.level);
                $('#popSeller .company').text(company);
                $('#popSeller .ceo').text(data.company.ceo);
                $('#popSeller .tel').text(data.company.tel);
                $('#popSeller .fax').text(data.company.fax);
                $('#popSeller .email').text(data.company.email);
                $('#popSeller .bizNum').text(data.company.bizRegNum);
                $('#popSeller .address').text("["+data.company.zip.substring(0,3)+"**] "+data.company.addr1 + data.company.addr2);
            }
        });

         $('#popSeller').addClass('on');

    });

    // 상호명 - 판매자 정보 팝업 - 문의하기 클릭 시 - 문의하기 팝업 출력
    $('.btnQuestion').click(function(e){
        e.preventDefault();
        $('.popup').removeClass('on');
        $('#popQuestion').addClass('on');
    });
    $('.btnQuestion .btnClose').click(function(e){
        e.preventDefault();
        $('#popQuestion').removeClass('on');
    });


    // 주문번호 클릭 시 - 주문 상세 팝업 출력
    $('.latest .info .orderNo > a').click(function(e){
        e.preventDefault();

        let ordNo = $(this).text();

        console.log('ordNo: '+ordNo);

        let jsonData = {"ordNo":ordNo}

        $.ajax({
            url: "/kmarket/my/orderDetails",
            type: "POST",
            data: jsonData,
            dataType: "json",
            success: (data) => {
                console.log(data);
                console.log(ordNo);
                console.log(data.ordNo.ordCount);
                $('#popOrder .date').text(data.ordNo.ordDate.substring(0,10));
                $('#popOrder .ordNo').text(ordNo);
                let imgPath = "@{/thumb/{cate1}/{cate2}/{thumb1}(cate1=${order.cate1}, cate2=${order.cate2}, thumb1=${order.thumb1})}"
                $('#popOrder .order img').attr('th:src', imgPath);
                $('#popOrder .company').text(data.ordNo.company);
                $('#popOrder .prodName').text(data.ordNo.prodName);
                $('#popOrder .prodCount').text(data.ordNo.ordCount);
                $('#popOrder .prodPrice').text((data.ordNo.price).toLocaleString());
                $('#popOrder .price > span:nth-of-type(2)').text((data.ordNo.ordPrice).toLocaleString()+"원");
                $('#popOrder .discount > span:nth-of-type(2)').text("-"+(data.ordNo.ordDiscount).toLocaleString()+"원");
                $('#popOrder .total > span:nth-of-type(2)').text((data.ordNo.ordTotPrice).toLocaleString()+"원");
                $('#popOrder .status').text(data.ordNo.ordState);
                $('#popOrder .name').text(data.ordNo.recipName);
                $('#popOrder .hp').text(data.ordNo.recipHp);
                $('#popOrder .address').text("["+data.ordNo.recipZip.substring(0,3)+"**]"+data.ordNo.recipAddr1+data.ordNo.recipAddr2);
            }
        });

        $('#popOrder').addClass('on');
    });

    // 수취확인 클릭 시 - confirm 팝업 출력
    $('.latest .confirm > .receive').click(function(e){
        e.preventDefault();

        if($(this).attr('data-no') != 3){
            alert('배송 완료 후 구매확정이 가능합니다.');
            return false;
        };
        $('#popReceive').addClass('on');


    });

    // 상품평 쓰기 클릭 시 - 상품평 작성하기 팝업 출력
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();

        let prodName = $('#prodName').text();

        console.log('prodName: '+prodName);

        $('#productName').text(prodName);

        $('#popReview').addClass('on');
    });

    // 팝업 내 닫기 버튼 클릭 시 - 팝업 닫기
    $('.btnClose').click(function(){
        $(this).closest('.popup').removeClass('on');
    });
    // 팝업 내 취소 버튼 클릭 시 - 팝업 닫기
    $('.btnCancel').click(function(){
       $(this).closest('.popup').removeClass('on');
   });

    // 상품평 작성 - 평점
    let rating = 0;
    $(".my-rating").starRating({
        starSize: 20,
        useFullStars: true,
        strokeWidth: 0,
        useGradient: false,
        minRating: 1,
        ratedColors: ['#ffa400', '#ffa400', '#ffa400', '#ffa400', '#ffa400'],
        callback: function(currentRating, $el){
            alert('rated ' + currentRating);
            console.log('DOM element ', $el);
        }
    });

    // 상품평 작성 완료
    $('#popReview .btnPositive').click(function(e){
        e.preventDefault();

        let prodNo = $('.latest .confirm > .review').attr('data-no');
    });
});