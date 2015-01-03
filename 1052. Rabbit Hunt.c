#include <stdio.h>

typedef struct point
{
    int x;
    int y;
} Point;


int main(void)
{
    int i, j, k, N;
    Point a[200];

    scanf("%d", &N);

    for (i = 0; i < N; i++)
        scanf("%d%d", &a[i].x, &a[i].y);

    int max = 0;
    for (i = 0; i < N - 1; i++)
        for (j = i + 1; j < N; j++)
        {
            int nr = 0;
            int dy = a[j].y - a[i].y;
            int dx = a[j].x - a[i].x;

            if (dx != 0)
                for (k = 0; k < N; k++)
                {
                    if (((a[k].x - a[i].x)*dy) % dx == 0)
                        if (a[k].y == a[i].y + ((a[k].x - a[i].x)*dy) / dx)
                            nr++;
                }
            else
                for (k = 0; k < N; k++)
                    if (a[k].x == a[i].x)
                        nr++;
            if (nr > max)
                max = nr;
        }
    printf("%d", max);
return 0;
}