$(function(){
      var num = document.querySelector('input[name=num]');

    // 장바구니 담기
    var cart = document.getElementById('cart')
    cart.addEventListener('click', function(){

        let prodNo = document.getElementById('prodNo').innerText;           //상품번호
        console.log("prodNo : " + prodNo);
        let count = num.value;                                              // 수량
        console.log("count : " + count);
        let price = document.getElementById('price').innerText.split(',').join(''); //가격
        console.log("price : "+price);
        let discount = document.getElementById('discount').innerText;       // 할인율
        console.log("discount :" + discount);
        let point = Math.round(price * 0.01 * count);                       // 포인트
        console.log("point : " +point);
        let delivery = document.getElementById('delivery').innerText.replace('원', '');       // 배송비
        if(delivery == '무료배송'){delivery = 0;}
        console.log("delivery : "+delivery);
        let totalPrice = document.getElementById('totalPrice').innerText.split(',').join('');   //최종가격
        console.log("totalPrice : "+totalPrice);

        let jsonData = {
                "prodNo" : prodNo,
                "count" : count,
                "price" : price * count,
                "discount" : discount,
                "point" : point,
                "delivery" : delivery,
                "total" : totalPrice
        }

        let prod = JSON.stringify(jsonData);


    });

});