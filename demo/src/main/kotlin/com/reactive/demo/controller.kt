package com.reactive.demo

import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
class Controller(val service: Service) {

    @PutMapping("/test/{number}")
    fun update(@PathVariable number: Int) {
service.emitNext(number)
    }

    @GetMapping(produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun eventFlux(): Flux<ServerSentEvent<Int>>? {
        return service.sink.asFlux().map { e -> ServerSentEvent.builder(e).build() }
    }
}
