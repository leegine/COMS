head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付注文確認レスポンスクラス(WEB3MstkSellConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
                   2004/12/10 桑原 (SRA) 修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * （株式ミニ投資売付注文確認レスポンス）。<BR>
 * <br>
 * 株式ミニ投資売付注文確認レスポンスクラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellConfirmResponse extends WEB3MstkConfirmCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_sellConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * @@roseuid 4167B0500330
     */
	public WEB3MstkSellConfirmResponse() 
	{

	}
    public WEB3MstkSellConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
