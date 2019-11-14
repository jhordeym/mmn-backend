package com.mmn.account.model;

import com.mmn.account.type.LevelStatus;
import org.springframework.data.annotation.Id;

public class Level {
    // Id will be used for the invite of child
    @Id
    String id;
    Account parent;
    Account child;
    LevelStatus status;
}
