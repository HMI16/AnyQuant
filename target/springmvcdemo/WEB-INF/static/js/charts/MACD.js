/**
 * Created by 15HR-1528SS on 2016/6/2.
 */
/**
 * Created by 15HR-1528SS on 2016/5/28.
 */
var myChartMACD = echarts.init(document.getElementById('macd'));

var id = location.search.substring(1);
macdTime=[];
macdDif=[];
macdDea=[];
macdData=[];
// alert("init macd");
var xhr = new XMLHttpRequest();
xhr.open("GET", "/MACDGraph"+"?id="+id, false);
xhr.send(null);
if(xhr.readyState==4 && xhr.status==200){
    var macd = eval("(" + xhr.responseText + ")");
    for(var i=0;i<macd.length;i++){
        macdTime[i]=macd[i].date;
        macdDif[i]=macd[i].diff;
        macdDea[i]=macd[i].dea;
        macdData[i]=macd[i].macd;
    }
// alert("finish init macd");
}




// bench + single
optionMACD = {
    title: {
        text: '股票ID',
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
        data: ['BAR', 'DIF', 'DEA'],
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
        data: macdTime
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
            stack: '总量',
            label: {
                normal: {
                    formatter: '{b}'
                }
            },
            data: macdData
        },
        {
            name: 'DIF',
            type: 'line',
            data: macdDif,
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'DEA',
            type: 'line',
            data: macdDea,
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
    ]
};

myChartMACD.setOption(optionMACD);