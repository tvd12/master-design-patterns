var errorMessageByCode = {
    'required': 'Required',
    'invalid': 'Invalid',
    'tooShort': 'Too short',
    'tooLong': 'Too long',
    'overLength': 'Over length',
    'duplicated': 'Duplicated',
    'incorrect': 'Incorrect',
    'mismatch': 'Mismatch',
    'tooMany': 'Too many'
};

var getErrorMessageByCode = function(code) {
    var errorMessage = errorMessageByCode[code];
    return errorMessage || 'Invalid';
}

var formDataToObject = function(formName) {
    var object = {};
    var formData = new FormData(document.querySelector('#' + formName));
    formData.forEach((value, key) => {
        if(!Reflect.has(object, key)){
            object[key] = value;
            return;
        }
        if(!Array.isArray(object[key])){
            object[key] = [object[key]];
        }
        object[key].push(value);
    });
    return object;
}

var processGetApiErrors = function(responseData, handler) {
	processUpdateApiErrors({}, responseData, handler);
}

var processUpdateApiErrors = function(formData, responseData, handler) {
	if (responseData.status == 401) {
    	return;
	} else if (responseData.status == 404 && (!handler || !handler[404])) {
		return;
	} else if (responseData.status == 500 && (!handler || !handler[500])) {
		return;
	}
	var errors = responseData.responseJSON;
	if (errors) {
        Object.keys(errors).forEach((field) => {
            var error = errors[field];
            var errorText = $('#' + field + 'Error');
            if (errorText.length) {
                errorText.text(getErrorMessageByCode(error));
            }
            var errorLabel = $('#' + field + 'ErrorLabel');
            if (errorLabel.length) {
                errorLabel.removeClass('d-none');
            }
        });
	}
	Object.keys(formData).forEach((field) => {
		if (!errors[field]) {
			var errorLabel = $('#' + field + 'ErrorLabel');
			if (errorLabel.length) {
				errorLabel.addClass('d-none');
			}
		}
	});
	if (handler) {
		if (handler.postHandle) {
			handler.postHandle(responseData);
		} else {
			handler(responseData);
		}
	}
}

var hideFormErrors = function(formData) {
	Object.keys(formData).forEach((field) => {
		var errorLabel = $('#' + field + 'ErrorLabel');
		if (errorLabel.length) {
			errorLabel.addClass('d-none');
		}
	});
}

var resetFormData = function(formData) {
	hideFormErrors(formData);
	Object.keys(formData).forEach((field) => {
		var dataField = $('#' + field);
		if (dataField.length) {
			dataField.val('');
		}
	});
}
