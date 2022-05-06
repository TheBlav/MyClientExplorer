package App;

import Data.Client;
import Data.ClientDataReader;
import Interface.Printer;

import java.io.FileNotFoundException;

public class AppManager implements Printer {
    private final String fileName = "C:/clients.csv";

    public void AppManager(){
        ClientDataReader clientDataReader = new ClientDataReader();

        try {
            Client[] clients = clientDataReader.readFile(fileName);
            for (Client client : clients) {
                print(client.toString());
            }
            print("\n Klient któy wydał najwięcej to: " +clientDataReader.mostValuableClient(clients) + "\n");
            clientDataReader.printClientsFromCountry(clients, "Poland");
        } catch (FileNotFoundException e) {
            System.err.println("Plik nie został wczytany poprawnie.");
        }


    }
}
