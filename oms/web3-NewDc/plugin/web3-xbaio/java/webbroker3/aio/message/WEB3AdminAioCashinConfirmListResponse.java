head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認一覧レスポンス(WEB3AdminAioCashinConfirmListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (入金連絡確認一覧レスポンス)<BR>
 * 入金連絡確認一覧レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinConfirmListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_confirm_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101830L;    
        
    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード（自）)<BR>
     * 画面にて入力された口座番号（顧客コード）（自）
     */
    public String minAccountCode;
    
    /**
     * (顧客コード（至）)<BR>
     * 画面にて入力された口座番号（顧客コード）（至）<BR>
     */
    public String maxAccountCode;
    
    /**
     * (連絡日時（自）)<BR>
     * 画面にて入力された連絡日時（自）<BR>
     */
    public Date minNoticeDate;
    
    /**
     * (連絡日時（至）)<BR>
     * 画面にて入力された連絡日時（至）<BR>
     */
    public Date maxNoticeDate;
    
    /**
     * (振込日（自）)<BR>
     * 画面にて入力された振込日（自）<BR>
     */
    public Date minTransferDate;
    
    /**
     * (振込日（至）)<BR>
     * 画面にて入力された振込日（至）<BR>
     */
    public Date maxTransferDate;
    
    /**
     * (振込先金融機@関コード)<BR>
     * 画面にて選択された金融機@関コード<BR>
     * <BR>
     * 「すべて」が選択された場合は、null<BR>
     */
    public String finInstitutionCode;
    
    /**
     * (振込先金融機@関名)<BR>
     * 画面にて選択された金融機@関コードに対応する名称
     */
    public String finInstitutionName;
    
    /**
     * (件数)<BR>
     * 入金連絡の件数<BR>
     */
    public String outputNumber;
    
    /**
     * (入金連絡明細一覧)<BR>
     * 入金連絡明細のリスト<BR>
     */
    public WEB3AioCashinNoticeUnit[] cashinNoticeUnits;
    
    /**
     * (出力区分)<BR>
     * 画面にて選択された出力区分<BR>
     * <BR>
     * 0： 一覧<BR>
     * 1： CSV<BR>
     */
    public String outputDiv;
    
    /**
     * (表示ページ番号)<BR>
     */
    public String pageIndex;
    
    /**
     * (総ページ数)<BR>
     */
    public String totalPages;
    
    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;
    
    /**
     * @@roseuid 4158EB630365
     */
    public WEB3AdminAioCashinConfirmListResponse() 
    {
     
    }
     
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminAioCashinConfirmListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
