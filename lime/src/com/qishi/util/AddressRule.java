package com.qishi.util;

public class AddressRule {
	public String rule(String address){	
		if(address.equals("朝阳区")){
			return "3";
		}
		if(address.equals("东城区")){
			return "4";
		}
		if(address.equals("西城区")){
			return "5";
		}
		if(address.equals("丰台区")){
			return "6";
		}
		if(address.equals("石景山区")){
			return "7";
		}
		if(address.equals("海淀区")){
			return "8";
		}
		if(address.equals("门头沟区")){
			return "9";
		}
		if(address.equals("房山区")){
			return "10";
		}
		if(address.equals("通州区")){
			return "11";
		}
		if(address.equals("顺义区")){
			return "12";
		}
		if(address.equals("昌平区")){
			return "13";
		}
		if(address.equals("大兴区")){
			return "14";
		}
		if(address.equals("怀柔区")){
			return "15";
		}
		if(address.equals("平谷区")){
			return "16";
		}
		
		return "2";
		
	}

}
