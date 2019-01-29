head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文有効期限取得サービスImpl(WEB3ExpirationDateListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 于瀟(中訊) 新規作成モデル319
Revision History : 2008/02/18 于瀟(中訊) 仕様変更モデル322
Revision History : 2008/02/19 于瀟(中訊) 仕様変更モデル323
*/

package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListResponse;
import webbroker3.gentrade.service.delegate.WEB3ExpirationDateListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文有効期限取得サービスImpl)<BR>
 * 注文有効期限取得サービス実装クラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ExpirationDateListServiceImpl implements WEB3ExpirationDateListService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListServiceImpl.class);

    /**
     * @@roseuid 47B3E6480109
     */
    public WEB3ExpirationDateListServiceImpl()
    {

    }

    /**
     * 注文有効期限取得サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図「注文有効期限取得」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9761B02C6
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

        WEB3ExpirationDateListRequest l_expirationDateListRequest = null;
        if (l_request instanceof WEB3ExpirationDateListRequest)
        {
            l_expirationDateListRequest =
                ((WEB3ExpirationDateListRequest)l_request);
            l_expirationDateListRequest.validate();
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

        WEB3GentradeHandlingOrderCond l_gentradeHandingOrderCond = null;

        //証券会社コード = OpLoginSecurityServiceより編集し設定
        //口座コード
        long l_lngAccountId = 0L;
        //証券会社コード
        String l_strInstitutionCode = null;
        //セキュリティサービスを取得
        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            //AccountIdを取得
            l_lngAccountId = l_opLoginSec.getAccountId();
            //FinAppサービスを取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            MainAccount l_acc;
            l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_acc.getInstitution().getInstitutionCode();
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //銘柄タイプ =
        //　@・リクエスト.商品区分 = ”現物株式”または、”信用取引”の場合、”株式”を設定
        //　@・リクエスト.商品区分 = ”先物”または、”オプション”の場合、”先物オプション”を設定
        ProductTypeEnum l_productTypeEnum = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_productTypeEnum = ProductTypeEnum.EQUITY;
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_productTypeEnum = ProductTypeEnum.IFO;
        }

        //先物／オプション区分 =
        //　@・リクエスト.商品区分 = ”先物”の場合、”先物”を設定
        //　@・リクエスト.商品区分 = ”オプション”の場合、”オプション”を設定
        //　@・以外、”DEFAULT”を設定
        String l_strFuturesOptionDivDef = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType))
        {
            l_strFuturesOptionDivDef = WEB3FuturesOptionDivDef.FUTURES;
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_strFuturesOptionDivDef = WEB3FuturesOptionDivDef.OPTION;
        }
        else
        {
            l_strFuturesOptionDivDef = WEB3FuturesOptionDivDef.DEFAULT;
        }

        //信用取引区分 = ”DEFAULT”(固定) を設定
        //市場コード =
        //　@・リクエスト.商品区分 = ”現物株式”または、”信用取引”の場合、リクエスト.市場コードを設定
        //　@・リクエスト.商品区分 = ”先物”または、”オプション”の場合、”DEFAULT”を設定
        String l_strMarketCode = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_strMarketCode = l_expirationDateListRequest.marketCode;
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_strMarketCode = WEB3MarketCodeDef.DEFAULT;
        }

        l_gentradeHandingOrderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode,
            l_productTypeEnum,
            l_strFuturesOptionDivDef,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strMarketCode);

        //取扱可能注文期限区分を取得する
        String[] l_strExpirationDateTypes =
            l_gentradeHandingOrderCond.getHandlingPossibleExpirationDateType();

        //出来まで注文が取扱可能であるかを判定する
        boolean l_blnOrderUntilDeadLinePossibleHandling = false;
        boolean l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering = false;
        if (WEB3CommodityDivDef.EQUITY.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_expirationDateListRequest.commodityType))
        {
            l_blnOrderUntilDeadLinePossibleHandling =
                l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleHandling();
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_expirationDateListRequest.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_expirationDateListRequest.commodityType))
        {
            l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering =
                l_gentradeHandingOrderCond.isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering();
        }

        //注文開始日
        Date l_datStartDay = null;

        //注文最終日
        Date l_datEndDay = null;

        //注文期限内の祝日一覧
        Date[] l_datDateHolidays = null;

        if (l_blnOrderUntilDeadLinePossibleHandling
            || l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering)
        {
            //出来るまで注文開始日を取得する。
            l_datStartDay = l_gentradeHandingOrderCond.getOrderUntilDeadLineStartDay();
            //出来るまで注文最終日を取得する。
            l_datEndDay = l_gentradeHandingOrderCond.getOrderUntilDeadLineEndDay();
            //出来るまで注文期限内の祝日一覧を取得する。
            l_datDateHolidays = l_gentradeHandingOrderCond.getExpirationDateHoliday();
        }

        //立会区分を取得する。
        String l_strSessionTypeTemp = WEB3GentradeTradingTimeManagement.getSessionType();

        //レスポンスデータを生成する
        //【注文有効期限取得レスポンス】
        //注文期限区分一覧：取扱可能注文条件.取扱可能注文期限区分取得( )の戻り値Listをセット
        //有効期限開始日：取扱可能注文条件.get出来るまで注文開始日( )の戻り値をセット(*1)
        //有効期限最終日：取扱可能注文条件.get出来るまで注文最終日( )の戻り値をセット(*1)
        //有効期限内祝日一覧：取扱可能注文条件.get注文期限内祝日一覧( )の戻り値をセット(*1)
        //立会区分：取引時間管理.get立会区分( )の戻り値をセット
        //(*1)　@取扱可能注文条件.is出来るまで注文取扱可能( )==trueの場合または、
        //　@ 取扱可能注文条件.is出来るまで注文取扱可能<取引最終日考慮無>( )==trueの場合のみ、セットする
        WEB3ExpirationDateListResponse l_response =
            (WEB3ExpirationDateListResponse)l_expirationDateListRequest.createResponse();
        l_response.expirationDateTypeList = l_strExpirationDateTypes;
        if (l_blnOrderUntilDeadLinePossibleHandling
            || l_blnOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering)
        {
            l_response.expirationStartDate = l_datStartDay;
            l_response.expirationEndDate = l_datEndDay;
            l_response.holidayList = l_datDateHolidays;
        }
        l_response.sessionType = l_strSessionTypeTemp;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
