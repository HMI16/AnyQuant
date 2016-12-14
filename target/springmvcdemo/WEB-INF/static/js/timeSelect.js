/**
 * Created by ryysuke on 16/5/31.
 */
$(document).ready(function() {
    $("#time-btn").click(function() {
        // alert("plzzz "+ document.getElementById("start"));
        var startin = document.getElementById("start").value;
        var endin = document.getElementById("end").value;

        // alert(d1 > d2);
        if(document.getElementById("start").value == "" || document.getElementById("end").value == ""){
            alert("请输入完整日期~");
        }

        else {
            var d1  = new Date(startin.replace(/-/g,   "/"));
            var d2 = new Date(endin.replace(/-/g, "/"));
            var nowD = new Date();
            nowD.toLocaleDateString();
            // alert(startin);

            if((d1 < d2) && !(d2 > nowD)) {
                if(location.href.indexOf("bench")>=0){
                    timeFileBench(startin, endin);
                }else {
                    timeFiltSingle(startin, endin);
                }
                $.ajax({
                    url: "/marketListByTime",
                    data: $("#filt-time-form").serialize(),
                    method: "get",
                    success: function (data) {
                        // alert($("#ben-time-form").serialize());
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
                })
            }
            else{
                alert("请输入正确日期~");
            }
        }

    })
})