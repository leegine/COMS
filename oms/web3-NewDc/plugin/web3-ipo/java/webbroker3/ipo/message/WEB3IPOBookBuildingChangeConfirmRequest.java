head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング訂正確認リクエストクラス(WEB3IPOBookBuildingChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * IPOブックビルディング訂正確認リクエストクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeConfirmRequest extends WEB3IPODemandCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171436L;
    
    /**
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112EDC400B7
     */
    public WEB3IPOBookBuildingChangeConfirmRequest() 
    {
     
    }
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E79E019A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingChangeConfirmResponse(this);
    }    
}
@
