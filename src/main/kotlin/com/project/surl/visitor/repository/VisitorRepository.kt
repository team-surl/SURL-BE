package com.project.surl.visitor.repository

import com.project.surl.visitor.VisitorEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface VisitorRepository : CoroutineCrudRepository<VisitorEntity, String> {
}
