head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyStateDetail.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込状況明細(WEB3PointApplyStateDetail.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (申込状況明細)<BR>
 * 申込状況明細クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointApplyStateDetail extends Message
{
    
    /**
     * (申込日時)<BR>
     * 申込日時<BR>
     */
    public Date applyDate;
    
    /**
     * (景品番号)<BR>
     * 景品番号<BR>
     */
    public String premiumNo;
    
    /**
     * (景品名)<BR>
     * 景品名<BR>
     */
    public String premiumName;
    
    /**
     * (必要ポイント)<BR>
     * 必要ポイント<BR>
     */
    public String requiredPoint;
    
    /**
     * (備考)<BR>
     * 受付区分と取消区分から判定した申込の状況<BR>
     * <BR>
     * 0： 受付中<BR>
     * 1： 受付済<BR>
     * 2： 取消<BR>
     * <BR>
     */
    public String applyStateDiv;
    
    /**
     * (入力区分)<BR>
     * 入力区分（申込経路区分）<BR>
     * <BR>
     * 1： コールセンター<BR>
     * 2： ＰＣ<BR>
     * 3： スリングショット<BR>
     * 4： i-mode<BR>
     * 5： Vodafone<BR>
     * 6： AU<BR>
     * 9： HOST<BR>
     * A： 管理者<BR>
     * <BR>
     */
    public String orderRootDiv;
    
    /**
     * (扱者)<BR>
     * 申込を行ったオペレーターのオペレータｰコード<BR>
     */
    public String operatorCode;
    
    /**
     * (申込状況明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 418F55E90240
     */
    public WEB3PointApplyStateDetail() 
    {
     
    }
}
@
