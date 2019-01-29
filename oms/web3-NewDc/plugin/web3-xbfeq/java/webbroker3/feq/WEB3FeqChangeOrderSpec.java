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
filename	WEB3FeqChangeOrderSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正注文内容(WEB3FeqChangeOrderSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  張玲(中訊) 新規作成
                 : 2005/07/28 呉艶飛(中訊) レビュー
*/


package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.ordersubmitter.io.FeqChangeOrderSpec;


/**
 * (外国株式訂正注文内容)
 * 外国株式訂正注文内容クラス
 * 
 * @@ author 張玲 
 * @@ version 1.0 
 */
public class WEB3FeqChangeOrderSpec extends FeqChangeOrderSpec 
{
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    private Date bizDate;
    
    /**
     * (訂正執行条件)<BR>
     * 訂正後の執行条件<BR>
     */
    private FeqExecutionConditionType changeExecutionCondition;
    
    /**
     * (訂正注文有効期限)<BR>
     * 訂正後の注文有効期限<BR>
     */
    private Date changeOrderExpirationDate;
    
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
     * (訂正発注条件演算子)<BR>
     * 訂正後の発注条件演算子<BR>
     */
    private String changeOrderCondOperator;
    
    /**
     * (訂正発注条件単価)<BR>
     * 訂正後の発注条件単価<BR>
     */
    private double changeOrderCondPrice;
    
    /**
     * (訂正（W指値）訂正指値)<BR>
     * 訂正後の（W指値）訂正指値<BR>
     */
    private double changeWLimitPrice;
    
    /**
     * (訂正注文期限区分)<BR>
     * 訂正後の注文期限区分<BR>
     */        
    private String changeOrderExpirationDiv;
    
    /**
     * (外国株式訂正注文内容)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値を各プロパティにセットする。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * 
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID
     * 
     * @@param l_dblChangeQuantity - (訂正数量)<BR>
     * 訂正数量
     * 
     * @@param l_dblChangePrice - (訂正単価)<BR>
     * 訂正単価
     * @@roseuid 42973C070148
     */
    public WEB3FeqChangeOrderSpec(
        long l_lngOrderId, 
        long l_lngOrderUnitId, 
        double l_dblChangeQuantity, 
        double l_dblChangePrice) 
    {        
        super(l_lngOrderId,l_lngOrderUnitId, l_dblChangeQuantity, l_dblChangePrice);       
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日
     * @@roseuid 429737B6005E
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
     * @@roseuid 429737B6006D
     */
    public Date getBizDate() 
    {
        return this.bizDate;
    }
    
    /**
     * (set訂正執行条件)<BR>
     * 訂正後の執行条件をセットする。<BR>
     * @@param l_strChangeExecutionType - (執行条件)<BR>
     * 訂正後の執行条件
     * @@roseuid 4297367002EE
     */
    public void setChangeExecutionCondition(FeqExecutionConditionType l_strChangeExecutionType) 
    {
        this.changeExecutionCondition = l_strChangeExecutionType; 
    }
    
    /**
     * (get訂正執行条件)<BR>
     * 訂正後の執行条件を取得する。<BR>
     * <BR>
     * this.訂正執行条件を返却する。<BR>
     * @@return FeqExecutionConditionType
     * @@roseuid 4297367002FD
     */
    public FeqExecutionConditionType getChangeExecutionCondition() 
    {
        return this.changeExecutionCondition;
    }
    
    /**
     * (set訂正注文有効期限)<BR>
     * 訂正後の注文有効期限をセットする。<BR>
     * @@param l_datChangeOrderExpirationDate - (注文有効期限)<BR>
     * 訂正後の注文有効期限
     * @@roseuid 42973670031D
     */
    public void setChangeOrderExpirationDate(Date l_datChangeOrderExpirationDate) 
    {
        this.changeOrderExpirationDate =  l_datChangeOrderExpirationDate;
    }
    
    /**
     * (get訂正注文有効期限)<BR>
     * 訂正後の注文有効期限を取得する。<BR>
     * <BR>
     * this.訂正注文有効期限を返却する。<BR>
     * @@return Date
     * @@roseuid 42973670032C
     */
    public Date getChangeOrderExpirationDate() 
    {
        return this.changeOrderExpirationDate;
    }
    
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件
     * @@roseuid 42973670033C
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
     * @@roseuid 42973670034C
     */
    public String getOrderConditionType() 
    {
        return this.orderConditionType;
    }
    
    /**
     * (set訂正発注条件演算子)<BR>
     * 訂正後の発注条件演算子をセットする。<BR>
     * @@param l_strChangeOrderCondOperator - (発注条件演算子)<BR>
     * 訂正後の発注条件演算子
     * @@roseuid 4297380A03D8
     */
    public void setChangeOrderCondOperator(String l_strChangeOrderCondOperator) 
    {
        this.changeOrderCondOperator =  l_strChangeOrderCondOperator;
    }
    
    /**
     * (get訂正発注条件演算子)<BR>
     * 訂正後の発注条件演算子を取得する。<BR>
     * <BR>
     * this.訂正発注条件演算子を返却する。<BR>
     * @@return String
     * @@roseuid 4297380B0000
     */
    public String getChangeOrderCondOperator() 
    {
        return this.changeOrderCondOperator;
    }
    
    /**
     * (set訂正発注条件単価)<BR>
     * 訂正後の発注条件単価をセットする。<BR>
     * @@param l_dblChangeOrderCondPrice - (発注条件単価)<BR>
     * 訂正後の発注条件単価
     * @@roseuid 429738BB037A
     */
    public void setChangeOrderCondPrice(double l_dblChangeOrderCondPrice) 
    {
        this.changeOrderCondPrice =  l_dblChangeOrderCondPrice;
    }
    
    /**
     * (get訂正発注条件単価)<BR>
     * 訂正後の発注条件単価を取得する。<BR>
     * <BR>
     * this.訂正発注条件単価を返却する。<BR>
     * @@return double
     * @@roseuid 429738BB03A9
     */
    public double getChangeOrderCondPrice() 
    {
        return this.changeOrderCondPrice;
    }
    
    /**
     * (set訂正（W指値）訂正指値)<BR>
     * 訂正後の（W指値）訂正指値をセットする。<BR>
     * @@param l_dblChangeWLimitPrice - (（W指値）訂正指値)<BR>
     * 訂正後の（W指値）訂正指値
     * @@roseuid 42973670036B
     */
    public void setChangeWLimitPrice(double l_dblChangeWLimitPrice) 
    {
        this.changeWLimitPrice = l_dblChangeWLimitPrice;
    }
    
    /**
     * (get訂正（W指値）訂正指値)<BR>
     * 訂正後の（W指値）訂正指値を取得する。<BR>
     * <BR>
     * this.訂正（W指値）訂正指値を返却する。<BR>
     * @@return double
     * @@roseuid 42973670039A
     */
    public double getChangeWLimitPrice() 
    {
        return this.changeWLimitPrice;
    }
    
    /**
     * (set訂正注文期限区分)<BR>
     * 訂正後の注文期限区分を取得する。<BR>
     * <BR>
     * @@param l_strChangeOrderExpirationDiv - (訂正後の注文期限区分)<BR>
     * 訂正後の注文期限区分
     */
    public void setChangeOrderExpirationDiv(String l_strChangeOrderExpirationDiv)
    {
        this.changeOrderExpirationDiv = l_strChangeOrderExpirationDiv;
    }
    
    /**
     * (get訂正注文期限区分)<BR>
     * 訂正後の注文期限区分を取得する。<BR>
     * <BR>
     * this.訂正後の注文期限区分を返却する。<BR>
     */
    public String getChangeOrderExpirationDiv()
    {
        return this.changeOrderExpirationDiv;
    }
}
@
