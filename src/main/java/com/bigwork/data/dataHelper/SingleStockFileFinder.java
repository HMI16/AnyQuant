package com.bigwork.data.dataHelper;


public class SingleStockFileFinder {

    public String getFilePath(String id) {

        String path = "";
        switch (id) {
            case "sz002644":
                path = "file" + "/" + "SingleStockOne.txt";
                break;
            case "sz002664":
                path = "file" + "/" + "SingleStockTwo.txt";
                break;
            case "sz000908":
                path = "file" + "/" + "SingleStockThree.txt";
                break;
            case "sh600216":
                path = "file" + "/" + "SingleStockFour.txt";
                break;
            case "sh600979":
                path = "file" + "/" + "SingleStockFive.txt";
                break;
            case "sh600724":
                path = "file" + "/" + "SingleStockSix.txt";
                break;
            case "sz300137":
                path = "file" + "/" + "SingleStockSeven.txt";
                break;
            case "sh600129":
                path = "file" + "/" + "SingleStockEight.txt";
                break;
            case "sz002569":
                path = "file" + "/" + "SingleStockNine.txt";
                break;

        }

        return path;
    }

//	public static void main(String[] args){
//		SingleStockFileFinder test = new SingleStockFileFinder();
//		System.out.println(test.getFilePath("sh600979"));
//	}

}
