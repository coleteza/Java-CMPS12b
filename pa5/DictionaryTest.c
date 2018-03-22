//DictionaryTest.c
//Cole Teza
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
   int i;

   for(i=0; i<7; i++){
      insert(A, word1[i], word2[i]);

   printDictionary(stdout, A);

   delete(A, "one");
   delete(A, "three");
   delete(A, "seven");

   printDictionary(stdout, A);

   printf("%s\n", (lookup(A));

   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false");

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
