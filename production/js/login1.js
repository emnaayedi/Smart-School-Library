

function loginUser() {
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;

    //firebase have pre built login function
    //it takes two parameters first email and second is password

    firebase.auth().signInWithEmailAndPassword(email,password).then(function () {
        //this function works when login successfully

    window.location="index.php";

    }).catch(function (error) {
       //this will handle error
        var errorMessage=error.message;
Swal.fire({
  icon: 'error',
  title: 'Oops...',
  text: errorMessage,
});
    });

firebase.auth().onAuthStateChanged(function(user) {
    if (user) {

      var user = firebase.auth().currentUser;


      if(user != null){ 
        var io=user.uid;




      }

    } else {
      // No user is signed in.
      Window.reload();

    }
  });
}

