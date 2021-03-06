
// Pad.cpp: 응용 프로그램에 대한 클래스 동작을 정의합니다.
//

#include "stdafx.h"
#include "Pad.h"
#include "PadDlg.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CPadApp

BEGIN_MESSAGE_MAP(CPadApp, CWinApp)
	ON_COMMAND(ID_HELP, &CWinApp::OnHelp)
END_MESSAGE_MAP()


// CPadApp 생성

CPadApp::CPadApp()
{
	// 다시 시작 관리자 지원
	m_dwRestartManagerSupportFlags = AFX_RESTART_MANAGER_SUPPORT_RESTART;

	// TODO: 여기에 생성 코드를 추가합니다.
	// InitInstance에 모든 중요한 초기화 작업을 배치합니다.
}


// 유일한 CPadApp 개체입니다.

CPadApp theApp;


// CPadApp 초기화

BOOL CPadApp::InitInstance()
{
	CWinApp::InitInstance();
	WSADATA temp;
	//소켓 사용은 해당 코드 이후 부터 가능함****
	WSAStartup(0x0202, &temp);

	CPadDlg dlg;
	m_pMainWnd = &dlg;
	dlg.DoModal();

	WSACleanup();
	//소켓을 지운다->쓰고 있는 건 다 쓰고 지움
	return FALSE;

}

