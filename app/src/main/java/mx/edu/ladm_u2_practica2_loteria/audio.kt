package mx.edu.ladm_u2_practica2_loteria

import android.media.MediaPlayer

class audio(este:MainActivity,recurso:Int) {
    val este = este
    val rec = recurso
    init{

    }
    fun play(){MediaPlayer.create(este,rec).start() }
}//class