package com.tvd12.designparttern.adapter;

public class AdapterExample {
	public static void main(String[] args) {
		MediaPlayer audioPlayer = new AudioPlayer();
		
		audioPlayer.play(MediaType.MP3, "bong_hoa_nho.mp3");
		audioPlayer.play(MediaType.MP4, "bong_hoa_vua.mp4");
		audioPlayer.play(MediaType.VLC, "bong_hoa_to.vlc");
	}
}

enum MediaType {
	MP3,
	MP4,
	VLC
}

interface MediaPlayer {
	public void play(MediaType mediaType, String fileName);
}

interface VideoMediaPlayer {
	
	public void playVideo(String fileName);
}

class MediaAdapter implements MediaPlayer {

	private VideoMediaPlayer vlcMediaPlayer = new VLCPVideoPlayer();
	private VideoMediaPlayer mp4MediaPlayer = new MP4VideoPlayer();
	

	@Override
	public void play(MediaType mediaType, String fileName) {
		if (mediaType == MediaType.VLC) {
			vlcMediaPlayer.playVideo(fileName);
		} 
		else {
			mp4MediaPlayer.playVideo(fileName);
		}
	}
}

class AudioPlayer implements MediaPlayer {

	private final MediaAdapter mediaAdapter = new MediaAdapter();
	
	@Override
	public void play(MediaType mediaType, String fileName) {
		if (mediaType == MediaType.MP3) {
			System.out.println("Playing MP3 file. Name: " + fileName);
		}
		else {
			mediaAdapter.play(mediaType, fileName);
		}
	}
}

class VLCPVideoPlayer implements VideoMediaPlayer {

	@Override
	public void playVideo(String fileName) {
		System.out.println("Playing VLC file. Name: " + fileName);
	}
}

class MP4VideoPlayer implements VideoMediaPlayer {

	@Override
	public void playVideo(String fileName) {
		System.out.println("Playing MP4 file. Name: " + fileName);
	}
}