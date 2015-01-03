#include <stdio.h>
#include <math.h>

#define NMAX 10000
#define NR   10

int primes[1300] = {0};

int isPrime(int n)
{
    int i;
    if (n == 1 || n == 2)
        return 1;

    if (n % 2 == 0)
        return 0;

    int k = sqrt(n) + 1;
    for (i = 3; i <= k; i = i + 2)
        if (n % i == 0)
            return 0;

    return 1;
}

void compute_primes(char factors[], int N)
{
    int i = 1;
    while (N != 1)
    {
        if (N % primes[i] == 0) {
            factors[i] += 1;
            N = N / primes[i];
        }
        else
            i++;
    }
    factors[++i] = 0;
}

int main(void)
{

    int i, j;
    int nr_primes = 0;

    /* Generate prime numbers */
    for (i = 1; i < 10000; i++)
        if (isPrime(i))
            primes[nr_primes++] = i;

    char factors[NR][10001] = {0};

    for (i = 0; i < NR; i++)
        for (j = 0; j < 10001; j++)
            factors[i][j] = 0;

    for (i= 0; i < NR; i++) {
        int N;
        scanf("%d", &N);
        compute_primes(factors[i], N);
    }
        int total = 1;
        for (j = 1; j <= 10000; j++)
        {
            int sum = 0;
            for (i = 0; i < NR; i++)
                sum += factors[i][j];
            if (sum > 0) {
                total = total*(sum + 1);;
            }

        }

        printf("%d \n", total % 10);
return 0;
}