head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPDepositAutoTransferStopInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//ソース ファ@イル: C:\\web3model-detail\\srcpath\\webbroker3\\tradingpoweradmin\\message\\WEB3AdminTPDepositAutoTransferStopInfo.java

package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 顧客別保証金自動振替停止登録情報クラス
 */
public class WEB3AdminTPDepositAutoTransferStopInfo extends Message
{

   /**
    * 預り金自動振替停止ID
    */
   public String autoTransferStopId;

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
    * 停止開始日
    */
   public Date transferStopStart;

   /**
    * 停止終了日
    */
   public Date transferStopEnd;

   /**
    * @@roseuid 41DBC97B00EC
    */
   public WEB3AdminTPDepositAutoTransferStopInfo()
   {

   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPDepositAutoTransferStopInfo={");
       l_sb.append("autoTransferStopId=").append(this.autoTransferStopId);
       l_sb.append(",branchCode=").append(this.branchCode);
       l_sb.append(",accountCode=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",transferStopStart=").append(this.transferStopStart);
       l_sb.append(",transferStopEnd=").append(this.transferStopEnd);
       l_sb.append("}");

       return l_sb.toString();

   }


}
@
