/**
 * Created by yang.tang on 2017/2/22.
 */

function selectFile(fnUpload) {
    var filename = fnUpload.value;
    var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
    if (mime == ".xls" || mime == ".xlsx") {
        // alert(mime);
    } else {
        // alert(mime != ".xls");
        // alert(mime != ".xlsx");
        alert("请上传正确的excel文件(xls和xlsx文件)");
        fnUpload.outerHTML = fnUpload.outerHTML;
    }
}