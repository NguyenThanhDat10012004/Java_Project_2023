package com.example.project_main;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
public class VoiceRSS {
    public void speak(String speaker) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        Voice[] voicelist = VoiceManager.getInstance().getVoices();
        for(int i = 0;i < voicelist.length; i++) {
            System.out.println("# voice" + voicelist[i].getName());
        }
        if(voice != null) {
            voice.allocate();
            System.out.println("void rate " + voice.getRate());
            System.out.println("void pitch " + voice.getPitch());
            System.out.println("void volume " + voice.getVolume());
            boolean status = voice.speak(speaker);
            System.out.println("status " + status);
            voice.deallocate();
        }
        else System.out.println("error");
    }
}



