package com.techeer.partners.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;

    private boolean isDeleted;

    public void delete() {
        this.isDeleted = true;
    }
}
