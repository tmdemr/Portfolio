#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true;
    int size_book = phone_book.size();
    
    sort(phone_book.begin(), phone_book.end());
    
    for(int i = 0; i < size_book; i++){
        if(i+1 >= size_book)
            return answer;
        if(phone_book[i].find(phone_book[i+1].substr(0,phone_book[i].length())) == 0){
            answer = false;
            return answer;
       }
    }
}