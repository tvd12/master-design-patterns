package com.tvd12.designparttern.composite;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BookControllerComposite {
	
	public static void main(String[] args) {
		EntityManager entityManager = new EntityManager();
		RequestToEntityConverter requestToEntityConverter =
				new RequestToEntityConverter();
		EntityToResponseConverter entityToResponseConverter =
				new EntityToResponseConverter();
		BookController bookController = new BookController(
				entityManager, 
				requestToEntityConverter, 
				entityToResponseConverter);
		BookRequest bookRequest = new BookRequest();
		bookRequest.setBookName("Design Patterns");
		bookRequest.setAuthor("youngmonkeys.org");
		bookRequest.setCategory("Technical");
		BookResponse bookResponse = bookController.addBook(bookRequest);
		System.out.println("add book response: " + bookResponse);
	}

	
	@AllArgsConstructor
	public static class BookController {
		
	    private EntityManager entityManager;
	    private RequestToEntityConverter requestToEntityConverter;
	    private EntityToResponseConverter entityToResponseConverter;
	    
	    public BookResponse addBook(BookRequest request) {
	        BookEntity bookEntity = requestToEntityConverter.bookRequestToEntity(request);
	        BookEntity savedBookEntity = entityManager.persist(bookEntity);
	        BookResponse bookResponse = entityToResponseConverter.bookEntityToResponse(savedBookEntity);
	        return bookResponse;
	    }
	}
	
	public static class RequestToEntityConverter {
	    public BookEntity bookRequestToEntity(BookRequest request) {
	        BookEntity bookEntity = new BookEntity();
	        bookEntity.setName(request.getBookName());
	        bookEntity.setAuthor(request.getAuthor());
	        bookEntity.setCategory(request.getCategory());
	        bookEntity.setCreatedDate(new Date());
	        return bookEntity;
	    }
	}
	
	public static class EntityToResponseConverter {
	    public BookResponse bookEntityToResponse(BookEntity entity) {
	        BookResponse bookResponse = new BookResponse();
	        bookResponse.setBookId(entity.getId());
	        bookResponse.setBookName(entity.getName());
	        bookResponse.setAuthor(entity.getAuthor());
	        bookResponse.setCategory(entity.getCategory());
	        return bookResponse;
	    }
	}
	
	public static class EntityManager {

		private final AtomicLong idGentor = new AtomicLong();
		
		public BookEntity persist(BookEntity bookEntity) {
			bookEntity.setId(idGentor.incrementAndGet());
			return bookEntity;
		}
		
	}

	@Data
	public static class BookEntity {
		private long id;
		private String name;
		private String author;
		private String category;
		private Date createdDate;
	}
	
	@Data
	public static class BookRequest {
		private String bookName;
		private String author;
		private String category;
	}
	
	@Data
	public static class BookResponse {
		private long bookId;
		private String bookName;
		private String author;
		private String category;
	}
	
}
