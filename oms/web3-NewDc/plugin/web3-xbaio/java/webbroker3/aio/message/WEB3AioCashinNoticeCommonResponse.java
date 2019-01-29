head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡共通レスポンスクラス(WEB3AioCashinNoticeCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入金連絡共通レスポンス)<BR>
 * 入金連絡共通レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCommonResponse extends WEB3GenResponse 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111452L;    
    /**
     * (顧客名称)<BR>
     * 顧客の名称
     */
    public String accountName;
    
    /**
     * (顧客コード)<BR>
     * 顧客の顧客コード
     */
    public String accountCode;
    
    /**
     * (振込日)<BR>
     * 画面にて入力された振込日
     */
    public String transferDate;
    
    /**
     * (振込先金融機@関コード)<BR>
     * 画面にて選択された振込先金融機@関のコード
     */
    public String finInstitutionCode;
    
    /**
     * (振込先金融機@関名称)<BR>
     * 画面にて選択された振込先金融機@関の名称
     */
    public String finInstitutionName;
    
    /**
     * (入金額)<BR>
     * 画面にて入力された入金額
     */
    public String cashinAmt;
    
    /**
     * (メールアドレス)<BR>
     * 画面にて入力されたメールアドレス
     */
    public String emailAddress;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinNoticeCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
