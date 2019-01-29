head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替一覧レスポンス(WEB3AdminFXTransferListResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 Revesion History : 2008/09/23 馮海濤 (中訊) 仕様変更・モデルNo.998
 */

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX振替一覧レスポンス) <BR>
 * 管理者・FX振替一覧レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferListResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (管理者・FX振替一覧レスポンス) <BR>
     * 画面にて選択された部店コード <BR>
     * ※null：全部店
     */
    public String branchCode;

    /**
     * (顧客コード) <BR>
     * 画面にて入力された顧客コード <BR>
     * ※null：指定なし
     */
    public String accountCode;

    /**
     * (振替区分) <BR>
     * 画面にて選択された振替区分 <BR>
     * <BR>
     * 1：入金 <BR>
     * 2：出金 <BR>
     * <BR>
     * ※全ての場合は、null
     */
    public String fxTransferDiv;

    /**
     * (受付日（自）) <BR>
     * 画面にて入力された受付日（自） <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ※null：指定なし
     */
    public Date receiptDateFrom;

    /**
     * (受付日（至）) <BR>
     * 画面にて入力された受付日（至） <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ※null：指定なし
     */
    public Date receiptDateTo;

    /**
     * (振替日) <BR>
     * 画面にて入力された振替日 <BR>
     * YYYYMMDD <BR>
     * <BR>
     * ※null：指定なし
     */
    public Date transferDate;

    /**
     * (ステータス区分) <BR>
     * 画面にて選択されたステータス <BR>
     * <BR>
     * 1：決済完了 <BR>
     * 5：その他 <BR>
     * <BR>
     * <BR>
     * ※全ステータスの場合は、null
     */
    public String statusDiv;

    /**
     * (FX振替明細一覧) <BR>
     * FX振替明細の一覧
     */
    public WEB3FXTransferDetailUnit[] fxTransferDetailList;

    /**
     * (表示ページ番号) <BR>
     * 表示ページ番号
     */
    public String pageIndex;

    /**
     * (総ページ数) <BR>
     * 総ページ数
     */
    public String totalPages;

    /**
     * (総レコード数) <BR>
     * 総レコード数
     */
    public String totalRecords;

    /**
     * (振替出金)
     * 振替出金
     */
    public String fxTotalDepositToGuaranty;

    /**
     * (振替入金)
     * 振替入金
     */
    public String fxTotalGuarantyToDeposit;

    /**
     * (振替合計)
     * 振替合計
     */
    public String fxTransferTotal;

    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXTransferListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
