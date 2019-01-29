head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNewOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式新規注文内容(WEB3FeqNewOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13 王煜 (中訊) 新規作成
                 : 2005/07/25 芦露(中訊) レビュー
*/
package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqNewOrderSpec;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式新規注文内容)<BR>
 * 外国株式新規注文内容クラス<BR>
 * 
 * @@author 王煜(中訊)
 * @@version 1.0 
 */
public class WEB3FeqNewOrderSpec extends FeqNewOrderSpec 
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNewOrderSpec.class);

     /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    private String institutionCode;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    private Date bizDate;

    /**
     * (発注条件)<BR>
     * 発注条件<BR>
     * <BR>
     * 0：DEFAULT（条件指定なし）<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    private String orderConditionType;
    
    /**
     * (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     */
    private double wLimitPrice;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    private String settleDiv;
    
    /**
     * (初回注文の注文単位ID) <BR>
     * 初回注文の注文単位ID。 <BR>
     */
    private Long firstOrderUnitId;   
  
    /**
     * @@roseuid 42CE39EA009C
     */
    public WEB3FeqNewOrderSpec(
        Trader trader, 
        boolean isBuyOrder, 
        String productCode, 
        String marketCode, 
        double quantity, 
        double limitPrice, 
        FeqExecutionConditionType execType, 
        Date orderExpDate, 
        TaxTypeEnum taxType, 
        String currencyCode) 
    {
        super(
            trader, 
            isBuyOrder, 
            productCode, 
            marketCode, 
            quantity, 
            limitPrice, 
            execType, 
            orderExpDate, 
            taxType, 
            currencyCode);                
    }
    
    /**
     * (create新規注文内容)<BR>
     * （staticメソッド）<BR>
     * 外国株式新規注文内容オブジェクトを生成し、返却する。<BR>
     * <BR>
     * シーケンス図「（外株注文）create新規注文内容」 参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_trader - (扱者)<BR>
     * 扱者オブジェクト<BR>
     * 
     * @@param l_blnIsBuyOrder - (is買付注文)<BR>
     * 買付注文かどうかのフラグ<BR>
     * 
     * 
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * 
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * 
     * @@param l_dblQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * 
     * @@param l_dblPrice - (注文単価)<BR>
     * 注文単価<BR>
     * 
     * @@param l_executionCond - (執行条件)<BR>
     * 執行条件<BR>
     * 
     * @@param l_datExpirationDate - (注文失効日)<BR>
     * 注文失効日<BR>
     * 
     * @@param l_taxType - (税区分)<BR>
     * 税区分<BR>
     * 
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード<BR>
     * 
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * 
     * @@param l_dblWLimitPrice - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * 
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分<BR>
     * 
     * @@param l_firstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 初回注文の注文単位ID<BR>
     * 
     * @@return webbroker3.feq.WEB3FeqNewOrderSpec
     * @@throws WEB3SystemLayerException
     * @@roseuid 428B4376005C
     */
    public static WEB3FeqNewOrderSpec createNewOrderSpec(
        String l_strInstitutionCode,
        WEB3GentradeTrader l_trader, 
        boolean l_blnIsBuyOrder, 
        String l_strProductCode,
        String l_strMarketCode,                         
        double l_dblQuantity,
        double l_dblPrice,
        FeqExecutionConditionType l_executionCond,        
        Date l_datExpirationDate,  
        TaxTypeEnum l_taxType,                                   
        String l_strCurrencyCode,  
        String l_strOrderConditionType,
        double l_dblWLimitPrice,
        String l_strSettleDiv,                                   
        Long l_firstOrderUnitId) 
        throws WEB3SystemLayerException    
    {
        final String NEW_ORDER_SPEC =     
            "createNewOrderSpec("
            + "String, 扱者, boolean, String, String, double, double," 
            + "FeqExecutionConditionType, Date, TaxTypeEnum,"
            + "String, String, double, String, Long)";
        log.entering(NEW_ORDER_SPEC);

        //1.1取引時間管理.get発注日( )で取得した日付を発注日にセットする
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //1.2 スーパークラスのコンストラクタにてインスタンスを生成

        //引数.注文失効日※引数.注文失効日==null の場合は、get発注日()の戻り値  
        if (l_datExpirationDate == null)
        {
            l_datExpirationDate = l_datBizDate;    
        }

        //[引数] 
        //扱者： 引数.扱者オブジェクト 
        //is買付注文： 引数.is買付注文 
        //銘柄コード： 引数.銘柄コード 
        //市場コード：引数.市場コード 
        //注文数量： 引数.注文数量 
        //注文単価： 引数.注文単価 
        //執行条件： 引数.執行条件 
        //注文失効日： （以下のとおり）
        //税区分： 引数.税区分 
        //通貨コード： 引数.通貨コード 
        WEB3FeqNewOrderSpec l_feqNewOrderSpec
            = new WEB3FeqNewOrderSpec(
                l_trader, 
                l_blnIsBuyOrder, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblPrice, 
                l_executionCond, 
                l_datExpirationDate, 
                l_taxType, 
                l_strCurrencyCode 
            );
 
        //1.3証券会社コードをセットする。
        l_feqNewOrderSpec.setInstitutionCode(l_strInstitutionCode);
      
        //1.4発注日をセットする。。
        l_feqNewOrderSpec.setBizDate(l_datBizDate);
      
        //1.5発注条件をセットする。
        l_feqNewOrderSpec.setOrderConditionType(l_strOrderConditionType);
      
        //1.6（W指値）訂正指値をセットする。 
        l_feqNewOrderSpec.setWLimitPrice(l_dblWLimitPrice);
      
        //1.7決済区分をセットする。
        l_feqNewOrderSpec.setSettleDiv(l_strSettleDiv);

        //1.8初回注文の注文単位IDをセットする。
        l_feqNewOrderSpec.setFirstOrderUnitId(l_firstOrderUnitId);
      
        return l_feqNewOrderSpec;
    }

     /**
      * (set発注日)<BR>
      * 発注日をセットする。<BR>
      * @@param l_datBizDate - (発注日)<BR>
      * 発注日<BR>
      * @@roseuid 428B42AC000E
      */
     public void setBizDate(Date l_datBizDate) 
     {
         this.bizDate = l_datBizDate; 
     }

     /**
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * <BR>
     * this.発注日を返却する。<BR>
     * @@return Date
     * @@roseuid 428B42AC002D
     */
    public Date getBizDate()
    {
        return this.bizDate;
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@roseuid 428B42000108
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * this.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 428B423D02DD
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
     
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@roseuid 428B42E001F3
     */
    public void setOrderConditionType(String l_strOrderConditionType) 
    {
        this.orderConditionType = l_strOrderConditionType;
    }
    
    /**
     * (get発注条件)<BR>
     * 発注条件を取得する。<BR>
     * <BR>
     * this.発注条件を返却する。<BR>
     * @@return String
     * @@roseuid 428B42E00212
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * （W指値）訂正指値をセットする。<BR>
     * @@param l_dblWLimitPriceChange - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値<BR>
     * @@roseuid 428B42E0029E
     */
    public void setWLimitPrice(double l_dblWLimitPriceChange) 
    {
        this.wLimitPrice = l_dblWLimitPriceChange;   
    }
    
    /**
     * (get（W指値）訂正指)<BR>
     * （W指値）訂正指値を取得する。<BR>
     * <BR>
     * this.（W指値）訂正指値を返却する。<BR>
     * @@return double
     * @@roseuid 428B42E002BE
     */
    public double getWLimitPrice() 
    {
        return this.wLimitPrice;
    }
    
    /**
     * (set決済区分)<BR>
     * 決済区分をセットする。<BR>
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分<BR>
     * @@roseuid 428C5D580380
     */
    public void setSettleDiv(String l_strSettleDiv) 
    {
        this.settleDiv = l_strSettleDiv;      
    }
    
    /**
     * (get決済区分)<BR>
     * 決済区分を取得する。<BR>
     * <BR>
     * this.決済区分を返却する。<BR>
     * @@return String
     * @@roseuid 428C5D580382
     */
    public String getSettleDiv() 
    {
        return this.settleDiv;
    }
    
    /**
     * (set初回注文の注文単位ID)<BR>
     * 初回注文の注文単位IDをセットする。<BR>
     * @@param l_firstOrderUnitId - (初回注文の注文単位ID)<BR>
     * 初回注文の注文単位ID<BR>
     */
    public void setFirstOrderUnitId(Long l_firstOrderUnitId) 
    {
        this.firstOrderUnitId = l_firstOrderUnitId;      
    }
    
    /**
     * (get初回注文の注文単位ID)<BR>
     * 初回注文の注文単位IDを取得する。<BR>
     * <BR>
     * this.初回注文の注文単位IDを返却する。<BR>
     * @@return Long
     */
    public Long getFirstOrderUnitId() 
    {
        return this.firstOrderUnitId;
    }

    /**
     * (is出来るまで注文) <BR>
     * 該当注文が出来るまで注文の場合true <BR>
     * 当日のみ注文の場合falseを返す。 <BR>
     * <BR>
     * １）　@this.初回注文の注文単位ID != nullの場合は、trueを返す。 <BR>
     * 　@　@  this.初回注文の注文単位ID == nullの場合は、falseを返す。 
     * @@return boolean
     */
    public boolean isOrderUntilDeadLine()
    {
        return (this.firstOrderUnitId != null) ? true : false; 
    }
}
@
