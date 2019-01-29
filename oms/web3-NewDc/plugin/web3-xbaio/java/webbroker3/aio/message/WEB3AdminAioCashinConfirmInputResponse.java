head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認入力レスポンス(WEB3AdminAioCashinConfirmInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (入金連絡確認入力レスポンス)<BR>
 * 入金連絡確認入力レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinConfirmInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_confirm_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101615L;        
    
    /**
     * (部店コード)<BR>
     * 画面表示のデフォルトとなる部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (連絡日時（自）)<BR>
     * 画面表示のデフォルトとなる連絡日時（自）<BR>
     */
    public Date minNoticeDate;
    
    /**
     * (連絡日時（至)<BR>
     * 画面表示のデフォルトとなる連絡日時（至）<BR>
     */
    public Date maxNoticeDate;
    
    /**
     * (振込先金融機@関一覧)<BR>
     * 該当する証券会社が使用している金融機@関のリスト<BR>
     */
    public WEB3AioFinancialInstitutionUnit[] financialInstitutionUnits;
    
    /**
     * @@roseuid 4158EB63003A
     */
    public WEB3AdminAioCashinConfirmInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminAioCashinConfirmInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
