package com.pcodelight.joindesign.response

import com.pcodelight.joindesign.model.Meta
import com.pcodelight.joindesign.model.RawMaterial

class ListMaterialResponse : BaseResponse<List<RawMaterial>>() {
    var meta: Meta? = null
}