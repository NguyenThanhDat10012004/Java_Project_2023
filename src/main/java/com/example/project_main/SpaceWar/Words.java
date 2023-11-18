package com.example.project_main.SpaceWar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Words {
    private HashMap<String, String> wordList = new HashMap<String, String>();
    
    public Words() {
        wordList.put("follow", "theo đuổi");
        wordList.put("understand", "hiểu");
        wordList.put("Improve", "cải thiện");
        wordList.put("Communicate", "giao tiếp");
        wordList.put("Attend", "tham dự");
        wordList.put("Continue", "tiếp tục");
        wordList.put("Announce", "thông báo");
        wordList.put("Revise", "ôn tập");
        wordList.put("Enroll", "nhập học");
        wordList.put("Succeed", "thành công");
        wordList.put("Memorise", "học thuộc");
        wordList.put("Call", "gọi (ai, là gì)");
        wordList.put("Know", "biết");
        wordList.put("Tell", "kể");
        wordList.put("Spell", "đánh vần");
        wordList.put("Live", "sinh sống");
        wordList.put("Describe", "miêu tả");
        wordList.put("Sign", "ký tên");
        wordList.put("Open", "mở");
        wordList.put("Offer", "đề xuất");
        wordList.put("Design", "thiết kế");
        wordList.put("Prepare", "chuẩn bị");
        wordList.put("Arrange", "sắp xếp");
        wordList.put("Manage", "quản lý");
        wordList.put("Spend", "tiêu tiền");
        wordList.put("Save", "tiết kiệm");
        wordList.put("Borrow", "mượn");
        wordList.put("Owe", "nợ");
        wordList.put("Lend", "cho vay");
        wordList.put("Raise", "tăng");
        wordList.put("Decrease", "giảm");
        wordList.put("Throw", "ném");
        wordList.put("Lack", "thiếu");
        wordList.put("Build", "xây");
        wordList.put("Face", "hướng về");
        wordList.put("Decorate", "trang trí");
        wordList.put("Share", "chia sẻ");
        wordList.put("Inform", "báo tin");
        wordList.put("Develop", "phát triển");
        wordList.put("Invite", "mời");
        wordList.put("Book", "đặt chỗ");
        wordList.put("Escape", "trốn khỏi");
        wordList.put("Experience", "trải nghiệm");
        wordList.put("Discover", "khám phá");
        wordList.put("Try", "thử");
        wordList.put("Recommend", "đề xuất");
        wordList.put("Taste", "nếm thử");
        wordList.put("Hike", "đi bộ đường dài");
        wordList.put("Pray", "cầu nguyện");
        wordList.put("Party", "tiệc tùng");
        wordList.put("Welcome", "hoan nghênh");
        wordList.put("Tidy", "dọn dẹp");
        wordList.put("Exchange", "trao đổi");
        wordList.put("Expect", "mong đợi");
        wordList.put("Respect", "tôn trọng");
        wordList.put("Believe", "tin");
        wordList.put("Mean", "có ý định");
        wordList.put("Surprise", "làm bất ngờ");
        wordList.put("Control", "điều khiển");
        wordList.put("Avoid", "tránh");
        wordList.put("Persuade", "thuyết phục");
        wordList.put("Approach", "tiếp cận");
        wordList.put("Forecast", "dự báo");
        wordList.put("Last", "kéo dài");
        wordList.put("Boil", "luộc");
        wordList.put("Grill", "nướng");
        wordList.put("Prepare", "chuẩn bị");
        wordList.put("Stir-fry", "xào");
        wordList.put("Serve", "phục vụ");
        wordList.put("Pour", "rót");
        wordList.put("Stir", "khuấy");
        wordList.put("put", "thêm");
        wordList.put("Roast", "nướng bỏ lò");
        wordList.put("Follow", "tuân theo");
        wordList.put("Stay", "giữ nguyên");
        wordList.put("Snack", "ăn vặt");
        wordList.put("Breathe", "thở");
        wordList.put("Mix", "trộn");
        wordList.put("Spoil", "bị hỏng");
        wordList.put("Plant", "trồng");
        wordList.put("Harvest", "thu hoạch");
        wordList.put("Pickle", "muối chua");
        wordList.put("Explore", "khám phá");
        wordList.put("Grow", "lớn lên");
        wordList.put("Protect", "bảo vệ");
        wordList.put("Rely", "dựa dẫm");
        wordList.put("Surround", "bao quanh");
        wordList.put("Sunbathe", "tắm nắng");
        wordList.put("Care", "quan tâm");
        wordList.put("Exercise", "tập thể dục");
        wordList.put("Damage", "phá hỏng");
        wordList.put("Prevent", "phòng tránh");
        wordList.put("Remain", "giữ nguyên");
        wordList.put("Injure", "gây thương tích");
        wordList.put("Bleed", "chảy máu");
        wordList.put("Hurt", "làm bị đau");
        wordList.put("Suffer", "chịu đựng");
        wordList.put("Ease", "xoa dịu");
        wordList.put("Consider", "cân nhắc");
        wordList.put("Admit", "thừa nhận");
    }

    public HashMap<String, String> getWordList() {
        return wordList;
    }

    /** shoot English */
    public List<String> randomWords() {
        List<String> words = new ArrayList<String>(wordList.keySet());

        List<String> output = new ArrayList<String>();
        while (output.size() < 4) {
            int ran = new Random().nextInt(words.size());
            String tmp = words.get(ran);
            if (!output.contains(tmp)) {
                output.add(tmp);
            }
        }
        return output;
    }

    public String getMeaning(List<String> output) {
        int ran = new Random().nextInt(4);
        return wordList.get(output.get(ran));
    }
    
    /** shoot Vietnamese. */
    public List<String> randomMeanings() {
        List<String> meanings = new ArrayList<String>(wordList.values());

        List<String> output = new ArrayList<String>();
        while (output.size() < 4) {
            int ran = new Random().nextInt(meanings.size());
            String tmp = meanings.get(ran);
            if (!output.contains(tmp)) {
                output.add(tmp);
            }
        }
        return output;
    }

    public String getWord(List<String> output) {
        String meaning = "";
        int ran = new Random().nextInt(4);
        String value = output.get(ran);
        for (String word : wordList.keySet()) {
            if (value.equals(wordList.get(word))) {
                meaning =  word;
            } 
        }
        return meaning;
    }

    public boolean isRightWord(String English, String Vietnamese) {
        if (Vietnamese.equals(wordList.get(English))) {
            return true;
        }
        return false;
    }

    // public static void main(String[] args) {
    //     Words a = new Words();
    //     // List<String> output = a.randomMeanings();
    //     // System.out.println(output);
    //     // System.out.println(a.getWord(output));
    //     System.out.println(a.isRightWord("nợ","Owe"));
    // }
}
