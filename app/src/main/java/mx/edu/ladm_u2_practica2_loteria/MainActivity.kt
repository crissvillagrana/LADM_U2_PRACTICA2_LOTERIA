package mx.edu.ladm_u2_practica2_loteria

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import mx.edu.ladm_u2_practica2_loteria.databinding.ActivityMainBinding
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val cartas = arrayOf(
        carta(this,R.drawable.carta1),
        carta(this,R.drawable.carta2),
        carta(this,R.drawable.carta3),
        carta(this,R.drawable.carta4),
        carta(this,R.drawable.carta5),
        carta(this,R.drawable.carta6),
        carta(this,R.drawable.carta7),
        carta(this,R.drawable.carta8),
        carta(this,R.drawable.carta9),
        carta(this,R.drawable.carta10),
        carta(this,R.drawable.carta11),
        carta(this,R.drawable.carta12),
        carta(this,R.drawable.carta13),
        carta(this,R.drawable.carta14),
        carta(this,R.drawable.carta15),
        carta(this,R.drawable.carta16),
        carta(this,R.drawable.carta17),
        carta(this,R.drawable.carta18),
        carta(this,R.drawable.carta19),
        carta(this,R.drawable.carta20),
        carta(this,R.drawable.carta21),
        carta(this,R.drawable.carta22),
        carta(this,R.drawable.carta23),
        carta(this,R.drawable.carta24),
        carta(this,R.drawable.carta25),
        carta(this,R.drawable.carta26),
        carta(this,R.drawable.carta27),
        carta(this,R.drawable.carta28),
        carta(this,R.drawable.carta29),
        carta(this,R.drawable.carta30),
        carta(this,R.drawable.carta31),
        carta(this,R.drawable.carta32),
        carta(this,R.drawable.carta33),
        carta(this,R.drawable.carta34),
        carta(this,R.drawable.carta35),
        carta(this,R.drawable.carta36),
        carta(this,R.drawable.carta37),
        carta(this,R.drawable.carta38),
        carta(this,R.drawable.carta39),
        carta(this,R.drawable.carta40),
        carta(this,R.drawable.carta41),
        carta(this,R.drawable.carta42),
        carta(this,R.drawable.carta43),
        carta(this,R.drawable.carta44),
        carta(this,R.drawable.carta45),
        carta(this,R.drawable.carta46),
        carta(this,R.drawable.carta47),
        carta(this,R.drawable.carta48),
        carta(this,R.drawable.carta49),
        carta(this,R.drawable.carta50),
        carta(this,R.drawable.carta51),
        carta(this,R.drawable.carta52),
        carta(this,R.drawable.carta53),
        carta(this,R.drawable.carta54)
    )
    var iniciado = false
    val songs = arrayOf(
        audio(this,R.raw.gallo1),
        audio(this,R.raw.diablo2),
        audio(this,R.raw.dama3),
        audio(this,R.raw.catrin4),
        audio(this,R.raw.paraguas5),
        audio(this,R.raw.sirena6),
        audio(this,R.raw.escalera7),
        audio(this,R.raw.botella8),
        audio(this,R.raw.barril9),
        audio(this,R.raw.arbol10),
        audio(this,R.raw.melon11),
        audio(this,R.raw.valiente12),
        audio(this,R.raw.gorrito13),
        audio(this,R.raw.muerte14),
        audio(this,R.raw.pera15),
        audio(this,R.raw.bandera16),
        audio(this,R.raw.bandolon17),
        audio(this,R.raw.violoncello18),
        audio(this,R.raw.garza19),
        audio(this,R.raw.pajaro20),
        audio(this,R.raw.mano21),
        audio(this,R.raw.bota22),
        audio(this,R.raw.luna23),
        audio(this,R.raw.cotorro24),
        audio(this,R.raw.borracho25),
        audio(this,R.raw.negrito26),
        audio(this,R.raw.corazon27),
        audio(this,R.raw.sandia28),
        audio(this,R.raw.tambor29),
        audio(this,R.raw.camaron30),
        audio(this,R.raw.jaras31),
        audio(this,R.raw.musico32),
        audio(this,R.raw.arania33),
        audio(this,R.raw.soldado34),
        audio(this,R.raw.estrella35),
        audio(this,R.raw.cazo36),
        audio(this,R.raw.mundo37),
        audio(this,R.raw.apache38),
        audio(this,R.raw.nopal39),
        audio(this,R.raw.alacran40),
        audio(this,R.raw.rosa41),
        audio(this,R.raw.calavera42),
        audio(this,R.raw.campana43),
        audio(this,R.raw.cantarito44),
        audio(this,R.raw.venado45),
        audio(this,R.raw.sol46),
        audio(this,R.raw.corona47),
        audio(this,R.raw.chalupa48),
        audio(this,R.raw.pino49),
        audio(this,R.raw.pescado50),
        audio(this,R.raw.palma51),
        audio(this,R.raw.maceta52),
        audio(this,R.raw.arpa53),
        audio(this,R.raw.rana54)
    )

    var x = 0
    val arre = Array(54,{x->x*1})
    val scoope = CoroutineScope(Job() + Dispatchers.Main)
    var objetoCoroutineControlada = scoope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
        while(true){
            runOnUiThread {
                    cartas[arre[x]].pintar()
                    songs[arre[x]].play()
                x++
            }
            delay(4000L)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        objetoCoroutineControlada.cancel()
        val shuf = HiloCarta(this)

        this.title = "Cristian Villagrana"
        binding.btniniciar.setOnClickListener {
            if(!iniciado){
                iniciado=true
                shuf.start()
            }
            shuf.reanudar()
            if(binding.btniniciar.text.equals("INICIAR") || binding.btniniciar.text.equals("REINICIAR")){
                arre.shuffle()
                binding.btniniciar.text = "PAUSAR/CONTINUAR"
            }

            if(objetoCoroutineControlada.isActive){
                shuf.pausar()
                objetoCoroutineControlada.cancel()
                return@setOnClickListener
            }
            if(objetoCoroutineControlada.isCancelled){
                objetoCoroutineControlada = scoope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
                    while(true){
                        runOnUiThread {
                                cartas[arre[x]].pintar()
                                songs[arre[x]].play()
                                x++
                            if(x==54){
                                binding.btniniciar.text = "REINICIAR"
                                objetoCoroutineControlada.cancel()
                                x=0
                            }
                        }
                        delay(4000L)
                    }
                }
            }
            objetoCoroutineControlada.start()

        }

        binding.btndetener.setOnClickListener {
            if(x<16){
                Toast.makeText(this,"No sea payaso padrino, la carta es de 4x4",Toast.LENGTH_LONG).show()
            }else{
                binding.txt.text = "¡Alguien dijo BUENA! Revisa su carta y verifica con las cartas que faltan"
                binding.btnok.visibility = View.VISIBLE
                binding.btnok.isEnabled = true
                binding.btnok.isClickable = true
                if(objetoCoroutineControlada.isActive){
                    objetoCoroutineControlada.cancel()
                }
            }
        }

        binding.btnok.setOnClickListener {
                shuf.reanudar()
            if(objetoCoroutineControlada.isActive){
                shuf.pausar()
                objetoCoroutineControlada.cancel()
                return@setOnClickListener
            }
            if(objetoCoroutineControlada.isCancelled){
                objetoCoroutineControlada = scoope.launch(EmptyCoroutineContext, CoroutineStart.LAZY){
                    while(true){
                        runOnUiThread {
                            cartas[arre[x]].pintar()
                            songs[arre[x]].play()
                            x++
                            if(x==54){
                                binding.btniniciar.text = "REINICIAR"
                                objetoCoroutineControlada.cancel()
                                x=0
                                binding.txt.text=""
                                binding.txtcontaResta.text=""
                                binding.txtcontaPasa.text=""
                                binding.btnok.visibility = View.INVISIBLE
                                binding.btnok.isEnabled = false
                                binding.btnok.isClickable = false
                            }
                        }
                        delay(4000L)
                    }
                }
            }
            objetoCoroutineControlada.start()
        }
    }//onCreate

}//class