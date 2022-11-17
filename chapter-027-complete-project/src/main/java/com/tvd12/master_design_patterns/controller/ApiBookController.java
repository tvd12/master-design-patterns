package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyfox.util.EzyFileUtil;
import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.resources.ResourceDownloadManager;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.*;
import com.tvd12.ezyhttp.server.core.handler.ResourceRequestHandler;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;
import com.tvd12.ezyhttp.server.core.resources.FileUploader;
import com.tvd12.master_design_patterns.converter.RequestToModelConverter;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.request.AddBookRequest;
import com.tvd12.master_design_patterns.service.AuthorService;
import com.tvd12.master_design_patterns.service.BookService;
import com.tvd12.master_design_patterns.validator.BookValidator;
import lombok.AllArgsConstructor;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.List;

@AllArgsConstructor
@Controller("/api/v1")
public class ApiBookController extends EzyLoggable {

    private final BookService bookService;
    private final BookValidator bookValidator;
    private final AuthorService authorService;
    private final FileUploader fileUploader;
    private final ResourceDownloadManager resourceDownloadManager;
    private final RequestToModelConverter requestToModelConverter;

    @DoGet("/books")
    public List<BookModel> getBooks() throws Exception {
        return bookService.getBooks();
    }

    @DoPost("/books/add")
    public ResponseEntity addBook(
        @RequestBody AddBookRequest request
    ) {
        bookValidator.validate(request);
        bookService.addBook(
            requestToModelConverter.toModel(request)
        );
        final long authorId = request.getAuthorId();
        final long authorBookCount = bookService
            .countBooksByAuthor(authorId);
        authorService.updateAuthorLevel(
            authorId,
            authorBookCount
        );
        return ResponseEntity.noContent();
    }

    @Async
    @DoPost("/books/images/add")
    public void addBookImage(
        HttpServletRequest request
    ) throws Exception {
        final Part part = request.getPart("file");
        final String fileName = part.getSubmittedFileName();
        final File file = new File("images/" + fileName);
        final AsyncContext asyncContext = request.getAsyncContext();
        fileUploader.accept(asyncContext, part, file, () ->
            logger.info("update file: {} done", fileName)
        );
    }

    @Async
    @DoGet("/books/images/{name}")
    public void getBookImage(
        RequestArguments requestArguments,
        @PathVariable String imageName
    ) throws Exception {
        final ResourceRequestHandler handler = new ResourceRequestHandler(
            "images/" + imageName,
            "images/" + imageName,
            EzyFileUtil.getFileExtension(imageName),
            resourceDownloadManager
        );
        handler.handle(requestArguments);
    }
}
