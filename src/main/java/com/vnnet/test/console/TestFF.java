package com.vnnet.test.console;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TestFF {
    public static void main(String[] args) {
        try {
            FFmpeg ffmpeg = new FFmpeg("D:\\ffmpeg\\bin\\ffmpeg.exe");
            FFprobe ffprobe = new FFprobe("D:\\ffmpeg\\bin\\ffprobe.exe");
            FFmpegProbeResult probeResult = ffprobe.probe("C:\\Users\\trant\\Desktop\\TestNextCloud\\input.mp3");
            FFmpegBuilder builder = new FFmpegBuilder()

                    .setInput(probeResult)     // Filename, or a FFmpegProbeResult
                    .overrideOutputFiles(true) // Override the output if it exists
                    .addOutput("output.mp3")   // Filename for the destination
                    .setTargetSize(250_000)  // Aim for a 250KB file 250_000
                    .setAudioChannels(1)         // Mono audio
                    .setAudioSampleRate(48000)  // at 48KHz 48_000
                    .setAudioBitRate(65536)      // at 64 kbit/s
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

            // Run a one-pass encode
            executor.createJob(builder).run();

            // Or run a two-pass encode (which is better quality at the cost of being slower)
            executor.createTwoPassJob(builder).run();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
