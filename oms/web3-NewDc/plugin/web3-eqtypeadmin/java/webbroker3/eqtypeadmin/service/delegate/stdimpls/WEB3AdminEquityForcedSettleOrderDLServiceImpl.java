head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderDLServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文DLサービスImpl（WEB3AdminEquityForcedSettleOrderDLServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/21 于瀟(中訊) 新規作成モデル171 191 194
Revision History : 2008/03/11 張騰宇(中訊) モデル202
Revision History : 2008/11/07 姜丹(中訊) モデル211
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminEquityForcedSettleOrderDLCsv;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・強制決済注文DLサービスImpl)<BR>
 * 管理者・強制決済注文DLサービス実装クラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderDLServiceImpl implements WEB3AdminEquityForcedSettleOrderDLService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderDLServiceImpl.class);

    /**
     * @@roseuid 479031F7037A
     */
    public WEB3AdminEquityForcedSettleOrderDLServiceImpl()
    {

    }

    /**
     * 管理者・強制決済注文DLサービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     * get入力画面()<BR>
     * getダウンロードファ@イル()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 476630F90061
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        //get入力画面
        if (l_request instanceof WEB3AdminForcedSettleDownloadInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminForcedSettleDownloadInputRequest)l_request);
        }
        //getダウンロードファ@イル
        else if (l_request instanceof WEB3AdminForcedSettleDownloadRequest)
        {
            l_response = this.getDownloadFile((WEB3AdminForcedSettleDownloadRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者・強制決済注文DL）get入力画面」 参照<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :get強制決済注文一覧()の戻り値 == nullの場合<BR>
     * 　@　@　@　@「条件に該当する強制決済注文がありません」の例外をthrowする。<BR>
     * 　@　@　@　@class       :WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag         :BUSINESS_ERROR_02985<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminForcedSettleDownloadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 476634E40198
     */
    protected WEB3AdminForcedSettleDownloadInputResponse getInputScreen(
        WEB3AdminForcedSettleDownloadInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminForcedSettleDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);

        // validate
        l_request.validate();

        //getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //get証券会社( )
        Institution l_institution = l_administrator.getInstitution();

        //get強制決済注文一覧(証券会社, 管理者・強制決済注文照会リクエスト)
        AdminEqForcedSettleOrderRow[] l_orderRows =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(l_institution, l_request);

        //get強制決済注文一覧()の戻り値 == nullの場合
        if (l_orderRows == null)
        {
            log.debug("条件に該当する強制決済注文がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02985,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当する強制決済注文がありません。");
        }

        //WEB3PageIndexInfo
        WEB3PageIndexInfo l_pageIndexInfo = null;
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_pageIndexInfo =
            new WEB3PageIndexInfo(l_orderRows, 1, l_intPageSize);

        //createResponse
        WEB3AdminForcedSettleDownloadInputResponse l_response =
            (WEB3AdminForcedSettleDownloadInputResponse)l_request.createResponse();
        //総ページ数
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        //総レコード数
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getダウンロードファ@イル)<BR>
     * ダウンロードファ@イル取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者・強制決済注文DL）getダウンロードファ@イル」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminForcedSettleDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 476635200371
     */
    protected WEB3AdminForcedSettleDownloadResponse getDownloadFile(
        WEB3AdminForcedSettleDownloadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminForcedSettleDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        // validate
        l_request.validate();

        //getInstanceFromログイン情報
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.DOMESTIC_EQUITY_FORCED_SETTLE, false);

        //validate部店権限(部店コード : String[])
        //部店コード：　@リクエストデータ.部店コード一覧
        l_administrator.validateBranchPermission(l_request.branchCodeList);

        //get証券会社( )
        Institution l_institution = l_administrator.getInstitution();

        //get強制決済注文一覧(証券会社, 管理者・強制決済注文照会リクエスト)
        AdminEqForcedSettleOrderRow[] l_orderRows =
            WEB3AdminPMEquityDataManager.getForcedSettleOrderList(l_institution, l_request);

        //get強制決済注文一覧()の戻り値 == nullの場合
        if (l_orderRows == null)
        {
            log.debug("条件に該当する強制決済注文がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02985,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当する強制決済注文がありません。");
        }

        //WEB3PageIndexInfo
        WEB3PageIndexInfo l_pageIndexInfo = null;
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_pageIndexInfo =
            new WEB3PageIndexInfo(l_orderRows, l_intPageIndex, l_intPageSize);

        //ダウンロードする分の強制決済注文一覧の配列を取得する。
        AdminEqForcedSettleOrderRow[] l_adminEqForcedSettleOrderRows =
            (AdminEqForcedSettleOrderRow[])l_pageIndexInfo.getArrayReturned(AdminEqForcedSettleOrderRow.class);

        //強制決済注文ダウンロードCSVインスタンスを生成する。
        WEB3AdminEquityForcedSettleOrderDLCsv l_orderDLCsv = new WEB3AdminEquityForcedSettleOrderDLCsv();

        int l_intProductStopSize = l_adminEqForcedSettleOrderRows.length;
        //getArrayReturned()の要素数分、Loop処理
        for (int i = 0; i < l_intProductStopSize; i++)
        {
            AdminEqForcedSettleOrderRow l_adminEqForcedSettleOrderRow =
                l_adminEqForcedSettleOrderRows[i];
            //add明細行
            int l_intSize = l_orderDLCsv.addRow();
            //set部店コード
            l_orderDLCsv.setBranchCode(l_intSize, l_adminEqForcedSettleOrderRow.getBranchId());
            //set顧客
            l_orderDLCsv.setAccount(l_intSize,
                l_adminEqForcedSettleOrderRow.getAccountCode(),
                l_adminEqForcedSettleOrderRow.getAccountId());
            //set強制決済理由
            l_orderDLCsv.setForcedSettleReason(l_intSize, l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            //set市場名
            l_orderDLCsv.setMarketName(l_intSize, l_adminEqForcedSettleOrderRow.getMarketId());
            //set銘柄
            l_orderDLCsv.setProduct(l_intSize,
                l_adminEqForcedSettleOrderRow.getProductCode(),
                l_adminEqForcedSettleOrderRow.getProductId());
            //set税区分
            l_orderDLCsv.setTaxType(l_intSize, l_adminEqForcedSettleOrderRow.getTaxType());
            //set建区分
            l_orderDLCsv.setContractType(l_intSize, l_adminEqForcedSettleOrderRow.getContractType());
            //set弁済区分
            l_orderDLCsv.setRepaymentDiv(l_intSize, l_adminEqForcedSettleOrderRow.getRepaymentType());
            //set建日
            l_orderDLCsv.setOpenDate(l_intSize, l_adminEqForcedSettleOrderRow.getOpenDate());
            //set決済期日
            l_orderDLCsv.setCloseDate(l_intSize, l_adminEqForcedSettleOrderRow.getCloseDate());
            //set建株数
            l_orderDLCsv.setContractQuantity(l_intSize,
                String.valueOf(l_adminEqForcedSettleOrderRow.getContractQuantity()));
            //set建単価
            l_orderDLCsv.setContractPrice(l_intSize,
                String.valueOf(l_adminEqForcedSettleOrderRow.getContractPrice()));
            //set建代金
            l_orderDLCsv.setContractExecPrice(l_intSize,
                WEB3StringTypeUtility.formatNumber(l_adminEqForcedSettleOrderRow.getContractAmount()));
            //set保証金率
            if (l_adminEqForcedSettleOrderRow.getMarginMaintenanceRateIsNull())
            {
                l_orderDLCsv.setMarginDepositRate(l_intSize, null);
            }
            else
            {
                l_orderDLCsv.setMarginDepositRate(l_intSize,
                    String.valueOf(l_adminEqForcedSettleOrderRow.getMarginMaintenanceRate()));
            }
            //set追証発生日
            l_orderDLCsv.setAdditionalMarginDate(l_intSize, l_adminEqForcedSettleOrderRow.getAdditionalMarginDate());
            //set経過日数
            if (l_adminEqForcedSettleOrderRow.getAdditionalMarginAccruedDaysIsNull())
            {
                l_orderDLCsv.setMarginAccruedDays(
                    l_intSize, null, l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            }
            else
            {
                l_orderDLCsv.setMarginAccruedDays(l_intSize,
                    String.valueOf(l_adminEqForcedSettleOrderRow.getAdditionalMarginAccruedDays()),
                    l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            }
            //set作成日時
            l_orderDLCsv.setCreatedTimestamp(l_intSize, l_adminEqForcedSettleOrderRow.getCreatedTimestamp());
            //set処理日時
            l_orderDLCsv.setProcessTime(l_intSize, l_adminEqForcedSettleOrderRow.getApproveTimestamp());
            //set承認状態
            l_orderDLCsv.setApproveStatus(l_intSize,
                l_adminEqForcedSettleOrderRow.getApproveStatusType(),
                l_adminEqForcedSettleOrderRow.getErrorReasonCode(),
                l_adminEqForcedSettleOrderRow.getForcedSettleReasonType());
            //set承認者
            l_orderDLCsv.setApprover(l_intSize, l_adminEqForcedSettleOrderRow.getApproverCode());
        }

        //createResponse
        WEB3AdminForcedSettleDownloadResponse l_response =
            (WEB3AdminForcedSettleDownloadResponse)l_request.createResponse();
        l_response.downloadFile = l_orderDLCsv.getCsvFileLines();
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
