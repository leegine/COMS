head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用現引現渡更新インタセプタ(WEB3MarginSwapMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 水落 (SRA) 新規作成
Revesion History : 2007/04/26 謝旋 (中訊) ＤＢ更新仕様No.199
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.define.WEB3MarginBaseNumber;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用現引現渡更新インタセプタ）。<BR>
 * <BR>
 * 現引現渡注文登録時の、DB更新仕様カスタマイズ用のクラス。<BR>
 *（EqTypeOrderManagerPersistenceEventInterceptorの実装）<BR>
 * 以下のサービスから利用される。<BR> 
 * ・「信用取引現引現渡サービス」 <BR>
 * ・「信用取引現引現渡注文通知サービス」<BR>
 * ・「連続注文発注サービス」
 * @@author 法@旭
 * @@version 1.0
 **/
public class WEB3MarginSwapMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginUpdateInterceptor.class);
    /**
     * (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。<BR>
     */
    private WEB3MarginSwapContractOrderSpec equitySwapMarginOrderSpec;
    
    /**
     * (弁済区分（SONAR）)。<BR>
     * 弁済区分（SONAR）。<BR>
     */
    private String sonarRepaymentType;
    
    /**
     * (概算受渡代金。)<BR>
     * 概算受渡代金。<BR>
     */
    private double estimatedPrice;
    
    /**
     * (弁済区分。)<BR>
     * 弁済区分。 <BR>
     */
    private String repaymentType;
    
    /**
     * (弁済期限値。)<BR>
     * 弁済期限値。<BR>
     */
    private double repaymentNum;
    
    /**
     * (譲渡益金額。)<BR>
     * 譲渡益金額。<BR>
     */
    private double capitalGain;
    
    /**
     * (譲渡益税額。)<BR>
     * 譲渡益税額。<BR>
     */
    private double capitalGainTax;
    
    /**
     * (初回注文の注文チャネル。)<BR>
     * 初回注文の注文チャネル。<BR>
     */
    private String orderChanel;
    
    /**
     * (注文経路区分。)<BR>
     * 注文経路区分。<BR>
     */
    private String orderRootDiv;
    
    /**
     * (識別コード。)<BR>
     * 識別コード。 <BR>
     * 信用取引現引現渡注文通知サービスから利用する場合のみセットして使用。以外、nullをセット。<BR>
     */
    private String orderRequestNumber;
    
    /**
     * (受注日時。)<BR>
     * 受注日時。 <BR>
     * 信用取引現引現渡注文通知サービス、連続注文発注サービスから利用する場合のみセットして使用。<BR>
     * 以外、nullをセット。<BR>
     */
    private Date receivedDateTime;
    
	/**
	 * (受渡日。)<BR>
	 * 受渡日。 <BR>
	 * 信用取引現引現渡注文通知サービスから利用する場合のみセットして使用。以外、nullをセット。<BR>
	 */
	private Date deliveryDate = null;
	
    /**
     * @@roseuid 4142BBF5032D
     */
    public WEB3MarginSwapMarginUpdateInterceptor() 
    {
     
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １） 必須プロパティ設定チェック <BR>
     * <BR>
     * 　@当オブジェクトのプロパティのうち、 <BR>
     * 　@信用現引現渡注文内容、注文経路区分の <BR>
     * 　@いずれか１つでもnullの場合は、 <BR>
     * 　@パラメータ.注文単位Paramsを返却し、処理を終了する。 <BR>
     * <BR>
     * ２） 拡張項目セット<BR>
     * 　@プロパティから、パラメータ.注文単位Paramsの拡張項目に値をセットし、返却する。<BR>
     * <BR>
     * 　@更新内容は、以下のDB設定論理を参照。 <BR>
     * 　@・「信用現引現渡_株式注文単位テーブル.xls」 <BR>
     * 　@・「信用取引現引現渡注文通知_株式注文単位テーブル.xls」の <BR>
     * 　@　@「（信用取引現引現渡注文通知）株式注文単位テーブル」シート <BR>
     * <BR>
     * 　@「受渡日」「受注日時」「識別コード」「発注日」の設定仕様は、以下の通りに分岐する。 <BR>
     * 　@----------------------------------------------------------------------- <BR>
     *   受渡日　@　@　@：this.get受渡日( )≠nullの場合のみ、this.受渡日プロパティをセット。<BR>
     * 　@　@　@　@　@　@　@　@※this.get受渡日( )＝nullの場合は、xTrade標準実装のままとする。<BR>
     * 　@受注日時　@　@：this.get受注日時( )＝nullの場合は、GtlUtils.getSystemTimestamp( )。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@this.get受注日時( )≠nullの場合は、this.受注日時プロパティをセット。 <BR>
     * 　@識別コード　@　@：this.get識別コード( )＝nullの場合は、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@注文識別コード採番サービス.get新規識別コード( )。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@this.get識別コード( )≠nullの場合は、this.識別コードプロパティをセット。 <BR>
     * 　@発注日　@　@　@　@：this.注文経路区分＝"HOST"の場合のみ、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@GtlUtils.getSystemTimestamp( )のYYYYMMDD。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@以外、セットなし（xTrade標準仕様通り） <BR>
     * 　@----------------------------------------------------------------------- <BR>
     * <BR>
     * @@param l_updateType - 更新タイプ<BR>
     * INSERTまたは、UPDATE。<BR>
     * 
     * EqTypeOrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * <BR>
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40BEF866039C
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, OrderManagerPersistenceContext l_process, EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "EqtypeOrderUnitParams mutate";
        log.entering(STR_METHOD_NAME);
        // ------------------------------
        // １）必須プロパティ設定チェック 
        // ------------------------------
        if ((this.equitySwapMarginOrderSpec == null) ||
            (this.orderRootDiv == null))
        {
            return l_orderUnitParams;
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        MainAccount l_mainAccount = null;
        try
        {    
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());
        }
        catch (NotFoundException l_nfe)
        {   

            log.error(STR_METHOD_NAME,l_nfe);   
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        Market l_market = null;
        long l_lngMarketId = l_orderUnitParams.getMarketId();
        try
        {
            l_market =
                l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,this.getClass().getName()+  "." + STR_METHOD_NAME);   
        }
        
        //値段条件
        l_orderUnitParams.setPriceConditionType(WEB3PriceConditionDef.DEFAULT);        
        //発注条件
        l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
        //発注条件演算子
        l_orderUnitParams.setOrderCondOperator(null);     
        //逆指値基準値タイプ
        l_orderUnitParams.setStopOrderPrice(null);
        //（W指値）訂正指値
        l_orderUnitParams.setWLimitPrice(null);
		//受渡日
		if (this.getDeliveryDate() != null)
		{
			l_orderUnitParams.setDeliveryDate(this.getDeliveryDate());
		}
        //税区分（現引現渡）
        l_orderUnitParams.setSwapTaxType(equitySwapMarginOrderSpec.getSwapTaxType());
        //弁済区分
        l_orderUnitParams.setRepaymentType(this.repaymentType);
        //弁済期限値
        l_orderUnitParams.setRepaymentNum((int)(this.repaymentNum));
        //弁済区分（SONAR）
        l_orderUnitParams.setSonarRepaymentType(this.sonarRepaymentType);

        //初回注文の注文チャネル
        l_orderUnitParams.setOrderChanel(this.orderChanel);
        //注文経路区分
        l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
                
        //受注日時
        if (this.getReceivedDateTime() == null)
        {   
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        }
        else
        {
            l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);   
        }
        
        //識別コード   
        String l_strOrderRequestNumber = null;            
        if (this.orderRootDiv.equals(WEB3OrderRootDivDef.HOST)){
            l_strOrderRequestNumber = this.orderRequestNumber;
        }
        else
        {            
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_lngInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            WEB3HostReqOrderNumberManageService l_numberService =
                (WEB3HostReqOrderNumberManageService) Services.getService(
                WEB3HostReqOrderNumberManageService.class);
            try 
            {
                l_strOrderRequestNumber =
                    l_numberService.getNewNumber(
                    l_lngInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.EQUITY);
            } 
            catch (WEB3BaseException l_ex) 
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);       
            }            
        }
        l_orderUnitParams.order_request_number = l_strOrderRequestNumber;        
        log.debug(l_strOrderRequestNumber);
        
        //発注日（this．注文経路区分＝”HOST”の場合のみ）YYYYMMDD形式に変換してセットする。        
        if (this.orderRootDiv.equals(WEB3OrderRootDivDef.HOST))
        {
            l_orderUnitParams.setBizDate(
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(GtlUtils.getSystemTimestamp())
                );
        }
        //注文失効日付
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate(l_orderUnitParams.getBizDate(),"yyyyMMdd"));
        
        //伝票Noを設定する
        int l_intRequestNumberLength = l_strOrderRequestNumber.length() - 3;
        String l_strVoucherNo = WEB3MarginBaseNumber.BaseNumber + l_strOrderRequestNumber.substring(l_intRequestNumberLength);
        l_orderUnitParams.setVoucherNo(l_strVoucherNo);
        //初回注文の手数料No     
        l_orderUnitParams.setCommTblNo(null);  
        //初回注文の手数料No枝番
        l_orderUnitParams.setCommTblSubNo(null);
        //扱者コード（SONAR）
        l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        //注文単価
        l_orderUnitParams.setPrice(null);
        //概算受渡代金
        l_orderUnitParams.setEstimatedPrice(this.estimatedPrice);
        //譲渡益金額
        l_orderUnitParams.setCapitalGain(this.capitalGain);
        //譲渡益税額
        l_orderUnitParams.setCapitalGainTax(this.capitalGainTax);
        //取引コード（SONAR）
        l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
        //市場コード（SONAR）
        MarketRow l_marketRow = (MarketRow) l_market.getDataSourceObject();
        l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        //手数料商品コード
        l_orderUnitParams.setCommProductCode(null);
        //空売フラグ = （ブランク：対象外）
        l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
        //注文訂正・取消区分
        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //市場から確認済みの注文単価
        l_orderUnitParams.setConfirmedOrderPrice(null);
        //市場から確認済みの概算受渡代金
        l_orderUnitParams.setConfirmedEstimatedPrice(null);
        //市場から確認済みの執行条件
        l_orderUnitParams.setConfirmedExecConditionType(null);
        //市場から確認済みの値段条件
        l_orderUnitParams.setConfirmedPriceConditionType(null);
        //決済順序 
        l_orderUnitParams.setClosingOrderType(this.equitySwapMarginOrderSpec.getClosingOrderType());
        //注文エラー理由コード
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        //リクエストタイプ 
        l_orderUnitParams.setRequestType(null);
        //初回注文の注文単位ＩＤ
        l_orderUnitParams.setFirstOrderUnitId(null);

        l_orderUnitParams.setConfirmedPrice(null);

        //強制決済理由区分
        l_orderUnitParams.setForcedSettleReasonType(null);

        //承認状態区分
        l_orderUnitParams.setApproveStatusType(null);

        //承認者コード
        l_orderUnitParams.setApproverCode(null);

        //承認／非承認日時
        l_orderUnitParams.setApproveTimestamp(null);

        //保証金維持率
        l_orderUnitParams.setMarginMaintenanceRate(null);

        //追証発生日
        l_orderUnitParams.setAdditionalMarginDate(null);

        //追証経過日数
        l_orderUnitParams.setAdditionalMarginAccruedDays(null);

        //強制失効区分
        //0：オープン（0：DEFAULT）
        l_orderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (信用現引現渡更新インタセプタ)<BR>
     * コンストラクタ。<BR>
     * 引数に指定されたオブジェクト／値を、同名のプロパティに設定する。<BR>
     * @@param l_equitySwapMarginOrderSpec - 信用現引現渡注文内容オブジェクト。<BR>
     * @@param l_strPayTypeSONAR - 弁済区分（SONAR）。<BR>
     * @@param l_dblEstimatedPrice - 概算受渡代金。<BR>
     * @@param l_strPayType - 弁済区分。<BR>
     * @@param l_dblRepaymentNum - 弁済期限値。<BR>
     * @@param l_dblCapitalGain - 譲渡益金額。<BR>
     * @@param l_dblCapitalGainTax - 譲渡益税額。<BR>
     * @@param l_strOrderChanel - 初回注文の注文チャネル。<BR>
     * @@param l_strOrderRootDiv - 注文経路区分。<BR>
     * @@roseuid 40BEF76B0341
     */
    public WEB3MarginSwapMarginUpdateInterceptor(
        WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec, 
        String l_strPayTypeSONAR, 
        double l_dblEstimatedPrice, 
        String l_strPayType, 
        double l_dblRepaymentNum, 
        double l_dblCapitalGain,
        double l_dblCapitalGainTax,
        String l_strOrderChanel,
        String l_strOrderRootDiv) 
    {
        this.equitySwapMarginOrderSpec = l_equitySwapMarginOrderSpec;
        this.capitalGainTax = l_dblCapitalGainTax;
        this.capitalGain = l_dblCapitalGain;
        this.estimatedPrice = l_dblEstimatedPrice;
        this.repaymentNum = l_dblRepaymentNum;
        this.repaymentType = l_strPayType;
        this.sonarRepaymentType = l_strPayTypeSONAR;   
        this.orderChanel = l_strOrderChanel;
        this.orderRootDiv = l_strOrderRootDiv;
    }    
    
    /**
     * (get識別コード)<BR>
     * 識別コードを取得する。<BR>
     * @@return String
     * @@roseuid 40F37D140369
     */
    public String getOrderRequestNumber() 
    {
        return orderRequestNumber;
    }
    
    /**
     * (set識別コード)<BR>
     * 識別コードをセットする。<BR>
     * @@param l_strOrderRequestNumber - 識別コード<BR>
     * @@roseuid 40F37D14036A
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }
    
    
    /**
     * (get受注日時)<BR>
     * 受注日時を取得する。<BR>
     * @@return String
     * @@roseuid 40F37D140369
     */
    public Date getReceivedDateTime() 
    {
        return receivedDateTime;
    }
    
    /**
     * (set受注日時)<BR>
     * 受注日時を取得する。<BR>
     * @@param l_strOrderRequestNumber - 識別コード<BR>
     * @@roseuid 40F37D14036A
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime) 
    {
        receivedDateTime = l_datReceivedDateTime;
    }
    
	/**
	 * (get受渡日)<BR>
	 * 受渡日を取得する。<BR>
	 * @@return Date
	 * @@roseuid XXXXXXXXXXX
	 */
	public Date getDeliveryDate() 
	{
		return this.deliveryDate;
	}
    
	/**
	 * (set受渡日)<BR>
	 * 受渡日をセットする。<BR>
	 * @@param 受渡日 - 受渡日
	 * @@roseuid XXXXXXXXXXX
	 */
	public void setDeliveryDate(Date l_datDeliveryDate) 
	{
		this.deliveryDate = l_datDeliveryDate;
	}
	
}
@
