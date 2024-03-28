package com.example.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponseHandler<T> {
    private boolean Success;
    private String Message;
    private String Error;
    private T Data;
}
