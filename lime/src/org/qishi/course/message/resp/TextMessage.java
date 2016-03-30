package org.qishi.course.message.resp;

/**
 * 文本消息
 * 
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:38:38 
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}