var main = {
    init : function () {
        var _this = this;
        $('#btn-fund-create').on('click',function () {
            _this.create();
        });

    },
    create : function () {
        var data = {
            name: $('#name').val(),
            image: $('#image').val(),
            goalPrice: $('#goalPrice').val(),
            address: $('#address').val(),
            due: $('#due').val()
        };

        $.ajax({
            type: 'POST',
            url: '/v1/fund/product',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('펀드가 등록되었습니다.');
            window.location.href = '/v1/fund/product';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();