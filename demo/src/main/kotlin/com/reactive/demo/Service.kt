package com.reactive.demo

import org.springframework.stereotype.Service
import reactor.core.publisher.Sinks

@Service
class Service {

    val sink: Sinks.Many<Int> = Sinks.many().multicast().directBestEffort()

    fun emitNext(number: Int) {
      return  sink.emitNext(number, Sinks.EmitFailureHandler.FAIL_FAST)
    }

}
