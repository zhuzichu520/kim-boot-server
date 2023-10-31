package com.chuzi.kim.component.exception

class BizException(message: String?) : RuntimeException(message){
   var code:Int = 500
    constructor(code:Int,message: String?):this(message){
        this.code = code
    }
}