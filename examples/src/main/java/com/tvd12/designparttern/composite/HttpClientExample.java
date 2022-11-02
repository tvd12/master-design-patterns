package com.tvd12.designparttern.composite;

public class HttpClientExample {
	
	public static class HttpRequest {
		private String url;
		
		public HttpRequest(String url) {
			this.url = url;
		}
		
		public String getUrl() {
			return url;
		}
	}
	
	public static class HttpResponse {
		public String data;
		
		public HttpResponse(String data) {
			this.data = data;
		}
		
		public String getData() {
			return data;
		}
	}
	
	public static class HttpClient {
		private HttpExecutor getter = new HttpGetter();
		private HttpExecutor poster = new HttpPoster();
		private HttpExecutor updater = new HttpUpdater();
		private HttpExecutor deleter = new HttpDeleter();
		
		public HttpResponse get(HttpRequest request) {
			return getter.execute(request);
		}
		
		public HttpResponse post(HttpRequest request) {
			return poster.execute(request);
		}
		
		public HttpResponse put(HttpRequest request) {
			return updater.execute(request);
		}
		
		public HttpResponse delete(HttpRequest request) {
			return deleter.execute(request);
		}
	}
	
	private static interface HttpExecutor {
		HttpResponse execute(HttpRequest request);
	}
	
	private static class HttpGetter implements HttpExecutor {
		@Override
		public HttpResponse execute(HttpRequest request) {
			return new HttpResponse("GET Response from: " + request.getUrl());
		}
	}
	
	private static class HttpPoster implements HttpExecutor {
		@Override
		public HttpResponse execute(HttpRequest request) {
			return new HttpResponse("POST Response from: " + request.getUrl());
		}
	}
	
	private static class HttpUpdater implements HttpExecutor {
		@Override
		public HttpResponse execute(HttpRequest request) {
			return new HttpResponse("PUT Response from: " + request.getUrl());
		}
	}
	
	private static class HttpDeleter implements HttpExecutor {
		@Override
		public HttpResponse execute(HttpRequest request) {
			return new HttpResponse("DELETE Response from: " + request.getUrl());
		}
	}
}
