/**
 * Created by ryysuke on 16/5/27.
 */

function createTable() {
    // alert("aaaaa");

    var index = new XMLHttpRequest();
    index.open("GET", "/PartIndex", false);
    index.send(null);
    var indecies=[];
    if(index.readyState==4&&index.status==200){
        var data = eval("("+index.responseText+")");
        for(var i=0;i<data.length;i++){
            indecies[i]=[];
            indecies[i][0]=data[i].id;
            indecies[i][1]=data[i].flu;
            indecies[i][2]=data[i].change;
        }
    }


    var list = new XMLHttpRequest();
    list.open("GET", "/StockList");
    list.send(null);
    list.onreadystatechange = function() {
        if (list.readyState==4 && list.status==200) {
            var obj = eval("("+ list.responseText+")");
            var count  = obj.length;
            for(var i = 0; i < count; i++){
                var table = document.getElementById("table-body");
                var row = table.insertRow(i);
                row.id=obj[i].id;
                var idc = row.insertCell(0);
                var namec = row.insertCell(1);
                var openc = row.insertCell(2);
                var highc = row.insertCell(3);
                var lowc = row.insertCell(4);
                var closec = row.insertCell(5);
                var adjc = row.insertCell(6);
                var volc = row.insertCell(7);
                var turnc = row.insertCell(8);
                var pec = row.insertCell(9);
                var pbc = row.insertCell(10);
                var timec = row.insertCell(11);
                idc.innerHTML = "<a href='../pages/single.html?"+obj[i].id+"' class='idref'" + " id='" + obj[i].id +
                    "'>"+obj[i].id+"</a>"; // href='../pages/single.html'
            //     idc.innerHTML = "<form action='pages/single.html?d1="+obj[i].id+"' name='f1' id='f1'>"+
            // "<input type='submit' value='"+obj[i].id+"'/>"+
            // "</form>";
                // alert("<a href='#' class='idref'" + " id='" + obj[i].id +
                //     "'>"+obj[i].id+"</a>");
                // "<a href='single.html' "+ "id='" +obj[i].id + "'>" +obj[i].id+"</a>"
                namec.innerHTML = obj[i].name;
                openc.innerHTML = obj[i].open;
                highc.innerHTML = obj[i].high;
                lowc.innerHTML = obj[i].low;
                closec.innerHTML = obj[i].close;
                adjc.innerHTML = obj[i].adj_price;
                volc.innerHTML = obj[i].volume;
                turnc.innerHTML = obj[i].turnover;
                pec.innerHTML = obj[i].pe;
                pbc.innerHTML = obj[i].pd;
                // alert(obj[i]);
                row.onmousemove=function(){
                   var stockid = window.event.srcElement.parentNode.id;
                    var count=0;
                    for(var i=0;i<indecies.length;i++){
                        if(stockid==indecies[i][0]){
                            count=i;
                            break;
                        }
                    }
                    document.getElementById("id").innerHTML="股票id：" + indecies[count][0];
                    document.getElementById("flu").innerHTML="历史波动率：" + indecies[count][1];
                    document.getElementById("increase").innerHTML="涨跌幅：" + indecies[count][2];
                    // alert(indecies[count][0]+" " + indecies[count][1] + " " + indecies[count][2]);
                }
            }
        }
    };

    function showIndex(i) {
        document.getElementById("id").innerHTML=indecies[i][0];
        document.getElementById("flu").innerHTML=indecies[i][1];
        document.getElementById("flu").innerHTML=indecies[i][2];
        alert(indecies[i][0]+" " + indecies[i][1] + " " + indecies[i][2]);
    }
}




$(document).ready(function () {
    // alert("oh");
    $("#filter-btn").click(function(){
        alert(document.getElementById("Hvol-input").getText);
        // alert("orz");
        $.ajax({
            url:"/StockFilt",
            data:$("#filt-form").serialize(),
            type:"get",
            success:function(data) { //ajax返回的数据
                // alert(data);
                alert($("#filt-form").serialize());
                // var obj = eval("(" + data + ")");
                var count = data.length;
                $('#table-body').empty();
                for (var i = 0; i < count; i++) {
                    var table = document.getElementById("table-body");
                    var row = table.insertRow(i);
                    var idc = row.insertCell(0);
                    var namec = row.insertCell(1);
                    var openc = row.insertCell(2);
                    var highc = row.insertCell(3);
                    var lowc = row.insertCell(4);
                    var closec = row.insertCell(5);
                    var adjc = row.insertCell(6);
                    var volc = row.insertCell(7);
                    var turnc = row.insertCell(8);
                    var pec = row.insertCell(9);
                    var pbc = row.insertCell(10);
                    var timec = row.insertCell(11);

                    idc.innerHTML = "<a href='../pages/single.html' class='idref'>" + data[i].id + "</a>";
                   // idc.innerHTML = "<form action='../pages/single.html?d1="+data[i].id+"' method = 'post' name='single' id='single'><input type='submit' name='s1' id='s1' value='123"+data0.id+"'</form>";

                    namec.innerHTML = data[i].name;
                    openc.innerHTML = data[i].open;
                    highc.innerHTML = data[i].high;
                    lowc.innerHTML = data[i].low;
                    closec.innerHTML = data[i].close;
                    adjc.innerHTML = data[i].adj_price;
                    volc.innerHTML = data[i].volume;
                    turnc.innerHTML = data[i].turnover;
                    pec.innerHTML = data[i].pe;
                    pbc.innerHTML = data[i].pb;
                    // alert("done");
                }
            }
        });
//              $("#filt-form").submit();
    });
});







var toID;
function getStockID() {
    alert(toID);
    return toID;
}


function filt() {
    alert("T_T");
    var Hadjv = parseFloat(document.getElementById("Hadj-input").value);
    var Ladjv = parseFloat(document.getElementById("Ladj-input").value);
    var Hvolumev = parseFloat(document.getElementById("Hvol-input").value);
    var Lvolumev = parseFloat(document.getElementById("Lvol-input").value);
    var Hturnv = parseFloat(document.getElementById("Hturn-input").value);
    var Lturnv = parseFloat(document.getElementById("Lturn-input").value);
    var Hpev = parseFloat(document.getElementById("Hpe-input").value);
    var Lpev = parseFloat(document.getElementById("Lpe-input").value);
    var Hpdv = parseFloat(document.getElementById("Hpd-input").value);
    var Lpdv = parseFloat(document.getElementById("Hpd-input").value);

    // var Lpdv = (double)($("#Lpd")).text();
    // alert(($("#Hadj-input")).text());

    // var para = "Hadj_price="+Hadjv+"&"+
    //     "Ladj_price="+Ladjv+"&"+
    //     "Hvolume="+Hvolumev+"&"+
    //     "Lvolume="+Lvolumev+"&"+
    //     "Hturnover="+Hturnv+"&"+
    //     "Lturnover="+Lturnv+"&"+
    //     "Hpe="+Hpev+"&"+
    //     "Lpe="+Lpev+"&"+
    //     "Hpd="+Hpdv+"&"+
    //     "Lpd="+Lpdv ;
    // alert(typeof Hadjv);
    // alert(para);

    var xhr = new XMLHttpRequest();
    var para = $("#filt-form").serialize();
    xhr.open("GET", "/StockFilt?"+para);
    xhr.send(null);
    xhr.onreadystatechange = function() {
        alert(xhr.readyState+" ; "+xhr.status);
        if (xhr.readyState==4 && xhr.status==200) {
            var obj =eval("("+ xhr.responseText+")");
            alert(xhr.responseText);
        }
    }
}
