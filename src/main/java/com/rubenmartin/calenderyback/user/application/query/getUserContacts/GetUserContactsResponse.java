package com.rubenmartin.calenderyback.user.application.query.getUserContacts;

import com.rubenmartin.calenderyback.message.domain.model.LastMessageDataModel;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Map;

@Data
@AllArgsConstructor
public class GetUserContactsResponse {
    private Page<User> contactPage;
    private Map<String, LastMessageDataModel> lastChatsMessage;

}
