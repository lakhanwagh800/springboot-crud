package com.springboot.rest.entity;

public class APIResponse<T> {

	private Integer code;
    public void setCode(Integer code) {
		this.code = code;
	}

	private boolean success;
    private T data;
    private ApiResponseError error;

    public APIResponse(Integer code, boolean success, T data, ApiResponseError error) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public APIResponse() {

    }

    public Integer getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiResponseError getError() {
        return error;
    }

    public void setError(ApiResponseError error) {
        this.error = error;
    }

    public static class APIResponseBuilder<T> {
        private Integer code;
        private boolean success;
        private T data;
        private ApiResponseError error;

        public APIResponseBuilder setCode(Integer code) {
            this.code = code;
            return this;
        }

        public APIResponseBuilder setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public APIResponseBuilder setData(T data) {
            this.data = data;
            return this;
        }

        public APIResponseBuilder setError(ApiResponseError error) {
            this.error = error;
            return this;
        }

        public APIResponse createAPIResponse() {
            return new APIResponse(code, success, data, error);
        }
    }
}
