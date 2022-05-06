package Data;
import Interface.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClientDataReader implements Printer {

    public Client[] readFile(String fileName) throws FileNotFoundException {
        final int linesNumber = countLinesWithoutHeader(fileName);
        Client[] clients = new Client[linesNumber];
        try (Scanner scanner = new Scanner(new File(fileName))) {
            scanner.nextLine();
            for (int i = 0; i < linesNumber; i++) {
                String csvLine = scanner.nextLine();
                clients[i] = readClientFromCsv(csvLine);
        }
    }
        return clients;
}

    private int countLinesWithoutHeader(String fileName) throws FileNotFoundException {
        int lines = 0;
        try (Scanner scanner = new Scanner(new File(fileName))){
            scanner.nextLine();
            while (scanner.hasNextLine()){
                scanner.nextLine();
                lines++;
            }
        }
        return lines;
    }

    private static Client readClientFromCsv(String csvLine) {
        String[] data = csvLine.split (",");
        int id = Integer.parseInt(data[0]);
        String firstName = data[1];
        String lastName = data[2];
        String country = data[3];
        double value = Double.parseDouble(data[4]);
        return new Client(id, firstName, lastName, country, value);
    }

    public Client mostValuableClient(Client[] clients){
        Client mvp = clients[0];
        for (Client client : clients) {
            if (client.getValue()>mvp.getValue()){
                mvp = client;
            }
        }
        return mvp;
    }

    public void printClientsFromCountry(Client[] clients, String country){
        boolean countryExists = false;
        StringBuilder clientPrinter = new StringBuilder();
        for (Client client : clients) {
            if (client.getCountry().equals(country)){
                clientPrinter.append(client);
                clientPrinter.append("\n");
                countryExists = true;
            }
        }
        if (countryExists) {
            print(clientPrinter.toString());
        }
        else
            print("Brak klientów z kraju: "+country);
    }

    }

