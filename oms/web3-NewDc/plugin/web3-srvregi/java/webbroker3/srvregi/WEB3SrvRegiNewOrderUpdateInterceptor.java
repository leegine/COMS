head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiNewOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用新規注文更新インタセプタ(WEB3SrvRegiNewOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
              001: 2005/01/05 張威 (中訊) 対応名称：web3-DB環境-V3.1
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用新規注文更新インタセプタ)<BR>
 * サービス利用新規注文更新インタセプタクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3SrvRegiNewOrderUpdateInterceptor extends WEB3AioCashTransUpdateInterceptor
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiNewOrderUpdateInterceptor.class);

    /**
     * (受渡日)<BR>
     */
    private Timestamp deliveryDate;

    /**
     * (サービス区分)<BR>
     */
    private String srvDiv;

    /**
     * (注文経路区分)<BR>
     */
    private String orderRootDiv;

    /**
     * (発注日)<BR>
     */
    private Timestamp orderBizDate;

    /**
     * (サービス利用新規注文更新インタセプタ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 4100EF2903C8
     */
    public WEB3SrvRegiNewOrderUpdateInterceptor()
    {

    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * アセット振替取引注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。 <BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.アセット振替取引注文単位Paramsの<BR>
     * 　@拡張項目に値をセットし、返却する。 <BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     * 「サービス利用申込_注文単位テーブル.xls」<BR>
     * 「サービス利用管理者・顧客アップロード_注文単位テーブル.xls」<BR>
     * 「サービス利用管理者・顧客登録_注文単位テーブル.xls」参照。<BR>
     * @@param l_persistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_persistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_aioOrderUnitParams - (アセット振替取引注文単位Params)<BR>
     * 永続化前のアセット振替取引注文単位Params<BR>
     *
     *
     * @@return AioOrderUnitParams
     * @@roseuid 4100EF100270
     */
    public AioOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_persistenceContext,
        AioOrderUnitParams l_aioOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            " mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, AioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_aioOrderUnitParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //get account info.
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngAccountId = l_aioOrderUnitParams.getAccountId();
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_account = null;
            l_account = l_accountMgr.getMainAccount(l_lngAccountId);//NotFoundException

            //税区分
            l_aioOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

            //受注日時
            Object l_receivedDateTime =
                ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            l_aioOrderUnitParams.setReceivedDateTime((Timestamp)l_receivedDateTime);

            //扱者コード（SONAR）
            MainAccountRow l_accountRow = (MainAccountRow)l_account.getDataSourceObject();
            String l_strSonarCode = l_accountRow.getSonarTraderCode();
            l_aioOrderUnitParams.setSonarTraderCode(l_strSonarCode);

            //識別番号採番インターフェース
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
                (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
            String l_strNewNumber = null;
            l_strNewNumber =
                l_hostReqOrderNumberManageService.getNewNumber(
                    l_account.getInstitution().getInstitutionCode(),
                    l_account.getBranch().getBranchCode(),
                    ProductTypeEnum.AIO);//WEB3BaseException
            //識別コード
            l_aioOrderUnitParams.setOrderRequestNumber(l_strNewNumber);

            //.comデビット決済取引番号
            l_aioOrderUnitParams.setComDebitNumber(null);

            //保証金区分
            l_aioOrderUnitParams.setGuaranteeDiv(null);

            //出金申込区分
            l_aioOrderUnitParams.setPaymentApplicationDiv(this.getSrvDiv());

            //注文取消区分
            l_aioOrderUnitParams.setCancelType(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT);

            //注文経路区分
            l_aioOrderUnitParams.setOrderRootDiv(this.orderRootDiv);

            //注文エラー理由コード
            l_aioOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

            //MQステータス
            l_aioOrderUnitParams.setMqStatus(WEB3MqStatusDef.MAIL_SENDED);

            //category  QA:WEB3-SRVREGI-A-UT-0091.xls
            //zhangwei modified according to:web3-DB環境-V3.1
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_IN_OUT);

            //発注日
			l_aioOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(this.getOrderBizDate(), "yyyyMMdd"));

            //受渡日
            l_aioOrderUnitParams.setDeliveryDate(this.getDeliveryDate());

            log.exiting(STR_METHOD_NAME);
            return l_aioOrderUnitParams;
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,  STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(), STR_METHOD_NAME);
        }

    }

    /**
     * (set受渡日)<BR>
     * 受渡日の設定を行う。<BR>
     * <BR>
     * １）　@引数.受渡日をthis.受渡日に設定する。<BR>
     * @@param l_tsDeliveryDate - (受渡日)<BR>
     * @@roseuid 4100F10F0231
     */
    public void setDeliveryDate(Timestamp l_tsDeliveryDate)
    {
        this.deliveryDate = l_tsDeliveryDate;
    }

    /**
     * (get受渡日)<BR>
     * 受渡日を返す。<BR>
     * <BR>
     * １）　@this.受渡日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 4100F11A03E7
     */
    public Timestamp getDeliveryDate()
    {
        return this.deliveryDate;
    }

    /**
     * (setサービス区分)<BR>
     * サービス区分の設定を行う。<BR>
     * <BR>
     * １）　@引数.サービス区分をthis.サービス区分に設定する。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@roseuid 4100F16C034B
     */
    public void setSrvDiv(String l_strSrvDiv)
    {
        this.srvDiv = l_strSrvDiv;
    }

    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * １）　@this.サービス区分を返す。<BR>
     * @@return String
     * @@roseuid 4100F16C034D
     */
    public String getSrvDiv()
    {
        return this.srvDiv;
    }

    /**
     * (set注文経路区分)<BR>
     * 注文経路区分の設定を行う。<BR>
     * <BR>
     * １）　@引数.注文経路区分をthis.注文経路区分に設定する。<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * @@roseuid 4100F1E4009B
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }

    /**
     * (get注文経路区分)<BR>
     * 注文経路区分を返す。<BR>
     * <BR>
     * １）　@this.注文経路区分を返す。<BR>
     * @@return String
     * @@roseuid 412BFED70269
     */
    public String getOrderRootDiv()
    {
        return this.orderRootDiv;
    }

    /**
     * (set発注日)<BR>
     * 発注日の設定を行う。<BR>
     * <BR>
     * １）　@引数.発注日をthis.発注日に設定する。<BR>
     * @@param l_tsOrderBizDate - (発注日)<BR>
     * @@roseuid 4100F10F0231
     */
    public void setOrderBizDate(Timestamp l_tsOrderBizDate)
    {
        this.orderBizDate = l_tsOrderBizDate;
    }

    /**
     * (get発注日)<BR>
     * 発注日を返す。<BR>
     * <BR>
     * １）　@this.発注日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 4100F11A03E7
     */
    public Timestamp getOrderBizDate()
    {
        return this.orderBizDate;
    }
}
@
