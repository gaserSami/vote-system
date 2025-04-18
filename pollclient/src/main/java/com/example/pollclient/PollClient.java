package com.example.pollclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.web.client.RestTemplate;

public class PollClient {
    static final String BASE_URL = "http://localhost:8080/";
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        int choice=0;
        System.out.println("Welcome to the Poll Client!");
        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Create Poll");
            System.out.println("2. Get Poll Vote Count");
            System.out.println("3. Enter A Poll");
            System.out.print("Enter your choice (or 0 to exit): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // consume invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 0 -> {
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                case 1 -> {
                    // Create a new poll
                    System.out.print("Enter poll name: ");
                    String pollName = scanner.nextLine().trim();
                    if (pollName.isEmpty()) {
                        System.out.println("Poll name cannot be empty.");
                        continue;
                    }
                    System.out.print("Enter poll options (comma separated): ");
                    String pollOptions = scanner.nextLine();
                    String[] optionsArray = pollOptions.split(",");
                    if (optionsArray.length == 0 || (optionsArray.length == 1 && optionsArray[0].isEmpty())) {
                        System.out.println("Invalid input. Please enter at least one option.");
                        continue;
                    }
                    for (int i = 0; i < optionsArray.length; i++) {
                        optionsArray[i] = optionsArray[i].trim();
                    }
                    ArrayList<String> requestData=new ArrayList<String>();
                    requestData.add(pollName);
                    requestData.addAll(List.of(optionsArray));
                    restTemplate.postForObject(BASE_URL + "create" , requestData, Void.class);
                }

                case 2 -> {
                    // Get the vote count for a poll
                    System.out.print("Enter the name of the poll: ");
                    String pollId = scanner.nextLine().trim();
                    Integer pollCount = restTemplate.getForObject(BASE_URL + "voteCount/" + pollId, Integer.class);

                    if (pollCount == -1) {
                        System.out.println("Poll does not exist.");
                    } else {
                        System.out.println("Vote Count: " + pollCount);
                    }
                }
                case 3 -> {
                    // Subscribe to updates of a poll and vote
                    System.out.print("Enter the name of the poll you want to enter: ");
                    String pollId = scanner.nextLine().trim();
                    String[] pollOptions = restTemplate.getForObject(BASE_URL + pollId, String[].class);
                    if (pollOptions.length == 0) {
                        System.out.println("Poll does not exist.");
                        continue;
                    }
                    System.out.println("Poll options:");
                    for (int i = 0; i < pollOptions.length; i++) {
                        System.out.println((i + 1) + ". " + pollOptions[i]);
                    }
                    int voteNumber = 0;
                    while (voteNumber < 1 || voteNumber > pollOptions.length) {
                        System.out.print("Enter the number of your vote: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Invalid input. Please enter a number: ");
                            scanner.next(); 
                        }
                        voteNumber = scanner.nextInt();
                    }
                    scanner.nextLine();
                    String voteOption = pollOptions[voteNumber - 1];
                    PollWebsocket websocket=new PollWebsocket();
                    websocket.connectToWebSocket();
                    websocket.subscribeToPoll(pollId);
                    websocket.sendVote(voteOption, pollId);
                    // Wait for user to exit
                    System.out.println("Press enter to exit...");
                    scanner.nextLine();
                    System.out.println("Exiting...");
                    scanner.close();
                    websocket.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter 0, 1, 2, or 3.");
            }
        }
    }
}
