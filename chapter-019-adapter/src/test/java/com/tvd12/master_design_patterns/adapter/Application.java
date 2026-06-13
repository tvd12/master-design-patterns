package com.tvd12.master_design_patterns.adapter;

public final class Application {

    public enum MediaType {
        AUDIO,
        VIDEO
    }

    public interface MediaPlayer {
        void play(MediaType mediaType, String fileName);
    }

    public static class AudioPlayer {

        public void playAudio(String fileName) {
            System.out.println("Play audio: " + fileName);
        }
    }

    public static class VideoPlayer {

        public void playVideo(String fileName) {
            System.out.println("Play video: " + fileName);
        }
    }

    public static class MediaAdapter implements MediaPlayer {
        private final AudioPlayer audioMediaPlayer =
            new AudioPlayer();
        private final VideoPlayer videoMediaPlayer =
            new VideoPlayer();

        @Override
        public void play(MediaType mediaType, String fileName) {
            if (mediaType == MediaType.AUDIO) {
                audioMediaPlayer.playAudio(fileName);
            }
            else {
                videoMediaPlayer.playVideo(fileName);
            }
        }
    }

    public static void main(String[] args) {
        final MediaPlayer mediaPlayer = new MediaAdapter();
        mediaPlayer.play(MediaType.AUDIO, "design.mp3");
        mediaPlayer.play(MediaType.VIDEO, "patterns.mp4");
    }
}
