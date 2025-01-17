/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.http.util;

import io.micronaut.http.HttpMessage;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Optional;

/**
 * Utility methods for HTTP handling.
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public class HttpUtil {

    /**
     * Return whether the given request features {@link MediaType#APPLICATION_FORM_URLENCODED} or
     * {@link MediaType#MULTIPART_FORM_DATA}.
     *
     * @param request The request
     * @return True if it is form data
     */
    public static boolean isFormData(HttpRequest<?> request) {
        Optional<MediaType> opt = request.getContentType();
        if (opt.isPresent()) {
            MediaType contentType = opt.get();
            return (contentType.equals(MediaType.APPLICATION_FORM_URLENCODED_TYPE) || contentType.equals(MediaType.MULTIPART_FORM_DATA_TYPE));
        }
        return false;
    }

    /**
     * Resolve the {@link Charset} to use for the request.
     *
     * @param request The request
     * @return An {@link Optional} of {@link Charset}
     */
    @SuppressWarnings("Duplicates")
    public static Optional<Charset> resolveCharset(HttpMessage<?> request) {
        try {
            Optional<Charset> contentTypeCharset = request
                .getContentType()
                .map(contentType -> {
                    Optional<String> charset = contentType.getParameters().get(MediaType.CHARSET_PARAMETER);
                    if (charset.isPresent()) {
                        try {
                            return Charset.forName(charset.get());
                        } catch (Exception e) {
                            // unsupported charset, default to UTF-8
                            return StandardCharsets.UTF_8;
                        }
                    } else {
                        return null;
                    }
                });

            if (contentTypeCharset.isPresent()) {
                return contentTypeCharset;
            } else {
                return Optional.ofNullable(request.getHeaders().acceptCharset());
            }
        } catch (UnsupportedCharsetException e) {
            return Optional.empty();
        }
    }
}
