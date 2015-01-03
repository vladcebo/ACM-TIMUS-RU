#include <stdio.h>
#include <stdlib.h>

typedef struct conf
{
    int start;
    int end;
} Conf;

int compare (const void * a, const void * b);

int main(void)
{
    int N;
    Conf T[100000];

    scanf("%d", &N);
    int i;

    for (i = 0; i < N; i++)
        scanf("%d%d",&T[i].start, &T[i].end);

    qsort(T, N, sizeof(Conf), compare);

    int ended = T[0].end;
    int Nr = 1;
    for (i = 1; i < N; i++)
        if (T[i].start > ended)
        {
            Nr = Nr + 1;
            ended = T[i].end;
        }

    printf("%d", Nr);
    return 0;


}

int compare (const void * a, const void * b)
{
  if (( (*(Conf*)a).end - (*(Conf*)b).end ) == 0)
    return (*(Conf*)a).start - (*(Conf*)b).start;
  else
   return (*(Conf*)a).end - (*(Conf*)b).end;
}