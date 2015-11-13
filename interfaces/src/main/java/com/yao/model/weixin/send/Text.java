package com.yao.model.weixin.send;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Text {
	@XStreamAlias("Content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
