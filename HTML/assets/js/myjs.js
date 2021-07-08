function uploadImg64(input){
    if (input.files && input.files[0]){
        let reader = new FileReader;
        reader.onload = function(e){
            $('#avatar').attr("src",e.target.result);
            $("#img64").attr("value",e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
}
