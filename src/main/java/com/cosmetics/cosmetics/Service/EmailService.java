package com.cosmetics.cosmetics.Service;

import com.cosmetics.cosmetics.Model.Entity.EmailDetails;

//Interface
public interface EmailService {

 // Method
 // To send a simple email
 int sendSimpleMail(EmailDetails details);

 // Method
 // To send an email with attachment
 String sendMailWithAttachment(EmailDetails details);
}
