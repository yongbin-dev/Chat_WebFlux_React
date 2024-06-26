package com.example.hello.domain.chat.service

import com.example.hello.domain.chat.dto.ChatDTO
import com.example.hello.domain.chat.entity.Chat
import com.example.hello.domain.chat.entity.Room
import com.example.hello.domain.chat.repository.ChatRepository
import com.mongodb.client.result.UpdateResult
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.repository.Tailable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@Service
class ChatService @Autowired constructor(
    val mongoTemplate: ReactiveMongoTemplate ,
    val chatRepository : ChatRepository
){

    /**채팅 내역 불러오기 */
    fun findChatsByRoomId(roomId: ObjectId): Flux<Chat> {
        return chatRepository.findChatsByRoomId(roomId);
    }

    /**채팅 전송 */
    fun createChat(chat: Chat): Mono<Chat> {
        return mongoTemplate.insert(chat, "chat")
    }

    /**채팅방 마지막 채팅 시간 업데이트 */
    fun updateTime(roomId: ObjectId?): Mono<UpdateResult> {
        val query = Query(Criteria.where("_id").`is`(roomId))
        val update: Update = Update.update("updatedAt", LocalDateTime.now())

        return mongoTemplate.updateFirst(query, update, Room::class.java)
    }

    /**메세지 읽음 표시 */
    fun updateIsRead(roomId: ObjectId?, userId: Int?): Mono<UpdateResult> {
        val query = Query(
            Criteria.where("roomId").`is`(roomId)
                .and("senderId").ne(userId)
        )
        val update: Update = Update.update("isRead", true)

        return mongoTemplate.updateMulti(query, update, Chat::class.java)
    }
}