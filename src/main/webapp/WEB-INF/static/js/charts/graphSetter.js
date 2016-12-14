/**
 * Created by ryysuke on 16/6/4.
 */

var Kchart = echarts.init(document.getElementById('k'));
var ATRchart = echarts.init(document.getElementById('atr'));

// alert("orz!!!!!");

// alert("in setter: " + orgdata);
// alert("1");
var id = location.search.substring(1);
alert("id = " + id);
var atr = getATRData();
var dataATR = splitData(atr);

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
};


function getColumnATR(dayCount) {
    var result = [];
    // alert("in getColumn    " + data0.values.length);
    for (var i = 0, len = dataATR.values.length; i < len; i++) {
        result.push(dataATR.values[i][dayCount - 1]);
    }
    return result;
};

function getColumnK(dayCount) {
    var result = [];
    // alert("in getColumn    " + data0.values.length);
    for (var i = 0, len = dataK.values.length; i < len; i++) {
        result.push(dataK.values[i][dayCount - 1]);
    }
    return result;
};


function calculateMA(dayCount) {
    // alert("in calculateMA: "+data0.values.length);
    var result = [];
    for (var i = 0, len = dataK.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += dataK.values[i - j][1];
        }
        result.push(sum / dayCount);
    }
    return result;
};



// alert("outside   " + data0.values);
// alert("another out   " + data0.categoryData);


// alert("test MA:   " + calculateMA(5));

optionK = {
    title: {
        text: '股票ID',
        left: 0
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'line'
        }
    },
    legend: {
        data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30','OBV']
    },
    grid: {
        left: '10%',
        right: '10%',
        bottom: '15%'
    },
    xAxis: {
        type: 'category',
        data: dataK.categoryData,
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
            y: '90%',
            start: 50,
            end: 100
        }
    ],
    series: [
        {
            name: '日K',
            type: 'candlestick',
            data: dataK.values,
            markPoint: {
                label: {
                    normal: {
                        formatter: function (param) {
                            return param != null ? Math.round(param.value) : '';
                        }
                    }
                },
                data: [
                    {
                        name: 'XX标点',
                        coord: ['2013/5/31', 2300],
                        value: 2300,
                        itemStyle: {
                            normal: {color: 'rgb(41,60,85)'}
                        }
                    },
                    {
                        name: 'highest value',
                        type: 'max',
                        valueDim: 'highest'
                    },
                    {
                        name: 'lowest value',
                        type: 'min',
                        valueDim: 'lowest'
                    },
                    {
                        name: 'average value on close',
                        type: 'average',
                        valueDim: 'close'
                    }
                ],
                tooltip: {
                    formatter: function (param) {
                        return param.name + '<br>' + (param.data.coord || '');
                    }
                }
            },
            markLine: {
                symbol: ['none', 'none'],
                data: [
                    [
                        {
                            name: 'from lowest to highest',
                            type: 'min',
                            valueDim: 'lowest',
                            symbol: 'circle',
                            symbolSize: 10,
                            label: {
                                normal: {show: false},
                                emphasis: {show: false}
                            }
                        },
                        {
                            type: 'max',
                            valueDim: 'highest',
                            symbol: 'circle',
                            symbolSize: 10,
                            label: {
                                normal: {show: false},
                                emphasis: {show: false}
                            }
                        }
                    ],
                    {
                        name: 'min line on close',
                        type: 'min',
                        valueDim: 'close'
                    },
                    {
                        name: 'max line on close',
                        type: 'max',
                        valueDim: 'close'
                    }
                ]
            }
        },
        {
            name: 'MA5',
            type: 'line',
            data: calculateMA(5),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'MA10',
            type: 'line',
            data: calculateMA(10),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'MA20',
            type: 'line',
            data: calculateMA(20),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'MA30',
            type: 'line',
            data: calculateMA(30),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'OBV',
            type: 'line',
            data: [2200,2200,2200,2200,2200],
            // data: getColumn(5),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },

    ]
};


optionATR = {
    title: {
        text: '股票ID',
        left: 0
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'line'
        }
    },
    legend: {
        data: ['AR', 'BR', 'ATR']
    },
    grid: {
        left: '10%',
        right: '10%',
        bottom: '15%'
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
            y: '90%',
            start: 50,
            end: 100
        }
    ],
    series: [
        {
            name: 'AR',
            type: 'line',
            data: getColumn(1),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'BR',
            type: 'line',
            data: getColumn(2),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
        {
            name: 'ATR',
            type: 'line',
            data: getColumn(3),
            smooth: true,
            lineStyle: {
                normal: {opacity: 0.5}
            }
        },
    ]
};

Kchart.setOption(optionK);
ATRchart.setOption(optionATR);
