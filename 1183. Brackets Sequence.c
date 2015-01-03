#include <stdio.h>


int Pair[100] = {0};
void BackTr(int D[100][100], char S[100], int i, int j);
int main(void)
{
    int i, j, k,  N;
    char S[100];
    int D[100][100] = {0};



    gets(S);
    N = strlen(S);

    for (i = 0; i < N; i++)
        D[i][i] = 1;

    for (j = 1; j < N; j++)
        for (i = 0; i < N - j; i++)
        {
            int min = 100;
            for (k = i; k < i + j; k++)
                if (D[i][k] + D[k+1][i+j] < min)
                    min = D[i][k] + D[k+1][i+j];
            if ((((S[i] == '[' && S[i+j] == ']') || (S[i] == '(' && S[i+j] == ')'))) && D[i+1][i+j-1] < min) min = D[i+1][i+j-1];

            D[i][i+j] = min;
        }

    BackTr(D, S, 0, N - 1);

    for (i = 0; i < N; i++)
        if (Pair[i])
        {
            if (S[i] == '(' || S[i] == ')') printf("()");
            if (S[i] == '[' || S[i] == ']') printf("[]");
        } else printf("%c", S[i]);


    return 0;
}

void BackTr(int D[100][100], char S[100], int i, int j)
{
    if (i == j)
    {
        Pair[i] = 1;
        return;
    }
    else
    if (i > j)
        return;
    else
    {
        int k, ik;
        int min = 100;
        for (k = i; k < j; k++)
            if (D[i][k] + D[k+1][j] < min)
            {
                min = D[i][k] + D[k+1][j];
                ik = k;
            }
        if ((((S[i] == '[' && S[j] == ']') || (S[i] == '(' && S[j] == ')'))) && D[i+1][j-1] <= min)
            BackTr(D, S, i+1, j-1);
        else
        {
            BackTr(D, S, i, ik);
            BackTr(D, S, ik + 1, j);
        }
    }
}