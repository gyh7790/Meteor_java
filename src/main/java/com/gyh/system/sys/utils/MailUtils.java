package com.gyh.system.sys.utils;

import com.gyh.common.exception.RRException;
import com.gyh.common.utils.SpringContext;
import com.gyh.system.sys.dto.MailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;

/**
 * @author gyh
 * @Date 2020/6/24 20:27
 */
@Component
public class MailUtils {

    private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送 简单邮件
     */
    public void sendSimpleMail(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailDto.getTo());
        message.setSubject(mailDto.getSubject());
        message.setText(mailDto.getContent());
        try {
            //简单邮件发送
            mailSender.send(message);
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            throw new RRException("发送简单邮件时发生异常！",e);
        }
    }

    /**
     * 发送 简单邮件
     */
    public void sendHtmlMail(MailDto mailDto){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailDto.getTo());
            helper.setSubject(mailDto.getSubject());
            helper.setText(mailDto.getContent(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            logger.error("发送HTML邮件时发生异常！", e);
            throw new RRException("发送HTML邮件时发生异常！",e);
        }
    }





    /**
     * 发送 邮件
     * @param to
     * @param subject
     * @param content
     * @param os
     * @param attachmentFilename
     * @throws Exception
     */
    public void sendMail(String to, String subject, String content, ByteArrayOutputStream os, String attachmentFilename) throws Exception {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            if (null != os) {
                //附件
                InputStreamSource inputStream = new ByteArrayResource(os.toByteArray());
                helper.addAttachment(attachmentFilename, inputStream);
            }

            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            throw new RRException("发送简单邮件时发生异常！",e);
        }
    }

}
