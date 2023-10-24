package com.chuzi.kim.mvc.request

import com.chuzi.kim.annotation.CreateAction
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Schema(description = "单人通话ice、offer、answer同步请求体")
class WebrtcRequest : Serializable {

    @NotNull(message = "UID不能为空", groups = [CreateAction::class])
    @Schema(description = "对方UID")
    var uid: String? = null

    @NotEmpty(message = "content不能超过2000个字符", groups = [CreateAction::class])
    @Schema(description = "ice信息json、offer或者answer的sdp")
    var content: String? = null

}

