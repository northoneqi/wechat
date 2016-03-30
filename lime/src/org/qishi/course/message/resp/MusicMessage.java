package org.qishi.course.message.resp;

/**
 * 音乐消息
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:38:02 
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}