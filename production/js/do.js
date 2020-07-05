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
 idll, nom_doc, nom_domain, qte, dateretour,prix
  ])
.draw();

});
 

function add() {
          const nom_doc=document.getElementById('nom_doc');
          const database=firebase.database();
          const rootRef=database.ref('stockage');

          var ud = firebase.database().ref().child('stockage').push().key;
   
              var daata = {
            id:ud,
            nomDomain:document.getElementById('nom_domain').value,
            dateEntree:document.getElementById('single_cal3').value,
            Qte:document.getElementById('qte').value,
            Prix:document.getElementById('id_prix').value,
            nomDoc:document.getElementById('nom_doc').value

             }

         const nomDoc=document.getElementById('nom_doc');
          const  nomDomain=document.getElementById('nom_domain');
          const  dateEntree=document.getElementById('single_cal3');
          const  Qte=document.getElementById('qte');
          const  Prix=document.getElementById('id_prix');
                      if((nomDoc=="")||(nomDomain=="")||(Qte=="")||(Prix=="")
                        ||(dateEntree=="")){
                        Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: "Verifier vos donnees",
                        });}
                      else{               
                       var updatess = {};
                      updatess['/stockage/' + ud] = daata;
                          firebase.database().ref().update(updatess);}
          /*rootRef.child(nom_doc.value).set({
            nomDoc:document.getElementById('nom_doc').value,
            nomDomain:document.getElementById('nom_domain').value,
            dateEntree:document.getElementById('single_cal3').value,
            Qte:document.getElementById('qte').value,
            Prix:document.getElementById('prix').value,



        });*/
        }

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
                                if(qte==""){
                                const newData ={ 
                                  Prix:prix
                                  }
                            database.ref(path).update(newData);

                                      reload_page();
                                }

                                if(prix==""){
                                const newData ={ 
                                  Qte:qte
                                  }

                                    database.ref(path).update(newData);

                                      reload_page();
                                }
                                if((qte!="")&&(prix!="")){
                                  const newData ={ Qte:qte,
                                  Prix:prix
                                  }
                                  database.ref(path).update(newData);

                                      reload_page();
                                                                  }

                                
                           });                                 
                                                          

}
 }); }  