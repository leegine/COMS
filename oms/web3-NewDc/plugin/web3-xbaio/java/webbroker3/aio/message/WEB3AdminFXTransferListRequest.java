head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替一覧リクエスト(WEB3AdminFXTransferListRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                  : 2006/08/04 鈴木(SCS)　@モデル No595,610対応
 Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.866
 */

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioTransferOperationDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・FX振替一覧リクエスト) <BR>
 * 管理者・FX振替一覧リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferListRequest extends WEB3GenRequest
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
     * (部店コード) <BR>
     * 画面にて選択された部店コード <BR>
     * ※未入力の場合は、PR層で保持している <BR>
     * 管理者取扱可能部店の一覧をセット。
     */
    public String[] branchCodeList;

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
     * 1:入金(FX)<BR> 
     * 2:出金(FX)<BR> 
     * 3:出金(先OP)<BR> 
     * 4:入金(先OP)<BR> 
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
     * ※全ステータスの場合は、null
     */
    public String statusDiv;

    /**
     * (要求ページ番号) <BR>
     * 要求ページ番号
     */
    public String pageIndex;

    /**
     * (ページ内表示行数) <BR>
     * ページ内表示行数
     */
    public String pageSize;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E7904C005D
     */
    public WEB3AdminFXTransferListRequest()
    {
    }

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferListRequest.class);
        
    /**
     * (validate) <BR>
     * リクエストデータのチェックを行う。 <BR>
     * <BR>
     * １）部店コード <BR>
     * <BR>
     * this.部店コード == null or <BR>
     * this.部店コード.length() == 0 <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01757 <BR>
     * <BR>
     * ２）振替区分 <BR>
     * <BR>
     * this.振替区分 != null and <BR>
     * this.振替区分 != ('1' or '2'or '3'or '4') <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01772 <BR>
     * <BR>
     * ３）受付日 <BR>
     * <BR>
     * this.受付日（自） != null and <BR>
     * this.受付日（至） != null and <BR>
     * this.受付日（自） >= this.受付日（至） <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01773 <BR>
     * <BR>
     * ４）ステータス区分 <BR>
     * <BR>
     * this.ステータス区分 != null and <BR>
     * this.ステータス区分 != ('1' or '5') <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01758 <BR>
     * <BR>
     * ５）要求ページ番号 <BR>
     * <BR>
     * this.要求ページ番号 == null <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00089 <BR>
     * <BR>
     * ６）ページ内表示行数 <BR>
     * <BR>
     * this.ページ内表示行数 == null <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00091 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C23D720026
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード 
        //  this.部店コード == null or 
        //  this.部店コード.length() == 0 
        //  の場合、例外をスローする。 
        if (this.branchCodeList == null)
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        if (this.branchCodeList.length == 0)
        {
            log.debug("部店コードの要素数が０です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が０です。");
        }
 
        //２）振替区分 
        //  this.振替区分 != null and 
        //  this.振替区分 != ('1' or '2' or '3' or '4') 
        //  の場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isEmpty(this.fxTransferDiv)
            && !(WEB3AioTransferOperationDivDef.CASH_IN.equals(this.fxTransferDiv)
                || WEB3AioTransferOperationDivDef.CASH_OUT.equals(this.fxTransferDiv)
                || WEB3AioTransferOperationDivDef.FUOP_OUT.equals(this.fxTransferDiv)
                || WEB3AioTransferOperationDivDef.FUOP_IN.equals(this.fxTransferDiv)))
        {
            log.debug("振替区分が存在しないコード値です");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01772,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "「1：入金(FX)」「2：出金(FX)」「3：出金(先OP)」「4：入金(先OP)」");
        }
        
        //３）受付日 
        //  this.受付日（自） != null and 
        //  this.受付日（至） != null and 
        //  this.受付日（自） >= this.受付日（至） 
        //  の場合、例外をスローする。 
        if (this.receiptDateFrom != null && (this.receiptDateTo != null))
        {
            if (WEB3DateUtility.compareToDay(this.receiptDateFrom, this.receiptDateTo) >= 0)
            {
                log.debug("受付日（自）は受付日（至）以上です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01773,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "受付日（自）は受付日（至）以上です。");
            }
        }
        
        //４）ステータス区分 
        //  this.ステータス区分 != null and 
        //  this.ステータス区分 != ('1' or '5') 
        //  の場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isEmpty(this.statusDiv)
            && !(WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(this.statusDiv)
                || WEB3TransferStatusDivDef.OTHER.equals(this.statusDiv)))
        {
            log.debug("ステータス区分が存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "「1：決済完了 」「 5：その他 」");
        }

        //５）要求ページ番号 
        //  this.要求ページ番号 == null 
        //  の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //６）ページ内表示行数 
        //  this.ページ内表示行数 == null 
        //  の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 管理者・FX振替一覧レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferListResponse(this);
    }
}@
