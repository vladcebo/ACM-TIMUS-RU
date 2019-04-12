#include <stdio.h>
#include <string.h>
#include <ctype.h>

typedef enum STATE {SS = 0, RL = 1, RW = 2, RP = 3, SE = 4} STATE;
typedef enum EVENT {LT = 0, PT = 1, SF = 2} 				EVENT;

void emptyRead(char c);
void wordRead(char c);
void startWord(char c);
void startSentence(char c);
EVENT getEvent(char c);

typedef struct fsm_tag {

	void (*action)(char c);
	STATE next[3];
} fsm_t;

fsm_t FSM[] = {	  			   /*LT  PT  SF */
/* SS */		{startSentence, {RL, RP, SE}},
/* RL */        {wordRead,   	{RL, RP, SE}},
/* RW */        {startWord, 	{RL, RP, SE}},
/* RP */        {emptyRead,     {RW, RP, SE}},
/* SE */        {emptyRead,     {SS, SE, SE}}
};

int mistakes 	   = 0;


void emptyRead(char c) {

}

void wordRead(char c) {
//	printf("Word read: %c\n", c);
	if (isupper(c)) {
//		printf("MISTAKE\n");
		mistakes++;
	}
}

void startWord(char c) {
//	printf("Start word char: %c\n", c);
}

void startSentence(char c) {
//	printf("Start sentence char: %c\n", c);
	if (!isupper(c) && (getEvent(c) != PT)) {
//		printf("MISTAKE\n");
		mistakes++;
	}
}

EVENT getEvent(char c) {

	if (isalpha(c)) {
		return LT;
	} else if (isdigit(c) || (c == ','|| c == ';' || c == ':'
						  ||  c == '-' || c == ' ' || c == '\n' || c == '\r')) {
		return PT;
	} else if (c == '.' || c == '!' || c == '?') {
		return SF;
	}

	return PT;
}

STATE currentState = SE;

int main(int argc, char **argv) {


	char c;
	while ((c = getchar()) != EOF) {
		EVENT e = getEvent(c);
//			printf("Read character %c\n", buff[i]);
		currentState = FSM[currentState].next[e];
		FSM[currentState].action(c);
	}

	printf("%d\n", mistakes);

	return 0;

}