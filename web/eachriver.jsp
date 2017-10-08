<%-- 
    Document   : echarts
    Created on : Oct 5, 2017, 12:41:29 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        
        <link rel="stylesheet" href="./lib/bootstrap/css/bootstrap.min.css">
         <link rel="stylesheet" href="./css/main.css">
        <script src="./lib/jquery-3.2.1.min.js"></script>
        <script src="./lib/popper.min.js" ></script>
        <script src="./lib/bootstrap/js/bootstrap.min.js" ></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid header" data-spy="affix">
            <div class="well text-center"><h3>WQVP</h3></div>
        </div>
        <div class="container-fluid">
                <div class="row">
                    <div class="col-2">
                    </div>
                    <div class="col-8">
                        <form action="map" method="POST">
                            <div class="form-group">
                                <label>Rivers</label>
                                <select name="rivername" class="form-control">
                                    <option value="0">Fitzroy River</option>
                                    <option value="1">Calliope River & anabranch</option>
                                    <option value="2">Boyne River & South Trees Inlet</option>
                                    <option value="3">Baffle Creek</option>
                                    <option value="4">Kolan River</option>
                                    <option value="5">Burnett River</option>
                                    <option value="6">Burrum River</option>
                                    <option value="7">Isis River</option>
                                    <option value="8">Gregory River</option>
                                    <option value="9">Mary River</option>
                                    <option value="10">Great Sandy Straits & Hervey Bay </option>
                                    <option value="11">Tin Can Inlet & Snapper Creek </option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Parameters</label>
                                <select name="parameter" class="form-control">
                                    <option value="-1">All</option>
                                    <option value="0">Dissolved Oxygen</option>
                                    <option value="1">pH</option>
                                    <option value="2">Temperature (deg C)</option>
                                    <option value="3">Turbidity (NTU)</option>
                                    <option value="4">Salinity (PSU)</option>
                                    <option value="5">Total Nitrogen</option>
                                    <option value="6">Total Phosphorus</option>
                                </select>
                            </div>
                            <button class="btn inner-button btn-block btn-secondary">
                                Submit
                            </button>
                        </form>
                    </div>
                    <div class="col-2">
                    </div>
                </div>
            <div class="row">
        <div class="col-2"></div>
                    <div class="col-8">
                      <a class="btn btn-primary btn-block" href="index.html" role="button">Back</a>
                    </div>
        <div class="col-2"></div>
    </div>
   </body>
</html>
