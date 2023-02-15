$(function(){
      var num = document.querySelector('input[name=num]');

    // 장바구니 담기
    var cart = document.getElementById('cart')
    cart.addEventListener('click', function(){

        let prodNo = document.getElementById('prodNo').innerText;
        console.log("prodNo : " + prodNo);
        let count = num.value;
        console.log("count : " + count);
        let price = document.getElementById('price').innerText.split(',').join('');
        console.log("price : "+price);
        let discount = document.getElementById('discount').innerText;
        console.log("discount :" + discount);
        let point = Math.round(price * 0.01 * count);
        console.log("point : " +point);
        let delivery = document.getElementById('delivery').innerText;
        if(delivery == '무료배송'){delivery = 0;}
        console.log("delivery : "+delivery);
        let totalPrice = document.getElementById('totalPrice').innerText.split(',').join('');
        console.log("totalPrice : "+totalPrice);
    });

});