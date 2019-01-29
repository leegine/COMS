head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会残高合計レスポンスクラス(WEB3MutualBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル536
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信残高照会残高合計レスポンス)<BR>
 * 投信残高照会残高合計レスポンスクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceTotalResponse extends WEB3GenResponse 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference_total";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;
   
   /**
    * (特定口座評価額合計)<BR>
    * 特定口座評価額合計
    */
   public String capitalGainTotalAsset = "0";
   
   /**
    * (特定口座評価損益合計)<BR>
    * 特定口座評価損益合計
    */
   public String capitalGainTotalAssetProfitLoss = "0";
   
   /**
    * ( 一般口座評価額合計)<BR>
    * 一般口座評価額合計
    */
   public String normalAccountTotalAsset = "0";
   
   /**
    * (一般口座評価損益合計)<BR>
    * 一般口座評価損益合計
    */
   public String normalAccountTotalAssetProfitLoss = "0";

    /**
     * (外貨MMF評価額合計)<BR>
     * 外貨MMF評価額合計
     */
    public String frgnMmfTotalAsset = "0";

   /**
    * コンストラクタ。<BR>
    * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
    * @@param l_request - リクエストオブジェクト
    */
   public WEB3MutualBalanceReferenceTotalResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   } 
   
   /**
    * @@roseuid 41D13CC1005D
    */
   public WEB3MutualBalanceReferenceTotalResponse() 
   {
    
   }
}
@
