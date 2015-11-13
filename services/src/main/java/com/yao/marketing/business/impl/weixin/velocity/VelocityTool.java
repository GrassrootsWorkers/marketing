package com.yao.marketing.business.impl.weixin.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityTool {

	public static String getTemplateContent(String templatePath,VelocityContext context, String basePath) throws Exception{
		Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,basePath);
		Velocity.setProperty(Velocity.ENCODING_DEFAULT, "GBK");
		Velocity.setProperty(Velocity.INPUT_ENCODING, "GBK");
		Velocity.setProperty(Velocity.OUTPUT_ENCODING, "GBK");
		Velocity.init();
		Template vt = Velocity.getTemplate(templatePath);
		StringWriter w = new StringWriter();
		vt.merge(context, w);
		return w.toString();
	}
}
