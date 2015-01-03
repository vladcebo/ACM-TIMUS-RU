#include <stdio.h>


int main(void)
{
    int D[100][100];
    int S[101][101];

    int n;
    int i, j, k, h;

    scanf("%d", &n);
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            scanf("%d", &D[i][j]);

    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
        {
            S[i][j] = 0;
            for (k = i; k < n; k++)
                for (h = j; h < n; h++)
                    S[i][j] += D[k][h];
        }
    for (i = 0; i <= n; i++)
        S[n][i] =  S[i][n] = 0;
    int max = -214748364;
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            for (k = i + 1; k <= n; k++)
                for (h = j + 1; h <= n; h++)
                    if (S[i][j] - S[k][j] - S[i][h] + S[k][h] > max)
                        max = S[i][j] - S[k][j] - S[i][h] + S[k][h];
    printf("%d", max);
return 0;
}