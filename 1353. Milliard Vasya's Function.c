#include <stdio.h>
#include <conio.h>

long long int V[82][10];
int S;



int main(void)
{


    int i,j;

    scanf("%d", &S);

    for (i = 0; i <= 9; i++)
        V[0][i] = 1;
    for (i = 1; i <= 9; i++)
        V[i][0] = 0;

    for (i = 1; i <= S; i++)
    {
        for (j = 1; j <= 9; j++)
        {

            int k;
            V[i][j] = 0;
            for (k = 0; k <= 9; k++ )
            {
                if (i - k < 0) break;
                V[i][j] += V[i-k][j-1];
            }
        }
    }
    if (S == 1) V[S][9]++;
    printf("%I64d", V[S][9]);
return 0;
}