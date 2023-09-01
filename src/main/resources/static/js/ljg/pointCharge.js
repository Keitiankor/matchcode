$(function() {
    $("#charge_kakao").on("click",function() {
        alert("???");
        // getter
        var IMP = window.IMP;
        IMP.init("imp22284462");
        var money = $("input[name=\"cp_item\"]:checked").val();
        console.log(money);

        IMP.request_pay(
            {
                pg: "kakaopay",
                merchant_uid: "merchant_" + new Date().getTime(),

                name: "주문명 : 주문명 설정",
                amount: money,
                buyer_email: "iamport@siot.do",
                buyer_name: "구매자이름",
                buyer_tel: "010-1234-5678",
                buyer_addr: "인천광역시 부평구",
                buyer_postcode: "123-456"
            },
            function(rsp) {
                console.log(rsp);
                if (rsp.success) {
                    var msg = "결제가 완료되었습니다.";
                    msg += "고유ID : " + rsp.imp_uid;
                    msg += "상점 거래ID : " + rsp.merchant_uid;
                    msg += "결제 금액 : " + rsp.paid_amount;
                    msg += "카드 승인번호 : " + rsp.apply_num;

                    $.ajax({
                        method: "GET",
                        url: "/chargePoint", //충전 금액값을 보낼 url 설정
                        data: {
                            memberId: $("#memberId").val(),
                            point: money
                        },
                        success: function() {
                            alert("성공");
                        }
                    });
                } else {
                    var msg = "결제에 실패하였습니다.";
                    msg += "에러내용 : " + rsp.error_msg;
                }
                alert(msg);
                document.location.href = "/point"; //alert창 확인 후 이동할 url 설정
            }
        );
    });
});
