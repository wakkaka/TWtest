public class BowlingGame {
	public String items[];

	public int trans(int i, boolean flag) {
		boolean oFlag = flag; //是否只返回第一位数

		if( items[i].indexOf('X') > -1) {
				//strike
				if(oFlag) {
					if(items[i].substring(1) == "-") {
						return 0;
					} else {
						return Integer.parseInt(items[i].substring(1));
					}
				} else {
					return 10;	
				}			
			} else if (items[i].indexOf("/") > -1) {
				//spare
				if(oFlag) {
					return Integer.parseInt(items[i].substring(0,1));
				} else {
					return 10;
				}
			} else if (items[i].indexOf("-") > -1) {
				//miss
				if(oFlag) {
					return items[i].indexOf("-") == 0 ? 0 : Integer.parseInt(items[i].substring(0,1));
				} else {
					return items[i].indexOf("-") == 0 ? Integer.parseInt(items[i].substring(1)) : Integer.parseInt(items[i].substring(0,1));
				}
			} else {
				if(oFlag) {
					return Integer.parseInt(items[i].substring(0,1));
				} else {
					return (Integer.parseInt(items[i].substring(0,1)) + Integer.parseInt(items[i].substring(1)) );
				}
				
			}
	}


    public  int getBowlingScore(String bowlingCode) {
		int sum = 0;
    	items = bowlingCode.split("\\|");
		if (items.length>10) {
			items[10]=items[11];
		}

		for(int i=0;i<9;i++) {
			if( items[i].indexOf("X") > -1) {
				//strike
				sum += 10;
				if(items[i+1].indexOf("X") > -1) {
					//第二位也是 X
					sum += 10;
					if(items[i+2].indexOf("X")>-1) {
						//第三位也是 X
						sum += 10;
					} else {
						//第三位是其他
						sum += trans( i+2, true );
					}
				} else {
					//第二位是其他
					sum += trans( i+1, false);
				}
 
			} else if (items[i].indexOf("/") > -1) {
				//spare
				sum += 10;
				if(items[i+1].indexOf("X")>-1) {
					//下一位是 X
					sum += 10;
				} else {
					//下一位是其他
					sum += trans( i+1, true );
				}
				
			} else{
				//miss or normal
				sum += trans(i, false);
			}
		}
		if(items[9].equals("X")) {
			//最后一次为 strike
			sum += 10;
			if(items[10].substring(0,1).equals("X")) {
				//额外机会为 X_
				sum += 10;
				if(items[10].substring(1).equals("X")) {
					//额外机会为 XX
					sum += 10;
				} else {
					//额外机会为 X?
					sum += trans( 10, true );
				}
			} else {
				//额外机会为 __				
				if(items[10].substring(1).equals("X")) {
					//额外机会为 _X
					sum += Integer.parseInt(items[10].substring(0,1));
					sum += 10;
				} else {
					//额外机会为 _?
					sum += trans( 10, false );
				}
			}
		} else if (items[9].indexOf('/') > -1) {
			//最后一次为spare
			sum += 10;
			sum += trans( 10, true );
			
		} else {
			//normal
			sum += trans(9,false);
		}
        return sum;
    }
}
