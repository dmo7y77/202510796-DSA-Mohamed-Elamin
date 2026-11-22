import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class App {

    static final String SITE_URL = "https://books.toscrape.com/";
    static final int LIMIT = 20;

    static class Book {
        String name;
        String cost;
        String link;

        Book(String name, String cost, String link) {
            this.name = name;
            this.cost = cost;
            this.link = link;
        }
    }

    static Book[] storeBooks = new Book[LIMIT];
    static int storeCount = 0;
    static Book[] savedBooks = new Book[LIMIT];
    static int savedCount = 0;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            showMenu();
            option = getNumber("Choose an option: ");
            switch (option) {
                case 1:
                    browseBooks();
                    break;
                case 2:
                    findBook(getLine("Enter book title: "), false);
                    break;
                case 3:
                    findBook(getLine("Enter author/price: "), true);
                    break;
                case 4:
                    addToSaved();
                    break;
                case 5:
                    showSavedBooks();
                    break;
                case 6:
                    deleteFromSaved();
                    break;
                case 7:
                    showBookInfo();
                    break;
                case 0:
                    System.out.println("Program closed.");
                    break;
                default:
                    System.out.println("Wrong option, please try again.");
            }
            System.out.println();
        } while (option != 0);
    }

    static void showMenu() {
        System.out.println("====== BOOK FINDER MENU ======");
        System.out.println("1. Browse Books");
        System.out.println("2. Search Title");
        System.out.println("3. Search Author/Price");
        System.out.println("4. Save Book to List");
        System.out.println("5. Show Saved List");
        System.out.println("6. Delete Book from List");
        System.out.println("7. Show Book Info");
        System.out.println("0. Exit");
    }

    static int getNumber(String msg) {
        System.out.print(msg);
        String text = input.nextLine().trim();
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static String getLine(String msg) {
        System.out.print(msg);
        return input.nextLine().trim();
    }

    static boolean fetchBooks() {
        try {
            Document page = Jsoup.connect(SITE_URL)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get();
            Elements cards = page.select("article.product_pod");
            if (cards.isEmpty()) {
                System.out.println("No books available.");
                return false;
            }
            storeCount = 0;
            for (Element card : cards) {
                if (storeCount == LIMIT) {
                    break;
                }
                Element anchor = card.selectFirst("h3 a");
                if (anchor == null) {
                    continue;
                }
                String title = anchor.attr("title");
                String url = anchor.absUrl("href");
                String price = card.select("p.price_color").text().replaceAll("[^0-9.]", "");
                storeBooks[storeCount] = new Book(title, price, url);
                storeCount++;
            }
            return storeCount > 0;
        } catch (Exception e) {
            System.out.println("Failed to load books: " + e.getMessage());
            return false;
        }
    }

    static boolean checkStoreLoaded() {
        if (storeCount > 0) {
            return true;
        }
        return fetchBooks();
    }

    static void listBooks(Book[] arr, int total) {
        for (int i = 0; i < total; i++) {
            System.out.println((i + 1) + ") " + arr[i].name + " | " + arr[i].cost + " GBP");
        }
    }

    static void browseBooks() {
        System.out.println("Fetching books...");
        if (!fetchBooks()) {
            return;
        }
        listBooks(storeBooks, storeCount);
    }

    static void findBook(String keyword, boolean matchPrice) {
        if (keyword.isEmpty()) {
            System.out.println("Please enter a search keyword.");
            return;
        }
        if (!checkStoreLoaded()) {
            return;
        }
        String query = keyword.toLowerCase();
        int matches = 0;
        for (int i = 0; i < storeCount; i++) {
            String target = matchPrice ? storeBooks[i].cost : storeBooks[i].name;
            if (target.toLowerCase().contains(query)) {
                System.out.println((i + 1) + ") " + storeBooks[i].name + " | " + storeBooks[i].cost + " GBP");
                matches++;
            }
        }
        if (matches == 0) {
            System.out.println("No matching books found.");
        }
    }

    static void addToSaved() {
        if (savedCount == LIMIT) {
            System.out.println("Saved list is full.");
            return;
        }
        if (!checkStoreLoaded()) {
            return;
        }
        listBooks(storeBooks, storeCount);
        int index = getNumber("Select book number to save (1-" + storeCount + "): ");
        if (index < 1 || index > storeCount) {
            System.out.println("Invalid selection.");
            return;
        }
        Book chosen = storeBooks[index - 1];
        for (int i = 0; i < savedCount; i++) {
            if (savedBooks[i].link.equals(chosen.link)) {
                System.out.println("Book is already saved.");
                return;
            }
        }
        savedBooks[savedCount] = chosen;
        savedCount++;
        System.out.println("Saved successfully.");
    }

    static void showSavedBooks() {
        if (savedCount == 0) {
            System.out.println("Saved list is empty.");
            return;
        }
        System.out.println("--- Saved Books ---");
        listBooks(savedBooks, savedCount);
    }

    static void deleteFromSaved() {
        if (savedCount == 0) {
            System.out.println("Saved list is empty.");
            return;
        }
        showSavedBooks();
        int index = getNumber("Select book number to delete (1-" + savedCount + "): ");
        if (index < 1 || index > savedCount) {
            System.out.println("Invalid selection.");
            return;
        }
        for (int i = index - 1; i < savedCount - 1; i++) {
            savedBooks[i] = savedBooks[i + 1];
        }
        savedCount--;
        savedBooks[savedCount] = null;
        System.out.println("Deleted from saved list.");
    }

    static void showBookInfo() {
        if (!checkStoreLoaded()) {
            return;
        }
        listBooks(storeBooks, storeCount);
        int index = getNumber("Select book number (1-" + storeCount + "): ");
        if (index < 1 || index > storeCount) {
            System.out.println("Invalid selection.");
            return;
        }
        Book b = storeBooks[index - 1];
        System.out.println("--- Book Info ---");
        System.out.println("Title: " + b.name);
        System.out.println("Author/Price: " + b.cost + " GBP");
        System.out.println("Link: " + b.link);
    }
}