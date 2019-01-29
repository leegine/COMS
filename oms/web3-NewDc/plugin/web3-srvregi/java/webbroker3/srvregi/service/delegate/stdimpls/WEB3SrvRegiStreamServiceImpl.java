head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用債券連携サービスImpl(WEB3SrvRegiStreamServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 武波 新規作成 モデルNo.371,No.373,No.376,No.377
Revision History : 2008/06/20 武波 (中訊) モデルNo.394
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiStreamCommon;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.define.WEB3SrvRegiTradingTypeDef;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用債券連携サービスImpl)<BR>
 * サービス利用債券連携サービス実装クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3SrvRegiStreamServiceImpl extends WEB3SrvRegiClientRequestService
    implements WEB3SrvRegiStreamService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamServiceImpl.class);

   /**
    * @@roseuid 4831260B03A9
    */
   public WEB3SrvRegiStreamServiceImpl()
   {

   }

   /**
    * サービス利用債券連携サービス処理を行う。<BR>
    * <BR>
    * シーケンス図「（サービス利用）債券連携」参照<BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：is提供中()=falseの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_01927 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：validate債券銘柄コード(銘柄コード:String, 証券会社コード:String)=falseの場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_01067 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：validate目論見書閲覧.チェック結果="閲覧未済"の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_03092 <BR>
     * ======================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * 具体位置：validate目論見書閲覧.チェック結果="閲覧未済（障害中）"の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag　@:  BUSINESS_ERROR_01984<BR>
     * ======================================================== <BR>
     * <BR>
    * @@param l_request - (リクエストデータ)<BR>
    * リクエストデータ<BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 48158D190042
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

       WEB3SrvRegiStreamResponse l_srvRegiStreamResponse = null;
       if (l_request instanceof WEB3SrvRegiStreamRequest)
       {
           WEB3SrvRegiStreamRequest l_srvRegiStreamRequest = (WEB3SrvRegiStreamRequest)l_request;

           //validate( )
           l_srvRegiStreamRequest.validate();

           //validate注文受付可能( )
           WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

           //get補助口座(補助口座タイプ : SubAccountTypeEnum)
           //補助口座タイプ="株式取引口座（預り金）"
           SubAccount l_subAccount =
               this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

           FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
           WEB3GentradeOrderValidator l_gentradeOrderValidator =
               (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

           //validate取引可能顧客(補助口座 : SubAccount)
           OrderValidationResult l_orderValidationResult =
               l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
           if(l_orderValidationResult.getProcessingResult().isFailedResult())
           {
               log.debug("取引可能顧客チェックエラー");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   l_orderValidationResult.getProcessingResult().getErrorInfo(),
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "取引可能顧客チェックエラー");
           }

           //getサービスマスター(String, String, boolean)
           //証券会社コード = 補助口座オブジェクト.getInstitution( ).getInstitutionCode( )の戻り値
           //サービス区分=リクエストデータ.サービス区分
           //is行ロック=false
           WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement =
               new WEB3SrvRegiServiceInfoManagement();
           WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
               l_srvRegiServiceInfoManagement.getSrvMaster(
                   l_subAccount.getInstitution().getInstitutionCode(),
                   l_srvRegiStreamRequest.serviceDiv,
                   false);

           //is提供中
           boolean l_blnIsProviding = l_srvRegiServiceMaster.isProviding();

           //・is提供中()=falseの場合、例外をスローする。
           if (!l_blnIsProviding)
           {
               log.debug("サービスが停止中の時のエラー（サービス起動）。");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01927,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   "サービスが停止中の時のエラー（サービス起動）。");
           }

           WEB3SrvRegiStreamCommon l_srvRegiStreamCommon =
               new WEB3SrvRegiStreamCommon();

           //リクエスト.取引区分 = 1 or リクエスト.取引区分 = 3
           if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType)
               || WEB3SrvRegiTradingTypeDef.PROSPECTUS_CHECK.equals(
                   l_srvRegiStreamRequest.tradingType))
           {
               WEB3GentradeBatoClientService l_service =
                   (WEB3GentradeBatoClientService)Services.getService(WEB3GentradeBatoClientService.class);

               //validate債券銘柄コード(String, String)
               //銘柄コード： リクエストデータ.銘柄コード
               boolean l_blnIsBondProductCode = l_srvRegiStreamCommon.validateBondProductCode(
                   l_srvRegiStreamRequest.productCode, l_subAccount.getInstitution().getInstitutionCode());

               if (!l_blnIsBondProductCode)
               {
                   log.debug("銘柄コードの入力が不正です。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       "銘柄コードの入力が不正です。");
               }

               //validate目論見書閲覧(種別コード : String, 銘柄コード : String)
               //種別コード： リクエストデータ.種別コード
               //銘柄コード： リクエストデータ.銘柄コード
               WEB3GentradeProspectusResult l_gentradeProspectusResult =
                   l_service.validateProspectus(
                       l_srvRegiStreamRequest.batTypeCode, l_srvRegiStreamRequest.productCode);

               //validate目論見書閲覧.チェック結果="閲覧未済"の場合、例外をスローする。
               if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(
                   l_gentradeProspectusResult.checkResult))
               {
                   log.debug("目論見書閲覧チェック結果が閲覧未済です。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_03092,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       "目論見書閲覧チェック結果が閲覧未済です。");
               }

               //validate目論見書閲覧.チェック結果="閲覧未済（障害中）"の場合、例外をスローする。
               if (WEB3GentradeBatoCheckResultDef.UNINSPECTION_TROUBLE.equals(
                   l_gentradeProspectusResult.checkResult))
               {
                   log.debug("[電子鳩システム障害中]障害中注文不可。");
                   log.exiting(STR_METHOD_NAME);
                   throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                       getClass().getName() + "." + STR_METHOD_NAME,
                       "[電子鳩システム障害中]障害中注文不可。");
               }
           }

           WEB3GentradeSubAccount l_gentradeSubAccount =
               (WEB3GentradeSubAccount)l_subAccount;

           WEB3TPBondSimplexCooperationService l_bondSimplexCooperationService =
               (WEB3TPBondSimplexCooperationService)Services.getService(WEB3TPBondSimplexCooperationService.class);
           //リクエスト.取引区分 = 1
           if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType))
           {
               //getMainAccount
               MainAccount l_mainAccount = l_gentradeSubAccount.getMainAccount();

               //getAccountId( )
               long l_lngAccountId = l_mainAccount.getAccountId();

               //get代理入力者( )
               Trader l_trader = this.getTrader();

               //validate債券連携余力チェック(補助口座, Trader, double, Timestamp, Timestamp)
               //補助口座：get補助口座()の戻り値
               //代理入力者：get代理入力者()の戻り値
               //利用料金：リクエストデータ.金額（String型→double型へキャスト）
               //受渡日：リクエストデータ.受渡日（String型→Timestamp型へキャスト）
               //発注日：リクエストデータ.発注日（String型→Timestamp型へキャスト）
               double l_dblAmount = Double.parseDouble(l_srvRegiStreamRequest.amount);

               Timestamp l_tsDeliveryDate =
                   new Timestamp(WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.deliveryDate,
                       WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());

               Timestamp l_tsOrderBizDate =
                   new Timestamp(WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.orderBizDate,
                       WEB3GentradeTimeDef.DATE_FORMAT_YMD).getTime());

               l_srvRegiStreamCommon.validateBondOrgTradingPowerCheck(
                   l_gentradeSubAccount,
                   l_trader,
                   l_dblAmount,
                   l_tsDeliveryDate,
                   l_tsOrderBizDate);

               //save債券買付代金(口座ID : ｌong, 債券買付代金 : ｄouble,
               //トランザクション発生日 : Date, 受渡日 : Date, 注文No : String)
               //口座ID： getAccountId()の戻り値
               //債券買付代金： リクエストデータ.金額（String型→double型へキャスト）
               //トランザクション発生日： リクエストデータ.発注日（String型→Date型へキャスト）
               //受渡日： リクエストデータ.受渡日（String型→Date型へキャスト）
               //注文№： リクエストデータ.注文№
               l_bondSimplexCooperationService.saveBondBuyAmount(
                   l_lngAccountId,
                   l_dblAmount,
                   WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.orderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                   WEB3DateUtility.getDate(
                       l_srvRegiStreamRequest.deliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
                   l_srvRegiStreamRequest.orderNo);
           }

           //リクエスト.取引区分 = 2
           if (WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType))
           {
               //cancel債券買付代金(注文No : String)
               //リクエストデータ.注文№
               l_bondSimplexCooperationService.cancelBondBuyAmount(l_srvRegiStreamRequest.orderNo);
           }

           //リクエスト.取引区分 = 1　@or　@リクエスト.取引区分 = 2
           if (WEB3SrvRegiTradingTypeDef.BUY_PROCESS.equals(
               l_srvRegiStreamRequest.tradingType)
               || WEB3SrvRegiTradingTypeDef.BUY_ORDER_CANCEL_PROCESS.equals(
                   l_srvRegiStreamRequest.tradingType))
           {
               //余力再計算(補助口座 : 補助口座)
               //補助口座：get補助口座()の戻り値
               WEB3TPTradingPowerService l_service =
                    (WEB3TPTradingPowerService)Services.getService(
                            WEB3TPTradingPowerService.class);

                l_service.reCalcTradingPower(l_gentradeSubAccount);
           }

           //createレスポンス( )
           l_srvRegiStreamResponse =
               (WEB3SrvRegiStreamResponse)l_srvRegiStreamRequest.createResponse();

           l_srvRegiStreamResponse.orderNo = l_srvRegiStreamRequest.orderNo;
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
       return l_srvRegiStreamResponse;
   }
}@
