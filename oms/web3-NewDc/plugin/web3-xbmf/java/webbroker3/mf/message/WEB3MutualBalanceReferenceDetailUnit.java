head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会明細クラス(WEB3MutualBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
                   2005/10/20 韋念瓊 (中訊) フィデリティ対応
*/

package webbroker3.mf.message;

/**
 * (投信残高照会明細)<BR>
 * 投信残高照会明細クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceDetailUnit extends WEB3MutualSellSwitchingProductGroup 
{    
   /**
    * (買付可能区分)<BR>
    * 買付可能フラグ<BR>
    * <BR>
    * null:取引可能<BR>
    * 1:全部解約中<BR>
    * 2:取扱不可<BR>
    * 3:取引不可<BR>
    * 4:緊急停止中<BR>
    * 5:取引時間外注文停止中<BR>
    * 6:募集期間中 <BR>
    */
   public String buyPosDiv;
   
   /**
    * (投信銘柄カテゴリーコード)<BR>
    * 投信銘柄カテゴリーコード<BR>
    */
   public String categoryCode;
   
   /**
    * (投信残高照会明細)<BR>
    * コンストラクタ
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceDetailUnit
    * @@roseuid 41AD90EF004F
    */
   public WEB3MutualBalanceReferenceDetailUnit() 
   {
    
   }
}
@
