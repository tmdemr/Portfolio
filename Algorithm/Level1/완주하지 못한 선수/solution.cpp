#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    int i = 0;
    sort(participant.begin(), participant.end());
    sort(completion.begin(), completion.end());
    
    
    while(participant[i] == completion[i])
        i++;
    
    answer = participant[i];  
    cout << "\"" << answer << "\"" << "는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다." << endl;

    return answer;

        
}