#include <stdio.h>

int main(void)
{
    int M[10] = {0};
    int D[10] = {0};
    int N, K;

    scanf("%d%d", &N, &K);
    M[0] = K - 1;
    D[0] = 0;
    int i;
    for (i = 1; i < N; i++)
    {
        M[i] = K*M[i-1] - D[i-1];
        D[i] = M[i-1] - D[i-1];
    }
    printf("%d", M[N-1]);
}