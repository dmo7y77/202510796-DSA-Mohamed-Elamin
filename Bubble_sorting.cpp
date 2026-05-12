#include <iostream>
using namespace std;

int main()
{
    int roll_numbers[4] = {16, 28, 9, 10};
    int n = 4;

    for (int i = 0; i < n - 1; i++)
    {
        for (int j = 0; j < n - i - 1; j++)
        {
            if (roll_numbers[j] > roll_numbers[j + 1])
            {
                int temp = roll_numbers[j];
                roll_numbers[j] = roll_numbers[j + 1];
                roll_numbers[j + 1] = temp;
            }
        }
    }

    cout << "sorted Array" << endl;
    for (int i = 0; i < 4; i++)
    {
        cout << roll_numbers[i] << endl;
    }

    return 0;
}