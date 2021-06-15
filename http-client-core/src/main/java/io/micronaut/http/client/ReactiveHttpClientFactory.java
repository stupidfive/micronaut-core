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
package io.micronaut.http.client;

import io.micronaut.core.annotation.Nullable;
import java.net.URL;

/**
 * Factory interface for creating clients.
 *
 * @author graemerocher
 * @since 2.0
 * @param <ReactiveHttpClient> Reactive HTTP Client.
 * @param <ReactiveStreamingHttpClient> Reactive Streaming HTTP Client.
 */
public interface ReactiveHttpClientFactory<ReactiveHttpClient extends HttpClient, ReactiveStreamingHttpClient extends StreamingHttpClient> {
    /**
     * Create a new {@link HttpClient}. Note that this method should only be used outside of the context of an application. Within Micronaut use
     * {@link jakarta.inject.Inject} to inject a client instead
     *
     * @param url The base URL
     * @return The client
     */
    ReactiveHttpClient createClient(@Nullable URL url);

    /**
     * Create a new {@link HttpClient} with the specified configuration. Note that this method should only be used
     * outside of the context of an application. Within Micronaut use {@link jakarta.inject.Inject} to inject a client instead
     *
     * @param url The base URL
     * @param configuration the client configuration
     * @return The client
     * @since 2.2.0
     */
    ReactiveHttpClient createClient(@Nullable URL url, HttpClientConfiguration configuration);

    /**
     * Create a new {@link HttpClient}. Note that this method should only be used outside of the context of an application. Within Micronaut use
     * {@link jakarta.inject.Inject} to inject a client instead
     *
     * @param url The base URL
     * @return The client
     */
    ReactiveStreamingHttpClient createStreamingClient(@Nullable URL url);

    /**
     * Create a new {@link HttpClient} with the specified configuration. Note that this method should only be used
     * outside of the context of an application. Within Micronaut use {@link jakarta.inject.Inject} to inject a client instead
     *
     * @param url The base URL
     * @param configuration The client configuration
     * @return The client
     * @since 2.2.0
     */
    ReactiveStreamingHttpClient createStreamingClient(@Nullable URL url, HttpClientConfiguration configuration);
}
