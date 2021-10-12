 function search(){

    var text= document.getElementById("text").value;

    var contact=firebase.database().ref().child("etudiants");

   contact.on("child_added",snap => {
  var nom=snap.child("name").val();
  var email=snap.child("email").val();
  var fil=snap.child("fil").val();
  var tel=snap.child("tel").val();
  var nb_emp=snap.child("nb_emp").val();
      if(text==nom){
          console.log("hfbvdfhvbdf");

        $("#etudiant").append("<div class=\"col-md-4 col-sm-4  profile_details\">"+
    "<div class=\"well profile_view\"><div class=\"col-sm-12\"><h4 class=\"brief\">"
    +"<i>2018-2019</i></h4><div class=\"left col-md-6 col-sm-6\"><h2>"+nom+"</h2>"
                              +"<ul class=\"list-unstyled\">"
                              + " <li><i class=\"fa fa-building\"></i>"+fil+"</li>"
                              +  "<li><i class=\"fa fa-phone\"></i> "+email+"</li>"
                              +  "<li><i class=\"fa fa-phone\"></i> "+tel+"</li>"
                              +  "<li><i class=\"fa fa-phone\"></i> "+nb_emp+"</li>"
                               
                             + "</ul>"
                               

                            +"</div>"
                            +"<div class=\"right col-md-5 col-sm-5 text-center\">"
                 +"<img src=\"images/img.jpg\"  class=\"img-circle img-fluid\">"
                              
                           + "</div>"

                          +"</div></div></div>")
      }
});

  }