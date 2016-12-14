package com.bigwork.bl.managementServiceImpl;

import com.bigwork.bl_service.BIAS_service;
import com.bigwork.bl_service.VR_service;
import com.bigwork.bl_service.pR_service;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/19.
 */
public class SingleAnalyze_Impl {
    private pR_service pr = new pR_Impl();
    private VR_service vr = new VR_Impl();
    private BIAS_service bias = new BIAS_Impl();
    public ArrayList<String> getSingleAnalyze(String id, String date) {
        ArrayList<String> result = new ArrayList<>();
        double prNUM = pr.getpR(id, date);
        double vrNUM = vr.getVR(id, date);
        double biasNUM = bias.getBIAS(id,12,date);
        String prStr = "";
        String vrStr = "";
        String biasStr = "";
        if(prNUM > 80){
            prStr = "%R较大，市场处于超卖状况，股价走势随时可能见底,投资者在此可以伺机买入";
        }else if(prNUM < 20){
            prStr = "%R处于正常范围，市场处于超买状况，走势可能即将见顶,投资者在此可以伺机卖出";
        }else{
            prStr = "%R较小，市场处于正常状态，走势较为平稳，可以持股观望";
        }
        result.add(prStr);

        if((vrNUM > 40)&&(vrNUM < 70)){
            vrStr = "VR较小，低价区域,可以买进";
            result.add(vrStr);
        }else if((vrNUM >= 80)&&(vrNUM <= 150)){
            vrStr = "VR正常，安全区域,持有股票";
            result.add(vrStr);
        }else if((vrNUM >= 160)&&(vrNUM <= 450)){
            vrStr = "VR较大，获利区域,根据情况获利了结";
            result.add(vrStr);
        }else if(vrNUM >= 450){
            vrStr = "VR过大，警戒区域,伺机卖出";
            result.add(vrStr);
        }

        if(biasNUM > 0.06){
            biasStr = "偏离过大，建议卖出";
            result.add(biasStr);
        }else if(biasNUM < -0.06){
            biasStr = "偏离过大，可以买入";
            result.add(biasStr);
        }
        return result;
    }


    public static void main(String args[]){
        SingleAnalyze_Impl a = new SingleAnalyze_Impl();
        ArrayList<String> str = a.getSingleAnalyze("sh600000","2016-06-15");
        System.out.println(str);
    }
}
