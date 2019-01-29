head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductBasicInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券銘柄基本情報(WEB3BondDomesticProductBasicInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 何文敏 (中訊) 新規作成 仕様変更・モデルNo.200
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (国内債券銘柄基本情報)<BR>
 * 国内債券銘柄基本情報<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondDomesticProductBasicInfo extends Message
{

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
    * (銘柄名（HOST))<BR>
    * 銘柄名（HOST)<BR>
    */
   public String productNameHost;

   /**
    * (種別コード)<BR>
    * 種別コード<BR>
    */
   public String bondCategCode;

   /**
    * (発行券種)<BR>
    * 発行券種<BR>
    */
   public String[] issueCouponType;

   /**
    * (発行日)<BR>
    * 発行日<BR>
    */
   public Date issueDate;

   /**
    * (応募単価)<BR>
    * 応募単価<BR>
    */
   public String applyPrice;

   /**
    * (利率)<BR>
    * 利率<BR>
    */
   public String coupon;

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
    * (償還日)<BR>
    * 償還日<BR>
    */
   public Date maturityDate;

   /**
    * (応募開始日（SONAR）)<BR>
    * 応募開始日（SONAR）<BR>
    */
   public Date recruitStartDateSONAR;

   /**
    * (応募終了日（SONAR）)<BR>
    * 応募終了日（SONAR）<BR>
    */
   public Date recruitEndDateSONAR;

   /**
    * (国内債券銘柄基本情報)<BR>
    * コンストラクタ<BR>
    * @@roseuid 466379500138
    */
   public WEB3BondDomesticProductBasicInfo()
   {

   }
}
@
