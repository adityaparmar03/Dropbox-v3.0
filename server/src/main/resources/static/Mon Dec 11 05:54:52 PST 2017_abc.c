#include <stdio.h>
#include <cpuid.h>
#include <stdint.h>
//#include <iostream>
//#include <string>



int
main(int argc, char **argv)
{
 uint32_t eax, ebx, ecx, edx;

 char str[4];

 __cpuid(0x0, eax, ebx, ecx, edx);
 printf("CPUID(0x0).EAX=0x%x\n", eax);
 printf("CPUID(0x0).EBX=0x%x\n", ebx);

 printf("%c", ebx & 0xff);
 printf("%c", (ebx & 0xff00)>>8);
 printf("%c", (ebx & 0xff0000)>>16);
 printf("%c", (ebx)>>24);
 printf("%c", edx & 0xff);
 printf("%c", (edx & 0xff00)>>8);
 printf("%c", (edx & 0xff0000)>>16);
 printf("%c", (edx)>>24);
 printf("%c", ecx & 0xff);
 printf("%c", (ecx & 0xff00)>>8);
 printf("%c", (ecx & 0xff0000)>>16);
 printf("%c\n", (ecx)>>24);

 __cpuid(0x4fffffff, eax, ebx, ecx, edx);
 __cpuid(0x0, eax, ebx, ecx, edx);
 printf("%c", ebx & 0xff);
 printf("%c", (ebx & 0xff00)>>8);
 printf("%c", (ebx & 0xff0000)>>16);
 printf("%c", (ebx)>>24);
 printf("%c", edx & 0xff);
 printf("%c", (edx & 0xff00)>>8);
 printf("%c", (edx & 0xff0000)>>16);
 printf("%c", (edx)>>24);
 printf("%c", ecx & 0xff);
 printf("%c", (ecx & 0xff00)>>8);
 printf("%c", (ecx & 0xff0000)>>16);
 printf("%c\n", (ecx)>>24);

 __cpuid(0x4fffffff, eax, ebx, ecx, edx);
 __cpuid(0x0, eax, ebx, ecx, edx);
 printf("%c", ebx & 0xff);
 printf("%c", (ebx & 0xff00)>>8);
 printf("%c", (ebx & 0xff0000)>>16);
 printf("%c", (ebx)>>24);
 printf("%c", edx & 0xff);
 printf("%c", (edx & 0xff00)>>8);
 printf("%c", (edx & 0xff0000)>>16);
 printf("%c", (edx)>>24);
 printf("%c", ecx & 0xff);
 printf("%c", (ecx & 0xff00)>>8);
 printf("%c", (ecx & 0xff0000)>>16);
 printf("%c\n", (ecx)>>24);
 return 0;
}
