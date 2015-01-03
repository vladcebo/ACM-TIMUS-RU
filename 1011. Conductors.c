#include <stdio.h>
#include <stdint.h>

int main(void)
{
    double P, Q;

    scanf("%lf%lf", &P, &Q);


    int64_t N = 1;
    int64_t C = 1;
    int p = round(P*100);
    int q = round(Q*100);
    if (p == q)
    {
        printf("%d",(int)(1/((double)p)*10000));
        return 0;
    }
    double x;
    while (1)
    {
        x = (C/((double)N))*10000;
        //printf("C = %d  N = %d    x = %d\n", C, N, x);
        if ((x > p) && (x < q))
            break;
        else if (x >= q)
            N = N + 1;
        else if (x <= p)
            C = C + 1;
    }

    printf("%I64d", N);
return 0;
}