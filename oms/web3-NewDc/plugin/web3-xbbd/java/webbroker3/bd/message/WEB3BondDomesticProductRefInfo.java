head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductRefInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券銘柄照会情報(WEB3BondDomesticProductRefInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 何文敏 (中訊) 新規作成 仕様変更・モデルNo.200
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (国内債券銘柄照会情報)<BR>
 * 国内債券銘柄照会情報<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondDomesticProductRefInfo extends Message
{

   /**
    * (銘柄ID)<BR>
    * 銘柄ID<BR>
    */
   public String productId;

   /**
    * (銘柄コード)<BR>
    * 銘柄コード<BR>
    */
   public String productCode;

   /**
    * (回号コード)<BR>
    * 回号コード<BR>
    */
   public String productIssueCode;

   /**
    * (銘柄名（HOST）)<BR>
    * 銘柄名（HOST）<BR>
    */
   public String productNameHost;

   /**
    * (銘柄名（WEB3))<BR>
    * 銘柄名（WEB3)<BR>
    */
   public String productNameWEB3;

   /**
    * (応募単価)<BR>
    * 応募単価<BR>
    */
   public String applyPrice;

   /**
    * (取扱区分)<BR>
    * 取扱区分<BR>
    * <BR>
    * 0：不可<BR>
    * 2：顧客<BR>
    */
   public String tradeHandleDiv;

   /**
    * (利率)<BR>
    * 利率<BR>
    */
   public String coupon;

   /**
    * (発行日)<BR>
    * 発行日<BR>
    */
   public Date issueDate;

   /**
    * (償還日)<BR>
    * 償還日<BR>
    */
   public Date maturityDate;

   /**
    * (年間利払回数)<BR>
    * 年間利払回数<BR>
    */
   public String yearlyInterestPayments;

   /**
    * (利払日1)<BR>
    * 利払日1<BR>
    */
   public String couponPaymentDate1;

   /**
    * (利払日2)<BR>
    * 利払日2<BR>
    */
   public String couponPaymentDate2;

   /**
    * (国内債券銘柄照会情報)<BR>
    * コンストラクタ<BR>
    * @@roseuid 466379A50138
    */
   public WEB3BondDomesticProductRefInfo()
   {

   }
}
@
