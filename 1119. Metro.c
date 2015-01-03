#include <stdio.h>
#include <math.h>

double min(double a, double b, double c);

int main(void)
{
    int N, M;
    int K;

    char** Cross = (char**)malloc(sizeof(char*)*1001);
    double** D = (double**)malloc(sizeof(double*)*1001);

    scanf("%d%d", &N, &M);
    scanf("%d", &K);

    int i, j;
    int x, y;

    for (i = 0; i < 1001; i++)
    {
        Cross[i] = (char*)calloc(1001, sizeof(char));
    }

    for (i = 0; i <= K; i++)
        for (j = 0; j <= K; j++)
            Cross[i][j] = 0;


    for (i = 1; i <= K; i++)
    {
        scanf("%d%d", &x, &y);
        Cross[y][x] = 1;
    }

    float D1[1001];
    float D2[1001];

    for (i = 0; i <= N; i++)
        D1[i] = 100*i;

    for (i = 1; i <= M; i++)
    {
        D2[0] = i*100;
        int j;
        for (j = 1; j <= N; j++)
            D2[j] = min(D2[j-1]+100, D1[j]+100, Cross[i][j] ? D1[j-1] + pow(2,1/2.)*100 : 2000000);
        for (j = 0; j <= N; j++)
            D1[j] = D2[j];
    }

    int res = (int)round(D2[N]);
    printf("%d", (int)(D2[N]+0.499999999));
    return 0;
}

double min(double a, double b, double c)
{
    if (a <= b && a <= c) return a;
    if (b <= a && b <= c) return b;
    if (c <= a && c <= b) return c;
}
