package com.tvd12.designparttern.decorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTMLGenerator {

	public static void main(String[] args) {
		View view = new ViewBuilder()
				.addDecorator(new ViewTemplateDecorator())
				.addDecorator(new ViewDataDecorator())
				.build();
		System.out.println(view.getHtml());
	}
	
	public static class View {
		private final String html;
		
		public View(String html) {
			this.html = html;
		}
		
		public String getHtml() {
			return this.html;
		}
	}
	
	public static class ViewBuilder {
		private String html;
		private List<ViewDecorator> decorators = new ArrayList<>();
		
		public void setHtml(String html) {
			this.html = html;
		}
		
		public String getHtml() {
			return html;
		}
		
		public ViewBuilder addDecorator(ViewDecorator decorator) {
			this.decorators.add(decorator);
			return this;
		}
		
		public View build() {
			for(ViewDecorator decorator : decorators) {
				decorator.decorate(this);
			}
			return new View(html);
		}
	}
	
	public static interface ViewDecorator {
		
		void decorate(ViewBuilder view); 
	}
	
	private static class ViewTemplateDecorator implements ViewDecorator {
		private String template = 
				"Chào mừng ${user} đến với ${myURL}\n"
				+ "Hôm nay chúng tôi có ${numberOfEvents} sự kiện dành cho bạn\n"
				+ "Nội dung được cung cấp bởi ${myURL}";
		@Override
		public void decorate(ViewBuilder view) {
			view.setHtml(template);
		}
	}
	
	private static class ViewDataDecorator implements ViewDecorator {
		@Override
		public void decorate(ViewBuilder view) {
			String html = view.getHtml();
			Map<String, Object> data = getViewData();
			for(String key : data.keySet()) {
				html = html.replace("${" + key + "}", data.get(key).toString());
			}
			view.setHtml(html);
		}
		
		private Map<String, Object> getViewData() {
			Map<String, Object> data = new HashMap<>();
			data.put("user", "Mr.Young");
			data.put("myURL", "youngmonkeys.org");
			data.put("numberOfEvents", 3000);
			return data;
		}
	}
	
}
