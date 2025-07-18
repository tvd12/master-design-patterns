package com.tvd12.master_design_patterns.validator.test;

import com.tvd12.master_design_patterns.request.AddAuthorRequest;
import com.tvd12.master_design_patterns.validator.AuthorValidator;
import org.testng.annotations.Test;

public class AuthorValidatorTest {

    @Test
    public void validateTest() {
        // given
        AuthorValidator instance = new AuthorValidator();

        AddAuthorRequest request = new AddAuthorRequest();
        request.setAuthorName("dung");
        request.setAge(10);

        // when
        instance.validate(request);

        // then
        // do nothing
    }
}
