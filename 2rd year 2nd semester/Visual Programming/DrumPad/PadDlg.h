
// PadDlg.h: 헤더 파일
//

#pragma once

#include "mmsystem.h" //MCI 사용 헤더
#include "digitalv.h" //볼륨 조절을 위한 헤더
#pragma comment (lib,"winmm.lib")

// CPadDlg 대화 상자
class CPadDlg : public CDialogEx
{
// 생성입니다.

public:
	CPadDlg(CWnd* pParent = nullptr);	// 표준 생성자입니다.

// 대화 상자 데이터입니다.
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_PAD_DIALOG };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 지원입니다.


// 구현입니다.
protected:
	HICON m_hIcon;

	// 생성된 메시지 맵 함수
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()

public :

	MCI_OPEN_PARMS      mciOpen; //파일을 로드
	MCI_PLAY_PARMS       mciPlay; //파일을 재생
	MCI_STATUS_PARMS   mciStatus; //파일의 상태

	UINT wDeviceID = 0;

	DWORD LoadWAV(HWND hWnd, LPCTSTR lpszWave)  //파일 불러오기에 관한 함수
{
		DWORD Result;

		mciOpen.lpstrDeviceType = L"WaveAudio";

		//WaveAudio = WAV   MPEGVideo = mp3 

		mciOpen.lpstrElementName = lpszWave;

		Result = mciSendCommand(wDeviceID, MCI_OPEN, MCI_OPEN_TYPE | MCI_OPEN_ELEMENT, (DWORD)(LPVOID)&mciOpen);

		if (Result)
			return Result;

		wDeviceID = mciOpen.wDeviceID;

		mciPlay.dwCallback = (DWORD)hWnd;

		if (Result)
			return Result;

		return 0;
	}
	
	DWORD Bass;
	DWORD Clap1;
	DWORD Clap2;
	DWORD Claves;
	DWORD Cymbal1;
	DWORD Cymbal2;
	DWORD Cymbal3;
	DWORD Hihat1;
	DWORD Hihat2;
	DWORD Hihat3;
	DWORD Snare1;
	DWORD Snare2;
	DWORD Tom1;
	DWORD Tom2;
	DWORD Tom3;
	DWORD Tom4;
	DWORD Instrument1;
	DWORD Instrument2;
	DWORD Instrument3;
	DWORD Instrument4;
	DWORD Instrument5;
	DWORD Instrument6;
	DWORD Instrument7;
	DWORD Lead1;
	DWORD Lead2;
	DWORD Lead3;
	DWORD Lead4;
	DWORD Lead5;
	DWORD Lead6;
	DWORD Lead7;



	/*
ass = LoadWAV(NULL, (LPCTSTR)L"Bass.wav");  /// b h123
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
	*/











	void Sclose() { //파일 불러오기 종료

		if (wDeviceID > 0)

		{
			mciSendCommand(1, MCI_CLOSE, 0, (DWORD)(LPVOID)NULL);
			mciSendCommand(2, MCI_CLOSE, 0, (DWORD)(LPVOID)NULL);
		}
	}

	afx_msg void OnBnClickedButton1();
	afx_msg void OnBnClickedButton2();
	afx_msg void OnDestroy();
	SOCKET mh_socket;

	char m_connect_flag;
protected:
	afx_msg LRESULT On25001(WPARAM wParam, LPARAM lParam);

public:
	void CPadDlg::SendFrameData(SOCKET ah_socket, char a_message_id, unsigned short int a_body_size, char* ap_send_data);
	char* CPadDlg::StringToChar(CString str);
	void CPadDlg::Playq();
	void CPadDlg::Playw();
	void CPadDlg::Playe();
	void CPadDlg::Playr();
	void CPadDlg::Playa();
	void CPadDlg::Plays();
	void CPadDlg::Playd();
	void CPadDlg::Playf();
	void CPadDlg::Playz();
	void CPadDlg::Playx();
	void CPadDlg::Playc();
	void CPadDlg::Playv();
	void CPadDlg::Play1();
	void CPadDlg::Play2();
	void CPadDlg::Play3();
	void CPadDlg::Play4();
	void CPadDlg::Playqq();
	void CPadDlg::Playww();
	void CPadDlg::Playee();
	void CPadDlg::Playrr();
	void CPadDlg::Playaa();
	void CPadDlg::Playss();
	void CPadDlg::Playdd();
	void CPadDlg::Playff();
	void CPadDlg::Playzz();
	void CPadDlg::Playxx();
	void CPadDlg::Playcc();
	void CPadDlg::Playvv();
	void CPadDlg::Play11();
	void CPadDlg::Play22();

	
protected:
	afx_msg LRESULT On25002(WPARAM wParam, LPARAM lParam);
public:
	CBitmapButton b_1;
	CBitmapButton b_10;
	CBitmapButton b_11;
	CBitmapButton b_12;
	CBitmapButton b_14;
	CBitmapButton b_15;
	CBitmapButton b_16;
	CBitmapButton b_2;
	CBitmapButton b_13;
	CBitmapButton b_3;
	CBitmapButton b_4;
	CBitmapButton b_5;
	CBitmapButton b_6;
	CBitmapButton b_7;
	CBitmapButton b_8;
	CBitmapButton b_9;
	afx_msg void OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags);
	CString SoundName;
	int x = 0;
	virtual BOOL PreTranslateMessage(MSG* pMsg);
	
};
