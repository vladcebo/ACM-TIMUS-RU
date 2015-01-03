#include <stdio.h>
#include <stdlib.h>

typedef struct listNode
{
    int nr;
    int div;
    struct listNode* nextPtr;
} ListNode;

typedef ListNode* ListNodePtr;

void hash(ListNodePtr* a, int n);

int main(void)
{
    int N;
    ListNodePtr* a = (ListNodePtr*)malloc(sizeof(ListNodePtr)*250000);

    if (a == NULL) printf("NULL\n");

    int i, n;
     scanf("%d", &N);

    for (i = 0; i < 250000; i++)
        a[i] = NULL;
    for (i = 1; i <= N; i++)
    {
        scanf("%d", &n);
        hash(a, n);
    }
    int max = 0;
    int maxn = 0;
    for (i = 0; i < 250000; i++)
        if (a[i] != NULL)
        {
            ListNodePtr iPtr = a[i];

            while (iPtr != NULL)
            {
                if (iPtr->nr > max)
                {
                    max = iPtr->nr;
                    maxn = i + iPtr->div * 250000;
                }
                iPtr = iPtr->nextPtr;
            }
        }
    printf("%d", maxn);
return 0;
}

void hash(ListNodePtr* a, int n)
{
    int r = n % 250000;
    int d = n / 250000;
    if  (a[r] == NULL)
    {
         a[r] = (ListNodePtr)malloc(sizeof(ListNodePtr));
        (a[r])->nr = 1;
        (a[r])->div = d;
        (a[r])->nextPtr = NULL;
        return;
    }
    ListNodePtr iPtr = a[r];
    while (iPtr->div != d && iPtr->nextPtr != NULL) iPtr = iPtr->nextPtr;
    if (iPtr->div == d)
    {
        iPtr->nr++;
    }
    else
    {
        ListNodePtr newPtr = (ListNodePtr)malloc(sizeof(ListNodePtr));
        newPtr->nr = 1;
        newPtr->div = d;
        newPtr->nextPtr = NULL;
        iPtr->nextPtr = newPtr;
    }
}

