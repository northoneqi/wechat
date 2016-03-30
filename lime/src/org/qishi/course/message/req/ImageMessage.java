package org.qishi.course.message.req;

/**
 * 图片消息
*
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:32:44 
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}