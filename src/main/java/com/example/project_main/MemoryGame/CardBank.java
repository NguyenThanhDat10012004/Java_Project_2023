package com.example.project_main.MemoryGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardBank {
    List<Card> deck;

    public CardBank() {

        this.deck = new ArrayList<Card>();

        deck.add(new Card("follow", "theo đuổi"));
        deck.add(new Card("understand", "hiểu"));
        deck.add(new Card("Improve", "cải thiện"));
        deck.add(new Card("Communicate", "giao tiếp"));
        deck.add(new Card("Attend", "tham dự"));
        deck.add(new Card("Continue", "tiếp tục"));
        deck.add(new Card("Announce", "thông báo"));
        deck.add(new Card("Revise", "ôn tập"));
        deck.add(new Card("Enroll", "nhập học"));
        deck.add(new Card("Succeed", "thành công"));
        deck.add(new Card("Memorise", "học thuộc"));
        deck.add(new Card("Call", "gọi (ai, là gì)"));
        deck.add(new Card("Know", "biết"));
        deck.add(new Card("Tell", "kể"));
        deck.add(new Card("Spell", "đánh vần"));
        deck.add(new Card("Live", "sinh sống"));
        deck.add(new Card("Describe", "miêu tả"));
        deck.add(new Card("Sign", "ký tên"));
        deck.add(new Card("Open", "mở"));
        deck.add(new Card("Offer", "đề xuất"));
        deck.add(new Card("Design", "thiết kế"));
        deck.add(new Card("Prepare", "chuẩn bị"));
        deck.add(new Card("Arrange", "sắp xếp"));
        deck.add(new Card("Manage", "quản lý"));
        deck.add(new Card("Spend", "tiêu tiền"));
        deck.add(new Card("Save", "tiết kiệm"));
        deck.add(new Card("Borrow", "mượn"));
        deck.add(new Card("Owe", "nợ"));
        deck.add(new Card("Lend", "cho vay"));
        deck.add(new Card("Raise", "tăng"));
        deck.add(new Card("Decrease", "giảm"));
        deck.add(new Card("Throw", "ném"));
        deck.add(new Card("Lack", "thiếu"));
        deck.add(new Card("Build", "xây"));
        deck.add(new Card("Face", "hướng về"));
        deck.add(new Card("Decorate", "trang trí"));
        deck.add(new Card("Share", "chia sẻ"));
        deck.add(new Card("Inform", "báo tin"));
        deck.add(new Card("Develop", "phát triển"));
        deck.add(new Card("Invite", "mời"));
        deck.add(new Card("Book", "đặt chỗ"));
        deck.add(new Card("Escape", "trốn khỏi"));
        deck.add(new Card("Experience", "trải nghiệm"));
        deck.add(new Card("Discover", "khám phá"));
        deck.add(new Card("Try", "thử"));
        deck.add(new Card("Recommend", "đề xuất"));
        deck.add(new Card("Taste", "nếm thử"));
        deck.add(new Card("Hike", "đi bộ đường dài"));
        deck.add(new Card("Pray", "cầu nguyện"));
        deck.add(new Card("Party", "tiệc tùng"));
        deck.add(new Card("Welcome", "hoan nghênh"));
        deck.add(new Card("Tidy", "dọn dẹp"));
        deck.add(new Card("Exchange", "trao đổi"));
        deck.add(new Card("Expect", "mong đợi"));
        deck.add(new Card("Respect", "tôn trọng"));
        deck.add(new Card("Believe", "tin"));
        deck.add(new Card("Mean", "có ý định"));
        deck.add(new Card("Surprise", "làm bất ngờ"));
        deck.add(new Card("Control", "điều khiển"));
        deck.add(new Card("Avoid", "tránh"));
        deck.add(new Card("Persuade", "thuyết phục"));
        deck.add(new Card("Approach", "tiếp cận"));
        deck.add(new Card("Forecast", "dự báo"));
        deck.add(new Card("Last", "kéo dài"));
        deck.add(new Card("Boil", "luộc"));
        deck.add(new Card("Grill", "nướng"));
        deck.add(new Card("Prepare", "chuẩn bị"));
        deck.add(new Card("Stir-fry", "xào"));
        deck.add(new Card("Serve", "phục vụ"));
        deck.add(new Card("Pour", "rót"));
        deck.add(new Card("Stir", "khuấy"));
        deck.add(new Card("Add", "thêm"));
        deck.add(new Card("Roast", "nướng bỏ lò"));
        deck.add(new Card("Follow", "tuân theo"));
        deck.add(new Card("Stay", "giữ nguyên"));
        deck.add(new Card("Snack", "ăn vặt"));
        deck.add(new Card("Breathe", "thở"));
        deck.add(new Card("Mix", "trộn"));
        deck.add(new Card("Spoil", "bị hỏng"));
        deck.add(new Card("Plant", "trồng"));
        deck.add(new Card("Harvest", "thu hoạch"));
        deck.add(new Card("Pickle", "muối chua"));
        deck.add(new Card("Explore", "khám phá"));
        deck.add(new Card("Grow", "lớn lên"));
        deck.add(new Card("Protect", "bảo vệ"));
        deck.add(new Card("Rely", "dựa dẫm"));
        deck.add(new Card("Surround", "bao quanh"));
        deck.add(new Card("Sunbathe", "tắm nắng"));
        deck.add(new Card("Care", "quan tâm"));
        deck.add(new Card("Exercise", "tập thể dục"));
        deck.add(new Card("Damage", "phá hỏng"));
        deck.add(new Card("Prevent", "phòng tránh"));
        deck.add(new Card("Remain", "giữ nguyên"));
        deck.add(new Card("Injure", "gây thương tích"));
        deck.add(new Card("Bleed", "chảy máu"));
        deck.add(new Card("Hurt", "làm bị đau"));
        deck.add(new Card("Suffer", "chịu đựng"));
        deck.add(new Card("Ease", "xoa dịu"));
        deck.add(new Card("Consider", "cân nhắc"));
        deck.add(new Card("Admit", "thừa nhận"));
    }

    public void tronBai() {
        Collections.shuffle(deck);
    }

    public Card layBai() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        } else return null;
    }
}
