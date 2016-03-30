package org.qishi.micromark.pojo;


/**
 * 子菜单
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:45:58 
 */
public class ComplexButton extends Button{
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

}
