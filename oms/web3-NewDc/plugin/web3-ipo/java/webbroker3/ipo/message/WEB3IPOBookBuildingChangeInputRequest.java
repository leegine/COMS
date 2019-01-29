head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング訂正入力リクエストクラス(WEB3IPOBookBuildingChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
*/


package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPOブックビルディング訂正入力リクエストクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171443L;
    
    /**
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112EC1B017E
     */
    public WEB3IPOBookBuildingChangeInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112EC1B01B0
     */
    public WEB3GenResponse createResponse() 
    {
     return new WEB3IPOBookBuildingChangeInputResponse(this);
    }
}
@
