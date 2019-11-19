package com.mmn.account.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.LevelStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Level {
    // Id will be used for the invite of child
    @Id
    @GeneratedValue(generator="UUID")
    @GenericGenerator(
    		name="UUID",
    		strategy="org.hibernate.id.UUIDGenerator"
    		)
    private UUID id;
    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private Account parent;
    @ManyToOne
    @JoinColumn(name="CHILD_ID")
    private Account child;
    private LevelStatus status = LevelStatus.Inactive;
    private Integer score;
    private LocalDate activeDate;
    private String emailInvited;//same email account child.email
    
    public boolean isActive() {
    	return this.score.equals(LevelStatus.Active);
    }
    
}
