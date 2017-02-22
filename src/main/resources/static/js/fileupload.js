/**
 * Created by yang.tang on 2017/2/22.
 */
$(function () {
    var tips = $(".fileerrorTip");
    tips.addClass("error");
    $(".a-upload").on("change","input[type='file']",function(){
        var filePath=$(this).val();
        if(filePath.indexOf("xls")!=-1 || filePath.indexOf("xlsx")!=-1){
            tips.html("").hide();
            var arr=filePath.split('\\');
            var fileName=arr[arr.length-1];
            $(".showFileName").html(fileName);
            tips.removeClass("error");
        }else{
            $(".showFileName").html("");
            tips.html("您未上传文件，或者您上传文件类型有误！").show();
            return false;
        }
    });
    $("#uploadButton").on("click",function(){
        if(!$(".fileerrorTip").hasClass("error")) {
            $("#fileuploadfrom").submit();
        }
    })
})

