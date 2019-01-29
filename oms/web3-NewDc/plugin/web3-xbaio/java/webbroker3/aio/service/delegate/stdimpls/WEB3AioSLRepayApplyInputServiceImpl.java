head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込入力サービスImpl(WEB3AioSLRepayApplyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 柴双紅 (中訊) 新規作成 仕様変更モデル757,モデル773
Revesion History : 2007/11/08  竹村 (SCS) バグ修正
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3SLRepayApplyInputResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券担保ローン返済申込入力サービスImpl)<BR>
 * 証券担保ローン返済申込入力サービス実装クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyInputServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLRepayApplyInputService
{
    /**
     * ログ出力ユーティリティ。
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioSLRepayApplyInputServiceImpl.class);

    /**
     * @@roseuid 46E890830358
     */
    public WEB3AioSLRepayApplyInputServiceImpl()
    {

    }

    /**
     * 証券担保ローン返済申込入力サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券担保ローン返済申込入力）入力画面表示データ取得」 参照<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込入力）入力画面表示データ取得<BR>
     * 具体位置：is証券担保ローン口座開設( )<BR>
     * 　@戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //get補助口座(補助口座タイプ : SubAccountTypeEnum)
        //[引数]
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //validate注文(SubAccount)
        //[引数]
        //補助口座： get補助口座()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.validateOrder(l_subAccount);

        //getMainAccount( )
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is証券担保ローン口座開設( )
        boolean l_blnIsSecuredLoanAccountOpen = l_gentradeMainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            //戻り値 == false の場合、例外をスローする。
            log.debug("証券担保ローン口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券担保ローン口座が未開設です。");
        }

        //get発注日( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //ArrayList( )
        List l_lisEstimatedTransferDates = new ArrayList();

        //get直近振込日(SubAccount, Date)
        //[引数]
        //補助口座： get補助口座()の戻り値
        //発注日： get発注日()の戻り値
        Date l_datClosestTransferDate =
            l_aioOrderManager.getClosestTransferDate(l_subAccount, l_datOrderBizDate);

        //add(arg0 : Object)
        //[引数]  
        //arg0： get直近振込日()の戻り値
        l_lisEstimatedTransferDates.add(l_datClosestTransferDate);

        //getInstitution( )
        Institution l_insititution = l_subAccount.getInstitution();

        //calc営業日(基準日 : Timestamp, 加算／減算日数 : int)
        //[引数]
        //基準日： 現在日付
        //加算／減算日数： 4
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        WEB3GentradeBizDate l_gentradeCurrentTime =
            new WEB3GentradeBizDate(l_tsSystemTime);
        Timestamp l_tsFourDayAfter = l_gentradeCurrentTime.roll(4);

        //証券会社.getDateSourceObject().出金予約実施 > 0 の場合
        //その値の回数のLoop処理を行う。
        InstitutionRow l_institutionRow =
            (InstitutionRow)l_insititution.getDataSourceObject();
        int l_intCnt = 0;
        if (l_institutionRow.getPaymentReserve() != null)
        {
            l_intCnt = Integer.parseInt(l_institutionRow.getPaymentReserve());
        }
        if (l_intCnt > 0)
        {
            for (int i = 1; i <= l_intCnt; i++)
            {
                //calc営業日(基準日 : Timestamp, 加算／減算日数 : int)
                //[引数]
                //基準日： get直近振込日() の戻り値
                //加算／減算日数：Loop回数
                WEB3GentradeBizDate l_gentradeTempDate =
                    new WEB3GentradeBizDate(new Timestamp(l_datClosestTransferDate.getTime()));
                Timestamp l_tsTempTime = l_gentradeTempDate.roll(i);
                if (WEB3DateUtility.compareToDay(l_tsTempTime, l_tsFourDayAfter) <= 0)
                {
                    //算出した返済予定日の日付 > 現在日付の4日後の日付 の場合、
                    //リストに追加しない。
                    l_lisEstimatedTransferDates.add(l_tsTempTime);
                }
            }
        }

        //toArray( )
        Date[] l_datRepayScheduledDates = new Date[l_lisEstimatedTransferDates.size()];
        l_lisEstimatedTransferDates.toArray(l_datRepayScheduledDates);
 
        //ArrayList( )
        List l_lisRepayableAmts = new ArrayList();
        for (int i = 0; i < l_datRepayScheduledDates.length; i++)
        {
            //get担保ローン振替可能額(補助口座 : 補助口座, 受渡日 : Date)
            //[引数]
            //補助口座： get補助口座()の戻り値
            //受渡日： 返済予定日リストの要素
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            double l_dblSLChangePossAmt =
                l_tradingPowerService.getSLChangePossAmt(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_datRepayScheduledDates[i]);

            //add(arg0 : Object)
            //[引数]
            //arg0： get担保ローン振替可能額()の戻り値
            l_lisRepayableAmts.add(WEB3StringTypeUtility.formatNumber(l_dblSLChangePossAmt));
        }

        //toArray( )
        String[] l_strRepayableAmts = new String[l_lisRepayableAmts.size()];
        l_lisRepayableAmts.toArray(l_strRepayableAmts);

        //createResponse( )
        WEB3SLRepayApplyInputResponse l_response =
            (WEB3SLRepayApplyInputResponse)l_request.createResponse();

        //プロパティセット
        //レスポンス.返済可能額 = 返済可能額リスト
        l_response.repayableAmtList = l_strRepayableAmts;

        //レスポンス.返済予定日 = 振込予定日リスト
        l_response.repayScheduledDateList = l_datRepayScheduledDates;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
