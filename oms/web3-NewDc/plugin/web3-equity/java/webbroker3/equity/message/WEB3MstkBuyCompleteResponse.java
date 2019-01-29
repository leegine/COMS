head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBuyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資買付注文完了レスポンス(WEB3MstkBuyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
                   2004/12/09 桑原（SAR）残案件対応 No.281
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資買付注文完了レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資買付注文完了レスポンスクラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3MstkBuyCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_buyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101059L;     
    /**
     * (更新時間)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (識別番号)
     */
    public String orderActionId;
    
	/**
	 * (インサイダー警告表示フラグ)<BR>
	 * true：警告表示要<BR>
	 * false：警告表示不要<BR>
	 */
	public boolean insiderWarningFlag;
	
    /**
     * @@roseuid 4167B04C0352
     */
	public WEB3MstkBuyCompleteResponse() 
	{

	}
	
    public WEB3MstkBuyCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
