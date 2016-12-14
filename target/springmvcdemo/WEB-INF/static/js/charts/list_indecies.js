/**
 * Created by asus on 2016/6/17.
 */

var indecies=[];
var xhr=new XMLHttpRequest();
xhr.open("get","/IndexTips",false);
xhr.send(null);
if(xhr.readyState==4&&xhr.status==200){
    var text=eval("(" + xhr.responseText + ")");
    for(var i=0;i<text.length;i++){
        indecies[i]=[];
        indecies[i][0]=text[i].id;
        indecies[i][1]=text[i].flu;
        indecies[i][2]=text[i].change;
    }
}