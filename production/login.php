<!DOCTYPE html>
<html>
<head>
  <title>Login Library</title>
</head>
<body>

<meta charset="UTF-8">
<!--===============================================================================================-->  
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

  <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">


  <link rel="stylesheet" type="text/css" href="css/util.css">
  <link rel="stylesheet" type="text/css" href="css/mainn.css">
<!--===============================================================================================-->
</head>
<body>
  
  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100">
        <div class="login100-form-title" style="background-image: url(images/bg-01.jpg);">
          <span class="login100-form-title-1">
            Login
          </span>
        </div>

        <form class="login100-form validate-form">
          

          <div class="wrap-input100 validate-input m-b-18" data-validate = "">
            <span class="label-input100">Email</span>
            <input class="input100" type="text" name="email" placeholder="Enter Email" id="email">
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input m-b-18" data-validate = "">
            <span class="label-input100">Password</span>
            <input class="input100" type="password" name="pass" placeholder="Enter password" id="password">
            <span class="focus-input100"></span>
          </div>

          <div class="container-login100-form-btn">
            <button  class="login100-form-btn" type="button" onclick="loginUser();" >
              Login
            </button>

            <button  class="login100-form-btn" style="margin-left:50px; background:#fff;">
              <a href="register.php">Register
            </button>
            
          </div>
        </form>
      </div>
    </div>
  </div>
  

  <script src="https://www.gstatic.com/firebasejs/7.9.1/firebase.js"></script>

  <script src="https://www.gstatic.com/firebasejs/7.9.1/firebase-auth.js"></script>


<script>
  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "AIzaSyBiUHy07aEruI_ZgBmg-A8ez6bBc-W1gtw",
    authDomain: "smart-library1.firebaseapp.com",
    databaseURL: "https://smart-library1.firebaseio.com",
    projectId: "smart-library1",
    storageBucket: "smart-library1.appspot.com",
    messagingSenderId: "215824460430",
    appId: "1:215824460430:web:6d9b13ab3c7629b377b7df",
    measurementId: "G-BC9C4EV24W"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
</script>


<!--===============================================================================================-->
  <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/bootstrap/js/popper.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
  <script src="vendor/daterangepicker/moment.min.js"></script>
  <script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
  <script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
  <script src="js/loginn.js"></script>

</body>
</html>
