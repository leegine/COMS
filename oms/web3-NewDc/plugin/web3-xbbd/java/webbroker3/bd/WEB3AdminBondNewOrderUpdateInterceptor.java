head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondNewOrderUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者新規注文更新インタセプタ(WEB3AdminBondNewOrderUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
                 : 2006/10/16 趙林鵬 (中訊) ＤＢ更新仕様No.017
*/

package webbroker3.bd;

import java.math.BigDecimal;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3HostReflectDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

/**
 * (債券管理者新規注文更新インタセプタ)<BR>
 * 債券管理者新規注文更新インタセプタクラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0  
 */
public class WEB3AdminBondNewOrderUpdateInterceptor extends WEB3AdminBondDefaultInterceptor 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondNewOrderUpdateInterceptor.class);
    
    /**
     * (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容<BR>
     */
    private WEB3BondNewOrderSpec bondNewOrderSpec;
    
    /**
     * (債券管理者新規注文更新インタセプタ)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 44C73A7E0220
     */
    public WEB3AdminBondNewOrderUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * １）拡張項目セット<BR>
     * 　@１－１）パラメータ.債券注文単位Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は 「債券新規注文_債券注文単位テーブルDB更新仕様.xls」参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     * @@roseuid 44C73A7E023F
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context, 
        BondOrderUnitParams l_params) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_params == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //買付、売却の場合、92(国内仕切取引)
        //応募の場合、35(募集取引)
        if (this.getBondNewOrderSpec().getBondOrderTypeJudge().isRecruitOrder())
        {
            l_params.setDealType(WEB3DealTypeDef.RECRUIT_TRADING);
        }
        else if (
            this.getBondNewOrderSpec().getBondOrderTypeJudge().isBuyOrder() || 
            this.getBondNewOrderSpec().getBondOrderTypeJudge().isSellOrder())
        {
            l_params.setDealType(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
        }
        
        //債券約定日情報.get受渡日(* 画面入力）
        l_params.setDeliveryDate(this.getBondExecuteDateInfo().getDeliveryDate());
        
        //債券約定日情報.get現地受渡日(* 画面入力）
        l_params.setForeignDeliveryDate(this.getBondExecuteDateInfo().getForeignDeliveryDate());
        
        //1：解除中
        l_params.setLockStatus(WEB3LockStatusDef.RELEASING);
        
        //0：未約定
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.UNEXECUTED);
        
        //債券約定日情報.get現地発注日
        
        l_params.setForeignBizDate(WEB3DateUtility.formatDate(
            this.getBondExecuteDateInfo().getForeignBizDate(), "yyyyMMdd"));
        
        //WEB3ChannelDef.営業店
        l_params.setOrderChanel(WEB3ChannelDef.BRANCH);
        
        //サーバ側でサービスが起動された時間（計算式書（共通）(*2) 処理日付　@を参照）
        l_params.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //顧客.扱者コード（SONAR）（* SONARで管理している顧客の扱者）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        MainAccountRow l_mainAccountRow;
        try
        {
            l_mainAccountRow = 
                (MainAccountRow)l_accountManager.getMainAccount(l_params.getAccountId()).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_params.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        
        //債券新規注文内容.getLimitPrice()(* 画面入力）
        l_params.setPrice(this.getBondNewOrderSpec().getLimitPrice());
        
        //注文識別コード採番サービス.get新規識別コード()
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService=
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class);
        try
        {
            String l_strNewNumber = 
                l_hostReqOrderNumberManageService.getNewNumber(
                    this.getAdministrator().getInstitutionCode(),
                    this.getAdministrator().getBranchCode(),
                    ProductTypeEnum.BOND);
            l_params.setOrderRequestNumber(l_strNewNumber);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }      
        
        //債券銘柄.get債券タイプ
        l_params.setBondType(this.getBondProduct().getBondType());
        
        //債券銘柄.get通貨コード
        l_params.setCurrencyCode(this.getBondProduct().getCurrencyCode());
        
        //債券新規注文内容.get決済区分() (* 画面入力）
        l_params.setSettlementDiv(this.getBondNewOrderSpec().getSettlementDiv());
        
        //債券銘柄.get自動約定区分
        l_params.setAutoExecDiv(this.getBondProduct().getAutoExecDiv());
        
        //null
        l_params.setExecutedPrice(null);
        
        //債券受渡代金計算結果.get為替レート
        if (this.getBondEstimatedPriceCalcResult().getFxRate() != null)
        {
            double l_dblFxRate = this.getBondEstimatedPriceCalcResult().getFxRate().doubleValue();
            l_params.setBaseFxRate(l_dblFxRate);
        }
        
        //null
        l_params.setExecFxRate(null);
        
        //債券受渡代金計算結果.get売買代金（円貨）
        if (this.getBondEstimatedPriceCalcResult().getTradingPrice() != null)
        {
            BigDecimal l_bdTradingPrice = this.getBondEstimatedPriceCalcResult().getTradingPrice();
            l_params.setTradingPrice(l_bdTradingPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get売買代金（外貨）
        if (this.getBondEstimatedPriceCalcResult().getForeignTradePrice() != null)
        {
            BigDecimal l_bdForeignTradingPrice = this.getBondEstimatedPriceCalcResult().getForeignTradePrice();
            l_params.setForeignTradingPrice(l_bdForeignTradingPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get経過利子（円貨）
        if (this.getBondEstimatedPriceCalcResult().getAccruedInterest() != null)
        {
            BigDecimal l_bdAccruedInterest = this.getBondEstimatedPriceCalcResult().getAccruedInterest();
            l_params.setAccruedInterest(l_bdAccruedInterest.doubleValue());
        }
        
        //債券受渡代金計算結果.get経過利子（外貨）
        if (this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest() != null)
        {
            BigDecimal l_bdForeignAccruedInterest = this.getBondEstimatedPriceCalcResult().getForeignAccruedInterest();
            l_params.setForeignAccruedInterest(l_bdForeignAccruedInterest.doubleValue());
        }
        
        //債券受渡代金計算結果.get受渡代金（円貨）
        if (this.getBondEstimatedPriceCalcResult().getEstimatedPrice() != null)
        {
            BigDecimal l_bdEstimatedPrice = this.getBondEstimatedPriceCalcResult().getEstimatedPrice();
            l_params.setEstimatedPrice(l_bdEstimatedPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get受渡代金（外貨）
        if (this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice() != null)
        {
            BigDecimal l_bdForeignEstimatedPrice = this.getBondEstimatedPriceCalcResult().getForeignEstimatedPrice();
            l_params.setForeignEstimatedPrice(l_bdForeignEstimatedPrice.doubleValue());
        }
        
        //債券受渡代金計算結果.get経過日数
        if (this.getBondEstimatedPriceCalcResult().getElapsedDays() != null)
        {
            l_params.setElapsedDays(this.getBondEstimatedPriceCalcResult().getElapsedDays());
        }
        
        //債券受渡代金計算結果.get基準日数
        if (this.getBondEstimatedPriceCalcResult().getCalcBaseDays() != null)
        {
            l_params.setCalcBaseDays(this.getBondEstimatedPriceCalcResult().getCalcBaseDays());
        }
        
        //債券約定日情報.get約定日
        l_params.setExecDate(this.getBondExecuteDateInfo().getExecuteDate());
        
        //債券約定日情報.get現地約定日
        l_params.setForeignExecDate(this.getBondExecuteDateInfo().getForeignExecuteDate());
        
        //インタセプタ.getカストディアンコード(* 画面入力）
        l_params.setCustodianCode(this.getCustodianCode());
        
        //WEB3OrderRootDivDef.管理者    
        l_params.setOrderRootDiv(WEB3OrderRootDivDef.ADMIN);
        
        //0：未送信
        l_params.setHostSendDiv(WEB3HostSendDivDef.UNSEND);
        
        //管理者.get管理者コード    
        l_params.setAdministratorCode(this.getAdministrator().getAdministratorCode());
        
        //null
        l_params.setErrorReasonCode(null);
        
        //中途換金調整額null
        l_params.setAdjustmentBeforeMaturity(null);
        
        //債券約定日情報.get入金日
        l_params.setPaymentDate(this.getBondExecuteDateInfo().getPaymentDate());
        
        //0：未反映
        l_params.setExecHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);
        //0：未反映
        l_params.setCancelHostReflectDiv(WEB3HostReflectDivDef.NOT_REFLECT);
        
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (get拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容を返却する<BR>
     * @@return webbroker3.bd.WEB3BondNewOrderSpec
     * @@roseuid 44D966D30000
     */
    public WEB3BondNewOrderSpec getBondNewOrderSpec() 
    {
        return this.bondNewOrderSpec;
    }
    
    /**
     * (set拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容を設定する<BR>
     * @@param l_bondNewOrderSpec - (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容<BR>
     * @@roseuid 44D966D3002E
     */
    public void setBondNewOrderSpec(WEB3BondNewOrderSpec l_bondNewOrderSpec) 
    {
        this.bondNewOrderSpec = l_bondNewOrderSpec;
    }
}
@
