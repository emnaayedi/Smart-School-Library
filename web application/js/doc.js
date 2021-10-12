

var rootRef=firebase.database() .ref().child("stockage");

   rootRef.on("child_added",snap => {
  var action=snap.child("action").val();
  var nom_doc=snap.child("nomDoc").val();
  var nom_domain=snap.child("nomDomain").val();
  var dateretour=snap.child("dateEntree").val();
  var qte=snap.child("Qte").val();
   var prix=snap.child("Prix").val();
  $("#table_body").append("<tr><th>"+ nom_doc+"</th><th>"+ nom_domain+"</th><th>"+action+
    "</th><th>"+qte+"</th><th>"+ dateretour+"</th><th>"+ prix+
    "</th>");

});
 

function add() {
          const nom_doc=document.getElementById('nom_doc');
          const database=firebase.database();
          const rootRef=database.ref('stockage');
          rootRef.child(nom_doc.value).set({
            nomDoc:document.getElementById('nom_doc').value,
            nomDomain:document.getElementById('nom_domain').value,
            dateEntree:document.getElementById('single_cal3').value,
            Qte:document.getElementById('qte').value,
            Prix:document.getElementById('prix').value,
        });
        }
