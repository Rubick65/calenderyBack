package com.rubenmartin.calenderyback.message.infrastructure.database;

import com.rubenmartin.calenderyback.message.infrastructure.database.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJPARepository extends JpaRepository<MessageEntity, Long> {

}
