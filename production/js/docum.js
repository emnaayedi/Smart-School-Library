

var rootRef=firebase.database() .ref().child("stockage");

   rootRef.on("child_added",snapp => {
  var idll=snapp.child("id").val();
  console.log(idll);
  var action=snapp.child("action").val();
  var nom_doc=snapp.child("nomDoc").val();
  var nom_domain=snapp.child("nomDomain").val();
  var dateretour=snapp.child("dateEntree").val();
  var qte=snapp.child("Qte").val();
   var prix=snapp.child("Prix").val();


   $("#datatable-buttons").DataTable().row.add([
  nom_doc, nom_domain, action, qte, dateretour,prix,idll
  ])
.draw();

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

/*
function update() {

  var id_livre= document.getElementById('idl').value;
  var qte= document.getElementById('qt').value;
  var prix= document.getElementById('prix').value;
  database=firebase.database();
  var stockage = firebase.database().ref().child('stockage');

  var l=false; 
  stockage.once('value', function(snapshoot) {
        var idliv;
        snapshoot.forEach(function(childSnapshoot) {
            var idkey = childSnapshoot.key;
              idliv=idkey;
              if(idliv==id_livre) {
                 l=true;          }
                                         });     
     if(l==false){
      Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "id-Livre est incorrect",
                        });}
       
    else {    
       stockage.orderByChild('id').equalTo(id_livre).on('child_added', function(snap) {
          

                              path='stockage/'+ id_livre;
                                
                                const newData ={ Qte:qte,
                                  Prix:prix
                                  }

                                database.ref(path).update(newData);

                                      reload_page();
                           });                                 
                                                          

}
 }); }