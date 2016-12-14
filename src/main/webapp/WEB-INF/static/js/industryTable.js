/**
 * Created by ryysuke on 16/5/28.
 */

function createLarge(){

}


function createIndusSpecTable(type) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/IndustryStockList?type=" + type, false);
    xhr.send(null);
    // xhr.onreadystatechange= function(){
        if(xhr.readyState==4 && xhr.status==200){
            // alert(xhr.readyState + " "+ xhr.status);
            var obj = eval("("+ xhr.responseText+")");
            var count = obj.length;
            for(var i = 0; i < count; i++){
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

                idc.innerHTML = "<a href='../pages/single.html?"+obj[i].id+"' class='idref'" + " id='" + obj[i].id +
                    "'>"+obj[i].id+"</a>";
                // idc.innerHTML = "<form action='../pages/single.html?d1="+data[i].id+"' method = 'post' name='single' id='single'><input type='submit' name='s1' id='s1' value='123"+data0.id+"'</form>";
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
                // idc.innerHTML = "<a href='../pages/single.html' class='idref'>" + obj[i].id + "</a>";
                // rankc.innerHTML = obj[i].rank;
            }
        }
    // }
}


function createIndusTable() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/IndustryList");
    xhr.send(null);

    xhr.onreadystatechange=function () {
        if(xhr.readyState==4 && xhr.status==200){
            // alert(xhr.readyState + " "+ xhr.status);

            var obj = eval("("+ xhr.responseText+")");
            var count = obj.length;
            for(var i = 0; i < count; i++){
                var table = document.getElementById("sec-table-body");
                var row = table.insertRow(i);
                var typec = row.insertCell(0);
                var increasec = row.insertCell(1);

                typec.innerHTML = "<a href='#' class='typelink' onclick=createIndusSpecTable('" + obj[i].type + "')>" + obj[i].type + "</a>";
                increasec.innerHTML = obj[i].increase;
                // idc.innerHTML = "<a href='../pages/single.html' class='idref'>" + obj[i].id + "</a>";
                // rankc.innerHTML = obj[i].rank;
            }
        }
    }
}

function initSpeIndusTable(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/IndustryStockList?type=农林牧渔业", false);
    xhr.send(null);
    if(xhr.readyState==4 && xhr.status==200){
        // alert(xhr.readyState + " "+ xhr.status);
        var obj = eval("("+ xhr.responseText+")");
        var count = obj.length;
        for(var i = 0; i < count; i++){
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

            idc.innerHTML = "<a href='../pages/single.html?"+obj[i].id+"' class='idref'" + " id='" + obj[i].id +
                "'>"+obj[i].id+"</a>";
            // idc.innerHTML = "<form action='../pages/single.html?d1="+data[i].id+"' method = 'post' name='single' id='single'><input type='submit' name='s1' id='s1' value='123"+data0.id+"'</form>";
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
            // idc.innerHTML = "<a href='../pages/single.html' class='idref'>" + obj[i].id + "</a>";
            // rankc.innerHTML = obj[i].rank;
        }
    }
}



$(document).ready(function () {
    // alert(typeof $(".typelink"));   // here......
    $(".typelink").click(function () {
    });
});