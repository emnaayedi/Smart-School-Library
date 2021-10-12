<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="images/favicon.ico" type="image/ico" />
      <script src='https://kit.fontawesome.com/a076d05399.js'></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.4/css/bulma.min.css">
        <!-- TODO: Se passer de FontAwesome. -->
    <title>Smart Library </title>
    <!-- Bootstrap -->
    <link href="../vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../vendors/iCheck/skins/flat/green.css" rel="stylesheet">
     <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
    <!-- bootstrap-datetimepicker -->
    <link href="../vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
    <!-- bootstrap-datetimepicker -->
    <link href="../vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
       <!-- Datatables -->
    
    <link href="../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
    <!-- Ion.RangeSlider -->
    <link href="../vendors/normalize-css/normalize.css" rel="stylesheet">
    <link href="../vendors/ion.rangeSlider/css/ion.rangeSlider.css" rel="stylesheet">
    <link href="../vendors/ion.rangeSlider/css/ion.rangeSlider.skinFlat.css" rel="stylesheet">
      <!-- Switchery -->
    <link href="../vendors/switchery/dist/switchery.min.css" rel="stylesheet">
    <!-- Bootstrap Colorpicker -->
    <link href="../vendors/mjolnic-bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="../vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="../vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../build/css/custom.min.css" rel="stylesheet">
               <link rel="stylesheet" type="text/css" href="css/nav.css">

<style type="text/css">
  html {
  scroll-behavior: smooth;
}

</style>

  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col menu_fixed mCustomScrollbar _mCS_1 mCS-autoHide" style="overflow: visible;">
          <div class="left_col">
            <div class="navbar nav_title" style="border: 0;">
              <a href=" php" class="site_title"><i class="fa fa-paw"></i> <span>Smart Libray </span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/logo1.jpg"  class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <br>
                <span><h4>Welcome</h4></span>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <ul class="nav side-menu">
                  <li ><a href="index.php" ><i class="fa fa-home"></i> Home </a>
                    
                  </li>
                  <li><a><i class="fa fa-edit"></i> Bibliotheque <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a><strong>Salle de Lecture</strong> <i class="fa fa-plus"></i></a>
                      <ul id="fct" class="nav child_menu">
                      <li><a href="#etat">Etat</a></li>
                      <li><a href="#calendrier">Regles</a></li>

                    </ul>
                  </li>
                      <li><a><strong>Librairie</strong> <i class="fa fa-plus"></i></a>
                      <ul id="fct" class="nav child_menu">
                      <li><a href="document.php">Documents</a></li>
                      <li><a href="livre_emprunte.php">Empruntes</a></li>

                 
                    </ul>
                  </li>
                      
                    </ul>
                  </li>
                   <li ><a href="reclamation.php" ><i class="fa big-icon fa-envelope icon-wrap"></i> Reclamation </a>
                    
                  </li>
            
                  <!-- <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="form.html">General Form</a></li>
                      <li><a href="form_advanced.html">Advanced Components</a></li>
                      <li><a href="form_validation.html">Form Validation</a></li>
                      <li><a href="form_wizards.html">Form Wizard</a></li>
                      <li><a href="form_upload.html">Form Upload</a></li>
                      <li><a href="form_buttons.html">Form Buttons</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="general_elements.html">General Elements</a></li>
                      <li><a href="media_gallery.html">Media Gallery</a></li>
                      <li><a href="typography.html">Typography</a></li>
                      <li><a href="icons.html">Icons</a></li>
                      <li><a href="glyphicons.html">Glyphicons</a></li>
                      <li><a href="widgets.html">Widgets</a></li>
                      <li><a href="invoice.html">Invoice</a></li>
                      <li><a href="inbox.html">Inbox</a></li>
                      <li><a href="calendar.html">Calendar</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">Tables</a></li>
                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>

                   <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="projects.html">Projects</a></li>
                      <li><a href="project_detail.html">Project Detail</a></li>
                      <li><a href="contacts.html">Contacts</a></li>
                      <li><a href="profile.html">Profile</a></li>
                    </ul>
                  </li>

                  <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="login.php">Login Page</a></li>
                      <li><a href="pricing_tables.html">Pricing Tables</a></li>
                    </ul>
                  </li> -->
                </ul>
              </div>
                    </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons >
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
          <a data-toggle="tooltip" data-placement="top" title="Logout" >
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            < /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">

              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
              <nav class="nav navbar-nav">
              <ul class=" navbar-right">
                <li class="nav-item dropdown open" style="padding-left: 15px;">
                  <a href="javascript:;" class="user-profile dropdown-toggle" aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                    <img src="images/logo1.jpg" alt="">Log Out
                  </a>
                  <div class="dropdown-menu dropdown-usermenu pull-right" aria-labelledby="navbarDropdown">
                 <a class="dropdown-item"  onclick="signout()"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                  </div>
                </li>

                <li role="presentation" class="nav-item dropdown open">
                  <a href="javascript:;" class="dropdown-toggle info-number" id="navbarDropdown1" data-toggle="dropdown" aria-expanded="false">
      <i class="fa fa-bell-o icon-wrap"></i>
                    <span  id="number_msg" > </span>
                  </a>
                  <ul class="dropdown-menu list-unstyled msg_list" role="menu" aria-labelledby="navbarDropdown1" id="ida">   
                  
                </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <!-- top tiles -->
                <div class="row" style="display: inline-block;" >
          <div class="tile_count"style="width: 1200px;">
            <div class="col-md-3 col-sm-5  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fa fa-user" aria-hidden="true" style="margin-right: 5px;"></i>Nombre d'etudiants </span>
              <div class="count" id="nb_etud"></div>
                <button type="button" class="btn btn-success btn-xs"><a href="contacts.php" style="color: white;">modifier</a><span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            </div>
            
            
           
            <div class="col-md-3 col-sm-3  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fas fa-chair"  style="margin-right: 3px;"></i> Nombre de chaises</span>
              <div class="count" id="nb_chaises"></div>
              <button type="button" class="btn btn-info">modifier<span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
              
            </div>
             <div class="col-md-3 col-sm-3  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fa fa-book"style="margin-right: 5px;" ></i>Documents </span>
              <div class="count" id="document"></div>
                <button type="button" class="btn btn-success btn-xs"><a href="document.php" style="color: white;">plus d'infos</a><span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            </div>

            <div class="col-md-3 col-sm-3  tile_stats_count" style="padding-left: 50px padding-right: 50px;">
              <span class="count_top"><i class="fa fa-book"style="margin-right: 5px;"></i>Livre Emprunte </span>
              <div class="count" id="liv_emp"></div>
                <button type="button" class="btn btn-info"><a href="livre_emprunte.php" style="color: white;">Ajouter</a><span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            </div>
             </div>
              <div class="col-md-12" id="calendrier">
 <div class="x_panel" style="">

    <div class="x_title">

        <h2>  <span class="glyphicon glyphicon-time" aria-hidden="true"style="padding-right: 10px;"></span>Horaires</h2>
        <ul class="nav navbar-right panel_toolbox">
           <div class="row">
                                           <nav class="level" style="padding-right: 400px; ">
                <div class="level-item">
                    <div class="field has-addons">
                        <p class="control">
                            <a class="button">
                                <span class="icon"><i class="fas fa-chevron-left"></i></span>
                            </a>
                        </p>
                        <p class="control">
                            <a class="button">
                                <?php echo date('l jS \of F Y ');  ?>
                            </a>
                        </p>
                        <p class="control">
                            <a class="button">
                                <span class="icon"><i class="fas fa-chevron-right"></i></span>
                            </a>
                        </p>
                    </div>

                </div>
            </nav>
                
                </div>
          <button type="button" id="add_button" data-toggle="modal" data-target="#userModal" class="btn btn-info ">modifier<span class="glyphicon glyphicon-pencil" aria-hidden="true"style="padding-left: 10px;"></span></button>
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
          
          <li><a class="close-link" style="margin-left: 15px">
              <i class="fa fa-times" ></i></a>           
                     </li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
  
        <div class="container">
            <div class="row">
<table  class="table table-striped table-bordered dataTable no-footer dtr-inline"  role="grid" aria-describedby="datatable-buttons_info">
                                       
    <div id="userModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" role="document">
       <form method="post" id="user_form" enctype="multipart/form-data">
       <div class="modal-content">
      <div class="modal-header">
       <h4 class="modal-title" id="myModalLabel" >Modifier Horaires</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
       <label>Periode</label><div id="reportrange" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                          <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                          <span>February 22, 2020 - March 22, 2020</span> <b class="caret"></b>
                        </div>
                        <label>Jour</label>
    
       <select type="dayselect" name="jour" id="jour" class="form-control"  >
       <option>Lundi</option>
       <option>Mardi</option>
        <option>mercredi</option>
        <option>Jeudi</option>
        <option>Vendredi</option>
        <option>Samedi</option>
        <option>Dimanche</option>
       </select>
       <label>Debut AM</label>
       <input type="time" name="debut" id="debut" class="form-control"  />
        <br />
        <label>Fin PM</label>
        <input type="time" name="fin" id="fin" class="form-control"  />
        <br />
        </div>
      <div class="modal-footer">
       <input type="hidden" name="jour" id="jour" />
   
      

        <button type="button"  data-dismiss="modal" class="btn btn-success" onclick="addh()">Add</button>
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       
  <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
      </div>
       </div></form>
     </div>
      </div>
                            <thead>
                        <tr role="row"><th aria-controls="datatable-buttons" rowspan="1"  style="width: 80px;" >Jour</th><th  style="width: 80px;">debut AM</th><th style="width: 80px;" >fin PM</th></tr>
                      </thead>
                      <tbody id="table_body">
                     </tbody>
                    </table></div></div></div></div>
                    
           <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

       <script src="https://www.gstatic.com/firebasejs/7.9.1/firebase.js"></script>

      <script src="https://www.gstatic.com/firebasejs/7.9.1/firebase-database.js"></script>

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

        var rootRef=firebase.database().ref().child("horaires");

   rootRef.orderByChild("id").on("child_added",snap => {
  var jour=snap.child("jour").val();
  var debut=snap.child("debut").val();
  var fin=snap.child("fin").val();

  $("#table_body").append("<tr><th>"+ jour+"</th><th>"+debut+"</th><th>"+fin+"</th></tr>");

});
 

function addh() {
          const jour=document.getElementById('jour');
          const database=firebase.database();
          const rootRef=database.ref('horaires');
          rootRef.child(jour.value).set({
            jour:document.getElementById('jour').value,
            debut:document.getElementById('debut').value,
           fin:document.getElementById('fin').value
        });
        }
        
        function reload_page(){
   window.location.reload();
  }
 var nb_etud=firebase.database().ref('biblio/').child("nb_etud");
      nb_etud.on('value', function(sna) {
              var etat_dispo = sna.val();  
              $("#nb_etud").append("<div>"+ etat_dispo+"</div>");
            }); 
   var nb_ch=firebase.database().ref('biblio/').child("nb_chaise");
      nb_ch.on('value', function(sna) {
              var etat_dispo = sna.val();  
              $("#nb_chaises").append("<div>"+ etat_dispo+"</div>");
                 var knob=firebase.database().ref('biblio/').child("nb_etud_existe");
    knob.on('value', function(sna) {
         var etat_dispo1 = sna.val();  
         $('.knob').trigger('configure', {
                  'max': etat_dispo
              });
      $("#nbe").val(etat_dispo1).trigger('change');
      $('#valeur').val(etat_dispo-etat_dispo1).trigger('change');
    });
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
      var temp=firebase.database().ref('biblio/').child("temp");
      temp.on('value', function(sna) {
              var etat_dispo = sna.val();  
      $("#temp").append("<h3>"+ etat_dispo+" °"+"</h3>");
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
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"image\"><img src=\"images/img.jpg\" alt=\"Profile Image\" /></span><span>John Smith</span><span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if((j_emp==30)&&((m_emp==4)||(m_emp==6)||(m_emp==9)||(m_emp==11))&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"image\"><img src=\"images/img.jpg\" alt=\"Profile Image\" /></span><span>John Smith</span><span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
                     console.log(date);

 }
 else if(((j_emp==28)||(j_emp==29))&&(m_emp==2)&&(y_emp==y)&&(j==1)&&(m>m_emp)&&(etat=="notReturned")){
   nb=nb+1;
        $("#ida").append("<li class=\"nav-item\"><a class=\"dropdown-item\"> <span class=\"image\"><img src=\"images/img.jpg\" alt=\"Profile Image\" /></span><span>John Smith</span><span class=\"message\" >L'emprunte d'ID :  "+ id_emprunte +  "  a dépassé la date de retour</span></a></li>");
 }
    
});
      
             $("#number_msg").append("<span class=\"badge bg-green\">" + nb + "</span>");

});

  function signout(){
    firebase.auth().signOut().then(function() {
  // Sign-out successful. 
    window.location="login.php";


   }).catch(function(error) {
  // An error happened.
});
  }

          
          </script>
        
  <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
             <div class="col-md-12" id="etat">
                <div class="col-md-4">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Places disponibles</h2>
                    
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                     
                      <li><a class="close-link" style="margin-left: 15px">
                        <i class="fa fa-times" ></i></a>                   
                           </li>
                    </ul>

                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content" style="display: block;">

                    
   <input class="knob" data-width="200" data-height="200" data-displayPrevious=true data-fgColor="#26B99A" data-angleOffset=-125 data-angleArc=250 id="valeur" type="text"   data-rotation="anticlockwise" value="" readonly />
    
                    </div>
                   
                    
                    
                  </div>
                </div>
            
                 <div class="col-md-4 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Nombre etudiants existants </h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                
                            <li><a class="close-link" style="margin-left: 15px">
                              <i class="fa fa-times" ></i></a>              
                                      </li>
                    </ul>

                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content" style="display: block;">
                    
                     
          <input class="knob" data-width="200" data-height="200" data-angleOffset=-125 data-angleArc=250 data-fgColor="#e01a1a"
           data-rotation="anticlockwise" value="" id="nbe" readonly>
          
                  </div>
                </div>
              </div>
                <!-- start of weather widget -->
                <div >
                        
                  <div class="x_panel">
                    <div class="x_title">
                      <h2>Temperature</h2>
                    
                      <div class="clearfix"></div>
                    </div>
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">


                        <div class="weather-text pull-right">
                          <h3  id="temp"></h3>
                        </div>
                     
                            <div class="card-box table-responsive">
                                       <div id="datatable-buttons_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap no-footer">
                                        <table class="table table-striped table-bordered dataTable no-footer dtr-inline" role="grid" aria-describedby="datatable-buttons_info">
                                        <div class="row">
                  <div class="col-sm-6">
                    <div class="mb-md">
                      <button type="button" id="add_button" data-toggle="modal" data-target="#climatiseur" class="btn btn-info btn-sm"><i class="fa fa-plus" ></i></button>
                    </div>
                  </div>
                </div>
    <div id="climatiseur" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" role="document">
       <form method="post" id="user_form" enctype="multipart/form-data">
       <div class="modal-content">
      <div class="modal-header">
       <h4 class="modal-title" id="myModalLabel" >Ajouter un climatiseur</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
       <label>id-Climatiseur</label>
       <input type="text" name="id_clim" id="id_clim" class="form-control"  />
       <br />
       <label>marque</label>
       <input type="text" name="marq" id="marq" class="form-control"  />
       <br />
       <label>Emplacement</label>
       <input type="text" name="empc" id="empc" class="form-control"  />
        <br />
           <label>Etat</label>
           <br/>
        <div class="radio">
                            <label for="ouv"></label>
                              <input type="radio" class="flat"  id="ouv" name="iCheck" > Ouvert
                            
                          </div>

                          <br/>
                          <div class="radio">
                            <label for="ferm"></label>
                              <input type="radio" class="flat" id="ferm" name="iCheck"> Ferme
                            
                          </div>
     <div id="cond" class="desc">
        
      
        <label>temperature</label>
       <input type="text" name="tempera" id="tempera" class="form-control" />
       <br />
        <label>Mode</label>
       <input type="text" name="mode" id="mode" class="form-control" />

       <br />
       </div>
    

      </div>
     
      <div class="modal-footer">
       <input type="hidden" name="id_clim" id="id_clim" />
   
      

        <button type="button"  data-dismiss="modal" class="btn btn-success" onclick="addt()">Add</button>
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
     </div></div></form></div></div>
       

      <script>

    
          
 

var rootRef=firebase.database().ref().child("climatiseur");
   rootRef.on("child_added",snap => {
  var id_clim=snap.child("id_clim").val();
  var emplace=snap.child("emplace").val();
  
  if (snap.child("etat").val()) {
    var etat="Ouvert";
  var mode=snap.child("mode").val();
  var temp=snap.child("temp").val();
}
  else
    { var etat="Ferme";}
  var marq=snap.child("marque").val();
  $("#table_tem").append("<tr><th>"+ id_clim+"</th><th>"+marq+"</th><th>"+emplace+"</th><th>"+etat+"<button id=\"add_button\" data-toggle=\"modal\" data-target=\"#cl\" class=\"btn btn-danger pull-right btn-sm\" ><i class=\"fa fa-edit\"></i></button></th><th>"+temp+"</th><th>"+ mode+"</th></tr>");
});


function addt() {
          const id_clim=document.getElementById('id_clim');
          const database=firebase.database();
          const rootRef=database.ref('climatiseur');
          rootRef.child(id_clim.value).set({
            id_clim:document.getElementById('id_clim').value,
            marque:document.getElementById('marq').value,
            emplace:document.getElementById('empc').value,
            temp:document.getElementById('tempera').value,
            mode:document.getElementById('mode').value ,
            etat:document.getElementById('ouv').checked})}
function updatet() {
          const id_clim=document.getElementById('id_clim');
          const database=firebase.database();
          const rootRef=database.ref('climatiseur');
          etat:document.getElementById('ouvv').checked
          const newData={etat}
            rootRef.child(id_clim.value).update(newData);
        }

</script>
        
    
     <div id="cl" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog" role="document">
       <form method="post" id="user_form" enctype="multipart/form-data">
       <div class="modal-content">
      <div class="modal-header">
       <h4 class="modal-title" id="myModalLabel" >Modifier l'etat du climatiseur</h4>
           <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
           <label>Etat</label>
           <br/>
        <div class="radio">
                            <label for="ouvv"></label>
                              <input type="radio" class="flat"  id="ouvv" name="iCheck" > Ouvert
                            
                          </div>

                          <br/>
                          <div class="radio">
                            <label for="ferm"></label>
                              <input type="radio" class="flat" id="ferm" name="iCheck"> Ferme
                            
                          </div>
     <div id="cond" class="desc">
        
      
        <label>temperature</label>
       <input type="text" name="tempera" id="t" class="form-control" />
       <br />
        <label>Mode</label>
       <input type="text" name="mode" id="mode" class="form-control" />

       <br />
       </div>
    

      </div>
     
      <div class="modal-footer">
      
   
      

        <button type="button"  data-dismiss="modal" class="btn btn-success" onclick="updatet()">valider</button>
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
       </div></form></div></div>
                            <thead>
                        <tr role="row"><th >Climatiseur</th><th>Marque</th><th>Emplacement</th><th >Etat</th><th>Temperature</th><th>Mode</th></tr>
                      </thead>
                      <tbody id="table_tem">

                     </tbody>
                    </table>  
                     

                     
                      </div>
                    </div>
                  </div>

                
                <!-- end of weather widget -->
</div>
                  </div></div></div></div></div></div>
                  
              
                        
<div class="col-md-6 col-sm-5 ">
              <br><br> <br><br><br>
              <div class="x_panel tile">
                <div class="x_title">
                  <h2>Satisfaction des etudiants</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                      
                    </li>
<li><a class="close-link" style="margin-left: 15px"><i class="fa fa-times" ></i></a>                    </li>
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content">
                  <table class="" style="width:100%">
                    <tr>
                      <th style="width:50%;">
                        <p></p>
                      </th>
                      <th>
                        <div class="col-lg-7 col-md-7 col-sm-7 ">
                          <p class=""></p>
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 ">
                          <p class=""></p>
                        </div>
                      </th>
                    </tr>
                    <tr>
                      <td>
                        <canvas class="canvasDoughnut" height="140" width="140" style="margin: 15px 10px 10px 0"></canvas>
                      </td>
                      <td>
                        <table class="tile_info">
                          <tr>
                            <td>
                              <p><i class="fa fa-square blue"></i>80%-100% </p>
                            </td>
                            <td></td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square green"></i>60%-80% </p>
                            </td>
                            <td></td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square purple"></i>40%-60% </p>
                            </td>
                            <td></td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square aero"></i>20%-40% </p>
                            </td>
                            <td></td>
                          </tr>
                          <tr>
                            <td>
                              <p><i class="fa fa-square red"></i>0%-20% </p>
                            </td>
                            <td></td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>

<div class="row">
            <div class="col-md-12 col-sm-12 ">
              <div class="dashboard_graph">

                <div class="row x_title">
                  <div class="col-md-6">
                    <h3>Nombre de visites <small>a la bibliotheque</small></h3>
                  </div>
                  <div class="col-md-6">
                    <div id="reportrange" class="pull-right" style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                      <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                      <span>December 30, 2014 - January 28, 2015</span> <b class="caret"></b>
                    </div>
                  </div>
                </div>

                <div class="col-md-9 col-sm-9 ">
                  <div id="chart_plot_01" class="demo-placeholder"></div>
                </div>
               

                <div class="clearfix"></div>
              </div>
            </div>

          
       
<!--          <div class="col-md-12">
      <div class="x_panel">
                <div class="x_title">
                  <h2><i class="fa fa-exclamation-circle" style="padding-right: 20px;"></i>Reclamations</h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                   
<li><a class="close-link" style="margin-left: 15px"><i class="fa fa-times" ></i></a>                    </li>
                  </ul>
                  <div class="clearfix"></div>
                </div>
                <div class="x_content"> -->

                  <!-- modals -->
     <!--              
                </div>
              </div>
     </div> -->
         
                
            </div>
        </div>
    </div>
</div>

        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
       <!-- Switchery -->
    <script src="../vendors/switchery/dist/switchery.min.js"></script>
    <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="../vendors/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="../vendors/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="../vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="../vendors/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="../vendors/skycons/skycons.js"></script>
    <!-- Flot -->
    <script src="../vendors/Flot/jquery.flot.js"></script>
    <script src="../vendors/Flot/jquery.flot.pie.js"></script>
    <script src="../vendors/Flot/jquery.flot.time.js"></script>
    <script src="../vendors/Flot/jquery.flot.stack.js"></script>
    <script src="../vendors/Flot/jquery.flot.resize.js"></script>
    <!-- Flot plugins -->
    <script src="../vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="../vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="../vendors/flot.curvedlines/curvedLines.js"></script>
    <!-- DateJS -->
    <script src="../vendors/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <script src="../vendors/jqvmap/dist/jquery.vmap.js"></script>
    <script src="../vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="../vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
    <!-- bootstrap-daterangepicker -->
    <script src="../vendors/moment/min/moment.min.js"></script>
    <script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
      <!-- jQuery Knob -->
    <script src="../vendors/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- bootstrap-daterangepicker -->
    <script src="../vendors/moment/min/moment.min.js"></script>
    <script src="../vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
    <!-- bootstrap-datetimepicker -->    
    <script src="../vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>
     <!-- Ion.RangeSlider -->
    <script src="../vendors/ion.rangeSlider/js/ion.rangeSlider.min.js"></script>
     <!-- Datatables -->
    <script src="../vendors/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="../vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="../vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="../vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    <script src="../vendors/jszip/dist/jszip.min.js"></script>
    <script src="../vendors/pdfmake/build/pdfmake.min.js"></script>
    <script src="../vendors/pdfmake/build/vfs_fonts.js"></script>
     <!-- FastClick -->
    <script src="../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="../vendors/nprogress/nprogress.js"></script>
     <!-- ECharts -->
    <script src="../vendors/echarts/dist/echarts.min.js"></script>
    <script src="../vendors/echarts/map/js/world.js"></script>
    <!-- morris.js -->
    <script src="../vendors/raphael/raphael.min.js"></script>
    <script src="../vendors/morris.js/morris.min.js"></script>

    

     <!-- Bootstrap Colorpicker -->
    <script src="../vendors/mjolnic-bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
  <script  type="text/javascript">
   $(function () {
                $('#myDatepicker').datetimepicker();
            });
    
    $('#myDatepicker2').datetimepicker({
        format: 'DD'
    });
    
    $('#myDatepicker3').datetimepicker({
        format: 'hh:mm A'
    });
    
    $('#myDatepicker4').datetimepicker({
        ignoreReadonly: true,
        allowInputToggle: true
    });

    $('#datetimepicker6').datetimepicker();
    
    $('#datetimepicker7').datetimepicker({
        useCurrent: false
    });
    
    $("#datetimepicker6").on("dp.change", function(e) {
        $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
    });
    
    $("#datetimepicker7").on("dp.change", function(e) {
        $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
    });
</script>


  </body>
</html>
