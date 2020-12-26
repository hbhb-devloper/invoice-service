package com.hbhb.cw.invoice.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbhb.springboot.web.view.ResponseVo;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * 全局响应参数处理类
 *
 * @author xiaokang
 * @since 2020-07-27
 */
@ControllerAdvice(annotations = RestController.class)
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class ResponseAdviceHandler implements ResponseBodyAdvice<Object> {

    private final ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annoys = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            PutMapping.class,
            DeleteMapping.class
    };

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annoys).anyMatch(annoy -> annoy.isAnnotation() && element.isAnnotationPresent(annoy));
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  MethodParameter returnType,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        Object out;
        ObjectMapper mapper = mapperThreadLocal.get();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (body instanceof ResponseVo) {
            out = body;
        } else if (body instanceof String) {
            ResponseVo result = new ResponseVo<>(body);
            try {
                out = mapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                out = ResponseVo.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            }
        } else {
            out = new ResponseVo<>(body);
        }
        return out;
    }
}
