/**
 * Created at 2020/7/21 15:05.
 * 
 * @author Liangcheng Juves
 */

#include "ansicol.h"

int main(int argc, char const *argv[])
{
    acolprnt(ANSI_BG_BLUE, ANSI_FG_RED, "hello,world\n");
    blackprnt("hello,world\n");
    redprnt("hello,world\n");
    greenprnt("hello,world\n");
    yellowprnt("hello,world\n");
    blueprnt("hello,world\n");
    fuchsiaprnt("hello,world\n");
    cyanprnt("hello,world\n");
    whiteprnt("hello,world\n");
    return 0;
}
