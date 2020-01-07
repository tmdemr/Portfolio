#include <stdio.h>
#include <Windows.h>

#define LOG printf

int main(){
    STARTUPINFO si;
    PROCESS_INFORMATION pi;

    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    ZeroMemory(&pi, sizeof(pi));
    
    LOG("CreateProcess Working...");


    if ( !CreateProcess(NULL, "workprocess.exe", NULL, NULL, FALSE, 0, NULL, NULL, &si, &pi) ){

        LOG("CreateProcess failed (%d)\n", GetLastError());

        return 1;
    }

    WaitForSingleObject(pi.hProcess, INFINITE);
    
    LOG("\nCreateProcess Finish...\n");

    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);


}