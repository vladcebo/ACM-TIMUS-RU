#include <stdio.h>


int main(void)
{
    int N, i;
    double a0, aN;

    scanf("%d", &N);
    scanf("%lf%lf", &a0, &aN);

    double csum = 0;
    for (i = N; i > 0; i--)
    {
        double c;
        scanf("%lf", &c);
        csum = csum + 2*i*c;
    }

    double result = 1.0/(N+1)*(N*a0 + aN - csum);
    printf("%.2lf\n",  result);

    return 0;
}