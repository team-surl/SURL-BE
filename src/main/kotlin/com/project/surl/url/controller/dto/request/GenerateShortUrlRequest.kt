package com.project.surl.url.controller.dto.request

import jakarta.validation.constraints.Pattern

data class GenerateShortUrlRequest(
    @field:Pattern(regexp = "^(http|https):\\/\\/([\\w+?\\.\\w+])+([a-zA-Z0-9\\~\\!\\@\\#\\\$\\%\\^\\&\\*\\(\\)_\\-\\=\\+\\\\\\/\\?\\.\\:\\;\\'\\,]*)?")
    val url: String,
)
