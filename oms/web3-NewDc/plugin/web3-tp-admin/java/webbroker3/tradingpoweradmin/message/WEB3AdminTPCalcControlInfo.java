head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPCalcControlInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御情報クラス(WEB3AdminTPCalcControlInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
Revision History : 2007/07/26 趙林鵬 (中訊) モデル：No.006
*/
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 余力制御情報クラス
 */
public class WEB3AdminTPCalcControlInfo extends Message
{
   /**
    * 顧客余力条件ID
    */
   public String calcConditionId;

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
    * 取引停止区分
    */
   public String tradingStop;

   /**
    * 信用新規建余力区分
    */
   public String marginOpenPositionStop;

   /**
    * 先物OP新規建余力区分
    */
   public String ifoOpenPositionStop;

   /**
    * 出金余力区分
    */
   public String paymentStop;

   /**
    * その他商品買付余力区分
    */
   public String otherTradingStop;

   /**
    * (追証未入金区分)<BR>
    * 0:追証未入金なし 1:追証未入金あり<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC92901D6
    */
   public WEB3AdminTPCalcControlInfo()
   {

   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPCalcControlInfo={");
       l_sb.append(",calcConditionId=").append(this.calcConditionId);
       l_sb.append(",branchCode=").append(this.branchCode);
       l_sb.append("calcConditionId=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",tradingStop=").append(this.tradingStop);
       l_sb.append(",marginOpenPositionStop=").append(this.marginOpenPositionStop);
       l_sb.append(",ifoOpenPositionStop=").append(this.ifoOpenPositionStop);
       l_sb.append(",paymentStop=").append(this.paymentStop);
       l_sb.append(",otherTradingStop=").append(this.otherTradingStop);
       l_sb.append("}");

       return l_sb.toString();

   }

}
@
