package cl.tato.cronomenso;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
@SuppressWarnings("unused")
public class CronomensoActivity extends Activity implements SensorEventListener {

	private Button BIniciar;
	private Button BDetener;
	private Button BReiniciar;
	private Chronometer Crono;
	private TextView MiliSeg;
	private Long ContCrono;
	private Boolean Detenido;
//	private Boolean MovIni; 
//	private float Xu, Yu, Zu;
//	private SensorManager MovSM;
//	private Sensor MovAceler;
//	private float Ruido = (float)2.0;  	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Evita que el dispositivo se bloquee
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Importo la Fuente Digital para usarla en el cron�metro
        Typeface Digital = Typeface.createFromAsset(getAssets(), "DS-DIGIB.TTF"); 

        //Obtengo acceso a las vistas por su id de recurso
        BIniciar = (Button)findViewById(R.id.BtnIniciar);
        BDetener = (Button)findViewById(R.id.BtnDetener);
        BReiniciar = (Button)findViewById(R.id.BtnReiniciar);
        Crono = (Chronometer)findViewById(R.id.Crono);
        MiliSeg = (TextView)findViewById(R.id.MiliSeg);

        Crono.setTypeface(Digital);
        MiliSeg.setTypeface(Digital);

        BIniciar.setOnClickListener(new OnClickListener() {

        	public void onClick(View v) {
    				IniciarCronometro();
    			}
    		});
        
        BDetener.setOnClickListener(new OnClickListener() {

        	public void onClick(View v) {
    				DetenerCronometro();				
    			}
    		});

        BReiniciar.setOnClickListener(new OnClickListener() {

        	public void onClick(View v) {
    				ReiniciarCronometro();
    				
    			}
    		});

        Crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

        	public void onChronometerTick(Chronometer chronometer) {
        		ContCrono = SystemClock.elapsedRealtime() - Crono.getBase();
        		String MiliSegundos = "" + ContCrono;
        		MiliSeg.setText(MiliSegundos);
        		}
    		});
            
        }
        
        public void IniciarCronometro(){ // Inicia el cron�metro
        	Crono.setBase(SystemClock.elapsedRealtime());
        	Crono.start();
        	Detenido = false;
        }
        
        public void DetenerCronometro(){ // Detiene el cron�metro
        	Crono.stop();
        	Detenido = true;
        }
        
        public void ReiniciarCronometro(){ //Deja el cron�metro en 00:00
        	Crono.stop();
        	Crono.setText("00:00");
        	MiliSeg.setText("0000");
        	Detenido = true;
        	
        }

		public void onAccuracyChanged(Sensor EvSensor, int precision) {
			//No es necesario implementar para el cron�metro.			
		}

		public void onSensorChanged(SensorEvent EvSensor) {
			//Se implementa para cuando suceda un cambio en los valores de los ejes del acelerometro 
			
		}

		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
		}

		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
		}
}