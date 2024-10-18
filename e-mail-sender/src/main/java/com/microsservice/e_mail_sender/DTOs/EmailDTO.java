package com.microsservice.e_mail_sender.DTOs;

import java.util.UUID;

public record EmailDTO(UUID userId,String emailTo,String subject,String text) {

}
