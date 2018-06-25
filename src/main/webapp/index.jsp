<html>
<body>
<h2>Hello World!</h2>

CDN上传文件  CDN
<form name="form3" action="/file/uploadFileCDN.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="springmvc上传文件" />
</form>

CDN导入excel文件  CDN
<form name="form4" action="/manage/transport/upload_excel.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="导入" />
</form>
</body>
</html>
