    let  prodCount = 0;
    let totalCount = 0;
    let totalPrice = 0;
    let totalDiscount = 0;
    let totalDelivery = 0;
    let totalPoint = 0;
    let totalTotal = 0;

    // 중복 사용되는 스크립트 묶어 사용

    //function totalCh


     $(function(){

        const checkbox = document.getElementById('all');

        checkbox.addEventListener('click', function(){

          if($('input[name=all]').is(':checked')){

            console.log('확인');

             $('input[id=chk]').prop('checked', true);

          }else{

          $('input[id=chk]').prop('checked', false);


          }

        });
    });