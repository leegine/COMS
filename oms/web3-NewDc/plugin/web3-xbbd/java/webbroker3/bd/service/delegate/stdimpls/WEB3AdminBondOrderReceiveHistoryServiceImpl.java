head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文受付履歴照会サービスImpl(WEB3AdminBondOrderReceiveHistoryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.217
Revision History : 2007/08/24 武波 (中訊) 仕様変更・モデルNo.253
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.bd.WEB3BondBranchCondition;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondOrderAcceptActionRow;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.message.WEB3BondOrderAcceptHistoryUnit;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderReceiveHistoryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者注文受付履歴照会サービスImpl)<BR>
 * 管理者注文受付履歴照会サービス実装クラス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryServiceImpl implements WEB3AdminBondOrderReceiveHistoryService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryServiceImpl.class);

    /**
     * @@roseuid 46A473FC02BF
     */
    public WEB3AdminBondOrderReceiveHistoryServiceImpl()
    {

    }

    /**
     * 管理者注文受付履歴照会サービスを実行する。<BR>
     * <BR>
     * シーケンス図：「管理者注文受付履歴照会」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者注文受付履歴照会リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 468895EB0227
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (l_request instanceof WEB3AdminBondOrderReceiveHistoryRequest)
        {
            WEB3AdminBondOrderReceiveHistoryRequest l_historyRequest =
                (WEB3AdminBondOrderReceiveHistoryRequest)l_request;

            //validate( )
            l_historyRequest.validate();

            //getInstanceFromログイン情報( )
            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

            //validate権限(機@能カテゴリコード : String, is更新 : boolean)
            //[引数]
            // 機@能カテゴリコード　@：　@機@能カテゴリコード．債券（銘柄管理）
            // is更新　@：　@false
            l_administrator.validateAuthority(
                WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
                false);

            //get証券会社コード( )
            String l_strInstitutionCode = l_administrator.getInstitutionCode();

            //get部店コード( )
            String l_strBranchCode = l_administrator.getBranchCode();

            //get部店(証券会社コード : String, 部店コード : String)
            //[引数]
            //証券会社コード：　@取得した証券会社コード
            //部店コード：　@　@　@　@取得した部店コード
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.BOND);

            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch = l_gentradeAccountManager.getWeb3GenBranch(
                    l_strInstitutionCode,
                    l_strBranchCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //getBranchId( )
            long l_lngBranchId = l_branch.getBranchId();

            //債券部店別条件(long)
            //[引数]
            //　@部店ID：取得した部店ID
            WEB3BondBranchCondition l_bondBranchCondition =
                new WEB3BondBranchCondition(l_lngBranchId);

            //get応募枠部店別管理区分( )
            String l_strBranchRecruitLimitDiv =
                l_bondBranchCondition.getBranchRecruitLimitDiv();

            //＜分岐処理＞get応募枠部店別管理区分（）の戻り値 == ”部店別管理する”　@の場合、以下のチェックを行なう
            if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT.equals(l_strBranchRecruitLimitDiv))
            {
                //validate部店権限(部店コード : String)
                //[引数]
                // 部店コード　@：　@リクエストデータ．部店コード
                l_administrator.validateBranchPermission(l_historyRequest.branchCode);
            }

            //get債券銘柄(long)
            //[引数]
            // 銘柄ID　@：　@リクエストデータ．銘柄ID
            WEB3BondProductManager l_productManager =
                (WEB3BondProductManager)l_tradingModule.getProductManager();
            long l_lngProductID = Long.parseLong(l_historyRequest.productID);
            WEB3BondProduct l_bondProduct =
                (WEB3BondProduct)l_productManager.getBondProduct(l_lngProductID);

            //create国内債券部店別応募枠情報(long, String, String)
            //[引数]
            // 銘柄ID　@：　@リクエストデータ．銘柄ID
            // 証券会社コード　@：　@取得した証券会社コード
            // 部店コード　@：　@リクエストデータ．部店コード
            WEB3BondDomesticBranchRecruitLimitInfo[] l_branchRecruitLimitInfos =
                l_productManager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductID,
                    l_strInstitutionCode,
                    l_historyRequest.branchCode);

            //get債券注文受付履歴一覧(String, String, String)
            //[引数]
            // 銘柄ID　@：　@リクエストデータ．銘柄ID
            // 証券会社コード　@：　@取得した証券会社コード
            // 部店コード　@：　@リクエストデータ．部店コード
            List l_lisBondOrderReceiveHistorys =
                l_productManager.getBondOrderReceiveHistoryList(
                    l_historyRequest.productID,
                    l_strInstitutionCode,
                    l_historyRequest.branchCode);

            //get債券注文受付履歴一覧　@の戻り値の要素数分　@LOOP処理
            int l_intCnt = l_lisBondOrderReceiveHistorys.size();
            WEB3BondOrderAcceptHistoryUnit[] l_orderAcceptHistorys =
                new WEB3BondOrderAcceptHistoryUnit[l_intCnt];

            for (int i = 0; i < l_intCnt; i++)
            {
                //注文受付履歴( )
                l_orderAcceptHistorys[i] = new WEB3BondOrderAcceptHistoryUnit();

                //プロパティセット
                BondOrderAcceptActionRow l_orderAcceptActionRow =
                    (BondOrderAcceptActionRow)l_lisBondOrderReceiveHistorys.get(i);

                //注文受付日：債券注文受付履歴行．注文受付日付
                l_orderAcceptHistorys[i].orderDate =
                    l_orderAcceptActionRow.getOrderAcceptDate();

                //注文金額　@ ：債券注文受付履歴行．注文金額
                if (l_orderAcceptActionRow.getOrderAmountIsNull())
                {
                    l_orderAcceptHistorys[i].orderAmount = "";
                }
                else
                {
                    l_orderAcceptHistorys[i].orderAmount =
                        WEB3StringTypeUtility.formatNumber(l_orderAcceptActionRow.getOrderAmount());
                }

                //注文件数　@ ：債券注文受付履歴行．注文件数
                if (l_orderAcceptActionRow.getOrderCountIsNull())
                {
                    l_orderAcceptHistorys[i].orderNumber = "";
                }
                else
                {
                    l_orderAcceptHistorys[i].orderNumber =
                        l_orderAcceptActionRow.getOrderCount() + "";
                }

                //累計　@　@　@　@ ：債券注文受付履歴行．注文金額累計
                if ( l_orderAcceptActionRow.getTotalOrderAmountIsNull())
                {
                    l_orderAcceptHistorys[i].accumulatedTotal = "";
                }
                else
                {
                    l_orderAcceptHistorys[i].accumulatedTotal =
                        WEB3StringTypeUtility.formatNumber(l_orderAcceptActionRow.getTotalOrderAmount());
                }
            }

            //createResponse( )
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_historyRequest.createResponse();

            //銘柄名　@　@　@：　@債券銘柄オブジェクト．銘柄名
            l_response.productName = l_bondProduct.getProductName();

            //応募開始日：　@債券銘柄オブジェクト．取扱開始日時
            l_response.recruitStartDate = l_bondProduct.getTradeStartDate();

            //応募終了日：　@債券銘柄オブジェクト．取扱終了日時
            l_response.recruitEndDate = l_bondProduct.getTradeEndDate();

            //注文受付履歴：　@作成した注文受付履歴の配列
            l_response.orderAcceptHistory = l_orderAcceptHistorys;

            //国内債券部店別応募枠情報：　@作成した国内債券部店別応募枠情報の配列の１つめの要素
            l_response.bondDomesticBranchRecruitLimitInfo = l_branchRecruitLimitInfos[0];

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
    }
}
@
