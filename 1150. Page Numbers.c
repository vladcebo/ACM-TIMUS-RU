#include <stdio.h>
#include <stdint.h>

int64_t  c[10] = {0};
int64_t ct[10] = {0};
void getChars(int n)
{
    while (n != 0)
    {
        c[n % 10]++;
        n /= 10;
    }
}

int main(void)
{


    int N, i;
    scanf("%d", &N);
    int64_t power = 1;

    int64_t Left   = N / (power * 10);
    int64_t Right  = 0;
    int     p      = N % 10;
    int     flag   = 0;

    if (Left == 0)
        flag++;

    while (flag != 2)
    {


       ct[0] += (Left - 1)*power;

       for (i = 1; i < 10; i++)
            ct[i] += Left*power;

        for (i = 0; i < p; i++)
            ct[i] += power;
        ct[p] += Right + 1;

        Right += power*p;
        p     = Left % 10;
        Left  = Left / 10;
        power = power * 10;

        if (Left == 0)
            flag++;

    }

    for (i = 0; i < 10; i++)
        printf("%I64d\n", ct[i]);

    return 0;
}
