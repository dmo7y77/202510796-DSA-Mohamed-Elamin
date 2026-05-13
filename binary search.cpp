#include <iostream>
#include <string>

using namespace std;

int main() {
    // Array size is fixed to 100 (assuming family won't be larger than this)
    int numMembers;
    string names[100];
    int ages[100];
    string searchKey;
    bool isFound = false;

    // Welcome header
    cout << "**************" << endl;
    cout << "*     My Family Binary Search Program    *" << endl;
    cout << "**************" << endl << endl;

    // Ask user for input
    cout << "How many family members do you want to enter? (max 100): ";
    cin >> numMembers;

    cout << "\n--- Please enter the details ---" << endl;
    for (int i = 0; i < numMembers; i++) {
        cout << "Enter name of member #" << i + 1 << ": ";
        cin >> names[i];
        cout << "Enter age for " << names[i] << ": ";
        cin >> ages[i];
    }

    cout << "\nSorting the data... Please wait." << endl;

    // Using Bubble Sort to sort the arrays alphabetically
    // Note: Binary search only works on sorted arrays!
    for (int i = 0; i < numMembers - 1; i++) {
        for (int j = 0; j < numMembers - i - 1; j++) {
            if (names[j] > names[j + 1]) {
                // Swap the names
                string tempName = names[j];
                names[j] = names[j + 1];
                names[j + 1] = tempName;

                // Swap the ages to match the sorted names
                int tempAge = ages[j];
                ages[j] = ages[j + 1];
                ages[j + 1] = tempAge;
            }
        }
    }

    cout << "Data is sorted successfully!\n" << endl;

    // Ask the user who they want to find
    cout << "Enter the name you want to search for: ";
    cin >> searchKey;

    cout << "\n==========================================" << endl;

    // Binary search algorithm implementation
    int left = 0;
    int right = numMembers - 1;

    while (left <= right) {
        int mid = (left + right) / 2; // Find the middle element

        if (names[mid] == searchKey) {
            // We found the person!
            cout << "SUCCESS! " << names[mid] << " was found in the list and their age is " << ages[mid] << "." << endl;
            isFound = true;
            break; // Exit the loop since we found a match
        }
        else if (names[mid] < searchKey) {
            left = mid + 1; // Search in the right half
        }
        else {
            right = mid - 1; // Search in the left half
        }
    }

    // Output if the person is not in the array at all
    if (!isFound) {
        cout << "Sorry, the name '" << searchKey << "' was not found in the list." << endl;
    }

    cout << "==========================================" << endl;
    cout << "Thank you for using my program!" << endl;

    return 0;
}
