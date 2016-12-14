/**
 * Created by ryysuke on 16/5/27.
 */
/**
 * Created by ryysuke on 16/5/27.
 */
function createMarketTable() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/marketList");
    xhr.send(null);
    // alert("get");

    xhr.onreadystatechange = function() {
        if (xhr.readyState==4 && xhr.status==200) {
            // alert(xhr.readyState+"   "+xhr.status);
            var obj =eval("("+ xhr.responseText+")");
            // alert(xhr.responseText);
            var count  = obj.length;
            for(var i = 0; i < count; i++){
                var objId = obj[i].id;

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
                var timec = row.insertCell(8);
                idc.innerHTML = "<a href='../pages/single.html' class='idref'>" + obj[i].id + "</a>";
                namec.innerHTML = obj[i].name;
                openc.innerHTML = obj[i].open;
                highc.innerHTML = obj[i].high;
                lowc.innerHTML = obj[i].low;
                closec.innerHTML = obj[i].close;
                adjc.innerHTML = obj[i].adj_price;
                volc.innerHTML = obj[i].volume;
                timec.innerHTML = obj[i].date;
            }
        }
    }
}