#include <iostream>

using namespace std;

int main() {
    // Variable to store the chosen number
    int targetNumber = 7;
    
    // Variable for user input
    int userGuess = 0;
    
    // Array to store all entered numbers and a counter
    int history[100];
    int count = 0;

    cout << "Welcome to the Guessing Game!" << endl;

    // Loop continues as long as the guess is not equal to the chosen number
    while (userGuess != targetNumber) {
        
        // Ask the user to enter a number
        cout << "Enter your guess: ";
        cin >> userGuess;

        // Immediately save the value into the array
        history[count] = userGuess;
        count++; // increase the count for the next attempt

        // Compare the user's guess with the chosen number
        if (userGuess > targetNumber) {
            cout << "Too high\n";
        } 
        else if (userGuess < targetNumber) {
            cout << "Too low\n";
        } 
        else {
            cout << "Correct\n";
        }
    }

    // After the loop finishes, display all guesses stored in the array
    cout << "\nAll your attempts: ";
    for (int i = 0; i < count; i++) {
        cout << history[i] << " ";
    }
    cout << endl;

    return 0;
}

