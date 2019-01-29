head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替一覧リクエスト(WEB3AdminFEqConTransferListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 周勇(中訊) 新規作成
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
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株振替一覧リクエスト)<BR>
 * 外株振替一覧リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_transfer_list";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (部店コード)<BR>
     * 画面にて選択された部店コード<BR>
     * ※未入力の場合は、PR層で保持している<BR>
     * 　@管理者取扱可能部店の一覧をセット。
     */
    public String[] branchCode;
    
    /**
     * (顧客コード)<BR>
     * 画面にて入力された顧客コード<BR>
     * ※null：指定なし
     */
    public String accountCode;
    
    /**
     * (振替区分)<BR>
     * 画面にて選択された振替区分<BR>
     * <BR>
     * 1：入金<BR>
     * 2：出金<BR>
     * <BR>
     * ※全ての場合は、null
     */
    public String transferDiv;
    
    /**
     * (受付日（自）)<BR>
     * 画面にて入力された受付日（自）<BR>
     * YYYYMMDD<BR>
     * <BR>
     * ※null：指定なし<BR>
     * ※時分秒の部分は、初期値（00:00:00）
     */
    public Date receiptDateFrom;
    
    /**
     * (受付日（至）)<BR>
     * 画面にて入力された受付日（至）<BR>
     * YYYYMMDD<BR>
     * <BR>
     * ※null：指定なし<BR>
     * ※時分秒の部分は、23:59:59
     */
    public Date receiptDateTo;
    
    /**
     * (振替日)<BR>
     * 画面にて入力された振替日<BR>
     * YYYYMMDD<BR>
     * <BR>
     * ※null：指定なし
     */
    public Date transferDate;
    
    /**
     * (ステータス区分)<BR>
     * 画面にて選択されたステータス<BR>
     * <BR>
     * 1：決済完了<BR>
     * 5：その他<BR>
     * <BR>
     * ※全ステータスの場合は、null
     */
    public String statusDiv;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数
     */
    public String pageSize;
    
    /**
     * @@roseuid 4235559F01B5
     */
    public WEB3AdminFEqConTransferListRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferListRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *   this.部店コード == null or<BR>
     *   this.部店コード.length == 0<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）振替区分<BR>
     * <BR>
     *   this.振替区分 != (null,1,2)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01772<BR>
     * <BR>
     * ３）ステータス区分<BR>
     * <BR>
     *   this.ステータス区分 != (null, 1, 5)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01758<BR>
     * <BR>
     * ４）要求ページ番号<BR>
     * <BR>
     *   this.要求ページ番号 == null or<BR>
     *   this.要求ページ番号 <= 0<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）ページ内表示行数<BR>
     * <BR>
     *   this.ページ内表示行数 == null or<BR>
     *   this.ページ内表示行数 <= 0<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@roseuid 41D0BA380264
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード
        //this.部店コード == null or 
        //this.部店コード.length == 0
        //の場合、例外をスローする。 
        if(this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("部店コード == null or 部店コード.length == 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード == null or 部店コード.length == 0");
        }
        
        //２）振替区分 
        //this.振替区分 != (null,1,2)
        //の場合、例外をスローする。 
        if(!(WEB3StringTypeUtility.isEmpty(this.transferDiv)
            ||WEB3AioTransferOperationDivDef.CASH_IN.equals(this.transferDiv) 
            || WEB3AioTransferOperationDivDef.CASH_OUT.equals(this.transferDiv)))
        {
            log.debug("振替区分 != (1,2)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01772,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振替区分 != (1,2)");
        }
        
        //３）ステータス区分 
        //this.ステータス区分 != (null, 1, 5)
        //の場合、例外をスローする。 
        if(!(WEB3StringTypeUtility.isEmpty(this.statusDiv) 
            || WEB3TransferStatusDivDef.PROCESS_COMPLETE.equals(this.statusDiv)
            || WEB3TransferStatusDivDef.OTHER.equals(this.statusDiv)))
        {
            log.debug("ステータス区分 != (null, 1, 5)");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01758,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ステータス区分 [ = " + this.statusDiv + "]");
        }
        
        //４）要求ページ番号 
        //this.要求ページ番号 == null or 
        //this.要求ページ番号 <= 0
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号 == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号 == null");
        }

        if(Long.parseLong(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号 <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号 <= 0");
        }
        
        //５）ページ内表示行数 
        //this.ページ内表示行数 == null or 
        //this.ページ内表示行数 <= 0
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数 == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数 == null");            
        }
        
        if(Long.parseLong(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数 <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数 <= 0");            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株振替一覧レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConTransferListResponse(this);
    }
}
@
