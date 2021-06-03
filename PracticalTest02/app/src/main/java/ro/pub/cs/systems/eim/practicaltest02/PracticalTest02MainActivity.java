package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class PracticalTest02MainActivity extends AppCompatActivity {

    private EditText pokemonNameEditText = null;
    private TextView abilitiesTextView = null;
    private TextView typeTextView = null;
    private Button connectButton = null;

    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            //String serverPort = serverPortEditText.getText().toString();
            String serverPort = "3000";
            serverThread = new ServerThread(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e("[PracticalTest02]", "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();

            String clientPort = "3000";
            String name = pokemonNameEditText.getText().toString();
            //String clientAddress = "https://pokeapi.co/api/v2/pokemon/" + name;


            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }

            abilitiesTextView.setText("");
            typeTextView.setText("");

            clientThread = new ClientThread(
                    "localhost", Integer.parseInt(clientPort), name, abilitiesTextView, typeTextView
            );
            clientThread.start();

        }
    }

    /*private GetPokemonButtonClickListener getPokemonButtonClickListener = new GetPokemonButtonClickListener();
    private class GetPokemonButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            //String clientAddress = clientAddressEditText.getText().toString();
            //String clientPort = clientPortEditText.getText().toString();
            String clientPort = "3000";
            String name = pokemonNameEditText.getText().toString();
            String clientAddress = "https://pokeapi.co/api/v2/pokemon/" + name;


            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }

            abilitiesTextView.setText("");
            typeTextView.setText("");

            clientThread = new ClientThread(
                    clientAddress, Integer.parseInt(clientPort), name, abilitiesTextView, typeTextView
            );
            clientThread.start();
        }

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("[PracticalTest02]", "[MAIN ACTIVITY] onCreate() callback method has been invoked");
        setContentView(R.layout.activity_practical_test02_main);

        //serverPortEditText = (TextView)findViewById(R.id.pokemon_name);
        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);

        pokemonNameEditText = (EditText)findViewById(R.id.pokemon_name);
        abilitiesTextView = (TextView)findViewById(R.id.abilities);
        typeTextView = (TextView)findViewById(R.id.type);
    }

    @Override
    protected void onDestroy() {
        Log.i("[PracticalTest02]", "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }
}