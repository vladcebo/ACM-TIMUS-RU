#include <stdio.h>
#include <math.h>

int main(void)
{
    int R[60002];
    int i, N;

    scanf("%d", &N);
    R[0] = 0;

    for (i = 1; i <= N; i++)
    {
        int min = 10;
        int k = (int)pow(i,1/2.);
        int a;
        for (a = 1; a <= k; a++)
            if (R[i - a*a] + 1 < min) min = R[i - a*a] + 1;
        R[i] = min;
    }

    printf("%d", R[N]);
    return 0;
}