#include <stdio.h>
#include <time.h>
#include <stdlib.h>

#define BLOCK_SIZE 50
#define MEM_MAX 140000
#define BLOCKS_NR (MEM_MAX/BLOCK_SIZE)

int   mem[MEM_MAX] = {};
char  mem_state[BLOCKS_NR] = {0}; // block i is allocated or not allocated
short mem_prev[BLOCKS_NR]  = {0}; // previous block in memory
short block[1000] = {0};  // in which block is located the top of the stack
short top[1000]   = {0}; // at which element is the top of the stack

void push(int s, int val)
{
    int i;
    if (block[s] == -1)  // no memory is allocated for the stack
        for (i = 0; i < BLOCKS_NR; i++) // search a free block in memory
            if (mem_state[i] == 0) // free block
            {
                mem_state[i] = 1;
                mem_prev[i]  = -1;
                top[s]       = -1;
                block[s]     = i;
                break;
            }
    if (top[s] == BLOCK_SIZE - 1)  // current block is full
        for (i = 0; i < BLOCKS_NR; i++) // search a free block in memory
            if (mem_state[i] == 0) // free block
            {
                mem_state[i] = 1;
                mem_prev[i]  = block[s];
                top[s]       = -1;
                block[s]     = i;
                break;
            }

    top[s] = top[s] + 1;
    mem[block[s]*BLOCK_SIZE + top[s]] = val;

}

int pop(int s)
{

    int val = mem[block[s]*BLOCK_SIZE+top[s]];

    top[s] = top[s] - 1;

    if (top[s] == -1)
    {
        mem_state[block[s]] = 0;
        block[s] = mem_prev[block[s]];
        top[s] = BLOCK_SIZE - 1;
    }

    return val;

}

int main(void)
{

    int N;
    char C[10];
    int i, a, b;

    for (i = 0; i < 1000; i++)
        block[i] = top[i] =  -1;

    scanf("%d", &N);

    for (i = 0; i < N; i++)
    {
        scanf("%4s", C);
        if (C[1] == 'U') { //push
            scanf(" %d %d", &a, &b);
            push(a - 1, b);
        }
        else {
            scanf("%d", &a);
            printf("%d\n", pop(a - 1));
        }
    }

return 0;
}