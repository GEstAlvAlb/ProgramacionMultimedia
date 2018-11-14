package com.example.alumnot.holamundo;


import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{ //el implements es para que este el escuchador lo del onClick(View v)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btMensaje=findViewById(R.id.btMensaje); //esto es para poder utilizarun componente por id
        btMensaje.setOnClickListener(this); //escuchador para esperar a que pulse el boton (la clase)

    }

    @Override
    public void onClick(View v) {
        EditText etMensaje=findViewById(R.id.etMensaje);//llamar la caja de texto
        String caja = etMensaje.getText().toString(); //mete el texto en la variable y hay que poner el toString para pasarke a string
       /* Toast.makeText(this,caja,Toast.LENGTH_LONG).show();//mostrar el mensaje
        etMensaje.setText(""); //limpia la caja de texto
        etMensaje.requestFocus(); //posiciona el cursor dentro de la caja de texto
*/
        Intent intent =new Intent(this, MensajeActivity.class); //creas el metodo para poder pasar al main del otro
        intent.putExtra("mensaje",caja); //nom_variable que vamos a utilizar en el otro main y la variable de este lado

        startActivity(intent); //llama al otro main


    }
}