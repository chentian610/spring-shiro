Vue.config.debug = true;// 开启vue 调试功能
new Vue({
    el: '#app',
    data: {
        images: []
    },
    methods: {
        addPic: function (e){
            e.preventDefault();
            $('input[type=file]').trigger('click');
            return false;
        },
        onFileChange: function (e) {
            var files = e.target.files || e.dataTransfer.files;
            if (!files.length)return;
            this.createImage(files);

        },
        createImage: function (file) {
            if(typeof FileReader==='undefined'){
                alert('您的浏览器不支持图片上传，请升级您的浏览器');
                return false;
            }
            var image = new Image();
            var vm = this;
            var leng=file.length;
            for(var i=0;i<leng;i++){
                var reader = new FileReader();
                reader.readAsDataURL(file[i]);
                reader.onload =function(e){
                    vm.images.push(e.target.result);
                };
            }
        },
        delImage:function(index){
            this.images.shift(index);
        },
        removeImage: function(e) {
            this.images = [];
        },
        uploadImage: function() {
            console.log(this.images);
            // return false;
            var obj = {};
            obj.files=this.images;
            obj.ruleId = 1;
            $.ajax({
                type: 'post',
                url: "../buscheck/upload",
                data: obj,
                dataType: "json",
                success: function(data) {
                    if(data.status){
                        alert(data.msg);
                        return false;
                    }else{
                        alert(data.msg);
                        return false;
                    }
                }
            });
        }
    }
})