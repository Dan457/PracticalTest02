package ro.pub.cs.systems.eim.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private String address;
    private int port;
    private String name;
    TextView typeTextView;
    TextView abilitiesTextView;

    private Socket socket;

    public ClientThread(String address, int port, String name, TextView typeTextView, TextView abilitiesTextView) {
        this.address = address;
        this.port = port;
        this.name= name;
        this.typeTextView = typeTextView;
        this.abilitiesTextView = abilitiesTextView;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, port);
            if (socket == null) {
                Log.e("[PracticalTest02]", "[CLIENT THREAD] Could not create socket!");
                return;
            }
            BufferedReader bufferedReader = Utilities.getReader(socket);
            PrintWriter printWriter = Utilities.getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                Log.e("[PracticalTest02]", "[CLIENT THREAD] Buffered Reader / Print Writer are null!");
                return;
            }
            printWriter.println(name);
            printWriter.flush();
            //printWriter.println(informationType);
            //printWriter.flush();
            String pokemonInformation;
            while ((pokemonInformation = bufferedReader.readLine()) != null) {
                final String finalizedPokemonInformation = pokemonInformation;
                abilitiesTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        typeTextView.setText(finalizedPokemonInformation);
                    }
                });
            }
        } catch (IOException ioException) {
            Log.e("[PracticalTest02]", "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
            if (true) {
                ioException.printStackTrace();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ioException) {
                    Log.e("[PracticalTest02]", "[CLIENT THREAD] An exception has occurred: " + ioException.getMessage());
                    if (true) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

}
