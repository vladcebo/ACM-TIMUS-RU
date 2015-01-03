#include <stdio.h>
int flag = 0;
int L = 0;
int A[20] = {0};

void print(int N, int k)
{
    int i;
    if (N == 1)
    {
        flag = 1;
        L = k;
        return;
    }
    for (i = 9; i > 1; i--)
        if (N % i == 0)
        {
            A[k] = i;
            print(N/i, k + 1);
            break;
        }
}

int main(void)
{
    int N;
    int i;

    scanf("%d", &N);
    if (N == 0)
    {
        printf("10");
        return 0;
    } else if (N == 1) {
        printf("1");
        return 0;
    }
    print(N, 0);
    if (!flag)
        printf("-1");
    else
        for (i = L - 1; i >= 0; i--)
            printf("%d", A[i]);
return 0;
}
