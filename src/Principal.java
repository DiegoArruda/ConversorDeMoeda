
import Modelos.Conversor;
import Modelos.ConversorAPI;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {



        int option;
        Scanner escolha = new Scanner(System.in);
        do {
            System.out.println("""
                    Bem-vindo/a ao Conversor de Moeda: Digite uma opção
                    1) Dolar => Real
                    2) Real => Dolar
                    3) Peso Argentino => Real
                    4) Real => Peso Argentino\s
                    5) Iene => Real
                    6) Real => Iene
                    7) Sair
                    """);
            try {
            option = escolha.nextInt();
            switch (option){
                case 1:
                    System.out.println("Digite o valor em dólar:");
                    float dolar = escolha.nextFloat();
                    System.out.println(consultarAPI("USD", "BRL", dolar));
                    break;
                case 2:
                    System.out.println("Digite o valor em Real:");
                    float realParaDolar  = escolha.nextFloat();
                    System.out.println(consultarAPI("BRL", "USD", realParaDolar));
                    break;
                case 3:
                    System.out.println("Digite o valor em Peso Argentino:");
                    float pesoArgentino = escolha.nextFloat();
                    System.out.println(consultarAPI("ARS", "BRL", pesoArgentino));
                    break;
                case 4:
                    // Código para conversão de Peso Argentino para Dólar
                    System.out.println("Digite o valor em Real:");
                    float realParaPesoArg  = escolha.nextFloat();
                    System.out.println(consultarAPI("BRL", "ARS", realParaPesoArg));
                    break;
                case 5:
                    System.out.println("Digite o valor em Iene:");
                    float ieneParaReal  = escolha.nextFloat();
                    System.out.println(consultarAPI("JPY", "BRL", ieneParaReal));
                    break;
                case 6:
                    System.out.println("Digite o valor em Reais:");
                    float realParaIene  = escolha.nextFloat();
                    System.out.println(consultarAPI("BRL", "JPY", realParaIene));
                    break;
                case 7:
                    System.out.println("Saindo do Conversor de Moeda...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } catch (InputMismatchException e){
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            escolha.next();
            option = 0;
            }
        }while (option != 7);



    }

    public static String consultarAPI(String valorBase, String valorAlvo, Float resultado) {
        String endereco = "https://v6.exchangerate-api.com/v6/fcd7bee2471d6e17b664c3b3/pair/"
                + valorBase + "/" + valorAlvo + "/" + resultado;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            Gson gson = new Gson();
            ConversorAPI conversorAPI = gson.fromJson(json, ConversorAPI.class);
            Conversor conversor = new Conversor(conversorAPI);
            return conversor.toString();

        } catch (IOException | InterruptedException e) {
            System.out.println("Um erro ocorreu");
            throw new RuntimeException(e);
        }
    }
}

