head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資産評価額履歴レスポンス (WEB3TPAssetHistoryResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25  艾興(中訊) 新規作成
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (資産評価額履歴レスポンス )<BR>
 * 資産評価額履歴レスポンスクラス。<BR>
 * @@author 艾興
 * @@version 1.0
 * 
 */
public class WEB3TPAssetHistoryResponse extends WEB3GenResponse 
{
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_asset_history";
   
   /**
    * 資産評価額履歴一覧
    */
   public WEB3TPAssetHistoryUnit[] assetHistoryList;   
   
   /**
    * (コンストラクタ)
    * @@param l_request
    * @@roseuid 41B69EEE0260
    */
   protected WEB3TPAssetHistoryResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B5544C03B1
    */
   public WEB3TPAssetHistoryResponse() 
   {
    
   }
   
}
@
