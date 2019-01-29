head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せサービス実装クラス(WEB3AdminAioCashoutInqServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 韋念瓊 (中訊) 新規作成
Revesion History : 2004/10/25 王蘭芬(中訊) レビュー
Revesion History : 2004/12/09 周勇 (中訊) 残対応
Revesion History : 2006/06/14 韋念瓊 (中訊) モデルNo.593
Revesion History : 2006/07/31 徐大方 (中訊) 式樣變更 モデル604
Revesion History : 2006/08/04 徐大方 (中訊) 式樣變更 モデル609
Revesion History : 2006/09/04 車進   (中訊) 式樣變更 モデルNo.628、643
Revesion History : 2006/10/12 車進   (中訊) 式樣變更 モデルNo.658
Revesion History : 2007/02/07 徐宏偉(中訊) 仕様変更モデル 699
Revesion History : 2007/02/13 徐宏偉(中訊) 仕様変更モデル 707
Revesion History : 2010/02/02 武波 (中訊)モデルNo.1262
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AdminAioCashoutInqDownloadCsv;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.define.WEB3AioCancelDivDef;
import webbroker3.aio.define.WEB3AioCurrencyCodeDef;
import webbroker3.aio.define.WEB3AioInputDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.aio.define.WEB3AioTransferDivDef;
import webbroker3.aio.define.WEB3OrderCancelPossibleDef;
import webbroker3.aio.define.WEB3OrderSendPossibleDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashoutInqListRequest;
import webbroker3.aio.message.WEB3AdminAioCashoutInqListResponse;
import webbroker3.aio.message.WEB3AioBranchCodeComparator;
import webbroker3.aio.message.WEB3AioCashoutInqUnit;
import webbroker3.aio.message.WEB3AioOrderDateComparator;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqService;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3DesignateDivDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TransferRangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TransferedFinInstitutionDao;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金申込問合せサービスImpl)<BR>
 * 出金申込問合せサービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashoutInqServiceImpl extends WEB3ClientRequestService
    implements WEB3AdminAioCashoutInqService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqServiceImpl.class);

    /**
     * 出金申込問合せサービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、create一覧()、validate送信()、<BR>
     * submit送信()、validate取消()、submit取消()、<BR>
     * getダウンロードファ@イル()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410105F603C8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (l_request instanceof WEB3AdminAioCashoutInqListRequest)
        {
            //リクエストデータの具象データ型が「出金申込問合せ一覧リクエスト」の場合
            WEB3AdminAioCashoutInqListResponse l_listResponse = 
                this.createList((WEB3AdminAioCashoutInqListRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_listResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqConfirmRequest)
        {
            //リクエストデータの具象データ型が「出金申込問合せ出金確認リクエスト」の場合
            WEB3AdminAioCashoutInqConfirmResponse l_comfirmResponse = 
                this.validateSend((WEB3AdminAioCashoutInqConfirmRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_comfirmResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqCompleteRequest)
        {
            //リクエストデータの具象データ型が「出金申込問合せ出金完了リクエスト」の場合
            WEB3AdminAioCashoutInqCompleteResponse l_completeResponse = 
                this.submitSend((WEB3AdminAioCashoutInqCompleteRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqCancelConfirmRequest)
        {
            //リクエストデータの具象データ型が「出金申込問合せ取消確認リクエスト」の場合
            WEB3AdminAioCashoutInqCancelConfirmResponse l_cancelConfirmResponse = 
                this.validateCancel((WEB3AdminAioCashoutInqCancelConfirmRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_cancelConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqCancelCompleteRequest)
        {
            //リクエストデータの具象データ型が「出金申込問合せ取消完了リクエスト」の場合
            WEB3AdminAioCashoutInqCancelCompleteResponse l_cancelConfirmResponse = 
                this.submitCancel((WEB3AdminAioCashoutInqCancelCompleteRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_cancelConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminAioCashoutInqDownloadRequest)
        {
            //リクエストデータの具象データ型が「出金申込問合せダウンロードリクエスト」の場合
        	WEB3AdminAioCashoutInqDownloadResponse l_cashoutInqDownloadResponse = 
                this.getDownloadFile((WEB3AdminAioCashoutInqDownloadRequest) l_request);

            log.exiting(STR_METHOD_NAME);
            return l_cashoutInqDownloadResponse;
        }
        else
        {
            log.debug("パラメータタイプ不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (create一覧)<BR>
     * 出金申込問合せ一覧画面表示データを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）一覧画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminAioCashoutInqListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129CF150026
     */
    protected WEB3AdminAioCashoutInqListResponse createList(
        WEB3AdminAioCashoutInqListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createList(" + 
            "WEB3AdminAioCashoutInqListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.1 リクエストデータの整合性をチェックする。
        l_request.validate();

        //1.2 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 該当の管理者がこの機@能が使えるか権限チェックを行う。
        //validate権限(String, boolean)
        //[引数]
        //機@能カテゴリコード： ”B0101”
        //is更新： false
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            false);

        //1.4 該当の管理者が指定した部店に対する処理権限があるかチェックする。
        //validate部店権限(String[])
        //[引数]
        //部店コード： リクエストデータ.部店コード
        l_web3Administrator.validateBranchPermission(l_request.branchCode);

        AccountManager l_accountManager = GtlUtils.getAccountManager();
        Branch l_branch = null;
        long l_lngBranchId = 0L;

        //1.5 リストのインスタンスを生成する。
        long[] l_lngBranchIds = 
            new long[l_request.branchCode.length];

        //1.6 証券会社オブジェクトを取得する。
        Institution l_institution = l_web3Administrator.getInstitution();

        //1.7 (*1) リクエストデータ.部店コードの各要素毎にLoop処理
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            //1.7.1 部店インスタンスを生成する。
            //［引数］
            //証券会社： get証券会社()の戻り値
            //部店コード： リクエストデータ.部店コード
            try
            {
                l_branch = l_accountManager.getBranch(
                    l_institution, l_request.branchCode[i]);
            }
            catch (NotFoundException l_ex)
            {
                log.error("部店インスタンスを生成する:", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.7.2 部店IDを取得する。
            l_lngBranchId = l_branch.getBranchId();

            //1.7.3 部店IDをリストに追加する。
            //[引数]
            //arg0： 部店ID
            l_lngBranchIds[i] = l_lngBranchId;
        }

        //1.9 データ取得条件文字列を生成する。
        //［引数］
        //部店ID： 部店IDの配列
        //受渡日： リクエストデータ.受渡日
        //注文受付区分： リクエストデータ.注文受付区分
        //入力区分:  リクエストデータ.入力区分
        //通貨コード：　@リクエストデータ.通貨コード

        String l_strWhereClause = null;
        l_strWhereClause = this.createGetCondCharacterString(
            l_lngBranchIds, 
            l_request.deliveryDate, 
            l_request.orderDiv, 
            l_request.inputDiv, 
            l_request.currencyCode);

        //1.10 取得条件データコンテナを生成する。
        //［引数］
        //部店ID： 部店IDの配列
        //受渡日： リクエストデータ.受渡日
        //注文受付区分： リクエストデータ.注文受付区分
        //入力区分:  リクエストデータ.入力区分
        //通貨コード：　@リクエストデータ.通貨コード

        Object l_bindVars[] = this.createGetCondDataContainer(
            l_lngBranchIds, 
            l_request.deliveryDate, 
            l_request.orderDiv, 
            l_request.inputDiv, 
            l_request.currencyCode);

        //1.11  1.12 注文単位テーブルから、レコードを取得する。
        //［引数］
        // arg0： 注文単位Row.TYPE
        // arg1： create取得条件文字列()の戻り値
        // arg2： "branch_id asc , received_date_time asc"
        // arg3： null
        // arg4： create取得条件データコンテナ()の戻り値
        List l_lisRows = null;
        String l_strSort = " branch_id asc , received_date_time asc";
        try
        {
            l_lisRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE, 
                    l_strWhereClause, 
                    l_strSort, 
                    null, 
                    l_bindVars);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.13 ArrayListインスタンスを生成する。
        List l_lisAioCashoutInqUnit = null;
        l_lisAioCashoutInqUnit = new ArrayList();

        log.debug("search 注文単位.size : = " + l_lisRows.size());

        //1.14 取得した要素分のLoop処理
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_lisRows.get(i);

            //1.14.1 出金申込問合せ明細オブジェクトを生成する。
            //[引数]
            //注文単位Params： 注文単位Params

            WEB3AioCashoutInqUnit l_aioCashoutInqUnit = this.createCashoutInqUnit(l_aioOrderUnitRow);

            //=========remain zhou-yong NO.1 begin ========

            //(*3) リクエストデータ.振込先区分 != 0（全て） の場合
            //
            //１）以下の条件で、振込先金融機@関テーブルからレコードを取得する。
            //   [条件]
            //   部店コード： 部店（注文単位Params.部店ID）.getBranchCode()の戻り値
            //   証券会社コード： 部店.getInstitution().getInstitutionCode()の戻り値
            //   顧客コード： 口座（注文単位Params.口座ID）.getAccountCode()の戻り値
            //   指定区分: 'A'
            if (!WEB3AioTransferDivDef.ALL.equals(l_request.transferDiv))
            {
                Branch l_unitBranch = null;
                MainAccount l_unitMainAccount = null;
                TransferedFinInstitutionRow l_transferedFinInstitutioRow = null;

                try
                {
                    l_unitBranch = l_accountManager.getBranch(
                        l_aioOrderUnitRow.getBranchId());
                    l_unitMainAccount = l_accountManager.getMainAccount(
                        l_aioOrderUnitRow.getAccountId());

                    l_transferedFinInstitutioRow = 
                        TransferedFinInstitutionDao.findRowByInstitutionCodeBranchCodeAccountCodeDesignateDiv(
                        l_unitBranch.getInstitution().getInstitutionCode(), 
                        l_unitBranch.getBranchCode(), 
                        l_unitMainAccount.getAccountCode(), 
                        WEB3TransferRangeDef.SALE_TURNOVER);

                }
                catch (NotFoundException l_ex)
                {
                    log.error("__NotFoundException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました: ", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //２）以下の条件を満たす場合、Listへ要素を追加する。
                //    ○リクエストデータ．振込先区分 = 1（郵貯）の場合、振込先金融機@関Params.振込区分 = 5（郵便振込）
                //    ○リクエストデータ．振込先区分 = 2（その他）の場合、振込先金融機@関Params.振込区分 != 5（郵便振込）

                if(WEB3AioTransferDivDef.POSTAL_SAVINGS.equals(l_request.transferDiv))
                {
                    if(l_transferedFinInstitutioRow != null 
                        && WEB3FinTransferDivDef.POST_TRANSFER.equals(l_transferedFinInstitutioRow.getTransferDiv()))
                    {
                        l_lisAioCashoutInqUnit.add(l_aioCashoutInqUnit);
                    }

                }
                if(WEB3AioTransferDivDef.OTHERS.equals(l_request.transferDiv))
                {
                    if(l_transferedFinInstitutioRow != null
                        && !WEB3FinTransferDivDef.POST_TRANSFER.equals(l_transferedFinInstitutioRow.getTransferDiv()))
                    {
                        l_lisAioCashoutInqUnit.add(l_aioCashoutInqUnit);
                    }
                }

            }
            else
            {
                l_lisAioCashoutInqUnit.add(l_aioCashoutInqUnit);
            }
            //=========remain zhou-yong NO.1 end ========

        } //end for

        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnits = 
            new WEB3AioCashoutInqUnit[l_lisAioCashoutInqUnit.size()];

        //1.15 出金申込問合せ明細の配列を取得する。
        l_lisAioCashoutInqUnit.toArray(l_aioCashoutInqUnits);

        //=========remain zhou-yong NO.2 begin ============
        //WEB3PageIndexInfo
        //[コンストラクタに指定する引数]
        // l_objs ： toArray()した値
        // l_intRequestPageIndex ： リクエストデータ.要求ページ内表示行数
        // l_intRequestPageSize ： リクエストデータ.要求ページ番号

        //ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_request.pageSize);

        //要求ページ番号
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_aioCashoutInqUnits,
                l_intPageIndex,
                l_intPageSize);

        //getArrayReturned
        //指定した行数分、レコードを返却する。
        //[引数]
        //l_classType ： 出金問合せ明細明細クラス.class
        WEB3AioCashoutInqUnit[] l_arrayReturneds =
            (WEB3AioCashoutInqUnit[])l_pageIndexInfo.getArrayReturned(WEB3AioCashoutInqUnit.class);

        //set出金余力
        //出金余力を取得し、セットする。
        //［引数］
        //証券会社コード ： get証券会社()の戻り値
        //出金問合せ明細 ： getArrayReturned()の戻り値
        this.setPaymentPower(l_institution.getInstitutionCode(), l_arrayReturneds);

        //1.17) createResponse( )(出金申込問合せ一覧リクエスト::createResponse)
        //アイテムの定義
        //レスポンスデータを生成する
        WEB3AdminAioCashoutInqListResponse l_response = 
            (WEB3AdminAioCashoutInqListResponse)l_request.createResponse();

        //1.18) (*5) プロパティセット
        //(*5) 以下のとおりに、プロパティをセットする。
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_response.branchCode = l_request.branchCode;

        //レスポンス.受渡日 = リクエストデータ.受渡日
        l_response.deliveryDate = l_request.deliveryDate;

        //レスポンス.注文受付区分 = リクエストデータ.注文受付区分
        l_response.orderDiv = l_request.orderDiv;

        //レスポンス.振込先区分 = リクエストデータ.振込先区分
        l_response.transferDiv = l_request.transferDiv;

        //レスポンス.入力区分 = リクエストデータ.入力区分
        l_response.inputDiv = l_request.inputDiv;

        //レスポンス.出金申込問合せ明細 = 出金問合せ明細一覧
        l_response.cashoutInqUnits = l_arrayReturneds;

        //レスポンス.表示ページ番号 = toIndex / リクエストデータ.ページ内容表示行数  ※小数点以下切り上げ
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";

        //レスポンス.総ページ数 = （出金申込問合せ明細［］.length()の戻り値） / リクエストデータ.ページ内容表示行数
        //※小数点以下切り上げ
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";

        //レスポンス.総レコード数 = 出金申込問合せ明細［］.length()の戻り値
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        //レスポンス.管理者処理フラグ = 証券会社Params.出金申込管理者タスク
        InstitutionParams l_institutionParams = (InstitutionParams)
            l_institution.getDataSourceObject();

        l_response.adminProcessingDiv = l_institutionParams.getPaymentApplyAdminTask();

        //レスポンス.通貨コード = リクエストデータ.通貨コード
        l_response.currencyCode = l_request.currencyCode;

        //=========remain zhou-yong NO.2 end ============

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate送信)<BR>
     * 出金送信の確認を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）validate送信」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminAioCashoutInqConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129CFAD0016
     */
    protected WEB3AdminAioCashoutInqConfirmResponse validateSend(
        WEB3AdminAioCashoutInqConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSend(" +
            "WEB3AdminAioCashoutInqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 リクエストデータの整合性をチェックする。
        l_request.validate();
        
        //1.2 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 権限チェックを行う。 
        //[引数] 
        // 機@能カテゴリコード： ”B0101” 
        // is更新： true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            true);
        
        //1.4 該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //[引数] 
        //部店コード： リクエストデータ.部店コード         
        l_web3Administrator.validateBranchPermission(l_request.branchCode);

        //1.5 ArrayListインスタンスを生成する。
        List l_lisAioCashoutInqUnit = new ArrayList();

        int l_intIndicationLisLength = l_request.directionsList.length;
        AioOrderUnitRow l_aioOrderUnitRow = null;
        
        //1.6 (*1) リクエストデータ.指示リストの要素毎のLoop処理
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 注文単位を取得する。 
            //※配列の1番目の要素を取得
            //[引数] 
            //注文ID： リクエストデータ.指示リストの要素 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                           
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];
            l_aioOrderUnitRow = (AioOrderUnitRow) 
                l_aioOrderUnit.getDataSourceObject();
                        
            //1.6.2 出金申込問合せ明細を生成する。 

            //[引数] 
            //注文単位Params： 注文単位.getDataSourceObject()の戻り値
            
            WEB3AioCashoutInqUnit l_web3AioCashoutInqUnit = this.createCashoutInqUnit(l_aioOrderUnitRow); 
                        
            //1.6.3 出金注文が送信可能かどうかのチェックを行う。 

            //[引数] 
            //注文単位： getOrderUnits()で取得した注文単位 
            
            String l_strSendPossible =     
                l_web3AioOrderMgr.validateOrderSendPossible(l_aioOrderUnit);
            
            //戻り値を出金申込問合せ明細.チェック結果にセットする。
            l_web3AioCashoutInqUnit.checkResult = l_strSendPossible;

            log.debug("出金申込問合せ明細.チェック結果 : = " + l_web3AioCashoutInqUnit.checkResult);
            
            //1.6.4 Listに要素を追加する。
            //[引数]
            //arg0： create出金申込問合せ明細()の戻り値
            l_lisAioCashoutInqUnit.add(l_web3AioCashoutInqUnit);

        }
                
        WEB3AioCashoutInqUnit[] l_web3AioCashoutInqUnits =  
            new WEB3AioCashoutInqUnit[l_lisAioCashoutInqUnit.size()];
            
        //1.7 Listから配列を取得する。 
        l_lisAioCashoutInqUnit.toArray(l_web3AioCashoutInqUnits);

        //出金余力を取得し、セットする。
        //［引数］
        //証券会社コード ： ログイン情報.get証券会社()の戻り値
        //出金問合せ明細 ： toArray()で変換した値
        this.setPaymentPower(l_web3Administrator.getInstitutionCode(), l_web3AioCashoutInqUnits);

        //1.8 レスポンスデータを生成する。 
        WEB3AdminAioCashoutInqConfirmResponse l_adminAioCashoutInqConfirmResponse = null;
        l_adminAioCashoutInqConfirmResponse = 
                (WEB3AdminAioCashoutInqConfirmResponse) 
                    l_request.createResponse();
                
        //1.9  プロパティセット        
        //(*3) 以下のとおりに、プロパティをセットする。
        
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_adminAioCashoutInqConfirmResponse.branchCode = l_request.branchCode;
               
        //log.debug("レスポンス.部店コード = " + l_adminAioCashoutInqConfirmResponse.branchCode);
        
        //レスポンス.受渡日 = リクエストデータ.受渡日 
        l_adminAioCashoutInqConfirmResponse.deliveryDate = l_request.deliveryDate;
               
        log.debug("レスポンス.受渡日 = " + l_adminAioCashoutInqConfirmResponse.deliveryDate);
        
        //レスポンス.注文受付区分 = リクエストデータ.注文受付区分 
        l_adminAioCashoutInqConfirmResponse.orderDiv = l_request.orderDiv;  
                
        log.debug("レスポンス.注文受付区分 = " + l_adminAioCashoutInqConfirmResponse.orderDiv);
        
        //===========remain zhou-yong NO.3 begin ===========
        
        //レスポンス.振込先区分 = リクエストデータ.振込先区分
        l_adminAioCashoutInqConfirmResponse.transferDiv = l_request.transferDiv;
        
        //レスポンス.出金申込問合せ明細 = 出金申込問合せ明細の配列
        l_adminAioCashoutInqConfirmResponse.cashoutInqUnits = l_web3AioCashoutInqUnits;
        
        //===========remain zhou-yong NO.3 end ===========

        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqConfirmResponse;
    }
    
    /**
     * (submit送信)<BR>
     * 出金注文の送信を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）submit送信」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminAioCashoutInqCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129D03F0045
     */
    protected WEB3AdminAioCashoutInqCompleteResponse submitSend(
        WEB3AdminAioCashoutInqCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSend(" +
            "WEB3AdminAioCashoutInqCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 権限チェックを行う。 
        //[引数] 
        // 機@能カテゴリコード： ”B0101” 
        // is更新： true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            true);
        
        //1.4 該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //validate部店権限(String[])
        //[引数]
        //部店コード： リクエストデータ.部店コード
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 暗証番号のチェックを行う。
        //［引数］ 
        //パスワード： リクエストデータ.暗証番号
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        WEB3AioMarketRequestSenderServiceImpl l_aioMarketSenderService = 
            (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();
                      
        String l_strInstitutionCode = "";
        String l_strBranchCode = "";                
        String l_strAccountCode = ""; 
        
        int l_intIndicationLisLength = l_request.directionsList.length;
        log.debug("指示リスト.size = " + l_intIndicationLisLength);
        
        //1.6 リクエストデータ.指示リストの要素毎のLoop処理
               
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 注文単位を取得する。 
            //※配列の1番目の要素を取得
            //[引数] 
            //注文ID： リクエストデータ.指示リストの要素 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];            
            
            //1.6.2 出金注文が送信可能かどうかのチェックを行う。

            //[引数] 
            //注文単位： getOrderUnits()で取得した注文単位 
            
            String l_strSendPossible = 
                l_web3AioOrderMgr.validateOrderSendPossible(l_aioOrderUnit);
            
            //validate注文送信可能()の戻り値 = ”OK” の場合、実施
            
            if (WEB3OrderSendPossibleDef.SEND_OK.equals(l_strSendPossible))
            {                
                log.debug("validate注文送信可能()の戻り値 = ”OK” の場合");
                try
                {                
                    AccountManager l_accountManager = GtlUtils.getAccountManager();  
                    MainAccount l_mainAccount = l_accountManager.getMainAccount(
                        l_aioOrderUnit.getAccountId());
                    Branch l_branch = l_accountManager.getBranch(
                        l_aioOrderUnit.getBranchId());
                        
                    l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode(); 
                    l_strBranchCode = l_branch.getBranchCode();
                    l_strAccountCode = l_mainAccount.getAccountCode();                     
                          
                }
                catch (NotFoundException l_ex)
                {
                    log.error("__NotFoundException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //1.6.3 出金請求注文キューテーブルの該当レコードの処理区分を処理中に更新する。 

                //[引数] 
                //証券会社コード： 部店.getInstitution().getInstitutionCode()の戻り値 
                //部店コード： 部店（注文単位.部店ID）.getBranchCode()の戻り値 
                //顧客コード： 口座（注文単位.口座ID）.getAccountCode()の戻り値 
                //識別コード： 注文単位.識別コード
                //注文単位ID： 注文単位.注文単位ID
                        
                AioOrderUnitRow l_aioOrderUnitRow = 
                    (AioOrderUnitRow) l_aioOrderUnit.getDataSourceObject();
                
                l_aioMarketSenderService.updateStatus(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    l_aioOrderUnitRow.getOrderRequestNumber(), 
                    l_aioOrderUnitRow.getOrderUnitId());
            }            

        }//end for
        
        //1.7 トリガを発行する。 

        //[引数] 
        //証券会社コード：　@部店（注文単位.部店ID）.getInstitution().getInstitutionCode()の戻り値 
        //データコード：　@”GI801T”
        
        l_aioMarketSenderService.submitTrigger(
            l_strInstitutionCode, WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T");

        
        //1.8 レスポンスデータを生成する。 
        WEB3AdminAioCashoutInqCompleteResponse l_adminAioCashoutInqCompleteResponse = 
            (WEB3AdminAioCashoutInqCompleteResponse) 
                l_request.createResponse();
                
        //1.9  プロパティセット  
        //(*2) 以下のとおりに、プロパティをセットする。
        
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_adminAioCashoutInqCompleteResponse.branchCode = l_request.branchCode;
       
        //レスポンス.受渡日 = リクエストデータ.受渡日
        l_adminAioCashoutInqCompleteResponse.deliveryDate = l_request.deliveryDate;
        log.debug("レスポンス.受渡日 = " + l_adminAioCashoutInqCompleteResponse.deliveryDate);
                
        //レスポンス.注文受付区分 = リクエストデータ.注文受付区分
        l_adminAioCashoutInqCompleteResponse.orderDiv = l_request.orderDiv;  

        log.debug("レスポンス.注文受付区分 = " + l_adminAioCashoutInqCompleteResponse.orderDiv);
        
        //レスポンス.振込先区分 = リクエストデータ.振込先区分               
        l_adminAioCashoutInqCompleteResponse.transferDiv = 
            l_request.transferDiv;
        
        log.debug("レスポンス.振込先金融機@関コード = " + 
                l_adminAioCashoutInqCompleteResponse.transferDiv);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqCompleteResponse;
    
    }
    
    /**
     * (validate取消)<BR>
     * 出金取消の確認を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）validate取消」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminAioCashoutInqCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129D0E60381
     */
    protected WEB3AdminAioCashoutInqCancelConfirmResponse validateCancel(
        WEB3AdminAioCashoutInqCancelConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateCancel(" +
            "WEB3AdminAioCashoutInqCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 権限チェックを行う。 
        //[引数] 
        //機@能カテゴリコード： ”B0101” 
        //is更新： true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ,
            true);
        
        //1.4 該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //validate部店権限(String[])
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 ArrayListインスタンスを生成する。
        List l_lisAioCashoutInqUnit =  new ArrayList();

        int l_intIndicationLisLength = l_request.directionsList.length;

        AioOrderUnitParams l_aioOrderUnitParams = null;
        
        //1.6 取得した要素分のLoop処理
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 注文単位を取得する。 
            //※配列の1番目の要素を取得
            //[引数] 
            //注文ID： リクエストデータ.指示リストの要素 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
                        
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                            
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];
            l_aioOrderUnitParams = (AioOrderUnitParams) 
                l_aioOrderUnit.getDataSourceObject();
                        
            //1.6.2 出金申込問合せ明細を生成する。 

            //[引数] 
            //注文単位Params： 注文単位.getDataSourceObject()の戻り値
            
            WEB3AioCashoutInqUnit l_web3AioCashoutInqUnit = new WEB3AioCashoutInqUnit();
            l_web3AioCashoutInqUnit = this.createCashoutInqUnit(l_aioOrderUnitParams); 
                        
            //1.6.3 出金注文が取消可能かどうかのチェックを行う。 
            //[引数] 
            //注文単位： getOrderUnits()で取得した注文単位 
            
            String l_strCancelPossible =  
                l_web3AioOrderMgr.validateOrderCancelPossible(l_aioOrderUnit);
            log.debug("出金注文が取消可能 : " + l_strCancelPossible);
            
            //戻り値を出金申込問合せ明細.チェック結果にセットする。
            l_web3AioCashoutInqUnit.checkResult = l_strCancelPossible;
            log.debug("出金申込問合せ明細.チェック結果 : " + l_web3AioCashoutInqUnit.checkResult);
            
            //1.6.4 Listに要素を追加する。 
            //[引数] 
            //arg0： create出金申込問合せ明細()の戻り値 
            l_lisAioCashoutInqUnit.add(l_web3AioCashoutInqUnit); 
            
        }
                
        WEB3AioCashoutInqUnit[] l_web3AioCashoutInqUnits =  
            new WEB3AioCashoutInqUnit[l_lisAioCashoutInqUnit.size()];
            
        //1.7 Listから配列を取得する。 
        l_lisAioCashoutInqUnit.toArray(l_web3AioCashoutInqUnits);        

        //出金余力を取得し、セットする。
        //［引数］
        //証券会社コード ： ログイン情報.get証券会社()の戻り値
        //出金問合せ明細 ： toArray()で変換した値
        this.setPaymentPower(l_web3Administrator.getInstitutionCode(), l_web3AioCashoutInqUnits);

        //1.8 レスポンスデータを生成する。 
        WEB3AdminAioCashoutInqCancelConfirmResponse l_adminAioCashoutInqCancelConfirmResponse = null;
        l_adminAioCashoutInqCancelConfirmResponse = 
                (WEB3AdminAioCashoutInqCancelConfirmResponse) 
                    l_request.createResponse();
                
        //1.9  プロパティセット
        //(*3) 以下のとおりに、プロパティをセットする。
        
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_adminAioCashoutInqCancelConfirmResponse.branchCode = l_request.branchCode;
               
        //レスポンス.受渡日 = リクエストデータ.受渡日 
        l_adminAioCashoutInqCancelConfirmResponse.deliveryDate = l_request.deliveryDate;        
        log.debug("レスポンス.受渡日 : " + l_adminAioCashoutInqCancelConfirmResponse.deliveryDate);
               
        //レスポンス.注文受付区分 = リクエストデータ.注文受付区分 
        l_adminAioCashoutInqCancelConfirmResponse.orderDiv = l_request.orderDiv;          
        log.debug("レスポンス.注文受付区分 : " + l_adminAioCashoutInqCancelConfirmResponse.orderDiv);       
        
        //レスポンス.振込先区分 = リクエストデータ.振込先区分
        l_adminAioCashoutInqCancelConfirmResponse.transferDiv = l_request.transferDiv;
        
        //レスポンス.出金申込問合せ明細 = 出金申込問合せ明細の配列
        l_adminAioCashoutInqCancelConfirmResponse.cashoutInqUnits = l_web3AioCashoutInqUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqCancelConfirmResponse;
    }
    
    /**
     * (submit取消)<BR>
     * 出金注文の取消を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）submit取消」 参照
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminAioCashoutInqCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4129D0E60391
     */
    protected WEB3AdminAioCashoutInqCancelCompleteResponse submitCancel(
        WEB3AdminAioCashoutInqCancelCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitCancel(" +
            "WEB3AdminAioCashoutInqCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(String, boolean)(管理者::validate権限)
        //アイテムの定義
        //該当の管理者がこの機@能が使えるか権限チェックを行う。
        //[引数] 
        //機@能カテゴリコード： ”B0101” 
        //is更新： true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ ,true);
        
        //1.4 validate部店権限(String[]): 
        //該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        //1.5 validate取引パスワード(String): 暗証番号のチェックを行う。
        //［引数］ 
        //パスワード： リクエストデータ.暗証番号]
        l_web3Administrator.validateTradingPassword(l_request.password);
        
        int l_intIndicationLisLength = l_request.directionsList.length;
        //1.6 リクエストデータ.指示リストの要素毎のLoop処理
               
        for (int i = 0; i < l_intIndicationLisLength; i++)
        {
            //1.6.1 注文単位を取得する。 
            //※配列の1番目の要素を取得
            //[引数] 
            //注文ID： リクエストデータ.指示リストの要素 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            OrderUnit[] l_orderUnit = l_web3AioOrderMgr.getOrderUnits(
                Long.parseLong(l_request.directionsList[i]));
                
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit) l_orderUnit[0];            
            
            //1.6.2 出金注文が取消可能かどうかのチェックを行う。
            //[引数] 
            //注文単位： getOrderUnits()で取得した注文単位 
            
            String l_strCancelPossible = 
                l_web3AioOrderMgr.validateOrderCancelPossible(l_aioOrderUnit);
            
            //=========remain zhou-yong NO.4 begin ===========
            
            //validate注文取消可能()の戻り値 = ”OK” の場合、実施
            //1.6.3
            if (WEB3OrderCancelPossibleDef.CANCEL_OK.equals(l_strCancelPossible))
            {
                log.debug("validate注文取消可能()の戻り値 = ”OK” の場合");

                //1.6.3.1) execute(AioOrderUnit,String)(出金取消UnitServiceImpl::execute)
                //アイテムの定義
                //取消注文内容インスタンスを生成する。
                //[引数] 
                //注文ID： リクエストデータ.指示リストの要素 
                //パスワード:リクエスト.パスワード
                WEB3AioCashoutCancelUnitService l_service =
                    (WEB3AioCashoutCancelUnitService) Services.getService(
                        WEB3AioCashoutCancelUnitService.class);  
                
                l_service.execute(l_aioOrderUnit,l_request.password);
            }

        }//end for
        
        //1.7 レスポンスデータを生成する。 
        WEB3AdminAioCashoutInqCancelCompleteResponse l_adminAioCashoutInqCancelCompleteResponse =
            (WEB3AdminAioCashoutInqCancelCompleteResponse)
                l_request.createResponse();
                
        //=========remain zhou-yong NO.4 end ===========
        
        //1.8  プロパティセット     
        //(*2) 以下のとおりに、プロパティをセットする。
        
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_adminAioCashoutInqCancelCompleteResponse.branchCode = l_request.branchCode;
               
        //レスポンス.受渡日 = リクエストデータ.受渡日 
        l_adminAioCashoutInqCancelCompleteResponse.deliveryDate = l_request.deliveryDate;
               
        //レスポンス.注文受付区分 = リクエストデータ.注文受付区分 
        l_adminAioCashoutInqCancelCompleteResponse.orderDiv = l_request.orderDiv;  
        
        //レスポンス.振込先区分 = リクエストデータ.振込先区分                  
        l_adminAioCashoutInqCancelCompleteResponse.transferDiv = l_request.transferDiv;
        
        log.exiting(STR_METHOD_NAME);        
        return l_adminAioCashoutInqCancelCompleteResponse;
    }
    
    /**
	 * (create取得条件文字列)<BR>
	 * リクエストデータから、データ取得条件文字列を生成する。<BR>
	 * <BR>
	 * １）空の文字列を生成する。<BR>
	 * <BR>
	 * ２）注文種別<BR>
	 * <BR>
	 * 条件文字列： "order_type=?"<BR>
	 * <BR>
	 * 上記文字列を１）の文字列の末尾に追加する。<BR>
	 * <BR>
	 * ３）出金申込区分<BR>
	 * <BR>
	 * 条件文字列： " and payment_application_div=null"<BR>
	 * <BR>
	 * 上記文字列を１）の文字列の末尾に追加する。<BR>
	 * <BR>
	 * ４）部店ID<BR>
	 * <BR>
	 * ４－１）引数.部店IDの要素数 = 1 の場合 <BR>
	 * <BR>
	 * 条件文字列： " and branch_id=?" <BR>
	 * <BR>
	 * 上記文字列を１）の文字列の末尾に追加する。<BR>
	 * <BR>
	 * ４－２）引数.部店IDの要素数 > 1 の場合 <BR>
	 * <BR>
	 * 条件文字列： " and (branch_id=? or branch_id=? or ... or branch_id=?)" <BR>
	 * ※括弧内の「branch_id=?」の数が要素数分あるようにする <BR>
	 * <BR>
	 * 上記文字列を１）の文字列の末尾に追加する。 <BR>
	 * <BR>
	 * ５）受渡日<BR>
	 * <BR>
	 * 引数.受渡日 != null の場合<BR>
	 * <BR>
	 * 条件文字列： " and delivery_date=?"<BR>
	 * <BR>
	 * 上記文字列を１）の文字列の末尾に追加する。<BR>
	 * <BR>
	 * ６）注文受付区分<BR>
	 * <BR>
	 * 引数.注文受付区分 != 3（すべて）の場合 <BR>
	 * <BR>
	 * 条件文字列： " and order_status=? and cancel_type=?" <BR>
	 * <BR>
	 * 上記文字列を１）の文字列の末尾に追加する。<BR>
	 * <BR>
     * ７）入力区分 <BR>
     * 引数.入力区分 !=0（すべて） 且つ 入力区分 =1（顧客）の場合<BR>
     * <BR>
     * 条件文字列： " and order_root_div in (? , ? , ? , ? , ?)"<BR>
     * <BR>
     * 引数.入力区分 !=0（すべて） 且つ 入力区分 =2（SONAR）の場合<BR>
     * <BR>
     * 条件文字列： " and order_root_div = ?"<BR>
     * <BR>
     * 上記文字列を１）の文字列の末尾に追加する。 <BR>
     * <BR>
     * ８）通貨コード<BR>
     * 　@引数.通貨コード != 0（すべて） 且つ 通貨コード = null(円貨) の場合<BR>
     * <BR>
　@　@ * 　@　@条件文字列： " and currency_code is null"<BR>
     * <BR>
     * 　@引数.通貨コード !=0（すべて） 且つ 通貨コード != null の場合<BR>
     * <BR>
　@　@ * 　@　@条件文字列： " and currency_code = ?"<BR>
     * <BR>
     * 　@上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ９）生成した文字列を返却する。<BR>
     * <BR>
	 * @@param l_lngBranchID -
	 *            (部店ID)
	 * @@param l_datDeliveryDate -
	 *            (受渡日)
	 * @@param l_strOrderAcceptedDiv -
	 *            (注文受付区分)
	 * @@param l_strInputDiv -
	 * 			  (入力区分)
     * @@param l_strCurrencyCode -
     *            (通貨コード)               
	 * @@return String
	 * @@roseuid 4108E3FB0167
	 */
    protected String createGetCondCharacterString(
        long[] l_lngBranchIDs, 
        Date l_datDeliveryDate, 
        String l_strOrderAcceptedDiv, 
        String l_strInputDiv, 
        String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString(" + 
            "long l_lngBranchID, Date l_datDeliveryDate, String l_strOrderAcceptedDiv, " +
            "String l_strInputDiv, String l_strCurrencyCode)";
        log.entering(STR_METHOD_NAME);        
        
        //１）空の文字列を生成する。
        String l_strWhereCondition = "";
        
        //２）注文種別
        //条件文字列： "order_type=?"         

        l_strWhereCondition += "order_type = ?";
        
        //３）出金申込区分 
        //条件文字列： " and payment_application_div=null" 

        l_strWhereCondition += " and payment_application_div is null";
        
        //４）部店ID 

        //４－１）引数.部店IDの要素数 = 1 の場合 
        //条件文字列： " and branch_id=?" 
        //上記文字列を１）の文字列の末尾に追加する。 

        if (l_lngBranchIDs.length == 1)
        {
            l_strWhereCondition += " and branch_id = ?";
        }

        //４－２）引数.部店IDの要素数 > 1 の場合 
        //条件文字列： " and (branch_id=? or branch_id=? or ... or branch_id=?)" 
        //※括弧内の「branch_id=?」の数が要素数分あるようにする 
        //上記文字列を１）の文字列の末尾に追加する。 

        else if (l_lngBranchIDs.length > 1)
        {
            l_strWhereCondition += " and (branch_id = ?";
            for (int i = 1; i < l_lngBranchIDs.length; i++)
            {
                l_strWhereCondition += " or branch_id = ?";                
            }
            l_strWhereCondition += ")";
        }
        
        //５）受渡日 
        //引数.受渡日 != null の場合 
        //条件文字列： " and delivery_date=?" 
        if (l_datDeliveryDate != null)
        {        
            l_strWhereCondition += " and delivery_date = ?";
        }
        
        //６）注文受付区分 
        //引数.注文受付区分 != 3（すべて）の場合 
        //条件文字列： " and order_status=? and cancel_type=?" 
        
        if (!WEB3AioOrderAcceptedDivDef.ALL.equals(l_strOrderAcceptedDiv))
        {
            l_strWhereCondition += " and order_status = ? and cancel_type = ?";
        }        

        // ７）入力区分
        // 引数.入力区分 !=0（すべて） 且つ 入力区分 =1（顧客）の場合
        // 条件文字列： " and order_root_div in (? , ? , ? , ? , ?)"
        // 引数.入力区分 !=0（すべて） 且つ 入力区分 =2（SONAR）の場合
        // 条件文字列： " and order_root_div = ?"
        if (!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.CUSTOMER.equals(l_strInputDiv))
        {
        	l_strWhereCondition += " and order_root_div in (? , ? , ? , ? , ?)";
        }
        else if(!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.SONAR.equals(l_strInputDiv))
        {
        	l_strWhereCondition += " and order_root_div = ?";
        }
        
        //８）通貨コード
        //引数.通貨コード != 0（すべて） 且つ 通貨コード = null(円貨) の場合
        //条件文字列:"and currency_code is null"
        //引数.通貨コード !=0（すべて） 且つ 通貨コード != null の場合
        //条件文字列： " and currency_code = ?"
        //上記文字列を１）の文字列の末尾に追加する。
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_strCurrencyCode) && 
            l_strCurrencyCode == null)
        {
            l_strWhereCondition += " and currency_code is null";
        }
        else if(!WEB3AioCurrencyCodeDef.ALL.equals(l_strCurrencyCode) && 
            l_strCurrencyCode != null)
        {
            l_strWhereCondition += " and currency_code = ?";
        }
        
        //9）生成した文字列を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_strWhereCondition;
    }
    
    /**
	 * (create取得条件データコンテナ)<BR>
	 * リクエストデータから、取得条件のデータリストを生成する。<BR>
	 * <BR>
	 * ２）注文種別 <BR>
	 * 1001（出金注文）を１）のリストに追加する。<BR>
	 * <BR>
	 * ３）部店ID <BR>
	 * 引数.部店IDの各要素を１）のリストに追加する。 <BR>
	 * <BR>
	 * ４）受渡日 <BR>
	 * 引数.受渡日 != null の場合 <BR>
	 * 引数.受渡日を１）のリストに追加する。 <BR>
	 * <BR>
	 * ５）注文状態 <BR>
	 * ５－１）引数.注文受付区分 = 0（受付未済）の場合 <BR>
	 * 1（受付済（新規注文））を１）のリストに追加する。<BR>
	 * <BR>
	 * ５－２）引数.注文受付区分 = 1（受付済）の場合 <BR>
	 * 3（発注済（新規注文））を１）のリストに追加する。<BR>
	 * <BR>
	 * ５－３）引数.注文受付区分 = 2（受付エラー）の場合 <BR>
	 * 6（発注失敗（新規注文））を１）のリストに追加する。<BR>
	 * <BR>
	 * ６）注文取消区分 <BR>
	 * 引数.注文受付区分 != 3（すべて）の場合 <BR>
	 * 0（初期値）を１）のリストに追加する。<BR>
	 * <BR>
	 * ７）入力区分 <BR>
	 * 引数.入力区分 !=0（すべて） 且つ 入力区分 =1（顧客）の場合<BR>
	 * <BR>
	 * 2（PC）、3（スリングショット）、4（i-mode）、5（Vodafone）、6（AU）<BR>
	 * <BR>
	 * を１）のリストに追加する。<BR>
	 * <BR>
	 * 引数.入力区分 !=0（すべて） 且つ 入力区分 =2（SONAR）の場合<BR>
	 * <BR>
	 * 9（HOST）を１）のリストに追加する。 <BR>
     * <BR>
     * ８）通貨コード<BR>
     * 　@引数.通貨コード !=0（すべて） 且つ 通貨コード != null の場合<BR>
     * <BR>
     * 　@引数.通貨コードを１）のリストに追加する。<BR>
     * <BR>
     * <BR>
	 * ９）リストから配列を取得し、返却する。<BR>
	 * <BR>
	 * @@param l_lngBranchIDs -
	 *            (部店ID)
	 * @@param l_datDeliveryDate -
	 *            (受渡日)
	 * @@param l_strOrderAcceptedDiv -
	 *            (注文受付区分)
	 * @@param l_strInputDiv - 
	 * 			  (入力区分)
     * @@param l_strCurrencyCode -
     *            (通貨コード)  
	 * @@return Object[]
	 * @@roseuid 4108EA2D02BF
	 */
    protected Object[] createGetCondDataContainer(
        long[] l_lngBranchIDs, 
        Date l_datDeliveryDate, 
        String l_strOrderAcceptedDiv, 
        String l_strInputDiv, 
        String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer(long[] l_lngBranchIDs, " +
            "Date l_datDeliveryDate, " + 
            "String l_strOrderAcceptedDiv, " + 
            "String l_strInputDiv, " + 
            "String l_strCurrencyCode)";
        log.entering(STR_METHOD_NAME);        
        
        //１）空のArrayListを生成する。 
        List l_lisAioSettleReportUnit = null;
        l_lisAioSettleReportUnit = new ArrayList();

        //２）注文種別 
        //1001（出金注文）を１）のリストに追加する。 
        l_lisAioSettleReportUnit.add(OrderTypeEnum.CASH_OUT);

        //３）部店ID
        //引数.部店IDの各要素を１）のリストに追加する。 
        for (int i = 0; i < l_lngBranchIDs.length; i++)
        {
            l_lisAioSettleReportUnit.add(new Long(l_lngBranchIDs[i]));
        }

        //４）受渡日 

        //引数.受渡日 != null の場合 
        //引数.受渡日を１）のリストに追加する。
        
        if (l_datDeliveryDate != null)
        {
            l_lisAioSettleReportUnit.add(l_datDeliveryDate);
        }
        
        //５）注文状態 

        //５－１）引数.注文受付区分 = 0（受付未済）の場合 
        //1（受付済（新規注文））を１）のリストに追加する。 
        if (WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(OrderStatusEnum.ACCEPTED);
        }

        //５－２）引数.注文受付区分 = 1（受付済）の場合 
        //3（発注済（新規注文））を１）のリストに追加する。 
        if (WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(OrderStatusEnum.ORDERED);
        }

        //５－３）引数.注文受付区分 = 2（受付エラー）の場合 
        //6（発注失敗（新規注文））を１）のリストに追加する。 
        if (WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(OrderStatusEnum.NOT_ORDERED);
        }
        
        //６）注文取消区分
        //引数.注文受付区分 != 3（すべて）の場合
        //0（初期値）を１）のリストに追加する。
        if (!WEB3AioOrderAcceptedDivDef.ALL.equals(l_strOrderAcceptedDiv))
        {
            l_lisAioSettleReportUnit.add(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        }
        
        //７）入力区分 
        //引数.入力区分 !=0（すべて） 且つ 入力区分 =1（顧客）の場合 
        //2（PC）、3（スリングショット）、4（i-mode）、5（Vodafone）、6（AU） 
        //を１）のリストに追加する。 
        //引数.入力区分 !=0（すべて） 且つ 入力区分 =2（SONAR）の場合 
        //9（HOST）を１）のリストに追加する。
        if (!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.CUSTOMER.equals(l_strInputDiv))
        {
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.PC);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.SLINGSHOT);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.I_MODE);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.VODAFONE);
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.AU);
        }
        else if(!WEB3AioInputDivDef.ALL.equals(l_strInputDiv) && WEB3AioInputDivDef.SONAR.equals(l_strInputDiv))
        {
        	l_lisAioSettleReportUnit.add(WEB3OrderRootDivDef.HOST);
        }
        
        //８）通貨コード
        //引数.通貨コード !=0（すべて） 且つ 通貨コード != null の場合
        //引数.通貨コードを１）のリストに追加する。
        if (!WEB3AioCurrencyCodeDef.ALL.equals(l_strCurrencyCode) && 
            l_strCurrencyCode != null)
        {
            l_lisAioSettleReportUnit.add(l_strCurrencyCode);
        }
              
        //９）リストから配列を取得し、返却する。 
        Object[] l_bindVars = 
            new Object[l_lisAioSettleReportUnit.size()];
            
        l_lisAioSettleReportUnit.toArray(l_bindVars);
        
        log.exiting(STR_METHOD_NAME);        
        return l_bindVars;
    }
    
    /**
     * (get注文受付区分)<BR>
     * 注文受付区分を取得する。<BR>
     * <BR>
     * １）１）（注文状態 = 1（受付済） and 注文取消区分 = 0（初期値） <BR>
     *  and MQステータス = 0（未送信）） or（注文状態 = 14（発注済（取消注文）） <BR>
     *  and 注文取消区分 = 3（全部取消完了）） の場合<BR>
     * <BR>
     *    0（受付未済）を返却する。<BR>
     * <BR>
     * ２）注文状態 = 3（発注済） and 注文取消区分 = 0（初期値） の場合<BR>
     * <BR>
     *    1（受付済）を返却する。<BR>
     * <BR>
     * ３）注文状態 = 6（発注失敗） and 注文取消区分 = 0（初期値） の場合<BR>
     * <BR>
     *    2（受付エラー）を返却する。<BR>
     * <BR>
     * ４）注文状態 = 1（受付済） and 注文取消区分 = 0（初期値） and<BR> 
     *   MQステータス = 1（送信済み） の場合 <BR>
     *   4（受付中）を返却する。<BR>
     * <BR>
     * @@param l_lngOrderStatus - (注文状態)
     * @@param l_strCancelType - (注文取消区分)
     * @@param l_strMqStatus - (MQステータス)
     * @@return String
     * @@roseuid 4108F6B20290
     */
    protected String getOrderAcceptType(long l_lngOrderStatus, String l_strCancelType, String l_strMqStatus) 
    {
        final String STR_METHOD_NAME = "getOrderAcceptType()";
        log.entering(STR_METHOD_NAME);
        
        //=========remain zhou-yong NO.6 begin ========
        
        String l_strOrderAcceptType = null;
        //１）（注文状態 = 1（受付済） and 注文取消区分 = 0（初期値） and MQステータス = 0（未送信）） or 
        // （注文状態 = 14（発注済（取消注文）） and 注文取消区分 = 3（全部取消完了）） の場合
        //  0（受付未済）を返却する。 
        if(((OrderStatusEnum.ACCEPTED.intValue() == l_lngOrderStatus) 
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
            && WEB3SubmitMarketTriggerDef.NOT_TRIGGER.equals(l_strMqStatus))
            || ((OrderStatusEnum.CANCELLED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.CANCELED.equals(l_strCancelType)))
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED;
        }
        
        //２）注文状態 = 3（発注済） and 注文取消区分 = 0（初期値） の場合 
        //  1（受付済）を返却する。 
        if((OrderStatusEnum.ORDERED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.ACCEPTED;
        }
        
        //３）注文状態 = 6（発注失敗） and 注文取消区分 = 0（初期値） の場合 
        //  2（受付エラー）を返却する。 
        if((OrderStatusEnum.NOT_ORDERED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType))
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR;
        }
        
        //４）注文状態 = 1（受付済） and 注文取消区分 = 0（初期値） and MQステータス = 1（送信済み） の場合 
        //  4（受付中）を返却する。
        if((OrderStatusEnum.ACCEPTED.intValue() == l_lngOrderStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strCancelType)
            && WEB3SubmitMarketTriggerDef.TRIGGER.equals(l_strMqStatus) )
        {
            l_strOrderAcceptType = WEB3AioOrderAcceptedDivDef.ACCEPTING;
        }
        
        //=========remain zhou-yong NO.6 end ========
        
        log.exiting(STR_METHOD_NAME);
        return l_strOrderAcceptType;
    }
    
    /**
     * (get取消区分)<BR>
     * 取消区分を取得する。<BR>
     * <BR>
     * １）注文状態 = 14（発注済（取消注文）） and <BR>
     * 注文取消区分 = 3（全部取消完了） の場合<BR>
     * <BR>
     *    2（取消済）を返却する。<BR>
     * <BR>
     * ２）それ以外の場合<BR>
     * <BR>
     *    0（初期値）を返却する。<BR>
     * @@param l_lngOrderStatus - (注文状態)
     * @@param l_strCancelType - (注文取消区分)
     * @@return String
     * @@roseuid 4108F9BE008D
     */
    protected String getCancelDivision(long l_lngOrderStatus, String l_strCancelType) 
    {
        final String STR_METHOD_NAME = "getCancelDivision()";
        log.entering(STR_METHOD_NAME);        
       
        //１）注文状態 = 14（発注済（取消注文）） and 注文取消区分 = 3（全部取消完了） の場合
        String l_strCancelDiv = null;
        
        if (l_lngOrderStatus == OrderStatusEnum.CANCELLED.intValue() &&
            WEB3ModifyCancelTypeDef.CANCELED.equals(l_strCancelType)) 
        {
            //2（取消済）を返却する。 
            l_strCancelDiv = WEB3ModifyCancelTypeDef.PART_CANCELED;
        }
        //２）それ以外の場合 
        else
        {
            //0（初期値）を返却する。 
            l_strCancelDiv = WEB3ModifyCancelTypeDef.INITIAL_VALUE;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strCancelDiv;
    }
    
    /**
     * (create出金申込問合せ明細)<BR>
     * 出金申込問合せ明細オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ）create出金申込問合せ明細」 参照<BR>
     * @@param l_aioOrderUnitParams - (注文単位Params)<BR>
     *     注文単位Paramsオブジェクト <BR>
     * @@return WEB3AioCashoutInqUnit
     * @@throws WEB3BaseException
     * @@roseuid 4109B3DA0250
     */
    protected WEB3AioCashoutInqUnit createCashoutInqUnit(
        AioOrderUnitRow l_aioOrderUnitRow) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "createCashoutInqUnit(" +
            "AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_aioOrderUnitRow == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 出金申込問合せ明細インスタンスを生成する。 
        WEB3AioCashoutInqUnit l_web3AioCashoutInqUnit = new WEB3AioCashoutInqUnit();        
        AccountManager l_accountManager = GtlUtils.getAccountManager();        
        WEB3GentradeMainAccount l_gentradeMainAccount = null;
        String l_strBranchCode = null;
        String l_strMainAccountCode = null;
        MainAccount l_mainAccount = null;
        
        try
        {  
            //1.2 部店オブジェクトを取得する。
            Branch l_branch = l_accountManager.getBranch(
                l_aioOrderUnitRow.getBranchId());
            
            //1.3 部店コードを取得する。 
            l_strBranchCode = l_branch.getBranchCode();
            
            //1.4 顧客オブジェクトを取得する。           
            l_mainAccount = l_accountManager.getMainAccount(
                l_aioOrderUnitRow.getAccountId());
            
            //1.5 顧客コードを取得する。 
            l_strMainAccountCode = l_mainAccount.getAccountCode().substring(0, 6);
            
            //1.6 顧客の名称を取得する。
            l_gentradeMainAccount =
                (WEB3GentradeMainAccount) l_mainAccount;  
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        String l_strMainAccountName = l_gentradeMainAccount.getDisplayAccountName();
        
        //1.7 get振込先金融機@関レコード(顧客)
        //検索条件から取得した振込先金融機@関レコードをList型で返却する。
        List l_lisTransferedFinInstitution = this.getTransferedFinInstitutionRecord(l_mainAccount);
        
        //1.8 create振込先金融機@関情報
        //振込先金融機@関の情報を出金申込問合せ明細オブジェクトに格納する。
        this.createTransferedFinInstitutionInfo(l_lisTransferedFinInstitution, l_web3AioCashoutInqUnit);
        
        // =============remain zhou-yong NO.7 begin =========
        
        //1.9 注文受付区分を取得する。 
        //[引数] 
        //注文状態： 引数.注文単位Params.注文状態 
        //注文取消区分： 引数.注文単位Params.注文取消区分 
        //MQステータス： 引数.注文単位Params.MQステータス

        log.debug("注文状態：" + l_aioOrderUnitRow.getOrderStatus().intValue());
        log.debug("注文取消区分：" + l_aioOrderUnitRow.getCancelType());
        
        String l_strOrderAcceptType = this.getOrderAcceptType(
            l_aioOrderUnitRow.getOrderStatus().intValue(),
            l_aioOrderUnitRow.getCancelType(),
            l_aioOrderUnitRow.getMqStatus());
        // =============remain zhou-yong NO.7 end =========
        
        //1.10 取消区分を取得する。 
        //[引数] 
        //注文状態： 引数.注文単位Params.注文状態 
        //注文取消区分： 引数.注文単位Params.注文取消区分 
        String l_strCancelDiv = this.getCancelDivision(
            l_aioOrderUnitRow.getOrderStatus().intValue(),
            l_aioOrderUnitRow.getCancelType());
        
        //1.13 getTrader(扱者ID : long)
        //注文単位.取引者IDの扱者コードを取得する。
        //[引数]
        //扱者ID： 注文単位Params.取引者ID

        String l_strTraderCode = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //注文単位.取引者ID≠nullの場合
        if(!l_aioOrderUnitRow.getTraderIdIsNull())
        {
            try
            {
                //拡張金融オブジェクトマネージャ.getTrader()
                Trader l_trader = null;
                l_trader = l_finObjectManager.getTrader(l_aioOrderUnitRow.getTraderId());
                l_strTraderCode = l_trader.getTraderCode();
            }
            catch (NotFoundException l_nfe)
            {
                //注文単位.取引者IDに該当するデータが扱者テーブルに存在しない場合
                l_strTraderCode = null;
            }
        }
        
        //1.14 プロパティセット
        //(*) 以下のとおり、プロパティをセットする。
                
        //出金申込問合せ明細. 注文ID = 引数.注文単位Params.注文ID
        l_web3AioCashoutInqUnit.orderId = l_aioOrderUnitRow.getOrderId() + "";
        log.debug("出金申込問合せ明細. 注文ID =" + l_web3AioCashoutInqUnit.orderId);
        
        //出金申込問合せ明細. 部店コード = 部店.getBranchCode()の戻り値
        l_web3AioCashoutInqUnit.branchCode = l_strBranchCode;
        log.debug("出金申込問合せ明細. 部店コード =" + l_web3AioCashoutInqUnit.branchCode);
                
        //出金申込問合せ明細. 顧客コード = 顧客.getAccountCode()の戻り値
        l_web3AioCashoutInqUnit.accountCode = l_strMainAccountCode;   
        log.debug("出金申込問合せ明細. 顧客コード =" + l_web3AioCashoutInqUnit.accountCode);
             
        //出金申込問合せ明細. 顧客名 = 顧客.get顧客表示名()の戻り値
        l_web3AioCashoutInqUnit.accountName = l_strMainAccountName;
        log.debug("出金申込問合せ明細. 顧客名 =" + l_web3AioCashoutInqUnit.accountName);
        
        //出金申込問合せ明細. 注文日時 = 引数.注文単位Params.注文経路区分
        l_web3AioCashoutInqUnit.orderRootDiv = l_aioOrderUnitRow.getOrderRootDiv();
        log.debug("出金申込問合せ明細. 注文経路区分 =" + l_web3AioCashoutInqUnit.orderRootDiv);
        
        //出金申込問合せ明細. 扱者コード = getTrader().getTraderCode
        l_web3AioCashoutInqUnit.traderCode = l_strTraderCode;
        log.debug("出金申込問合せ明細. 扱者コード =" + l_web3AioCashoutInqUnit.traderCode);
                
        //出金申込問合せ明細. 注文日時 = 引数.注文単位Params.受付日時
        l_web3AioCashoutInqUnit.orderDate = l_aioOrderUnitRow.getReceivedDateTime();
        log.debug("出金申込問合せ明細. 注文日時 =" + l_web3AioCashoutInqUnit.orderDate);
        
        //出金申込問合せ明細. 受渡日 = 引数.注文単位Params.受渡日
        l_web3AioCashoutInqUnit.deliveryDate = l_aioOrderUnitRow.getDeliveryDate();
        log.debug("出金申込問合せ明細. 受渡日 =" + l_web3AioCashoutInqUnit.deliveryDate);
        
        //出金申込問合せ明細. 出金額 = 引数.注文単位Params.数量
        l_web3AioCashoutInqUnit.cashoutAmt = WEB3StringTypeUtility.formatNumber(
                l_aioOrderUnitRow.getQuantity());
        log.debug("出金申込問合せ明細. 出金額 =" + l_web3AioCashoutInqUnit.cashoutAmt);
        
        //出金申込問合せ明細. 注文受付区分 = 出金申込問合せサービスImpl.get注文受付区分()の戻り値
        l_web3AioCashoutInqUnit.orderDiv = l_strOrderAcceptType;
        log.debug("出金申込問合せ明細. 注文受付区分 =" + l_web3AioCashoutInqUnit.orderDiv);
        
        //出金申込問合せ明細. 取消区分 = 出金申込問合せサービスImpl.get取消区分()の戻り値
        l_web3AioCashoutInqUnit.cancelDiv = l_strCancelDiv;
        log.debug("出金申込問合せ明細. 取消区分 =" + l_web3AioCashoutInqUnit.cancelDiv);
        
        //出金申込問合せ明細. 取消日時 = （以下のとおり）
        //     出金申込問合せ明細. 取消区分 = 0（初期値）の場合、null
        //     出金申込問合せ明細. 取消区分 = 2（取消済）の場合、引数.注文単位Params.更新日付
        if (WEB3AioCancelDivDef.INITIAL_VALUE.equals(l_web3AioCashoutInqUnit.cancelDiv))
        {
            l_web3AioCashoutInqUnit.cancelDate = null;
        }
        else if (WEB3AioCancelDivDef.CANCElED.equals(l_web3AioCashoutInqUnit.cancelDiv))
        {
            l_web3AioCashoutInqUnit.cancelDate = l_aioOrderUnitRow.getLastUpdatedTimestamp();
        }
        log.debug("出金申込問合せ明細. 取消日時 =" + l_web3AioCashoutInqUnit.cancelDate);
        
        //出金申込問合せ明細. 通貨コード = 引数.注文単位Params.通貨コード
        l_web3AioCashoutInqUnit.currencyCode = l_aioOrderUnitRow.getCurrencyCode();
        log.debug("出金申込問合せ明細. 通貨コード =" + l_web3AioCashoutInqUnit.currencyCode);
          
        //出金申込問合せ明細. チェック結果 = null
        l_web3AioCashoutInqUnit.checkResult = null;
        
        log.exiting(STR_METHOD_NAME);
        return l_web3AioCashoutInqUnit;

    }
    
    /**
     * (sort出金申込問合せ明細)<BR>
     * 出金申込問合せ明細の配列をソートする。<BR>
     * <BR>
     * ソート順： 部店コード（昇順） ＞ 注文日時（昇順）<BR>
     * <BR>
     * １）空のArrayListインスタンスを生成する。 <BR>
     * <BR>
     * ２）部店コードComparatorを生成する。<BR>
     *    引数.orderByには、A（昇順）を設定する。<BR>
     * <BR>
     *    生成したComparatorをListに追加する。<BR>
     * <BR>
     * ３）注文日時Comparatorを生成する。<BR>
     *    引数.orderByには、A（昇順）を設定する。<BR>
     * <BR>
     *    生成したComparatorをListに追加する。<BR>
     * <BR>
     * ４）ArrayListからComparatorの配列を取得する。<BR>
     * <BR>
     * ５）Comparatorの配列順のソート処理を行う。<BR>
     *    WEB3ArraysUtility.sort(引数.明細, Comparator[]) <BR>
     * <BR>
     * ６）ソートされた明細の配列を返却する。<BR>
     * <BR>
     * @@param l_cashoutInqUnit - (出金申込問合せ明細の配列)
     * @@return WEB3AioCashoutInqUnit
     * @@roseuid 4109B7340146
     */
    protected WEB3AioCashoutInqUnit[] sortCashoutInqUnit(
        WEB3AioCashoutInqUnit[] l_cashoutInqUnit) 
    {
        final String STR_METHOD_NAME = "sortCashoutInqUnit()";
        log.entering(STR_METHOD_NAME);
           
        //１）空のArrayListインスタンスを生成する。        
        ArrayList l_lisComparator = new ArrayList();
        
        //２）部店コードComparatorを生成する。
        WEB3AioBranchCodeComparator l_branchCodeComparator = 
            new WEB3AioBranchCodeComparator(WEB3AscDescDef.ASC);
            
        l_lisComparator.add(l_branchCodeComparator);
            
        //３）注文日時Comparatorを生成する。
        WEB3AioOrderDateComparator l_orderDateComparator = 
            new WEB3AioOrderDateComparator(WEB3AscDescDef.ASC);
        
        l_lisComparator.add(l_orderDateComparator);
        
        //４）ArrayListからComparatorの配列を取得する。
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        
        //５）Comparatorの配列順のソート処理を行う。 
        //WEB3ArraysUtility.sort(引数.明細, Comparator[]) 
        WEB3ArraysUtility.sort(l_cashoutInqUnit, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
        return l_cashoutInqUnit;
    }
    
    /**
     * (get表示明細)<BR>
     * 出金申込問合せ明細の配列から要求ページ内に表示される明細<BR>
     * の配列を取得して返却する。<BR>
     * <BR>
     * １）空のArrayListインスタンスを生成する。<BR>
     * <BR>
     * ２）出金申込問合せ明細[引数.fromIndex]から出金申込問合せ<BR>
     * 明細［引数.toIndex]の要素を、１）のArrayListに追加する。<BR>
     * <BR>
     * ３）ArrayListから配列を取得する。<BR>
     * <BR>
     * ４）生成した配列を返却する。<BR>
     * @@param l_cashoutInqUnits - (出金申込問合せ明細の配列)
     * @@param l_intFromIndex - (表示対象の開始位置のインデックス番号)
     * @@param l_intToIndex - (表示対象の終了位置のインデックス番号)
     * @@return WEB3AioCashoutInqUnit[]
     * @@roseuid 4109E31D00BA
     */
    protected WEB3AioCashoutInqUnit[] getIndicationDetails(
        WEB3AioCashoutInqUnit[] l_cashoutInqUnits, 
        int l_intFromIndex, 
        int l_intToIndex)
    {
        final String STR_METHOD_NAME = "getIndicationDetails" + 
            "WEB3AioCashoutInqUnit[] l_cashoutInqUnits, " +
            "int l_intFromIndex, int l_intToIndex)";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashoutInqUnits == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        if (l_cashoutInqUnits.length == 0)
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）空のArrayListインスタンスを生成する。        
        List l_lisCashoutInqUnits = null;
        l_lisCashoutInqUnits = new ArrayList();
        
        //２）出金申込問合せ明細[引数.fromIndex]から出金申込問合せ
        //明細［引数.toIndex]の要素を、１）のArrayListに追加する。
        for (int i = l_intFromIndex; i <= l_intToIndex; i++)
        {          
            l_lisCashoutInqUnits.add(l_cashoutInqUnits[i]);
        }
        
        //３）ArrayListから配列を取得する。
        WEB3AioCashoutInqUnit[] l_web3AioCashoutInqUnits =  
            new WEB3AioCashoutInqUnit[l_lisCashoutInqUnits.size()];
            
        //1.5 Listから配列を取得する。 
        l_lisCashoutInqUnits.toArray(l_web3AioCashoutInqUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_web3AioCashoutInqUnits;
    }
    
    /**
	 * (get振込先金融機@関レコード )<BR>
	 * 検索条件から取得した振込先金融機@関レコードをList型で返却する。<BR>
	 * <BR>
	 * １）doFindAllQuery()を使用して振込先金融機@関テーブルを以下の条件で検索。<BR>
	 * <BR>
	 * [条件] 
	 * 振込先金融機@関テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode()
	 * 振込先金融機@関テーブル.部店コード = 顧客.getBranch().getBranchCode() 
	 * 振込先金融機@関テーブル.顧客コード = 顧客.getAccountCode()
	 * 振込先金融機@関テーブル.指定区分 = 'A'
	 * <BR>
	 * 2）検索結果を返却<BR>
	 * <BR>
	 * @@param l_mainAccount - (顧客)
	 * @@return List
	 * @@roseuid 4109E31D00BA
	 */
    protected List getTransferedFinInstitutionRecord(MainAccount l_mainAccount)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransferedFinInstitutionRecord" + 
        	"(MainAccount l_mainAccount) ";
        log.entering(STR_METHOD_NAME);
    	
    	//[条件]  
    	//振込先金融機@関テーブル.証券会社コード = 顧客.getInstitution().getInstitutionCode()  
    	//振込先金融機@関テーブル.部店コード = 顧客.getBranch().getBranchCode()  
    	//振込先金融機@関テーブル.顧客コード = 顧客.getAccountCode()  
    	//振込先金融機@関テーブル.指定区分 = 'A'  
    	String l_strWhere = null;
		l_strWhere = "institution_code = ? and branch_code = ? " +
			"and account_code = ? and designate_div = ?";
        Object[] l_bindVars = 
            new Object[]
                {l_mainAccount.getInstitution().getInstitutionCode(),
        		l_mainAccount.getBranch().getBranchCode(),
        		l_mainAccount.getAccountCode(),	
        		WEB3DesignateDivDef.SALE_TURNOVER};
        
        List l_lisRows = null;
        try
		{
			l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
				TransferedFinInstitutionRow.TYPE, 
				l_strWhere, 
				null,
				l_bindVars);
		}
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    	return l_lisRows;
    }
    
    /**
	 * (create振込先金融機@関情報)<BR>
	 * 振込先金融機@関の情報を出金申込問合せ明細オブジェクトに格納する。<BR>
	 * <BR>
	 * １）引数:振込先金融機@関レコード.size() == 0 の場合、<BR>
	 * <BR>
	 * １－１） 出金問合せ明細.金融機@関コード = null<BR>
	 * <BR>
	 * １－２） 出金問合せ明細.振込先支店コード = null<BR>
	 * <BR>
	 * １－３） 出金問合せ明細.預金区分 = null<BR>
	 * <BR>
	 * １－４） 出金問合せ明細.振込先口座番号 = null<BR>
	 * <BR>
	 * １－５） 出金問合せ明細.振込先口座名義人 = null<BR>
	 * <BR>
	 * <BR>
	 * ２）引数:振込先金融機@関レコード.size() != 0 の場合、<BR>
	 * <BR>
	 * ２－１） 出金問合せ明細.金融機@関コード = 引数:振込先金融機@関レコード.get(0).get銀行コード<BR>
	 * <BR>
	 * ２－２） 出金問合せ明細.振込先支店コード = 引数:振込先金融機@関レコード.get(0).get支店コード<BR>
	 * <BR>
	 * ２－３） 出金問合せ明細.預金区分 = 引数:振込先金融機@関レコード.get(0).get預金区分<BR>
	 * <BR>
	 * ２－４） 出金問合せ明細.振込先口座番号 = 引数:振込先金融機@関レコード.get(0).get口座番号<BR>
	 * <BR>
	 * ２－５） 出金問合せ明細.振込先口座名義人 = 引数:振込先金融機@関レコード.get(0).get口座名義人<BR>
	 * <BR>
	 * @@param l_lstTransferedFinInstitution - (振込先金融機@関レコード)
	 * @@param l_cashoutInqUnit - (出金申込問合せ明細)
	 * @@roseuid 4109E31D00BA
	 */
    protected void createTransferedFinInstitutionInfo(List l_lstTransferedFinInstitution,
    	WEB3AioCashoutInqUnit l_cashoutInqUnit)
    {
        final String STR_METHOD_NAME = "createTransferedFinInstitutionInfo" + 
    	"(List l_lstTransferedFinInstitution, WEB3AioCashoutInqUnit l_cashoutInqUnit)";
        log.entering(STR_METHOD_NAME);
    
    	//１）引数:振込先金融機@関レコード.size() == 0 の場合
    	if(l_lstTransferedFinInstitution == null || l_lstTransferedFinInstitution.size() ==0)
    	{
    		//１－１） 出金問合せ明細.金融機@関コード = null
    		l_cashoutInqUnit.financialInstitutionCode = null;
    		
    		//１－２） 出金問合せ明細.振込先支店コード = null
    		l_cashoutInqUnit.transferBranchCode = null;
    		
    		//１－３） 出金問合せ明細.預金区分 = null
    		l_cashoutInqUnit.transferAccountDiv = null;
    		
    		//１－４） 出金問合せ明細.振込先口座番号 = null
    		l_cashoutInqUnit.transferAccountNumber = null;
    		
    		//１－５） 出金問合せ明細.振込先口座名義人 = null
    		l_cashoutInqUnit.transferAccountName = null;
    	}
    	//２）引数:振込先金融機@関レコード.size() != 0 の場合
    	else
    	{
    		TransferedFinInstitutionRow l_transferedFinInstitutionRow = 
    			((TransferedFinInstitutionRow)l_lstTransferedFinInstitution.get(0));
			// ２－１） 出金問合せ明細.金融機@関コード = 引数:振込先金融機@関レコード.get(0).get銀行コード
    		l_cashoutInqUnit.financialInstitutionCode = 
    			l_transferedFinInstitutionRow.getFinInstitutionCode();
    		
			// ２－２） 出金問合せ明細.振込先支店コード = 引数:振込先金融機@関レコード.get(0).get支店コード
    		l_cashoutInqUnit.transferBranchCode =
    			l_transferedFinInstitutionRow.getFinBranchCode();
    		
			// ２－３） 出金問合せ明細.預金区分 = 引数:振込先金融機@関レコード.get(0).get預金区分
    		l_cashoutInqUnit.transferAccountDiv = 
    			l_transferedFinInstitutionRow.getFinSaveDiv();
    		
			// ２－４） 出金問合せ明細.振込先口座番号 = 引数:振込先金融機@関レコード.get(0).get口座番号
    		l_cashoutInqUnit.transferAccountNumber =
    			l_transferedFinInstitutionRow.getFinAccountNo();
    		
			// ２－５） 出金問合せ明細.振込先口座名義人 = 引数:振込先金融機@関レコード.get(0).get口座名義人
    		l_cashoutInqUnit.transferAccountName =
    			l_transferedFinInstitutionRow.getFinAccountName();
		}
    }
    
    /**
	 * (getダウンロードファ@イル)<BR>
	 * 出金申込問合せダウンロードファ@イルデータ取得処理を行う。<BR>
	 * <BR>
	 * シーケンス図 「（出金申込問合せ）getダウンロードファ@イル」 参照<BR>
	 * <BR>
	 * @@param WEB3AdminAioCashoutInqDownloadRequest - (リクエストデータ)<BR>
	 *     出金申込問合せダウンロードリクエストオブジェクト<BR>
	 * @@return WEB3AdminAioCashoutInqDownloadResponse - (出金申込問合せダウンロードレスポンス)<BR>
	 * @@throws WEB3BaseException
	 * @@roseuid 4109E31D00BA
	 */
    protected WEB3AdminAioCashoutInqDownloadResponse getDownloadFile(
    	WEB3AdminAioCashoutInqDownloadRequest l_request)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDownloadFile" + 
        	"(WEB3AdminAioCashoutInqDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
    	
        //1.1 validate()
        l_request.validate();
        
    	//1.2 管理者インスタンスを取得する。
		WEB3Administrator l_web3Administrator = 
			WEB3Administrator.getInstanceFromLoginInfo();
		
        //1.3 validate権限(String, boolean)(管理者::validate権限)
        //アイテムの定義
        //該当の管理者がこの機@能が使えるか権限チェックを行う。
        //[引数] 
        //機@能カテゴリコード： ”B0101” 
        //is更新： true 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_OUT_INQ, true);

        //1.4 validate部店権限(String[]): 
        //該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_web3Administrator.validateBranchPermission(l_request.branchCode);
        
        AccountManager l_accountManager = GtlUtils.getAccountManager();
        Branch l_branch = null;
        long l_lngBranchId = 0L;
        //1.5 リストのインスタンスを生成する。
        long[] l_lngBranchIds = 
            new long[l_request.branchCode.length];
        
        //1.6 証券会社オブジェクトを取得する。 
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.7 (*1) リクエストデータ.部店コードの各要素毎にLoop処理
        for (int i = 0; i < l_request.branchCode.length; i++)
        {
            //1.7.1 部店インスタンスを生成する。 
            //［引数］ 
            //証券会社： get証券会社()の戻り値
            //部店コード： リクエストデータ.部店コード                        
            try
            {
                l_branch = l_accountManager.getBranch(
                    l_institution, l_request.branchCode[i]);
            }
            catch (NotFoundException l_ex)
            {
                log.error("部店インスタンスを生成する:", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.7.2 部店IDを取得する。 
            l_lngBranchId = l_branch.getBranchId();    
                
            //1.7.3 部店IDをリストに追加する。 
            //[引数] 
            //arg0： 部店ID
            l_lngBranchIds[i] = l_lngBranchId;
        }
        	
        // 1.8 toArray()
        	
        // 1.9 出金申込問合わせダウンロードCSV()
        // 1.9.1 createキーヘッダ()
        // 1.9.2 createカラムヘッダ()
        WEB3AdminAioCashoutInqDownloadCsv l_cashoutInqDownloadCsv = 
        	new WEB3AdminAioCashoutInqDownloadCsv();
         
        // 1.10 データ取得条件文字列を生成する。
        // ［引数］
        // 部店ID： 部店IDの配列
        // 受渡日： リクエストデータ.受渡日
        // 注文受付区分： リクエストデータ.注文受付区分
        // 入力区分：　@リクエストデータ.入力区分
        // 通貨コード：　@null
        String l_strWhereClause = null;
        l_strWhereClause = this.createGetCondCharacterString(
            l_lngBranchIds, 
            l_request.deliveryDate,
            l_request.orderDiv,
            l_request.inputDiv, 
            null);

        // 1.11 取得条件データコンテナを生成する。
        // ［引数］
        // 部店ID： 部店IDの配列
        // 受渡日： リクエストデータ.受渡日
        // 注文受付区分： リクエストデータ.注文受付区分
        // 入力区分：　@リクエストデータ.入力区分
        // 通貨コード：　@null
        Object l_bindVars[] = this.createGetCondDataContainer(
            l_lngBranchIds, 
            l_request.deliveryDate, 
            l_request.orderDiv,
            l_request.inputDiv, 
            null);
            
        //1.12  1.13 注文単位テーブルから、レコードを取得する。
		//［引数］ 
		//arg0： 注文単位Row.TYPE 
		//arg1： create取得条件文字列()の戻り値 
		//arg2： "branch_id asc , received_date_time asc" 
		//arg3： null 
		//arg4： create取得条件データコンテナ()の戻り値 
        List l_lisRows = null;
        String l_strSort = " branch_id asc , received_date_time asc";
        try
        {
            l_lisRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhereClause,                    
                    l_strSort,
                    null,
                    l_bindVars);                                        
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        	
        //1.15 (*2)取得したレコード毎にLoop処理
        int l_lisRowsSize = 0;
        if (l_lisRows != null)
        {
        	l_lisRowsSize = l_lisRows.size();
        }
        AioOrderUnitRow l_aioOrderUnitRow = null;
        for (int i = 0;i < l_lisRowsSize;i ++)
        {
        	l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(i);
        	//1.15.1 add明細行( )
        	l_cashoutInqDownloadCsv.addRow();
        	
        	//1.15.2 set部店(int, String)
        	l_cashoutInqDownloadCsv.setBranch(
        		i, 
        		l_aioOrderUnitRow.getBranchId() + "");
        	
        	//1.15.3 set顧客(int, long)
        	l_cashoutInqDownloadCsv.setAccount(
        		i, 
        		l_aioOrderUnitRow.getAccountId());
        	
        	//1.15.4 set注文日時(int, Date)
        	l_cashoutInqDownloadCsv.setOrderDate(
        		i, 
        		l_aioOrderUnitRow.getReceivedDateTime());
        	
        	//1.15.5 set受渡日(int, Date)
        	l_cashoutInqDownloadCsv.setDeliveryDate(
        		i, 
        		l_aioOrderUnitRow.getDeliveryDate());
        	
        	//1.15.6 set出金額(int, double)
        	l_cashoutInqDownloadCsv.setCashoutAmount(
        		i, 
        		l_aioOrderUnitRow.getQuantity());
        	
        	//1.15.7 get注文受付区分(long, String, String)
        	String l_strOrderAcceptType =
        		this.getOrderAcceptType(
        			l_aioOrderUnitRow.getOrderStatus().intValue(),
        			l_aioOrderUnitRow.getCancelType(), 
        			l_aioOrderUnitRow.getMqStatus());
        	
        	//1.15.8 set注文状態(int, String)
        	l_cashoutInqDownloadCsv.setOrderStatus(i, l_strOrderAcceptType);
        	
        	//1.15.9 get取消区分(long, String)
        	String l_strCancelDivision = 
        		this.getCancelDivision(
        			l_aioOrderUnitRow.getOrderStatus().intValue(), 
        			l_aioOrderUnitRow.getCancelType());
        	
        	//1.15.10 set取消区分(int, String)
        	l_cashoutInqDownloadCsv.setCancelDiv(i, l_strCancelDivision);
        	
        	//1.15.11 set取消日時(int, Date)
        	if(WEB3AioCancelDivDef.INITIAL_VALUE.equals(l_strCancelDivision))
        	{
        		l_cashoutInqDownloadCsv.setCancelDate(i, null);
        	}
        	else if(WEB3AioCancelDivDef.CANCElED.equals(l_strCancelDivision))
        	{
        		l_cashoutInqDownloadCsv.setCancelDate(
        			i, 
        			l_aioOrderUnitRow.getLastUpdatedTimestamp());
        	}
        	
        	//1.15.12 MainAccountImpl(arg0 : long)              
        	MainAccount l_mainAccount = null;
        	try
			{
				l_mainAccount = 
					l_accountManager.getMainAccount(
						l_aioOrderUnitRow.getAccountId());

		        //1.14 ArrayListインスタンスを生成する。
				//1.15.13 get振込先金融機@関レコード(顧客)
				List l_lisTransferedFinInstitutionRow = new ArrayList();
				l_lisTransferedFinInstitutionRow = this.getTransferedFinInstitutionRecord(l_mainAccount);

				if (l_lisTransferedFinInstitutionRow == null || l_lisTransferedFinInstitutionRow.size() == 0)
				{
					//1.15.14 set振込先銀行コード(int, String)
					l_cashoutInqDownloadCsv.setTransferBankCode(i, null);

					//1.15.15 set振込先支店コード(int, String)
					l_cashoutInqDownloadCsv.setTransferBranchCode(i, null);

					//1.15.16 set預金区分(int, String)
					l_cashoutInqDownloadCsv.setDepositDiv(i, null);

					//1.15.17 set振込先口座番号(int, String)
					l_cashoutInqDownloadCsv.setTransferAccountNumber(i, null);

					//1.15.18 set振込先名義人(int, String)
					l_cashoutInqDownloadCsv.setTransferAccountName(i, null);
				}
				else
				{
					TransferedFinInstitutionRow l_transferedFinInstitutionRow =
						((TransferedFinInstitutionRow) l_lisTransferedFinInstitutionRow.get(0));
					//1.15.14 set振込先銀行コード(int, String)
					l_cashoutInqDownloadCsv.setTransferBankCode(
						i, 
						l_transferedFinInstitutionRow.getFinInstitutionCode());

					//1.15.15 set振込先支店コード(int, String)
					l_cashoutInqDownloadCsv.setTransferBranchCode(
						i, 
						l_transferedFinInstitutionRow.getFinBranchCode());

					//1.15.16 set預金区分(int, String)
					l_cashoutInqDownloadCsv.setDepositDiv(
						i, 
						l_transferedFinInstitutionRow.getFinSaveDiv());

					//1.15.17 set振込先口座番号(int, String)	
					l_cashoutInqDownloadCsv.setTransferAccountNumber(
						i,
						l_transferedFinInstitutionRow.getFinAccountNo());

					//1.15.18 set振込先名義人(int, String)
					l_cashoutInqDownloadCsv.setTransferAccountName(
						i,
						l_transferedFinInstitutionRow.getFinAccountName());
				}

				//1.15.19 is信用口座開設(弁済区分 : String)
				WEB3GentradeMainAccount l_gentradeMainAcc = (WEB3GentradeMainAccount) l_mainAccount;
				boolean l_blnIsMarginAccountEstablished = l_gentradeMainAcc.isMarginAccountEstablished(
					WEB3GentradeRepaymentDivDef.DEFAULT);
				
				//1.15.20 getSubAccount(SubAccountTypeEnum)
				//補助口座を取得する。  
				//[引数]  
				//補助口座タイプ：　@SubAccountTypeEnum.EQUITY_SUB_ACCOUNT 
				SubAccount l_subAccount = null;
				l_subAccount = l_gentradeMainAcc.getSubAccount(
					SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
				
				WEB3TPTradingPowerService l_tradingPowerService = 
					(WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
				//1.15.22 is信用口座開設( ) == trueの場合
				if (l_blnIsMarginAccountEstablished)
				{				
					//1.15.22.1 get資産余力情報<信用顧客>(補助口座 : 補助口座)
					WEB3GentradeSubAccount l_gentradeSubAccount = 
						(WEB3GentradeSubAccount)l_subAccount;
					WEB3TPTradingPowerCalcMargin l_tPTradingPowerCalcMargin = 
			        	l_tradingPowerService.getTradingPowerCalcMargin(l_gentradeSubAccount);
				
			        //1.15.22.2 set信用顧客余力情報(資産余力情報<信用顧客>, int)
			        l_cashoutInqDownloadCsv.setTPTradingPowerCalcMargin(
			        	l_tPTradingPowerCalcMargin, i);
				}
				//1.15.21 is信用口座開設( ) == falseの場合
				else
				{	
					//1.15.21.1 get資産余力情報<現物顧客>(補助口座 : 補助口座)
					WEB3GentradeSubAccount l_gentradeSubAccount = 
						(WEB3GentradeSubAccount)l_subAccount;
					WEB3TPTradingPowerCalcEquity l_tPTradingPowerCalcEquity = 
			        	l_tradingPowerService.getTradingPowerCalcEquity(l_gentradeSubAccount);
				
			        //1.15.21.2 set現物顧客余力情報(資産余力情報<現物顧客>, int)
			        l_cashoutInqDownloadCsv.setTPTradingPowerCalcEquity(
			        	l_tPTradingPowerCalcEquity, i);
				}
			}
			catch (NotFoundException l_ex)
			{
				log.error("証券会社インスタンスを生成する", l_ex);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(), l_ex);
			}
		}
        //1.16 getCSVファ@イル行( )
        String[] l_strCsvFileLines = l_cashoutInqDownloadCsv.getCsvFileLines();
        
        //1.17 createResponse( )
        WEB3AdminAioCashoutInqDownloadResponse l_response = 
        	(WEB3AdminAioCashoutInqDownloadResponse)l_request.createResponse();
 
        //1.18 （*5）プロパティセット
        l_response.downloadFile = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();
        
		log.exiting(STR_METHOD_NAME);
		return l_response;
    }

    /**
     * (set出金余力)<BR>
     * 出金余力の計算し、セットする。<BR>
     * <BR>
     * <BR>
     * １）　@引数:出金問合せ明細の要素分、Loop処理を行う。<BR>
     * <BR>
     * 　@１－１）　@拡張アカウントマネージャ.get顧客（）にて顧客オブジェクトの取得<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@・　@引数:証券会社コード<BR>
     * 　@　@　@　@　@　@　@・　@出金問合せ明細[index].部店コード<BR>
     * 　@　@　@　@　@　@　@・　@出金問合せ明細[index].口座コード<BR>
     * <BR>
     * 　@１－２）　@拡張アカウントマネージャ.get補助口座（）にて補助口座オブジェクトの取得<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@・　@顧客オブジェクトから取得した口座ID<BR>
     * 　@　@　@　@　@　@　@・　@SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * <BR>
     * 　@１－３）　@取引余力サービスImpl.get出金可能額～出金入力画面表示用～()　@にて出金可能額を取得<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@・　@get補助口座（）で取得した値<BR>
     * 　@　@　@　@　@　@　@・　@出金問合せ明細[index].受渡日<BR>
     * <BR>
     * 　@１－４）　@出金問合せ明細[index].出金余力=１－３）で取得した値<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_aioCashoutInqUnit - (出金問合せ明細一覧)<BR>
     * 出金問合せ明細一覧<BR>
     * @@throws WEB3BaseException
     */
    protected void setPaymentPower(
        String l_strInstitutionCode,
        WEB3AioCashoutInqUnit[] l_aioCashoutInqUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setPaymentPower(String, WEB3AioCashoutInqUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_aioCashoutInqUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        int l_intAioCashoutInqUnit = l_aioCashoutInqUnit.length;

        try
        {        
            //１）　@引数:出金問合せ明細の要素分、Loop処理を行う。
            for (int i = 0; i < l_intAioCashoutInqUnit; i++)
            {
                //　@１－１）　@拡張アカウントマネージャ.get顧客（）にて顧客オブジェクトの取得
                //　@　@　@　@　@　@[引数]
                //　@　@　@　@　@　@　@・　@引数:証券会社コード
                //　@　@　@　@　@　@　@・　@出金問合せ明細[index].部店コード
                //　@　@　@　@　@　@　@・　@出金問合せ明細[index].口座コード
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_web3GentradeAccountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();

                MainAccount l_mainAccount =
                    l_web3GentradeAccountManager.getMainAccount(
                        l_strInstitutionCode,
                        l_aioCashoutInqUnit[i].branchCode,
                        l_aioCashoutInqUnit[i].accountCode);

                //１－２）　@拡張アカウントマネージャ.get補助口座（）にて補助口座オブジェクトの取得
                //　@　@　@　@　@　@[引数]
                //　@　@　@　@　@　@　@・　@顧客オブジェクトから取得した口座ID
                //　@　@　@　@　@　@　@・　@SubAccountTypeEnum.EQUITY_SUB_ACCOUNT
                SubAccount l_subAccount =
                    l_web3GentradeAccountManager.getSubAccount(
                        l_mainAccount.getAccountId(),
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                //　@１－３）　@取引余力サービスImpl.get出金可能額～出金入力画面表示用～()　@にて出金可能額を取得
                //　@　@　@　@　@　@[引数]
                //　@　@　@　@　@　@　@・　@get補助口座（）で取得した値
                //　@　@　@　@　@　@　@・　@出金問合せ明細[index].受渡日
                WEB3TPTradingPowerService l_tradingPowerService = 
                    (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

                double l_dblPaymentTradingPower =
                    l_tradingPowerService.getPaymentTradingPowerAioCashoutInput(
                        (WEB3GentradeSubAccount)l_subAccount,
                        l_aioCashoutInqUnit[i].deliveryDate);

                //　@１－４）　@出金問合せ明細[index].出金余力=１－３）で取得した値
                l_aioCashoutInqUnit[i].paymentPower =
                    WEB3StringTypeUtility.formatNumber(l_dblPaymentTradingPower);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            //例外をスローする
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
