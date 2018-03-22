//Cole Teza
//#1361038
//cteza@ucsc.edu
//PA5

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize=101;

typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
}NodeObj;


typedef NodeObj* Node;

// newNode()
// constructor for private Node type
Node newNode(char* k, char* v) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);

   N->key = k;
   N->value = v;
   N->next = NULL;

   return(N);
}

// nFree()
// destructor for the Node type
void nFree(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		free(*pN);
		*pN = NULL;
	}
}
// DictionaryObj
typedef struct DictionaryObj{
Node* table;
   int numItems;
   
} DictionaryObj;
//+++++++++++++++++++++++++++++++++HASH++METHODS+++++++++++++++++++++++++

// rotate_left()
// rotate the bits in an unsigned int
   unsigned int rotate_left(unsigned int value, int shift) {
      int sizeInBits = 8*sizeof(unsigned int);
      shift = shift & (sizeInBits - 1);
       if ( shift == 0 ){
         return value;
       }
       return (value << shift) | (value >> (sizeInBits - shift));
   }
// pre_hash()
// turn a string into an unsigned int
   unsigned int pre_hash(char* input) {
       unsigned int result = 0xBAE86554;
       while (*input) {
         result ^= *input++;
         result = rotate_left(result, 5);
       }
      return result;
   }

// hash()
// turns a string into an int in the range 0 to tableSize-1
   int hash(char* key){
      return pre_hash(key)%tableSize;
   }

//+++++++++++++++++++++++REFFERENCE++METHODS++++++++++++++++++++++++++++++++
void nDump(Dictionary D, Node N){
	if(N!=NULL){

		nDump(D, N->next); 
		N->next = NULL;
		nFree(&N);
	}
}

Node keyMatch(Dictionary D, char* k){ 
   int x= hash(k);
	Node N = D->table[x];
   //if N is not in Dictionary
	if(N == NULL){ 
		return NULL;
	}
	while(N->next!=NULL){
		if(strcmp(N->key, k)==0){
			return N;
		}

		N = N->next;
	}
	if(strcmp(N->key, k)==0){
		return N;

	}else{

		N = NULL;
		return N;
	}
}
// ++++++++++++++++++++++++PUBLIC++METHODS++++++++++++++++++++++++++++

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D= malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->table= calloc(tableSize, sizeof(NodeObj));
   int i;
   for(i=0; i<tableSize; i++){
      D->table[i]=NULL;
   }
   D->numItems=0;
   return D;
}
//Resets Dictionary to empty state

void makeEmpty(Dictionary D){
	if(D==NULL){
		fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
   //fprint("STEP1\n");
	for(int i=0; i<tableSize; i++){
      //fprint("STEP2\n");
		nDump(D, D->table[i]);
		D->table[i] = NULL;
	}
   //fprint("STEP3\n");
	D->numItems = 0;
}
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if( pD!=NULL && *pD!=NULL ){
		makeEmpty(*pD);
		free((*pD)->table);
		free(*pD);
		*pD = NULL;
	}
}

   //returns 1 if empty 0 if not
   int isEmpty(Dictionary dict){
      if (size(dict)>0){
         return 0;
      }
      else if (size(dict)==0){
         return 1;
      }
      else return 1;
   }
// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out

void printDictionary(FILE* out, Dictionary printD){
	Node N;
	if(printD==NULL){
		fprintf(stderr, "Dictionary Error: You are printing a non existant dictionary\n");
		exit(EXIT_FAILURE);
	}
	for(int i=0; i<tableSize; i++){
		for(N=printD->table[i]; N!=NULL; N=N->next){
			fprintf(out, "%s %s\n", N->key, N->value);
		}
	}
}
  
char* lookup(Dictionary dict, char* k){
      if(dict==NULL){
		   fprintf(stderr, "Dictionary Error: You can't look up\n");
		   exit(EXIT_FAILURE);
   	} 
      //Call keyMatch
      Node temp= keyMatch(dict, k);
      return(temp->value);
   }
 //returns number of items in the dictionary
   int size(Dictionary dict){
      return dict->numItems;
   }

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
	if(D==NULL){
		fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	int x = hash(k);
	if(keyMatch(D, k)==NULL){
		Node N = D->table[x];
      //Create initial root node
		if(N==NULL){
			D->table[x] = newNode(k, v);
		}else{
			Node P = newNode(k, v);
			P->next = N;
			D->table[x] = P;
		}
	}else{
		fprintf(stderr, "Dictionary Error: cannot insert() duplicate keys in Dictionary\n");
		exit(EXIT_FAILURE);
	}
   //incremnet counter
	D->numItems++;
}
// Delete method takes in dictionary and key for the desired deletion 
void delete(Dictionary dict, char* k){

   //temporary node for parsing and matching desired deletion
   NodeObj* P = dict->table[hash(k)];

   //Throw error if dictionary is un-initialized
   if(dict==NULL){
		fprintf(stderr, "Dictionary Error: You can not call delete on a empty dictionary\n");
		exit(EXIT_FAILURE);
   }
   //Throw error if deletion is called on a non-existant key
   if(keyMatch(dict, k)==NULL){
		fprintf(stderr, "Dictionary Error: You are calling delete on a non-existant key\n");
      exit(EXIT_FAILURE);
   }
   //if parsing node is equal to k re-structure and free node
   if(strcmp(P->key, k)==0){ 
		dict->table[hash(k)] = P->next;
		nFree(&P);
   }
   //else parse through table via while loop
   else{
		while(P!=NULL){ 
		   //compare parsing the next parsing node with the desired k deletion 
			if(strcmp(P->next->key, k)!=0){
            //if not equal go one deeper into the linked list
				P = P->next;
         //When you have found the desired key to delete break out
			}else{
				break;
			}
		}
      //if the last value in the linked list, i.e. next value is null 
      Node N = P->next;
		if(N->next == NULL){
         //simply set previous node to null and delete the old end node
			P->next = NULL;
			nFree(&N);
      }
      //if there is a value on either side of the desired delition
      else{
         //set parent node to node after desired deletion then free
			P->next = N->next;
			N->next = NULL;
			nFree(&N);
		}
	
   //decrease numnber of items
	dict->numItems--;
   }



}
