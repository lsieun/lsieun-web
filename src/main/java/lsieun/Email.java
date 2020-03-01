package lsieun;

import lsieun.utils.EmailUtils;

public class Email {
    public static void main(String[] args) {
        boolean flag = EmailUtils.send("插秧诗", "手把青秧插满田，低头便见水中天。心地清净方为道，退步原来是向前。");
        System.out.println(flag);
    }
}
