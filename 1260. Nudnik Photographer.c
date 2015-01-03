#include <stdio.h>

int main(void)
{
    int A[60] = {0};

    int i, j, N;

    scanf("%d", &N);
    A[0] = 0;
    A[1] = 1;
    A[2] = 1;

    for (i = 3; i <= N; i++)
        A[i] = A[i-1] + A[i-3] + 1;

    printf("%d", A[N]);
    return 0;

}