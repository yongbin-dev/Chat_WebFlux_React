package com.example.hello.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.status
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

//@Component
//class ReservationHandler (
//    @Autowired private val reservationService : ReservationService
//){
//    fun reserve(serverRequest: ServerRequest) : Mono<ServerResponse>{
//        val userId = serverRequest.headers().firstHeader("userId") ?: return status(HttpStatus.UNAUTHORIZED).build()
//        return reservationService.reserve(serverRequest.bodyToMono() , Mono.just(userId.toLong())).flatMap{
//            if (it) status(HttpStatus.NO_CONTENT).build()
//            else status(HttpStatus.FORBIDDEN).build()
//        }
//    }
//}