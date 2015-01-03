
#include <iostream>

using namespace std;

int main(void)
{
    __int64  a[100000];


    cin >> a[0] >> a[1] >> a[2];
   __int64  min  = 922337203685477580;
   __int64 n = 3;

    while (min != 0)
    {
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                if (a[i] >= a[j] && i != j )
                if ( a[i] - a[j] < min)
                    min = a[i] - a[j];;
        a[n++] = min;   }
   cout << n - 3;
return 0;
}