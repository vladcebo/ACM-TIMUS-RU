#include <stdio.h>
#include <stdint.h>

#define N_MAX 100
#define M_MAX 100
#define S_MAX 4000
#define K_MAX 4


int maze[K_MAX + 1][N_MAX + 1][M_MAX + 1];
int S[S_MAX];
int N, M, Ns;
int k, i, j, x, y, m;

int candidates[N_MAX + 1][M_MAX + 1];
int cand_copy[(N_MAX) * (M_MAX)];
int Ncand;
int Nc;

int main(int argc, char **argv) {


	scanf("%d %d", &N, &M);

	for (k = 1; k <= K_MAX; k++) {
		for (j = 1; j <= N; j++) {
			for (i = 1; i <= M; i++) {
				scanf("%d %d", &x, &y);
				maze[k][j][i] = (x << 8) | y;
			}
		}
	}

	scanf("%d", &Ns);
	for (i = 0; i < Ns; i++) {
		scanf("%d", &S[i]);
	}

	for (j = 1; j <= N; j++) {
		for (i = 1; i <= M; i++) {
			candidates[j][i] = 1;
		}
	}

	for (m = 0; m < Ns; m++) {

		int Nc = 0;
		for (j = 1; j <= N; j++) {
			for (i = 1; i <= M; i++) {
				if (candidates[j][i]) {
					cand_copy[Nc++] =  (j << 8) | i;
					candidates[j][i] = 0;
				}
			}
		}

//		printf("%d\n", Nc);
		for (k = 0; k < Nc; k++) {
			uint16_t j = (cand_copy[k] & 0xFF00) >> 8;
			uint16_t i =  cand_copy[k] & 0x00FF;
			uint16_t x = (maze[S[m]][j][i] & 0xFF00) >> 8;
			uint16_t y =  maze[S[m]][j][i] & 0x00FF;
//			printf("%d %d -> %d\n", x, y, maze[S[m]][j][i]);
			candidates[x][y] = 1;
		}

	}

	Ncand = 0;
	for (j = 1; j <= N; j++) {
		for (i = 1; i <= M; i++) {
				Ncand += candidates[j][i];
		}
	}

	printf("%d\n", Ncand);
	for (j = 1; j <= N; j++) {
		for (i = 1; i <= M; i++) {
			if (candidates[j][i]) {
				printf("%d %d\n", j, i);
			}
		}
	}


}