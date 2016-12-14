/**
 * Created by 15HR-1528SS on 2016/6/2.
 */
/**
 * Created by 15HR-1528SS on 2016/5/28.
 */
var myChartV = echarts.init(document.getElementById('vol'));

var id = location.search.substring(1);
volumnTime=[];
volumnData=[];
// alert("init volumn");
var xhr = new XMLHttpRequest();
xhr.open("GET", "/GetGrailVolume?id="+id, false);
xhr.send(null);
if(xhr.readyState==4 && xhr.status==200){
    var volumn = eval("(" + xhr.responseText + ")");
    alert(xhr.responseText);
    for(var i=0;i<volumn.length;i++){
        volumnTime[i]=volumn[i].date;
        volumnData[i]=volumn[i].volumn;
    }
// alert("finish init volumn");
}




// bench + single
optionV = {
    title: {
        text: '大盘ID',
        left: 3,
        y:'2%'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '6%',
        right: '8%',
        bottom: '32%'
    },
    legend: {
        data: ['BAR'],
        y:'3%'
    },
    yAxis: {
        type: 'value',
        position: 'top',
        splitLine: {lineStyle: {type: 'dashed'}},
    },
    xAxis: {
        type: 'category',
        axisLine: {show: false},

        axisTick: {show: false},
        splitLine: {show: false},
        data: volumnTime
    },
    dataZoom: [
        {
            type: 'inside',
            start: 50,
            end: 100
        },
        {
            show: true,
            type: 'slider',
            y: '80%',
            start: 50,
            end: 100
        }
    ],
    series: [
        {
            name: 'BAR',
            type: 'bar',
            stack: '成交量',
            label: {
                normal: {
                    formatter: '{b}'
                }
            },
            data: volumnData
        }
    ]
};

myChartV.setOption(optionV);