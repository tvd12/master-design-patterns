package com.tvd.designparttern.adapter;

public class AdapterExample {
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();
		
		audioPlayer.play(AudioPlayer.TYPE_MP3, "bong_hoa_nho.mp3");
		audioPlayer.play(AdvancedMediaPlayer.TYPE_MP4, "bong_hoa_vua.mp4");
		audioPlayer.play(AdvancedMediaPlayer.TYPE_VLC, "bong_hoa_to.vlc");
		audioPlayer.play("wav", "bong_hoa_tihon.wav");
	}
}

interface MediaPlayer {
	public void play(String audioType, String fileName);
}

interface AdvancedMediaPlayer {
	public void playVLC(String fileName);

	public void playMP4(String fileName);
	
	public static final String TYPE_VLC	= "VLC";
	public static final String TYPE_MP4 = "MP4";
}

class MediaAdapter implements MediaPlayer {

	public MediaAdapter(String audioType) {
		if (audioType.equalsIgnoreCase(
				AdvancedMediaPlayer.TYPE_VLC)) {
			mAdvancedMusicPlayer = new VLCPlayer();

		} 
		else if (audioType.equalsIgnoreCase(
				AdvancedMediaPlayer.TYPE_MP4)) {
			mAdvancedMusicPlayer = new MP4Player();
		}
	}

	@Override
	public void play(String audioType, String fileName) {
		if (audioType.equalsIgnoreCase(
				AdvancedMediaPlayer.TYPE_VLC)) {
			mAdvancedMusicPlayer.playVLC(fileName);
		} 
		else if (audioType.equalsIgnoreCase(
				AdvancedMediaPlayer.TYPE_MP4)) {
			mAdvancedMusicPlayer.playMP4(fileName);
		}
	}

	private AdvancedMediaPlayer mAdvancedMusicPlayer;
}

class AudioPlayer implements MediaPlayer {

	@Override
	public void play(String audioType, String fileName) {
		// inbuilt support to play mp3 music files
		if (audioType.equalsIgnoreCase(
				AudioPlayer.TYPE_MP3)) {
			System.out.println("Playing MP3 file. Name: " + fileName);
		}

		// mediaAdapter is providing support to play other file formats
		else if (audioType.equalsIgnoreCase(
				AdvancedMediaPlayer.TYPE_VLC) 
				|| audioType.equalsIgnoreCase(
						AdvancedMediaPlayer.TYPE_MP4)) {
			mMediaPlayer = new MediaAdapter(audioType);
			mMediaPlayer.play(audioType, fileName);
		}

		else {
			System.out.println("Invalid media. " + audioType + " format not supported");
		}
	}

	private MediaPlayer mMediaPlayer;
	
	public static final String TYPE_MP3 = "MP3";

}

class VLCPlayer implements AdvancedMediaPlayer {

	@Override
	public void playVLC(String fileName) {
		System.out.println("Playing VLC file. Name: " + fileName);
	}

	@Override
	public void playMP4(String fileName) {

	}
}

class MP4Player implements AdvancedMediaPlayer {

	@Override
	public void playVLC(String fileName) {

	}

	@Override
	public void playMP4(String fileName) {
		System.out.println("Playing MP4 file. Name: " + fileName);
	}
}