<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
     <link rel="stylesheet" href="uploadPictureStyle.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Picture</title>
</head>
<body>
    <div class="main">
        <form action="uploadPicture" method="post" enctype="multipart/form-data">
            <input class="addfile" type="file" name="file" id="file"> <br><br>
            <input class ="upload" type="submit" value="Upload">
        </form>
    </div>
</body>
</html>