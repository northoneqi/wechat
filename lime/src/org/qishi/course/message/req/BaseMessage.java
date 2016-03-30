package org.qishi.course.message.req;

/**
 * 娑堟伅鍩虹被锛堟櫘閫氱敤鎴�-> 鍏紬甯愬彿锛�
*
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:32:09 
 */
public class BaseMessage {
	// 寮�彂鑰呭井淇″彿
	private String ToUserName;
	// 鍙戦�鏂瑰笎鍙凤紙涓�釜OpenID锛�
	private String FromUserName;
	// 娑堟伅鍒涘缓鏃堕棿 锛堟暣鍨嬶級
	private long CreateTime;
	// 娑堟伅绫诲瀷锛坱ext/image/location/link锛�
	private String MsgType;
	// 娑堟伅id锛�4浣嶆暣鍨�
	private long MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
}