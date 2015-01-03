#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int N;
    int k;

    scanf("%d", &N);

    int* a = (int*)malloc(sizeof(int)*N);
    int i;
    for (i = 0; i < N; i++)
    {
        int x, y;
        scanf("%d%d", &x, &y);
        a[i] = (x + y) % 10;
        int t = (x + y)/10;
        k = i - 1;
        while (t != 0)
        {
            a[k] = a[k] + t;
            t = a[k] / 10;
            a[k] = a[k] % 10;
            k = k - 1;
        }
    }
    for (k = 0; k < N; k++)
        printf("%d", a[k]);
return 0;
}