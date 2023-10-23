package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int dicountFixAmount = 1000;    // 1000원 할인

    @Override
    public int discount(Member member, int price) {
        // enum type은 == 써야함
        if (member.getGrade() == Grade.VIP) {
            return dicountFixAmount;
        } else {
            return 0;
        }
    }
}
