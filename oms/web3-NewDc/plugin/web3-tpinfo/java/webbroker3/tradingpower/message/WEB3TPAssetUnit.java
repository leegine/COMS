head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 預り資産明細ユニットクラス(WEB3TPAssetUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 Revision History : 2007/08/06 トウ鋒鋼(中訊) モデルNo.119
 Revision History : 2008/01/22 孟亞南(中訊) モデルNo.231
 */
package webbroker3.tradingpower.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (預り資産明細ユニット)<BR>
 * 預り資産明細ユニットクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetUnit extends Message 
{
   
   /**
    * 清算済区分
    */
   public String clearUpDiv;
   
   /**
    * 預り金
    */
   public String accountBalance;
   
   /**
    * 預り金<当日取引分含む>
    */
   public String accountBalanceDay;
   
   /**
    * 株式評価額
    */
   public String equityAsset;
   
   /**
    * 株式ミニ投資評価額
    */
   public String mstkAsset;
   
   /**
    * 累積投資評価額
    */
   public String ruitoAsset;
   
   /**
    * 投資信託評価額
    */
   public String mutualAsset;
   
   /**
    * 債券評価額
    */
   public String bondAsset;
   
   /**
    * 合計評価額
    */
   public String totalAsset;

   /**
    * 預り金（ドル）
    */
   public String accountBalanceDollar;
   
   /**
    * 預り金（ユーロ）
    */
   public String accountBalanceEuro;

   /**
    * 外国株式評価額
    */
   public String feqAsset;

   /**
    * (コンストラクタ)
    * @@roseuid 41B54A5F0065
    */
   public WEB3TPAssetUnit() 
   {
   }
   
}
@
