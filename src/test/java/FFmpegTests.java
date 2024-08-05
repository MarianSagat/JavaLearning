import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.bytedeco.ffmpeg.global.avformat.av_register_all;

public class FFmpegTests
{
    @Test
    public void basic()
    {
        FFmpegFrameGrabber  grabber = new FFmpegFrameGrabber("desktop");
        grabber.setFormat("gdigrab");
       // grabber.setFrameRate(30);

        try
        {
            grabber.start();
            Frame frame = grabber.grabImage();
            BufferedImage image = new Java2DFrameConverter().convert(frame);
            System.out.println("");

        }
        catch (FrameGrabber.Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
