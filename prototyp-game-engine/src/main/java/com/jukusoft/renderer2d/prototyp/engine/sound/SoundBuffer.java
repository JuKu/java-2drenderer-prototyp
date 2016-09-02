package com.jukusoft.renderer2d.prototyp.engine.sound;

import org.lwjgl.stb.STBVorbisInfo;

import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.*;

/**
 * Created by Justin on 30.08.2016.
 */
public class SoundBuffer {

    protected volatile int bufferID = 0;

    public SoundBuffer (String file) {
        //create new sound buffer
        this.create();

        //load sound from file
        this.load(file);
    }

    protected void create () {
        //generate new OpenAL buffer
        this.bufferID = alGenBuffers();
    }

    public void load (String file) {
        try (STBVorbisInfo info = STBVorbisInfo.malloc()) {
            //TODO: add code here

            ///ShortBuffer pcm = readVorbis(file, 32 * 1024, info);

            //copy to buffer
            //alBufferData(bufferID, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
        }
    }

    public int getBufferID () {
        return this.bufferID;
    }

    public void cleanUp () {
        //delete OpenAL sound buffer
        alDeleteBuffers(this.bufferID);
    }

}
