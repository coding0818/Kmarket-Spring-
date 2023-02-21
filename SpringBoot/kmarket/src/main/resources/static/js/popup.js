$(function(){

    // 상호명 클릭 시 - 판매자 정보 팝업 출력
    $('.latest .info .company > a').click(function(e){
        e.preventDefault();
        $('#popSeller').addClass('on');
    });

    // 상호명 - 판매자 정보 팝업 - 문의하기 클릭 시 - 문의하기 팝업 출력
    $('.btnQuestion').click(function(e){
        e.preventDefault();
        $('.popup').removeClass('on');
        $('#popQuestion').addClass('on');
    });

    // 주문번호 클릭 시 - 주문 상세 팝업 출력
    $('.latest .info .orderNo > a').click(function(e){
        e.preventDefault();
        $('#popOrder').addClass('on');
    });

    // 수취확인 클릭 시 - confirm 팝업 출력
    $('.latest .confirm > .receive').click(function(e){
        e.preventDefault();
        $('#popReceive').addClass('on');
    });

    // 상품평 쓰기 클릭 시 - 상품평 작성하기 팝업 출력
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();
        $('#popReview').addClass('on');
    });

    // 팝업 내 닫기 버튼 클릭 시 - 팝업 닫기
    $('.btnClose').click(function(){
        $(this).closest('.popup').removeClass('on');
    });

    // 상품평 작성 - 평점
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

});