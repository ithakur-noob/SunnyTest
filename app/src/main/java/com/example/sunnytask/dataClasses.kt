package com.example.sunnytask

data class MsgList(
    val isActive: Boolean,
    val isFunnerUserDisabled: Boolean,
    val messages: List<Message>
)

data class Message(
    val DateCreate: String,
    val File: Any,
    val FileType: String,
    val GlobalUserId: String,
    val GlobalUserImage: Any,
    val GlobalUserName: String,
    val Id: Int,
    val IsEncrypted: Boolean,
    val IsForwarded: Boolean,
    val Message: String,
    val Object: Any,
    val Reply: Any,
    val Status: String,
    val Type: String,
    val reactions: Any
)