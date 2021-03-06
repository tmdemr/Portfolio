
// PadDlg.cpp: 구현 파일
//

#include "stdafx.h"
#include "Pad.h"
#include "PadDlg.h"
#include "afxdialogex.h"
//#include "resource.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif
#pragma warning (disable : 4996)
#pragma warning (disable : 0020)

// 응용 프로그램 정보에 사용되는 CAboutDlg 대화 상자입니다.

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// 대화 상자 데이터입니다.
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_ABOUTBOX };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 지원입니다

// 구현입니다.
protected:
	DECLARE_MESSAGE_MAP()
public:
protected:
};

CAboutDlg::CAboutDlg() : CDialogEx(IDD_ABOUTBOX)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CPadDlg 대화 상자



CPadDlg::CPadDlg(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_PAD_DIALOG, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CPadDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_BUTTON1, b_1);
	DDX_Control(pDX, IDC_BUTTON2, b_2);
	DDX_Control(pDX, IDC_BUTTON3, b_3);
	DDX_Control(pDX, IDC_BUTTON4, b_4);
	DDX_Control(pDX, IDC_BUTTON5, b_5);
	DDX_Control(pDX, IDC_BUTTON6, b_6);
	DDX_Control(pDX, IDC_BUTTON7, b_7);
	DDX_Control(pDX, IDC_BUTTON8, b_8);
	DDX_Control(pDX, IDC_BUTTON9, b_9);
	DDX_Control(pDX, IDC_BUTTON10, b_10);
	DDX_Control(pDX, IDC_BUTTON11, b_11);
	DDX_Control(pDX, IDC_BUTTON12, b_12);
	DDX_Control(pDX, IDC_BUTTON13, b_13);
	DDX_Control(pDX, IDC_BUTTON14, b_14);
	DDX_Control(pDX, IDC_BUTTON15, b_15);
	DDX_Control(pDX, IDC_BUTTON16, b_16);
	DDX_Control(pDX, IDC_BUTTON1, b_1);
	DDX_Control(pDX, IDC_BUTTON10, b_10);
	DDX_Control(pDX, IDC_BUTTON11, b_11);
	DDX_Control(pDX, IDC_BUTTON12, b_12);
	DDX_Control(pDX, IDC_BUTTON13, b_13);
	DDX_Control(pDX, IDC_BUTTON14, b_14);
	DDX_Control(pDX, IDC_BUTTON15, b_15);
	DDX_Control(pDX, IDC_BUTTON16, b_16);
	DDX_Control(pDX, IDC_BUTTON2, b_2);
	DDX_Control(pDX, IDC_BUTTON3, b_3);
	DDX_Control(pDX, IDC_BUTTON4, b_4);
	DDX_Control(pDX, IDC_BUTTON5, b_5);
	DDX_Control(pDX, IDC_BUTTON6, b_6);
	DDX_Control(pDX, IDC_BUTTON7, b_7);
	DDX_Control(pDX, IDC_BUTTON8, b_8);
	DDX_Control(pDX, IDC_BUTTON9, b_9);
}

BEGIN_MESSAGE_MAP(CPadDlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_BUTTON1, &CPadDlg::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &CPadDlg::OnBnClickedButton2)
	ON_WM_DESTROY()
	ON_MESSAGE(25001, &CPadDlg::On25001)
	ON_MESSAGE(25002, &CPadDlg::On25002)
	ON_WM_KEYDOWN()
	ON_WM_KEYUP()
END_MESSAGE_MAP()


// CPadDlg 메시지 처리기

BOOL CPadDlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 시스템 메뉴에 "정보..." 메뉴 항목을 추가합니다.

	// IDM_ABOUTBOX는 시스템 명령 범위에 있어야 합니다.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != nullptr)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// 이 대화 상자의 아이콘을 설정합니다.  응용 프로그램의 주 창이 대화 상자가 아닐 경우에는
	//  프레임워크가 이 작업을 자동으로 수행합니다.
	SetIcon(m_hIcon, TRUE);			// 큰 아이콘을 설정합니다.
	SetIcon(m_hIcon, FALSE);		// 작은 아이콘을 설정합니다.

	// TODO: 여기에 추가 초기화 작업을 추가합니다.
	//버튼 디자인
	b_1.LoadBitmaps(IDB_RED, IDB_Button_after, NULL, NULL);
	b_2.LoadBitmaps(IDB_RED, IDB_Button_before, NULL, NULL);
	b_3.LoadBitmaps(IDB_RED, IDB_Button_before, NULL, NULL);
	b_4.LoadBitmaps(IDB_RED, IDB_Button_before, NULL, NULL);
	b_5.LoadBitmaps(IDB_YELLOW, IDB_Button_before, NULL, NULL);
	b_6.LoadBitmaps(IDB_YELLOW, IDB_Button_before, NULL, NULL);
	b_7.LoadBitmaps(IDB_YELLOW, IDB_Button_before, NULL, NULL);
	b_8.LoadBitmaps(IDB_YELLOW, IDB_Button_before, NULL, NULL);
	b_9.LoadBitmaps(IDB_GREEN, IDB_Button_before, NULL, NULL);
	b_10.LoadBitmaps(IDB_GREEN, IDB_Button_before, NULL, NULL);
	b_11.LoadBitmaps(IDB_GREEN, IDB_Button_before, NULL, NULL);
	b_12.LoadBitmaps(IDB_GREEN, IDB_Button_before, NULL, NULL);
	b_13.LoadBitmaps(IDB_BLUE, IDB_Button_before, NULL, NULL);
	b_14.LoadBitmaps(IDB_BLUE, IDB_Button_before, NULL, NULL);
	b_15.LoadBitmaps(IDB_BLUE, IDB_Button_before, NULL, NULL);
	b_16.LoadBitmaps(IDB_BLUE, IDB_Button_before, NULL, NULL);
	

	b_1.SizeToContent();
	b_2.SizeToContent();
	b_3.SizeToContent();
	b_4.SizeToContent();
	b_5.SizeToContent();
	b_6.SizeToContent();
	b_7.SizeToContent();
	b_8.SizeToContent();
	b_9.SizeToContent();
	b_10.SizeToContent();
	b_11.SizeToContent();
	b_12.SizeToContent();
	b_13.SizeToContent();
	b_14.SizeToContent();
	b_15.SizeToContent();
	b_16.SizeToContent();



    //사운드 출력 초기화
	Bass = LoadWAV(NULL, (LPCTSTR)L"Bass.wav");  /// b h123
	Clap1 = LoadWAV(NULL, (LPCTSTR)L"Clap1.wav");  //clap12 snare 12
	Clap2 = LoadWAV(NULL, (LPCTSTR)L"Clap2.wav");
	Claves = LoadWAV(NULL, (LPCTSTR)L"Claves.wav"); // cym123 cl1
	Cymbal1 = LoadWAV(NULL, (LPCTSTR)L"Cymbal1.wav");
	Cymbal2 = LoadWAV(NULL, (LPCTSTR)L"Cymbal2.wav");
	Cymbal3 = LoadWAV(NULL, (LPCTSTR)L"Cymbal3.wav");
	Hihat1 = LoadWAV(NULL, (LPCTSTR)L"Hihat1.wav");
	Hihat2 = LoadWAV(NULL, (LPCTSTR)L"Hihat2.wav");
	Hihat3 = LoadWAV(NULL, (LPCTSTR)L"Hihat3.wav");
	Snare1 = LoadWAV(NULL, (LPCTSTR)L"Snare1.wav");
	Snare2 = LoadWAV(NULL, (LPCTSTR)L"Snare2.wav");
	Tom1 = LoadWAV(NULL, (LPCTSTR)L"Tom1.wav"); // tom1234
	Tom2 = LoadWAV(NULL, (LPCTSTR)L"Tom2.wav");
	Tom3 = LoadWAV(NULL, (LPCTSTR)L"Tom3.wav");
	Tom4 = LoadWAV(NULL, (LPCTSTR)L"Tom4.wav");
	Instrument1 = LoadWAV(NULL, (LPCTSTR)L"Instrument1.wav");
	Instrument2 = LoadWAV(NULL, (LPCTSTR)L"Instrument2.wav");
	Instrument3 = LoadWAV(NULL, (LPCTSTR)L"Instrument3.wav");
	Instrument4 = LoadWAV(NULL, (LPCTSTR)L"Instrument4.wav");
	Instrument5 = LoadWAV(NULL, (LPCTSTR)L"Instrument5.wav");
	Instrument6 = LoadWAV(NULL, (LPCTSTR)L"Instrument6.wav");
	Instrument7 = LoadWAV(NULL, (LPCTSTR)L"Instrument7.wav");
	Lead1 = LoadWAV(NULL, (LPCTSTR)L"Lead1.wav");
	Lead2 = LoadWAV(NULL, (LPCTSTR)L"Lead2.wav");
	Lead3 = LoadWAV(NULL, (LPCTSTR)L"Lead3.wav");
	Lead4 = LoadWAV(NULL, (LPCTSTR)L"Lead4.wav");
	Lead5 = LoadWAV(NULL, (LPCTSTR)L"Lead5.wav");
	Lead6 = LoadWAV(NULL, (LPCTSTR)L"Lead6.wav");
	Lead7 = LoadWAV(NULL, (LPCTSTR)L"Lead7.wav");


	//네트워크 초기화
	mh_socket = socket(AF_INET, SOCK_STREAM, 0);//소켓 생성
	struct sockaddr_in srv_addr;
	memset(&srv_addr, 0, sizeof(struct sockaddr_in));
	srv_addr.sin_family = AF_INET;
	srv_addr.sin_addr.s_addr = inet_addr("127.0.0.1"); //접속할 서버의 ip
	srv_addr.sin_port = htons(18000);
	WSAAsyncSelect(mh_socket, m_hWnd, 25001, FD_CONNECT);
	m_connect_flag = 1;//접속중 상태를 나타내는 플래그값

	AfxMessageBox(_T("서버에 접속을 시도합니다..."));

	connect(mh_socket, (LPSOCKADDR)&srv_addr, sizeof(srv_addr));
	//서버가 실행되고(listen) 있어야됨, 커넥트 함수가 실패하면 최대 28초 동안 응답없음 상태에 빠짐


	return TRUE;  // 포커스를 컨트롤에 설정하지 않으면 TRUE를 반환합니다.
}

void CPadDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// 대화 상자에 최소화 단추를 추가할 경우 아이콘을 그리려면
//  아래 코드가 필요합니다.  문서/뷰 모델을 사용하는 MFC 응용 프로그램의 경우에는
//  프레임워크에서 이 작업을 자동으로 수행합니다.

void CPadDlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 그리기를 위한 디바이스 컨텍스트입니다.

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 클라이언트 사각형에서 아이콘을 가운데에 맞춥니다.
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 아이콘을 그립니다.
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
		CClientDC dc(this);
		CDC MemDC;
		MemDC.CreateCompatibleDC(&dc);
		CBitmap bitmap;
		bitmap.LoadBitmapW(IDB_BITMAP3);
		CBitmap *oldBitmap = MemDC.SelectObject(&bitmap);
		dc.BitBlt(0, 0, 500, 500, &MemDC, 30, 30, SRCCOPY);
		dc.SelectObject(oldBitmap);
		
	}
}

// 사용자가 최소화된 창을 끄는 동안에 커서가 표시되도록 시스템에서
//  이 함수를 호출합니다.
HCURSOR CPadDlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}

void CPadDlg::OnBnClickedButton1()
{
	SoundName.Format(_T("q"));
	if (m_connect_flag == 2) {
		SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
	}
}

void CPadDlg::OnBnClickedButton2()
{
	SoundName.Format(_T("w"));
	if (m_connect_flag == 2) {
		SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
	}
}

void CPadDlg::OnDestroy()
{
	CDialogEx::OnDestroy();
	if (mh_socket != INVALID_SOCKET) {
		closesocket(mh_socket);
		mh_socket = INVALID_SOCKET;
	}

	// TODO: 여기에 메시지 처리기 코드를 추가합니다.
}

afx_msg LRESULT CPadDlg::On25001(WPARAM wParam, LPARAM lParam)
{
	if (WSAGETSELECTERROR(lParam)) {
		m_connect_flag = 0;
		closesocket(mh_socket);
		mh_socket = INVALID_SOCKET;
		AfxMessageBox(_T("서버에 접속을 실패했습니다.~"));
	}
	else {
		m_connect_flag = 2;
		WSAAsyncSelect(mh_socket, m_hWnd, 25002, FD_READ | FD_CLOSE);
		AfxMessageBox(_T("서버에 접속했습니다."));
	}

	CString Msg;


	return 0;
}



void CPadDlg::SendFrameData(SOCKET ah_socket, char a_message_id,unsigned short int a_body_size, char* ap_send_data)//헤더와 데이터의 content를 보내는 함수
{
	char *p_send_data = new char[4 + a_body_size];//헤더 4 byte
	*p_send_data = 27;//key의 고유값을 27로 통일
	*(p_send_data + 1) = a_message_id;//message_id
	*(unsigned short *)(p_send_data + 2) = a_body_size;
	//일시적으로 char*를 short*에 맞게 변위를 조절

	memcpy(p_send_data + 4, ap_send_data, a_body_size);//데이터를 복사
	send(ah_socket, p_send_data, a_body_size + 4, 0);// 클라이언트에게 전송
						//send(동기화함수->다 진행될때까지 벗어나지 못함)
	delete[] p_send_data;//전송후 삭제
}

char* CPadDlg::StringToChar(CString str)
{
	int len = str.GetLength() + 1;
	char* ch = new char[len];

	memcpy(ch, (unsigned char*)(LPCTSTR)str, len);

	return ch;
}

void CPadDlg::Playq() {
	Bass = mciSendCommand(1, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Bass = mciSendCommand(1, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playw() {
	Hihat1 = mciSendCommand(2, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Hihat1 = mciSendCommand(2, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playe() {
	Hihat2 = mciSendCommand(3, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Hihat2 = mciSendCommand(3, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playr() {
	Hihat3 = mciSendCommand(4, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Hihat3 = mciSendCommand(4, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playa() {
	Clap1 = mciSendCommand(5, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Clap1 = mciSendCommand(5, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Plays() {
	Clap2 = mciSendCommand(6, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Clap2 = mciSendCommand(6, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playd() {
	Snare1 = mciSendCommand(7, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Snare1 = mciSendCommand(7, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playf() {
	Snare2 = mciSendCommand(8, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Snare2 = mciSendCommand(8, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playz() {
	Cymbal1 = mciSendCommand(9, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Cymbal1 = mciSendCommand(9, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playx() {
	Cymbal2 = mciSendCommand(10, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Cymbal2 = mciSendCommand(10, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playc() {
	Cymbal3 = mciSendCommand(11, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Cymbal3 = mciSendCommand(11, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playv() {
	Claves = mciSendCommand(12, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Claves = mciSendCommand(12, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Play1() {
	Tom1 = mciSendCommand(13, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Tom1 = mciSendCommand(13, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Play2() {
	Tom2 = mciSendCommand(14, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Tom2 = mciSendCommand(14, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Play3() {
	Tom3 = mciSendCommand(15, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Tom3 = mciSendCommand(15, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Play4() {
	Tom4 = mciSendCommand(16, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Tom4 = mciSendCommand(16, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playqq() {
	Instrument1 = mciSendCommand(17, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument1 = mciSendCommand(17, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}

void CPadDlg::Playww() {
	Instrument2 = mciSendCommand(18, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument2 = mciSendCommand(18, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playee() {
	Instrument3 = mciSendCommand(19, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument3 = mciSendCommand(19, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playrr() {
	Instrument4 = mciSendCommand(20, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument4 = mciSendCommand(20, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playaa() {
	Instrument5 = mciSendCommand(21, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument5 = mciSendCommand(21, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playss() {
	Instrument6 = mciSendCommand(22, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument6 = mciSendCommand(22, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playdd() {
	Lead1 = mciSendCommand(23, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead1 = mciSendCommand(23, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playff() {
	Lead2 = mciSendCommand(24, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead2 = mciSendCommand(24, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playzz() {
	Lead3 = mciSendCommand(25, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead3 = mciSendCommand(25, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playxx() {
	Lead4 = mciSendCommand(26, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead4 = mciSendCommand(26, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playcc() {
	Lead5 = mciSendCommand(27, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead5 = mciSendCommand(27, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Playvv() {
	Lead6 = mciSendCommand(28, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead6 = mciSendCommand(28, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Play11() {
	Lead7 = mciSendCommand(29, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Lead7 = mciSendCommand(29, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}
void CPadDlg::Play22() {
	Instrument7 = mciSendCommand(29, MCI_SEEK, MCI_SEEK_TO_START, (DWORD)(LPVOID)NULL);
	Instrument7 = mciSendCommand(29, MCI_PLAY, MCI_NOTIFY, (DWORD)(LPVOID)&mciPlay);
}



afx_msg LRESULT CPadDlg::On25002(WPARAM wParam, LPARAM lParam)
{
	CString str;
	if (WSAGETSELECTEVENT(lParam) == FD_READ) {
		WSAAsyncSelect(wParam, m_hWnd, 25002, FD_CLOSE);
		char key;
		recv(wParam, &key, 1, 0);
		if (key == 27) {
			char message_id;
			recv(wParam, &message_id, 1, 0);
			unsigned short body_size;
			recv(wParam, (char *)&body_size, 2, 0);
			char* p_body_data = NULL;
			if (body_size > 0) {
				p_body_data = new char[body_size];
				int total = 0, x, retry = 0;
				while (total < body_size) {
					x = recv(wParam, p_body_data + total, body_size - total, 0);
					if (x == SOCKET_ERROR)break;
					total = total + x;
					if (total < body_size) {
						Sleep(50);
						retry++;
						if (retry > 5)break;//재시도 5번 넘으면 break
					}
				}
			}
			if (message_id == 1) {//서버와 다른 부분-1
				//추가할곳
			
			}//여기까지-1
			if (p_body_data != NULL) {
				CString a;
				a = p_body_data;
				if (a == "q"||a == "Q") {
					Playq();
					Sleep(10);
				}
				if (a == "w" || a == "W") {
					Playw();
					Sleep(10);
				}
				if (a == "e" || a == "E") {
					Playe();
					Sleep(10);
				}
				if (a == "r" || a == "R") {
					Playr();
					Sleep(10);
				}
				if (a == "a" || a == "A") {
					Playa();
					Sleep(10);
				}
				if (a == "s" || a == "s") {
					Plays();
					Sleep(10);
				}
				if (a == "d" || a == "D") {
					Playd();
					Sleep(10);
				}
				if (a == "f" || a == "F") {
					Playf();
					Sleep(10);
				}
				if (a == "z" || a == "Z") {
					Playz();
					Sleep(10);
				}
				if (a == "x" || a == "X") {
					Playx();
					Sleep(10);
				}
				if (a == "c" || a == "C") {
					Playc();
					Sleep(10);
				}
				if (a == "v" || a == "V") {
					Playv();
					Sleep(10);
				}
				if (a == "t" || a == "T") {
					Play1();
					Sleep(10);
				}
				if (a == "y" || a == "Y") {
					Play2();
					Sleep(10);
				}
				if (a == "u" || a == "U") {
					Play3();
					Sleep(10);
				}
				if (a == "i" || a == "I") {
					Play4();
					Sleep(10);
				}
				if (a == "o" || a == "O") {
					Playqq();
					Sleep(10);
				}
				if (a == "p" || a == "P") {
					Playww();
					Sleep(10);
				}
				if (a == "[") {
					Playee();
					Sleep(10);
				}
				if (a == "]") {
					Playrr();
					Sleep(10);
				}
				if (a == "g" || a == "G") {
					Playaa();
					Sleep(10);
				}
				if (a == "h" || a == "H") {
					Playss();
					Sleep(10);
				}
				if (a == "j" || a == "J") {
					Playdd();
					Sleep(10);
				}
				if (a == "k" || a == "K") {
					Playff();
					Sleep(10);
				}
				if (a == "l" || a == "L") {
					Playzz();
					Sleep(10);
				}
				if (a == "b" || a == "B") {
					Playxx();
					Sleep(10);
				}
				if (a == "n" || a == "N") {
					Playcc();
					Sleep(10);
				}
				if (a == "m" || a == "M") {
					Playvv();
					Sleep(10);
				}
				if (a == ",") {
					Play11();
					Sleep(10);
				}
				if (a == "v") {
					Play22();
					Sleep(10);
				}
				delete[] p_body_data;
			}

			WSAAsyncSelect(wParam, m_hWnd, 25002, FD_READ | FD_CLOSE);
		}
	}
	else {//FD_CLOSE
		//서버와 다른 부분-2
		closesocket(mh_socket);//클라이언트 소켓 클로즈
		mh_socket = INVALID_SOCKET;
		m_connect_flag = 0;//연결 해제 flag 변경
		//-여기까지-2
	}
	return 0;
}


void CPadDlg::OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags)
{
	// TODO: 여기에 메시지 처리기 코드를 추가 및/또는 기본값을 호출합니다.
	/*switch (nChar)
	{
	case 'w' :
	case 'W' :
		AfxMessageBox(_T("T"));
		CString SoundName;
		SoundName.Format(_T("1")); //첫번째 글자밖에 안나옴 
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
		break;
	}*/

	CDialogEx::OnKeyDown(nChar, nRepCnt, nFlags);
}



BOOL CPadDlg::PreTranslateMessage(MSG* pMsg)
{
	

	// TODO: 여기에 특수화된 코드를 추가 및/또는 기본 클래스를 호출합니다. 

	if (pMsg->hwnd == b_1.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'Z'|| pMsg->wParam == 'z')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("o"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_2.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'X' || pMsg->wParam == 'x')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("p"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_3.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'C' || pMsg->wParam == 'c')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("["));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_4.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'V' || pMsg->wParam == 'v')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("]"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_5.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'Q' || pMsg->wParam == 'q')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("g"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_6.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'W' || pMsg->wParam == 'w')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("h"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_7.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'E' || pMsg->wParam == 'e')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("j"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_8.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'R' || pMsg->wParam == 'r')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("k"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_9.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'A' || pMsg->wParam == 'a')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("l"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_10.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'S' || pMsg->wParam == 's')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("b"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_11.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'D' || pMsg->wParam == 'd')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("n"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_12.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'F' || pMsg->wParam == 'f')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("m"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}

	if (pMsg->hwnd == b_13.GetSafeHwnd() &&	// 이 이벤트가 일어난 곳이 m_edtUserId라는 컨트롤이고 
		pMsg->message == WM_KEYDOWN &&	// 무슨 키가 눌러졌는데 
		pMsg->wParam == 'B' || pMsg->wParam == 'b')	// 그 키가 엔터키이면 
	{
		SoundName.Format(_T("i"));
		if (m_connect_flag == 2) {
			SendFrameData(mh_socket, 1, SoundName.GetLength() + 1, StringToChar(SoundName));
		}
	}




	return false;
}

