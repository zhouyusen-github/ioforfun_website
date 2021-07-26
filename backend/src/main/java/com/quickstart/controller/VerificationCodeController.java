package com.quickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class VerificationCodeController {
    @RequestMapping("/verificationCode")
    public void getVerificationCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Not cached
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        // getVerificationCode
        String verificationCode = getVerificationCode();
        request.getSession().setAttribute("verificationCodeRecord",verificationCode);

        // draw
        int width = 90;
        int height = 40;
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0,0, width,height);
        graphics.setColor(Color.BLUE);
        graphics.setFont(new Font("default",Font.BOLD,30));
        graphics.drawString(verificationCode,10,28);

        // output
        ImageIO.write(bufferedImage,"JPG",response.getOutputStream());
    }

    /**
     *  return 4 random digit
     */
    private String getVerificationCode() {
        Random r = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=1;i<=4;i++){
            int digit = r.nextInt(10);
            stringBuffer.append(digit);
        }
        return String.valueOf(stringBuffer);
    }
}
