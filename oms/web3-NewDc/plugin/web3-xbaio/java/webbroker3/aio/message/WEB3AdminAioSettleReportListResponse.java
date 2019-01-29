head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポート一覧レスポンスクラス(WEB3AdminAioSettleReportListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (決済連携レポート一覧レスポンス)<BR>
 * 決済連携レポート一覧レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_list";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121138L;           
    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード
     */
    public String branchCode;
    
    /**
     * (決済機@関ID)<BR>
     * 画面にて選択された決済機@関ID
     */
    public String paySchemeId;
    
    /**
     * (決済機@関名)<BR>
     * 画面にて選択された決済機@関の名称
     */
    public String paySchemeName;
    
    /**
     * (顧客コード)<BR>
     * 画面にて入力された顧客コード
     */
    public String accountCode;
    
    /**
     * (受渡日)<BR>
     * 画面にて入力された受渡日
     */
    public Date deliveryDate;
    
    /**
     * (注文日（自）)<BR>
     * 画面にて入力された注文日（自）
     */
    public Date minOrtderDate;
    
    /**
     * (注文日（至）)<BR>
     * 画面にて入力された注文日（至）
     */
    public Date maxOrtderDate;
    
    /**
     * (処理状態)<BR>
     * 画面にて選択された処理状態
     */
    public String transactionStatus;
    
    /**
     * (.comデビット決済取引番号（自）)<BR>
     * 画面にて入力された.comデビット決済取引番号（自）
     */
    public String minComDebitNumber;
    
    /**
     * (.comデビット決済取引番号（至）)<BR>
     * 画面にて入力された.comデビット決済取引番号（至）
     */
    public String maxComDebitNumber;
    
    /**
     * (決済連携レポート明細)<BR>
     * 決済連携レポートの明細行のリスト
     */
    public WEB3AioSettleReportUnit[] settleReportUnits;
    
    /**
     * (表示ページ番号)
     */
    public String pageIndex;
    
    /**
     * (総ページ数)
     */
    public String totalPages;
    
    /**
     * (総レコード数)
     */
    public String totalRecords;   
    
    /**
     * @@roseuid 4158EB6602DD
     */
    public WEB3AdminAioSettleReportListResponse(WEB3AdminAioSettleReportListRequest l_request) 
    {
        super(l_request);
    }
    
}
@
