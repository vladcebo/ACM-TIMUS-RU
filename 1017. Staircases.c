#include <stdio.h>

int main(void)
{
    int n;
    unsigned long long Q[505][255];
    scanf("%d", &n);
    int i, j;
    int d;
    for (i = 1; i <= n; i++)
        for (j = 1; j <= n; j++)
            Q[i][j] = 0;
    for (d = 2; d <= n; d++)
        for (i = d; i <= n; i++)
        {
            int k = i - d + 1;
            unsigned long long S = 0;
            if (2*k + 1 > i)
            {
                Q[i][k] = 0;
                break;
            }
            int h;
            for (h = 1; h <= (i - 2*k + 1)/2; h++)
                S += Q[i - k - h + 1][k + h];
            Q[i][k] = S + (i - 2*k + 1)/2;
        }
    printf("%I64d", Q[n][1]);

    return 0;
}



