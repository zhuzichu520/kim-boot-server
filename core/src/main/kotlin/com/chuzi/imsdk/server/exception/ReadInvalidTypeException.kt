package com.chuzi.imsdk.server.exception

class ReadInvalidTypeException(type: Byte) : RuntimeException("Read invalid tag : $type")

