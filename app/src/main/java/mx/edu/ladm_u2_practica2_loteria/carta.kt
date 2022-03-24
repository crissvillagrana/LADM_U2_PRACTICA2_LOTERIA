package mx.edu.ladm_u2_practica2_loteria

class carta(l:MainActivity, recurso:Int){
    val l = l
    val rec = recurso

    init{

    }
    fun pintar(){l.binding.imagen.setImageResource(rec)}
}//class