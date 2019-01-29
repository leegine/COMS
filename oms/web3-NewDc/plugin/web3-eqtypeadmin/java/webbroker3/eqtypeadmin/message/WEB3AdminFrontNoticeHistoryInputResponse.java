head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontNoticeHistoryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・通知履歴参照入力レスポンスクラス (WEB3AdminFrontNoticeHistoryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.119
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 管理者・通知履歴参照入力レスポンスクラス<BR>
 */
public class WEB3AdminFrontNoticeHistoryInputResponse extends WEB3GenResponse 
{
   
	/**
	 * PTYPE<BR>
	 */
	public final static String PTYPE = "admin_front_Notice_History_Input";

   /**
    * 市場コードの一覧<BR>
    */
   public String[] convertMarketCodeList;
   
   /**
	* 通知受信日付の一覧<BR>
	*/
   public Date[] createdTimestampList;

   /**
    * 銘柄タイプ一覧<BR>
    */
   public String[] productTypeList;
   
   /**
    * @@roseuid 42FFFEAE0189
    */
   public WEB3AdminFrontNoticeHistoryInputResponse() 
   {
    
   }
   
   /**
    *
    * @@param l_request WEB3GenRequest<BR>
    */
   public WEB3AdminFrontNoticeHistoryInputResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   }
}
@
