#include <iostream>
#include <string>
#include <vector> // Required to use dynamic lists (vectors)

using namespace std;

int main() {
    // --- Task 1: Linear Search Algorithm (Dynamic Number of Members) ---
    int numMembers;
    cout << "How many family members do you want to enter? ";
    cin >> numMembers;

    // Using vectors to allow the user to decide the size
    vector<string> namesList(numMembers);
    vector<int> agesList(numMembers);

    cout << "\nPlease enter the details for " << numMembers << " members:" << endl;
    for(int i = 0; i < numMembers; i++) {
        cout << "Member " << i + 1 << " Name: ";
        cin >> namesList[i];
        cout << "Member " << i + 1 << " Age: ";
        cin >> agesList[i];
    }

    string searchName;
    cout << "\nEnter the name you want to search for: ";
    cin >> searchName;

    bool isFound = false;
    for(int i = 0; i < numMembers; i++) {
        if(namesList[i] == searchName) {
            cout << "Result: " << namesList[i] << " was found and is aged " << agesList[i] << endl;
            isFound = true;
            break;
        }
    }

    if(!isFound) {
        cout << "Not found" << endl;
    }

    // --- Task 2: Bubble Sorting for Names (Fixed List) ---
    string studentNames[] = {"Zuck", "Shayd", "Emely", "Amjed", "Esther", "Manar", "Anne"};
    int nNames = 7;

    for(int i = 0; i < nNames - 1; i++) {
        for(int j = 0; j < nNames - i - 1; j++) {
            if(studentNames[j] > studentNames[j+1]) {
                string temp = studentNames[j];
                studentNames[j] = studentNames[j+1];
                studentNames[j+1] = temp;
            }
        }
    }

    cout << "\nNames in ascending order: ";
    for(int i = 0; i < nNames; i++) cout << studentNames[i] << " ";
    cout << endl;

    // --- Task 3: Bubble Sorting for Numbers (Fixed List) ---
    int numbers[] = {109, 99, 23, 45, 23, 2, 5, 1};
    int nNums = 8;

    for(int i = 0; i < nNums - 1; i++) {
        for(int j = 0; j < nNums - i - 1; j++) {
            if(numbers[j] > numbers[j+1]) {
                int tempNum = numbers[j];
                numbers[j] = numbers[j+1];
                numbers[j+1] = tempNum;
            }
        }
    }

    cout << "Numbers in ascending order: ";
    for(int i = 0; i < nNums; i++) cout << numbers[i] << " ";
    cout << endl;

    return 0;
}
