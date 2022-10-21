package com.cosmetics.cosmetics.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// Class
public class EmailDetails {
 
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
