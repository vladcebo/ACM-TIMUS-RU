#include <stdio.h>
#include <math.h>

#define PI 3.14159265358979

double Area(int R)
{
    return PI*pow(R, 2);
}

double f(double x, int R)
{
    return Area(R)/2 - (pow(R, 2)*asin(x/R) + x*pow(pow(R,2) - pow(x, 2), 1/2.0) );
}

double Inter(int R1, int R2, int d)
{
    double x0 = ( pow(R1, 2) - pow(R2, 2) + pow(d, 2))/(2*d);
    if (d + R2 <= R1) return Area(R2);
    else if (d + R1 <= R2) return Area(R1);
    else if (d >= R1 + R2 || x0 < -15000 || x0 > 15000
        )
        return 0;

    return f(x0, R1) + f(d - x0, R2);
}

int main(void)
{
    int d, r1, R1, r2, R2;

    scanf("%d%d%d%d%d", &d, &r1, &R1, &r2, &R2);

    double BombedArea = Area(R1) + Area(R2) - Inter(R1, R2, d) - Area(r1) +
            Inter(r1, R2, d) - Area(r2) + Inter(R1, r2, d) -
            Inter(r1, r2, d);
    printf("%.6lf", BombedArea);
    return 0;
}