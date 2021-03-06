package com.jukusoft.renderer2d.prototyp.engine.shader;

import com.jukusoft.renderer2d.prototyp.engine.asset.Asset;

/**
 * Created by Justin on 24.08.2016.
 */
public class Shader extends Asset {

    /**
    * code of gpu shader
    */
    protected final String shaderCode;

    /**
    * default constructor
     *
     * @param shaderCode shader code
    */
    public Shader (String shaderCode) {
        this.shaderCode = shaderCode;
    }

    /**
    * get shader code
     *
     * @return gpu shader code as string
    */
    public String getShaderCode () {
        return this.shaderCode;
    }

}
