package com.yao.model.weixin.send;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Voice {
	@XStreamAlias("MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
}
