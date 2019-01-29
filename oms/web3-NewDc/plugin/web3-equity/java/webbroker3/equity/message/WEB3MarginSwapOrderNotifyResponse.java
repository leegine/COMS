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
filename	WEB3MarginSwapOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文通知レスポンスクラス(WEB3MarginSwapOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 森川 (SRA) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （信用取引現引現渡注文通知レスポンスクラス）。<BR>
 * <BR>
 * 信用取引現引現渡注文通知レスポンスクラス。
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyResponse extends WEB3BackResponse 
{
    /**
     * (PTYPE)。<BR>
     */
    public static final String PTYPE = "margin_swapOrderNotify";


    /**
     * (SerialVersionUID)。<BR>
     */
    public static final long serialVersionUID = 200412241500L;


    /**
     * (コンストラクタ)。
     */
    public WEB3MarginSwapOrderNotifyResponse() 
    {
    }


    /**
     * (コンストラクタ)。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginSwapOrderNotifyResponse(WEB3MarginSwapOrderNotifyRequest l_request)
    {
       super(l_request);
    }
}
@
