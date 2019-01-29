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
filename	WEB3AdminFrontMarketNoticeHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場通知履歴明細 (WEB3AdminFrontMarketNoticeHistoryUnitt.java)
Author Name      : Daiwa Institute of Research
*/


package webbroker3.eqtypeadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * 市場通知履歴明細クラス<BR>
 */
public class WEB3AdminFrontMarketNoticeHistoryUnit extends Message 
{
   
   /**
    * 注文受付番号<BR>
    */
   public String acceptNumber;
   
   /**
    * 仮想サーバNo<BR>
    */
   public String virtualServerNumber;
   
   /**
    * 社内処理項目<BR>
    */
   public String corpCode;
   
   /**
    * 電文通番<BR>
    */
   public String noticeNumber;
   
   /**
    * 通知受信時刻<BR>
    */
   public Date createdTimestamp;
   
   /**
    * 部店コード<BR>
    */
   public String branchCode;
   
   /**
    * 口座コード<BR>
    */
   public String accountCode;
   
   /**
    * 銘柄コード<BR>
    */
   public String productCode;
   
   /**
    * 売買区分<BR>
    */
   public String dealingType;
   
   /**
    * データ種別コード<BR>
    */
   public String dataClassCode;
   
   /**
    * データ種別詳細コード<BR>
    */
   public String dataClassDetailCode;
   
   /**
    * 再送ダブリフラグ<BR>
    */
   public String resendFlg;
   
   /**
    * 執行条件<BR>
    */
   public String execCondType;
   
   /**
    * 値段条件<BR>
    */
   public String priceCondType;
   
   /**
    * 注文数量<BR>
    */
   public String orderQuantity;
   
   /**
    * 注文単価<BR>
    */
   public String limitPrice;
   
   /**
    * エラーコード<BR>
    */
   public String errorCode;
   
   /**
    * 信用取引区分<BR>
    */
   public String marginCode;
   
   /**
    * 自己委託区分<BR>
    */
   public String tradeauditCode;
   
   /**
    * 空売りフラグ<BR>
    */
   public String shortSellOrderFlag;
   
   /**
    * 削除数量<BR>
    */
   public String cutQuantity;
   
   /**
    * （被訂正）注文数量<BR>
    */
   public String orgOrderQuantity;
   
   /**
    * （被訂正）注文単価<BR>
    */
   public String orgLimitPrice;
   
   /**
    * （被訂正）社内処理項目<BR>
    */
   public String orgCorpCode;
   
   /**
    * （被訂正）執行条件<BR>
    */
   public String orgExecCondType;
   
   /**
    * （被訂正）値段条件<BR>
    */
   public String orgPriceCondType;
   
   /**
    * 削除済数量<BR>
    */
   public String canceledQuantity;
   
   /**
    * 内出来数量<BR>
    */
   public String executedQuantity;
   
   /**
    * 結果コード<BR>
    */
   public String modifiedResult;
   
   /**
    * 単価符号<BR>
    */
   public String priceMark;
   
   /**
    * 約定数量<BR>
    */
   public String execQuantity;
   
   /**
    * 約定単価<BR>
    */
   public String execPrice;
   
   /**
    * 注文残数量<BR>
    */
   public String leftQuantity;
   
   /**
    * 約定通知番号<BR>
    */
   public String execNumber;
   
   /**
    * 出来符号<BR>
    */
   public String execMark;
   
   /**
    * 失効注文数量<BR>
    */
   public String expirationQuantity;
   
   /**
    * 失効理由コード<BR>
    */
   public String reasonCode;
   
   /**
    * ストップ符号<BR>
    */
   public String stopMark;
   
   /**
    * コンストラクタ<BR>
    * @@roseuid 42D1F5960246
    */
   public WEB3AdminFrontMarketNoticeHistoryUnit() 
   {
    
   }
}
@
