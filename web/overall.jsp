<%-- 
    Document   : overall
    Created on : Oct 8, 2017, 10:34:14 AM
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
        <style>
            .xbtn{
                margin:5px;
            }
        </style>
        <script type="text/javascript">
            function resetcharts(){
                 if(myChart1 != null && myChart1 != undefined){
                myChart1.resize();
            }
            if(myChart != null && myChart != undefined){
                myChart.resize();
            }
            }
        $(window).on('resize', function(){
            if(myChart1 != null && myChart1 != undefined){
                myChart1.resize();
            }
            if(myChart != null && myChart != undefined){
                myChart.resize();
            }
            if(myChart2 != null && myChart2 != undefined){
                myChart2.resize();
            }
        });
        $(document).ready(function(){
            $("#container_doc").hide();
            $("#container_sal").hide();
            $("#docbutton").click(function(){
                $("#container_ph").hide();
                $("#container_sal").hide();
                $("#container_doc").show();
                myChart.resize();
            });
            $("#phbutton").click(function(){
                $("#container_sal").hide();
                $("#container_doc").hide();
                $("#container_ph").show();
                myChart1.resize();
            });
            $("#phbutton").click(function(){
                
                $("#container_doc").hide();
                $("#container_ph").hide();
                $("#container_sal").hide();
                myChart2.resize();
            });
        });
        </script>
    </head>
    <body>
         <div class="container-fluid header" data-spy="affix">
            <div class="well text-center"><h3>WQVP</h3></div>
        </div>
        <div class="container-fluid" data-spy="affix">
            <div class="well text-center"><h4>Overall Analysis</h4></div>
        </div>
        <div id="container_ph" style="height:400px;width:100%"></div>
        <div id="container_doc" style="height:400px;width:100%"></div>
        <div id="container_sal" style="height:400px;width:100%"></div>
        
        <button id="phbutton" type="button" class="xbtn btn btn-primary">
            pH
        </button>
        <button id="docbutton" type="button" class="xbtn btn btn-primary">
            DOC
        </button>
        <button id="salbutton" type="button" class="xbtn btn btn-primary">
            Salinity
        </button>
        
        
        

       <script type="text/javascript">
        var dom1 = document.getElementById("container_ph");
        var myChart1 = echarts.init(dom1);
        var app1 = {};
        option1 = null;
        app1.title = 'ph';
        
        <% 
            String ph_string="";
            String[] ph_para=(String[])request.getAttribute("ph_para");
            int[] ph=(int[])request.getAttribute("ph");
            for(int i=0;i<ph_para.length;i++){
                ph_string+="'"+ph_para[i]+"',";
            }
            ph_string=ph_string.substring(0,ph_string.length()-1);
        %>
        option1 = {
                title: {
                    left:'center',
                    text:'pH',
                    padding:10
                },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
               orient: 'horizontal',
               padding: [50, 5],
               x: 'center',
               data:[<%=ph_string%>]
            },
            series: [
                {
                    name:'pH',
                    type:'pie',
                     radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '15',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                    <%
                    for(int i=0;i<ph.length;i++){
                    %>
                    {value:<%=ph[i]%>, name:'<%=ph_para[i]%>'},
                    <%
                    }
                    %>
                    ]
                }
            ]
        };
        
        if (option1 && typeof option1 === "object") {
            myChart1.setOption(option1, true);
        }
       </script>
       
       
       
       
       
       
       
       
       <script type="text/javascript">
        var dom = document.getElementById("container_doc");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        app.title = 'Dissoloved Oxygen';
        
        <% 
            String doc_string="";
            String[] doc_para=(String[])request.getAttribute("doc_para");
            int[] doc=(int[])request.getAttribute("doc");
            for(int i=0;i<doc_para.length;i++){
                doc_string+="'"+doc_para[i]+"',";
            }
            doc_string=doc_string.substring(0,doc_string.length()-1);
        %>
        option = {
            
                title: {
                    left:'center',
                    text:'Dissolved Oxygen',
                    padding:10
                },
            tooltip: {
                
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
               orient: 'horizontal',
               padding:[40,5],
               x: 'center',
               data:[<%=doc_string%>]
            },
            series: [
                {
                    name:'Dissoloved Oxygen',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '15',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                    <%
                    for(int i=0;i<doc.length;i++){
                    %>
                    {value:<%=doc[i]%>, name:'<%=doc_para[i]%>'},
                    <%
                    }
                    %>
                    ]
                }
            ]
        };
        
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
       
            </script>
       
       <script type="text/javascript">
        var dom2 = document.getElementById("container_sal");
        var myChart2 = echarts.init(dom2);
        var app2 = {};
        option2 = null;
        app2.title = 'Salinity';
        
        <% 
            String sal_string="";
            String[] sal_para=(String[])request.getAttribute("sal_para");
            int[] sal=(int[])request.getAttribute("sal");
            for(int i=0;i<sal_para.length;i++){
                sal_string+="'"+sal_para[i]+"',";
            }
            sal_string=sal_string.substring(0,sal_string.length()-1);
        %>
        option2 = {
            
                title: {
                    left:'center',
                    text:'Salinity',
                    padding:10
                },
            tooltip: {
                
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
               orient: 'horizontal',
               padding:[40,5],
               x: 'center',
               data:[<%=sal_string%>]
            },
            series: [
                {
                    name:'Salinity',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '15',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                    <%
                    for(int i=0;i<sal.length;i++){
                    %>
                    {value:<%=sal[i]%>, name:'<%=sal_para[i]%>'},
                    <%
                    }
                    %>
                    ]
                }
            ]
        };
        
        if (option2 && typeof option2 === "object") {
            myChart2.setOption(option2, true);
        }
       
            </script>
       
       
    </body>
</html>
