head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文約定照会注文単位(WEB3MstkExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 彭巍(中訊) 新規作成
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * （株式ミニ投資注文約定照会注文単位）。<BR>
 * <BR>
 * 株式ミニ投資注文約定照会注文単位クラス
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3MstkExecuteGroup extends Message 
{
    
    /**
     * （ID）。<BR>
     * <BR>
     * 注文ＩＤ<BR>
     * 取消確認画面への遷移に使用。
     */
    public String id;
    
    /**
     * （銘柄コード）。
     */
    public String productCode;
    
    /**
     * （銘柄名）。
     */
    public String productName;
    
    /**
     * （市場コード）。<BR>
     * <BR>
     * 1：東京 2：大阪 3：名古屋 6：福岡 8：札幌 9：NNM 10：JASDAQ
     */
    public String marketCode;
    
    /**
     * （売買区分）。<BR>
     * <BR>
     * 1:売付　@2:買付
     */
    public String dealingType;
    
    /**
     * （注文株数）。
     */
    public String orderQuantity;
    
    /**
     * （注文日時）。
     */
    public Date orderDate;
    
    /**
     * （注文執行日）。
     */
    public Date orderExecuteDate;
    
    /**
     * （約定日）。
     */
    public Date executionTimestamp;
    
    /**
     * （受渡日）。
     */
    public Date deliveryDate;
    
    /**
     * （約定株数）。
     */
    public String execQuantity;
    
    /**
     * （約定単価）。
     */
    public String execPrice;
    
    /**
     * （受渡代金）。
     */
    public String deliveryPrice;
    
    /**
     * （注文状況区分）。
     */
    public String miOrderState;
    
    /**
     * （取消可能フラグ）。<BR>
     * <BR>
     * true：取消可能<BR>
     * false：取消不可<BR>
     */
    public boolean cancelFlag;
    
    /**
     * （株式ミニ投資注文約定明細）。<BR>
     * <BR>
     * デフォルトコンストラクタ。<BR>
     * @@roseuid 4167B04F008F
     */
    public WEB3MstkExecuteGroup() 
    {
     
    }
}
@
