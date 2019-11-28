package com.mmn.account.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.model.type.LevelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Level {
    // Id will be used for the invite of child
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(length=36)
    private String id;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Account parent;
    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Account child;
    @Builder.Default
    private LevelStatus status = LevelStatus.Inactive;
    private Integer score;
    private LocalDate activeDate;
    private String emailInvited;//same to account child.to

    public boolean isActive() {
        return this.score.equals(LevelStatus.Active);
    }

}
