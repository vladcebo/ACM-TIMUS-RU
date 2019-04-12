#include <stdio.h>
#include <stdint.h>
#include <stdarg.h>

#define DEBUG 0

#if DEBUG
#define PRINT_DEBUG(fmt, ...) printf(fmt, ##__VA_ARGS__)
#else
#define PRINT_DEBUG(fmt, ...)
#endif

/* States:
 * 0  - start      : ignore trailing \n\r characters until first valid or incalid char is found
 * 1  - sign_read  : sign is read, wait for digits or point
 * 2  - int_part   : reads integers into the integer part of the number
 * 3  - point_read : switches to fractional part reading
 * 4  - frac_part  : reads fractional part of the real number
 * 5  - exp_read   : switch to exponential part read
 * 6  - exp_sign   : exp sign is read
 * 7  - exp_part   : reads digits into the exponential part of the number
 * 8  - fail       : invalidation detected, invalid format
 * 9  - done       : number is read, format it and print it
 * 10 - exit       : all readings are done
 * 11 - ignore_chrs: ignore all chars so we don't need them when input is already invalid
 * 12 - read_N     : read N so we don't have problems with reading it as a valid nr
 */

void StartState(char c);
void SignRead(char c);
void IntPart(char c);
void PointRead(char c);
void FracPart(char c);
void ExpRead(char c);
void ExpSign(char c);
void ExpPart(char c);
void Fail(char c);
void Done(char c);
void Exit(char c);
void IgnoreChrs(char c);
void FlushN(char c);

/* Events
 *
 *	0 - sign read   ('+', '-')
 *	1 - digit read  ('0'..'9')
 *	2 - point read  ('.')
 *	3 - exponential ('e', 'E')
 *	4 - newline     ('\n')
 *	5 - hashtag     ('#')
 *  6 - invalid
 *
 */

typedef struct state {

	void (*action)(char c);
	uint8_t  next[7];

} State;

State transitions[] = {
				    /* s   d   p   e   n   #   i */
		{StartState, { 1,  2,  3,  8,  0, 10,  8}}, /* 0  */
		{SignRead,   { 8,  2,  3,  8,  12, 8,  8}}, /* 1  */
		{IntPart,    { 8,  2,  3,  5,  9,  8,  8}}, /* 2  */
		{PointRead,  { 8,  4,  8,  8,  12, 8,  8}}, /* 3  */
		{FracPart,   { 8,  4,  8,  5,  9,  8,  8}}, /* 4  */
		{ExpRead,    { 6,  7,  8,  8,  12, 8,  8}}, /* 5  */
		{ExpSign,    { 8,  7,  8,  8,  12, 8,  8}}, /* 6  */
		{ExpPart,    { 8,  7,  8,  8,  9,  8,  8}}, /* 7  */
		{Fail,       {11, 11, 11, 11, 11, 11, 11}}, /* 8  */
		{Done,       { 0,  0,  0,  0,  9,  0,  0}}, /* 9  */
		{Exit,       { 8,  8,  8,  8,  10, 8,  8}}, /* 10 */
		{IgnoreChrs, {11, 11, 11, 11, 12, 11, 11}}, /* 11 */
		{FlushN,     { 0,  0,  0,  0,  0,  0,  0}}  /* 12 */
};

uint8_t nr[202];
uint8_t nr_len;
int16_t point_pos;
int64_t exp_part;

uint8_t  exp_digits;
int8_t sign;
int8_t exp_sign;

uint8_t current_state = 0;
uint8_t fail_wrriten  = 0;
char buff[105];


void StartState(char c) {
	PRINT_DEBUG("Start\n");
	sign       = +1;
	exp_sign   = +1;
	nr_len     = 0;
	point_pos  = 0;
	exp_part   = 0;
	exp_digits = 0;

	int i;
	for (i = 0; i < 202; i++) {
		nr[i] = 0;
	}
}

void SignRead(char c) {
	PRINT_DEBUG("SignRead\n");
	sign = (c == '-' ? -1 : +1);
}

void IntPart(char c) {
	PRINT_DEBUG("IntPart\n");
	nr[nr_len++] = c - '0';
	point_pos = nr_len;
}

void PointRead(char c) {
	PRINT_DEBUG("PointPart\n");
	/* nothing to do */
}

void FracPart(char c) {
	PRINT_DEBUG("FracPart\n");
	nr[nr_len++] = c - '0';
}

void ExpRead(char c) {
	PRINT_DEBUG("ExpRead\n");
	/* nothing to do */
}

void ExpSign(char c) {
	PRINT_DEBUG("ExpSing\n");
	exp_sign = (c == '-' ? -1 : +1);
}

void ExpPart(char c) {
	PRINT_DEBUG("ExpPart\n");
	exp_part = exp_part*10 + (c - '0');
	if (exp_part != 0 || exp_digits != 0)
		exp_digits++;
}

void Fail(char c) {
	printf("Not a floating point number\n");
	fail_wrriten = 1;
}

void Done(char c) {

	/* adjust nr */
	int i, N;
	gets(buff);
	N = atoi(buff);
	PRINT_DEBUG("N = %d\n", N);
	/* compute the floating point position (shifted by exponential) */

	/* Fractional part */
	if (exp_digits > 3) {
		exp_part = 500;
	}

	point_pos = point_pos + exp_part*exp_sign;

	int16_t max_pp  = (point_pos > 200) ? 200 : point_pos;

	for (i = 0; i < nr_len; i++) {
		PRINT_DEBUG("%d", nr[i]);
	}
	PRINT_DEBUG("\n");
	PRINT_DEBUG("point_pos = %d\n", point_pos);
	PRINT_DEBUG("exp_part = %ld\n", exp_part);
	/* Leading zeros */
	int inon_zero_idx = -1;
	for (i = 0; i < max_pp; i++) {
		if (nr[i] != 0) {
			inon_zero_idx = i;
			break;
		}
	}

	/* Trailing zeros */
	int fnon_zero_idx = -1;
	for (i = point_pos; i < point_pos + N; i++) {
		if (i >= 0 && i < 200) {
			if (nr[i] != 0) {
				fnon_zero_idx = i;
				break;
			}
		}
	}

	/* Printing */

	/* Print sign */
	int chars_written = 0;
	if (inon_zero_idx == -1) {
		if (fnon_zero_idx != -1) {
			if (sign == -1) {
				chars_written++;
				printf("-");
			}
		}
		chars_written++;
		printf("0");
	} else {
		if (sign == -1) {
			chars_written++;
			printf("-");
		}
		/* Print trailing zeros */
		for (i = inon_zero_idx; i < point_pos; i++) {
			chars_written++;
			if (i >= 200) {
				printf("0");
			} else {
				printf("%d", nr[i]);
			}
		}
	}


	if (N > 0) {
		chars_written++;
		printf(".");

		for (i = point_pos; i < point_pos + N; i++) {
			if (i >= 0 && i < 200) {
				chars_written++;
				printf("%d", nr[i]);
			} else {
				chars_written++;
				printf("0");
			}
		}
	}

	chars_written++;
	printf("\n");


	current_state = 0;
	StartState(0);
}

void Exit(char c) {
	PRINT_DEBUG("Exit\n");
}

void IgnoreChrs(char c) {
	PRINT_DEBUG("IgnoreChrs\n");
	/* Nothing to do */
}

void FlushN(char c) {
	if (!fail_wrriten) {
		printf("Not a floating point number\n");
	}
	fail_wrriten = 0;
	PRINT_DEBUG("FlushN\n");
	/* we can use the same buffer since we don't need the content anymore */
	gets(buff);
	PRINT_DEBUG("GOT BUFFER: %s", buff);
	/* Go to start state without reading a character */
	current_state = 0;
	StartState(0);
}

static uint8_t GetEvent(char c) {

	if (c == '+' || c == '-') {
		return 0;
	} else if (c >= '0' && c <= '9') {
		return 1;
	} else if (c == '.') {
		return 2;
	} else if (c == 'e' || c == 'E') {
		return 3;
	} else if (c == '\0' || c == '\r' || c == '\n') {
		return 4;
	} else if (c == '#') {
		return 5;
	} else {
		return 6;
	}
	return 0;
}

int main(void) {


	current_state = 0;
	StartState(0);

	while (gets(buff) != NULL) {
		if (strlen(buff) == 0) continue;
		PRINT_DEBUG("GOT BUFFER: %s", buff);
		uint8_t i = 0;
		char c;
		do {
			c = buff[i++];
			uint8_t event = GetEvent(c);
			PRINT_DEBUG("char read c = %c %d, event = %d\n", c, c, event);
			current_state = transitions[current_state].next[event];
			transitions[current_state].action(c);

		} while (c != '\0');
	}

	return 0;
}
