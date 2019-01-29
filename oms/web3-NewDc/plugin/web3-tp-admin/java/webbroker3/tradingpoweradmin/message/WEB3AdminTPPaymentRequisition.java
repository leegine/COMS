head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客別入金請求クラス(WEB3AdminTPPaymentRequition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 顧客別入金請求クラス
 */
public class WEB3AdminTPPaymentRequisition extends Message
{

   /**
    * 部店コード
    */
   public String branchCode;

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * 顧客名
    */
   public String accountName;

   /**
    * 発生日
    */
   public Date occurredDate;

   /**
    * 入金請求区分
    */
   public String paymentRequisitionDivision;

   /**
    * 実績区分
    */
   public String requisitionStatus;

   /**
    * 入金請求金額
    */
   public double paymentRequisitionAmount;

   /**
    * 計算元区分
    */
   public String calclationSource;

   /**
    * @@roseuid 41DE22F90332
    */
   public WEB3AdminTPPaymentRequisition()
   {

   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPPaymentRequisition={");
       l_sb.append("branchCode=").append(this.branchCode);
       l_sb.append("accountCode=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",occurredDate=").append(this.occurredDate);
       l_sb.append(",requisitionStatus=").append(this.requisitionStatus);
       l_sb.append(",paymentRequisitionAmount=").append(this.paymentRequisitionAmount);
       l_sb.append(",calclationSource=").append(this.calclationSource);
       l_sb.append("}");

       return l_sb.toString();

   }

}
@
