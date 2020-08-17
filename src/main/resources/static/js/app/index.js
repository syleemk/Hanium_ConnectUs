var main = {
    init: function(){
        var _this =this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-update').on('click',function(){
            _this.update();
        });
        $('#btn-delete').on('click',function(){
            _this.delete();
        })
    },


};

main.init();