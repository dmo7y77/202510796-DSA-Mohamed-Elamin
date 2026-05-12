#include <iostream>
#include <string>

using namespace std;

int main()
{
    long keynumber = 999;
    int phonenumbers[3];
    int i = 0;
    // filling numbers
    do
    {
        cout << "enter your phone number" << endl;
        cin >> phonenumbers[i];
        i = i + 1;
    } while (i < 3);

    // linear search
    // number esther
    for (int i = 0; i < 3; i++)
    {
        if (phonenumbers[i] == keynumber)
        {
            cout << "Esther number's found" << endl;
            break;
        }
    }

    return 0;
}
