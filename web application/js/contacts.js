var contact=firebase.database().ref().child("etudiants");

   contact.on("child_added",snap => {
  var nom=snap.child("name").val();
  var email=snap.child("email").val();
  var fil=snap.child("fil").val();
  var tel=snap.child("tel").val();
  var nb_emp=snap.child("nb_emp").val();



   $("#datatable").DataTable().row.add([
  "<div class=\"right col-md-5 col-sm-5 text-center\">"
                 +"<img src=\"images/img.jpg\"  class=\"img-circle img-fluid\">"
                              
                           + "</div>", nom, email, fil, tel
  ])
.draw();

});

  var nb_etud=firebase.database().ref('biblio/').child("nb_etud");
      nb_etud.on('value', function(sna) {
              var etat_dispo = sna.val();  
              $("#nb_etud").append("<div>"+ etat_dispo+"</div>");
            }); 

   var nb_ch=firebase.database().ref('biblio/').child("nb_chaise");
      nb_ch.on('value', function(sna) {
              var etat_dispo = sna.val();  
              $("#nb_chaises").append("<div>"+ etat_dispo+"</div>");
            });

       var doc=firebase.database().ref('stockage/');
      doc.on('value', function(sna) {
              var etat_dispo = sna.numChildren(); 
              $("#document").append("<div>"+ etat_dispo+"</div>");
            });

       var liv_emp=firebase.database().ref('emprunte/');
      liv_emp.on('value', function(sna) {
              var etat_dispo = sna.numChildren();  
              $("#liv_emp").append("<div>"+ etat_dispo+"</div>");
            });

      var dateRetour=firebase.database().ref().child("emprunte");
      dateRetour.on("child_added",snap => {
            var date_retour=snap.child("date_retour").val();
            var etat=snap.child("etat").val();
            var d=new Date();
            var d1=d.getMonth()+1 +'/'+(d.getDate())+'/'+d.getFullYear();
              if ((d1<=date_retour) && (etat=="notReturned")) 
                $("#id_msg").append("<span>"+ "Livre n'est pas encore retournee"+"</span>");

            });

      var test=firebase.database().ref().child("emprunte");
  test.on("value", function(snapshot){
          var nb=0;
          var t=firebase.database().ref().child("emprunte");
          t.on("child_added",snap => {
             var id_emprunte=snap.child("emp_id").val();
             var date=snap.child("date_retour").val();
             var test=new Date(date);
             var j_emp=test.getDate(); var m_emp=test.getMonth()+1; var y_emp=test.getFullYear();
             var etat=snap.child("etat").val();
             var d=new Date();
            // var j=d.getDate(); var m=d.getMonth()+1; var y=d.getFullYear();
       var j=1;var m=6;var y=2020;
            if ((((j_emp<d)&&((m==m_emp)||(m_emp<m))&&(y==y_emp)))&&(etat=="notReturned")) {
                  nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if((j_emp==31)&&((m_emp==1)||(m_emp==3)||(m_emp==5)||(m_emp==7)||(m_emp==8)||(m_emp==10)||(m_emp==12))&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if((j_emp==30)&&((m_emp==4)||(m_emp==6)||(m_emp==9)||(m_emp==11))&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if(((j_emp==28)||(j_emp==29))&&(m_emp==2)&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
 }
    
});
      
             $("#number_msg").append("<span class=\"badge bg-green\">" + nb + "</span>");

});