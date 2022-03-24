package mx.edu.ladm_u2_practica2_loteria

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.media.MediaPlayer
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HiloCarta (este:MainActivity) : Thread(){//class
    private var es = este
    private var ejecutar = true
    private var pausar = false
    override fun run() {
        super.run()
        while(ejecutar){
            es.runOnUiThread {
                if(!pausar){
                    if(es.x>0){
                        es.binding.txtcontaPasa.text = "Cartas pasadas: ${es.x}"
                        es.binding.txtcontaResta.text = "Cartas restantes: ${54-es.x}"
                    }
                }
            }
            sleep(1000)
        }
    }//run

    fun pausar(){pausar=true}
    fun reanudar(){pausar=false}
    fun terminar(){ejecutar=false}
    fun estado():Boolean{return pausar}
}//class