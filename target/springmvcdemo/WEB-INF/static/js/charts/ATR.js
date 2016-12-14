/**
 * Created by 15HR-1528SS on 2016/5/28.
 */

var myChartATR = echarts.init(document.getElementById('atr'));
// single graph
var dataATR;
var id = location.search.substring(1);
var atrxhr = new XMLHttpRequest();
atrxhr.open("get", "/ATRGraph?id="+id, false);
atrxhr.send(null);
if(atrxhr.readyState==4&&atrxhr.status==200){
    var text = eval("(" +  atrxhr.responseText + ")");
    var data = [];
    for(var i=0;i<text.length;i++){
        data[i] = [];
        data[i][0]=text[i].date;
        data[i][1]=text[i].atr6;
        data[i][2]=text[i].atr26;
        data[i][3]=text[i].atr65;
    }
    dataATR=splitData(data);
}

function splitData(rawData) {
    var categoryData = [];
    var values = [];
    for (var i = 0; i < rawData.length; i++) {
        categoryData.push(rawData[i].splice(0, 1)[0]);
        values.push(rawData[i])
    }
    return {
        categoryData: categoryData,
        values: values
    };
}

function calculateMA(dayCount) {
    var result = [];
    for (var i = 0, len = dataATR.values.length; i < len; i++) {
        result.push(dataATR.values[i][dayCount - 1]);
    }
    return result;
}


optionATR = {
    title: {
        text: '股票ID',
        left: 3,
        y:'2%'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'line'
        }
    },
    legend: {
        data: ['ATR6', 'ATR26', 'ATR65'],
        y:'3%'
    },
    grid: {
        left: '6%',
        right: '8%',
        bottom: '32%'
    },
    xAxis: {
        type: 'category',
        data: dataATR.categoryData,
        scale: true,
        boundaryGap: false,
        axisLine: {onZero: false},
        splitLine: {show: false},
        splitNumber: 20,
        min: 'dataMin',
        max: 'dataMax'
    },
    yAxis: {
        scale: true,
        splitArea: {
            show: true
        }
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
            name: 'ATR6',
            type: 'line',
            data: calculateMA(1),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'ATR26',
            type: 'line',
            data: calculateMA(2),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'ATR65',
            type: 'line',
            data: calculateMA(3),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
    ]
};

myChartATR.setOption(optionATR);