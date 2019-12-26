package com.kweku.andradio.mappers

import com.kweku.andradio.models.CodecTypeEntity
import com.kweku.andradio.domain.models.CodecType
import com.kweku.andradio.domain.repository.ModelMapper

class CodecTypeEntityMapper:ModelMapper<CodecType, CodecTypeEntity> {
    override fun fromEntity(from: CodecTypeEntity): CodecType {
        return CodecType(from.codec,
        from.stationCount)
    }

    override fun toEntity(from: CodecType): CodecTypeEntity {
        return CodecTypeEntity(
            from.codec,
            from.stationCount
        )
    }
}