#include <stdio.h>

/* Queue used for BFS */
struct coord
{
    int x, y;
};

typedef struct queueNode
{
    struct coord idx;
    struct queueNode* next;

} QueueNode;


typedef struct queue
{
   QueueNode* Head;
   QueueNode* Tail;
} Queue;



int isEmpty(Queue Q)
{
    return (Q.Head == NULL);
}

void enqueue(Queue* Q, struct coord idx)
{
    QueueNode* newNode = (QueueNode*) malloc(sizeof(QueueNode));
    newNode->idx  = idx;
    newNode->next = NULL;
    if (Q->Tail == NULL)
    {
        Q->Tail = newNode;
        Q->Head = Q->Tail;
    }
    else
    {
        Q->Tail->next = newNode;
        Q->Tail       = newNode;
    }

}

struct coord dequeue(Queue* Q)
{
    struct coord temp;
    if (isEmpty(*Q))
    {
        printf("Queue is empty.\n");
        return temp;
    }

    QueueNode* oldHead = Q->Head;

    struct coord idx  = oldHead->idx;

    Q->Head = Q->Head->next;
    free(oldHead);
    if (Q->Head == NULL)
        Q->Tail = NULL;

    return idx;
}


/********************/


#define WHITE   0
#define BLACK   1
#define VISITED 2
#define N       10


int G[11][11];
int rep; /* representation */

int main(void)
{
    Queue Q = {NULL, NULL};

    int i, j, h;
    int NrNodes = 1;
    struct coord st;
    char s[20];

    for (i = 1; i <= N; i++)
        for (j = 1; j <= N; j++)
            G[i][j] = WHITE;



    gets(s);
    rep = sscanf(s, "%d %d", &st.x, &st.y);



    if (rep == 1)
    {
        int x, y;
        for (i = 0; i < st.x; i++)
        {
            scanf("%d %d", &x, &y);
            G[x][y] = BLACK;
        }
    }
    else /* rep == 2 */
    {
        gets(s);
        enqueue(&Q, st);

        G[st.x][st.y] = BLACK;

        while (s[0] != '.') {

            st = dequeue(&Q);
            NrNodes++;
            for (i = 0; i < strlen(s) - 1; i++)
            {
                struct coord tmp;
                switch (s[i])
                {
                    case 'R' :
                         tmp.x = st.x + 1; tmp.y = st.y;
                         enqueue(&Q, tmp);
                         G[tmp.x][tmp.y] = BLACK;
                         break;
                    case 'T' :
                         tmp.x = st.x; tmp.y = st.y + 1;
                         enqueue(&Q, tmp);
                         G[tmp.x][tmp.y] = BLACK;
                         break;
                    case 'L' :
                         tmp.x = st.x - 1; tmp.y = st.y;
                         enqueue(&Q, tmp);
                         G[tmp.x][tmp.y] = BLACK;
                         break;
                    case 'B' :
                         tmp.x = st.x; tmp.y = st.y - 1;
                         enqueue(&Q, tmp);
                         G[tmp.x][tmp.y] = BLACK;
                         break;
                }

            }

        gets(s);

        }

    }

/*
    for (i = 1; i <= N; i++) {
        for (j = 1; j <= N; j++)
            printf("%3d ", G[i][j]);
        printf("\n");

    }
*/

    if (rep == 1)
    {
        /* Show second representation */
        /* (1) - find left and lower */
        for (i = 1; i <= N; i++)
            for (j = 1; j <= N; j++)
                if (G[i][j] != 0)
                {
                    st.x = i;
                    st.y = j;
                    goto end_loop;
                }

end_loop:
        /* (2) - run a BFS */
        enqueue(&Q, st);
        printf("%d %d\n", st.x, st.y);
        while (!isEmpty(Q))
        {
            struct coord c;
            st = dequeue(&Q);
            G[st.x][st.y] = VISITED;
            /* Right */
            c.x = st.x + 1; c.y = st.y;
            if (G[c.x][c.y] == BLACK)
            {
                enqueue(&Q, c);
                G[c.x][c.y] = VISITED;
                printf("R");
            }
            /* Top */
            c.x = st.x; c.y = st.y + 1;
            if (G[c.x][c.y] == BLACK)
            {
                enqueue(&Q, c);
                G[c.x][c.y] = VISITED;
                printf("T");
            }
            /* Left */
            c.x = st.x - 1; c.y = st.y;
            if (G[c.x][c.y] == BLACK)
            {
                enqueue(&Q, c);
                G[c.x][c.y] = VISITED;
                printf("L");
            }
            /* Bottom */
            c.x = st.x; c.y = st.y - 1;
            if (G[c.x][c.y] == BLACK)
            {
                enqueue(&Q, c);
                G[c.x][c.y] = VISITED;
                printf("B");
            }
            if (isEmpty(Q))
                printf(".\n");
            else
                printf(",\n");
        }
    }
    else
    {
        printf("%d\n", NrNodes);
        for (i = 1; i <= N; i++)
            for (j = 1; j <= N; j++)
                if (G[i][j] != 0)
                    printf("%d %d\n", i, j);
    }

    return 0;
}


