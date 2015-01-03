#include <stdio.h>

#define n 64
int dx[8]={1,2,2,1,-1,-2,-2,-1};
int dy[8]={2,1,-1,-2,-2,-1,1,2};
int a[n][n]={0};
int N;

int Find(int x, int y);
int Cal(int x, int y, int k);
int main( void )
{

    int i, j;
    int x, y;
    scanf("%d", &N);
    if (N == 1)
    {
        printf("a1");
        return 0;
    }

    for (i = 0; i < N; i++)
        for (j = 0; j < N; j++)
        {
            a[i][j]=1;
            if (Cal(i,j, 2))
            {
                x = i;
                y = j;
                while (1)
                {
                    printf("%c%d\n", 97+y, x+1);
                    if (a[x][y] == N*N) return 0;
                    for (i = 0; i < 8; i++)
                        if ((x+dx[i]>=0) && (x+dx[i]<N) &&
                            (y+dy[i]>=0) && (y+dy[i]<N) &&
                            (a[x+dx[i]][y+dy[i]] == a[x][y] + 1))
                            {
                                x = x + dx[i];
                                y = y + dy[i];
                                break;
                            }
                }
            }
            for (x = 0; x < N; x++)
                for (y = 0; y < N; y++)
                    a[x][y] = 0;
        }
    printf("IMPOSSIBLE");
  return 0;
}

int Find(int x, int y)
{
    int f,i;
    int flag;

    f=0;
     for (i=0; i<8; i++)
       if ((x+dx[i]>=0) && (x+dx[i]<N) &&
             (y+dy[i]>=0) && (y+dy[i]<N) &&
               (a[x+dx[i]][y+dy[i]]==0)) f++;
      return f;
}
int Cal(int x, int y, int k)
{
     int min,rx,ry,i,rez;
     int flag = 0;
     min=9;
     if (k>N*N) return 1;
     for (i=0; i<8; i++)
      if ((x+dx[i]>=0) && (x+dx[i]<N) &&
             (y+dy[i]>=0) && (y+dy[i]<N) &&
               (a[x+dx[i]][y+dy[i]]==0))
            {
               flag  = 1;
               rez=Find(x+dx[i],y+dy[i]);
               if (rez<min)
                {
                  min=rez;
                  rx=x+dx[i];
                  ry=y+dy[i];
                  }
                }
             if (!flag) return 0;
             a[rx][ry]=k;
             return Cal(rx,ry,k+1);
           }








