<!DOCTYPE html>
<html lang="en">
<head>
  <title>Edit Brand Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<header style="height: 30px;background-color: maroon;">
</header>

<div class="container">
  <h2>Edit Data Page!!!</h2>
   <img src="img/student.png" style="height: 120px;">
   <hr/>
   <span style="font-size: 18;color: blue;font-weight: bold;">${message}</span>
   <hr/>
   <div style="width: 60%">
  <form action="editBiz" method="POST">
    <input type="hidden" name="did" value="${pbiz.id}">
    <div class="form-group">
      <label>Name:</label>
      <input type="text" class="form-control"  placeholder="Enter Username" name="name" value="${pbiz.name}">
    </div>
    <div class="form-group">
      <label for="pwd">Brand:</label>
      <input type="text" class="form-control"  name="brand"  value="${pbiz.brand}">
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
  </form>
  </div>
</div>

</body>
</html>
