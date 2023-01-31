package facade;

import java.io.File;
import some_complex_media_library.AudioMixer;
import some_complex_media_library.BitrateReader;
import some_complex_media_library.Codec;
import some_complex_media_library.CodecFactory;
import some_complex_media_library.MPEG4CompressionCodec;
import some_complex_media_library.OggCompressionCodec;
import some_complex_media_library.VideoFile;

public class VideoConversionFacade {
	public File convertVideo(String fileName, String format) {
		System.out.println("VideoConversionFacade: conversion started.");
		VideoFile file = new VideoFile(fileName);
		Codec sourceCodec = CodecFactory.extract(file);
		Codec destinationCodec;
		if (format.equals("mp4")) {
			destinationCodec = new MPEG4CompressionCodec();
		} else {
			destinationCodec = new OggCompressionCodec();
		}
		VideoFile buffer = BitrateReader.read(file, sourceCodec);
		VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
		File result = (new AudioMixer()).fix(intermediateResult);
		System.out.println("VideoConversionFacade: conversion completed.");
		return result;
	}
}
