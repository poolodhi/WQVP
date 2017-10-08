<%-- 
    Document   : visulaize1
    Created on : Oct 7, 2017, 4:49:22 PM
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
        <script src="./lib/echarts.min.js"></script>
        <title>JSP Page</title>
        
    </head>
    <body>
         <div class="container-fluid header" data-spy="affix">
            <div class="well text-center"><h3>WQVP</h3></div>
        </div>
        <!--
       <form action="map" method="POST">
            <select name="rivername">
                <option value="0">Fitzroy River</option>
                <option value="1">Calliope River and anabranch</option>
                <option value="2">Boyne River and South Trees Inlet</option>
                <option value="3">Baffle Creek</option>
                <option value="4">Kolan River</option>
                <option value="5">Burnett River</option>
                <option value="6">Burrum River</option>
                <option value="7">Isis River</option>
                <option value="8">Gregory River</option>
                <option value="9">Mary River</option>
                <option value="10">Great Sandy Straits and Hervey Bay </option>
                <option value="11">Tin Can Inlet & Snapper Creek </option>
            </select>
            <select name="parameter">
                <option value="-1">All</option>
                <option value="0">Dissolved Oxygen</option>
                <option value="1">pH</option>
                <option value="2">Temperature (deg C)</option>
                <option value="3">Turbidity (NTU)</option>
                <option value="4">Salinity (PSU)</option>
                <option value="5">Total Nitrogen</option>
                <option value="6">Total Phosphorus</option>
            </select>
            <button>Submit</button>
        </form>
        -->
       <div id="main" class="c" style="width: 100%; min-height: 400px"></div>
       <script type="text/javascript">
            $(window).on('resize', function(){
            if(myChart != null && myChart != undefined){
                myChart.resize();
            }
        });
        // based on prepared DOM, initialize echarts instance
        var myChart = echarts.init(document.getElementById('main'));
       
      // specify chart configuration item and data
        var option = {
                title: {
                    left:'center',
                    text:'<%= request.getAttribute("title")%>'
                },
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                        return [pt[0], '10%'];
                    }
                },
                legend: {
                    data:['']
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: [<%= request.getAttribute("date")%>]
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '100%']
                },
                dataZoom: [{
                type: 'inside',
                start: 0,
                end: 10
                }, {
                start: 0,
                end: 10,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
                }],
                series: [
                    <% Integer p=Integer.parseInt((String)request.getAttribute("parameter"));
                       String []insights= (String [])request.getAttribute("insights");
                       String []pramentername= (String [])request.getAttribute("pramentername");
                        if(p==-1){
                            for(int i=0;i<7;i++){
                    %>            
                                {
                                    name: '<%=pramentername[i]%>',
                                    type:'line',
                                    smooth:true,
                                    symbol: 'none',
                                    sampling: 'average',
                                    data: [<%=insights[i]%>]
                                },
                            <%    
                            }
                        }else{
                            %>
                                {
                                    name: '<%=pramentername[p]%>',
                                    type:'line',
                                    smooth:true,
                                    symbol: 'none',
                                    sampling: 'average',
                                    data: [<%=insights[p]%>]
                                }
                         <%   
                        }
                    %>
                ]
        };

        // use configuration item and data specified to show chart
        myChart.setOption(option);
    </script>
    <div class="row">
        <div class="col-2"></div>
                    <div class="col-8">
                      <a class="btn btn-primary btn-block" href="eachriver.jsp" role="button">Back</a>
                    </div>
        <div class="col-2"></div>
    </div>
    
    </body>
</html>
