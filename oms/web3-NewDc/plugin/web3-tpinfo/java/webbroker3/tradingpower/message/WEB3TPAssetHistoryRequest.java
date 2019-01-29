head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資産評価額履歴リクエスト(WEB3TPAssetHistoryRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25  艾興(中訊) 新規作成
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (資産評価額履歴リクエスト)<BR>
 * 資産評価額履歴リクエストクラス 。<BR>
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3TPAssetHistoryRequest extends WEB3GenRequest 
{
    
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_asset_history";
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B5544C02A7
    */
   public WEB3TPAssetHistoryRequest() 
   {
   }
 
   /**
    * (createレスポンス)
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B5469E0353
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPAssetHistoryResponse(this);
   }
}
@
