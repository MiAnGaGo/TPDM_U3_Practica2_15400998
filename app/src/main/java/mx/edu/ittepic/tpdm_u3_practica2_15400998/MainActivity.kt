package mx.edu.ittepic.tpdm_u3_practica2_15400998

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {
    var descripcion : EditText ?= null
    var monto : EditText ?= null
    var fechaVencimiento : EditText ?= null
    var pagado : EditText ?= null
    var insertar : Button ?= null
    var cargar : Button ?= null
    var consulta : TextView ?= null

    var jsonRegreso = ArrayList<org.json.JSONObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        descripcion = findViewById(R.id.descripcion)
        monto = findViewById(R.id.monto)
        fechaVencimiento = findViewById(R.id.fechaVencimiento)
        pagado = findViewById(R.id.pagado)
        insertar = findViewById(R.id.insertar)
        cargar = findViewById(R.id.cargar)
        consulta = findViewById(R.id.consulta)

        insertar?.setOnClickListener {
            var conexionWeb = ConexionWeb(this)
            conexionWeb.agregarVariables("descripcion",descripcion?.text.toString())
            conexionWeb.agregarVariables("monto",monto?.text.toString())
            conexionWeb.agregarVariables("fechaVencimiento",fechaVencimiento?.text.toString())
            conexionWeb.agregarVariables("pagado",pagado?.text.toString())
            conexionWeb.execute(URL("http://intense-anchorage-90569.herokuapp.com/insertarPractica2.html"))
        }
        cargar?.setOnClickListener {
            var conexionWeb = ConexionWeb(this)
            conexionWeb.execute(URL("http://intense-anchorage-90569.herokuapp.com/mostrartodos.php"))
        }
    }
    fun mostrarRespuesta(cadena: String){
        var jsonArray = org.json.JSONArray(cadena)
        var total=jsonArray.length()
        (0..total).forEach {
            jsonRegreso.add((jsonArray.getJSONObject(it)))
        }
    }
}
