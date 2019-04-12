#include <stdio.h>

#define N_MAX 100000
int main(int argc, char const *argv[])
{
    
    int a[N_MAX];
    int i;
    a[0] = 0;
    a[1] = 1;
    for (i = 2; i < N_MAX; i++) {
        if (i & 1) {
            a[i] = a[i/2] + a[i/2 + 1];
        } else {
            a[i] = a[i/2];
        }
    }
    
    int n;
    scanf("%d", &n);
    do {

        int max = 0;

        for (i = 0; i <= n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        printf("%d\n", max);
        scanf("%d", &n);        
    } while (n != 0);
    return 0;
}