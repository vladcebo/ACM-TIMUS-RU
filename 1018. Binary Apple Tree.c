#include <stdio.h>

#define NMAX 101

int Tree[NMAX][NMAX];
int B[NMAX][NMAX];
int Branches[NMAX];

int N, Q;

void traverse(int root, int parent)
{
    int i;
    for (i = 1; i <= N; i++)
        if (Tree[root][i] != -1 && i != parent)
        {
            traverse(i, root);
            Branches[root] += Branches[i] + 1;
        }

    int q;
    for (q = 0; q <= N - Q; q++)
    {

        int max = 0;
        int k;

        int left = 0;
        for (i = 1; i <= N; i++)
            if (Tree[root][i] != -1 && i != parent)
            {
                left = i;
                break;
            }
        int right = 0;
        for (i = left + 1; i <= N; i++)
            if (Tree[root][i] != -1 && i != parent)
            {
                 right = i;
                 break;
            }

        for (k = 0; k <= q; k++)
        {

            int Cleft, Cright;

            if (k == Branches[left] + 1 || left == 0)
                Cleft = 0;
            else
                Cleft = Tree[root][left] + B[left][k];

            if (q - k == Branches[right] + 1 || right == 0)
                Cright = 0;
            else
                Cright = Tree[root][right] + B[right][q - k];

            if (k > Branches[left] + 1 || q - k > Branches[right] + 1)
            {
                Cleft = Cright = 0;
            }

            if (Cleft + Cright > max)
                max = Cleft + Cright;

        }

        B[root][q] = max;
    }

}

int main(void)
{
    int i, j;

    scanf("%d %d", &N, &Q);
    for (i = 1; i <= N; i++)
        for (j = 1; j <= N; j++)
            Tree[i][j] = -1;

    for (i = 0; i < N - 1; i++)
    {
        int n1, n2, a;
        scanf("%d %d %d", &n1, &n2, &a);
        Tree[n1][n2] = Tree[n2][n1] = a;
    }


    traverse(1, -1);

    printf("%d",B[1][N - 1 - Q]);


    return 0;
}