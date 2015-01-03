#include <stdio.h>
#include <math.h>
#include <stdint.h>

int64_t Q(int KK, int M);
int64_t mypow(int a, int b);

int main(void)
{
    int K, M;
    scanf("%d%d", &K, &M);
    //K = 100000001;
    //M = 1000000000;
    printf("%I64d", Q(K, M));

return 0;
}

int64_t Q(int KK, int M)
{

    int Mc = 0;
    int Kp = KK;
    int k = 0;
    while (Kp != 0)
    {
        k = k + 1;
        int r = Kp % 10;
        if (Kp >= 10)
            Mc = Mc + r*(mypow(10, k) - 1)/9 + 1;
        else {
            Mc = Mc + (r-1)*(mypow(10, k) - 1)/9 + 1;
        }
        Kp = Kp / 10;

    }  // am determinat Mc
    if (Mc > M)
        return 0;
    else if (Mc == M)
        return KK;

    int64_t K = KK;
    int64_t N = mypow(10, cifre(K));
    if (K*10 - N != 0)
        Mc = Mc + 1;
    while (Mc < M) {
       //printf("N is %I64d    Mc is  %d\n dM is  %d  K*10 - N is %d \n", N, Mc, M-Mc, K*10 - N);
       if ((M - Mc) < (K*10 - N)) {
            N = N + M - Mc;
           // printf("RETURNED SUCCES\n");
            return N;
       }
       else if (K*10 - N !=0){
            Mc = Mc + K*10 - N;
            N = N * 10;
        }
       else return 0;
    K = K*10;
    }

    if (M == Mc)
        return N;
    else
        return 0;
}

int cifre(int n)
{
    int k = 0;
    while (n != 0)
    {
        n = n / 10;
        k++;
    }
    return k;
}

int64_t mypow(int a, int b)
{
    int64_t s = 1;
    int i;
    for (i = 0; i < b; i++)
        s = s*a;
    return s;
}