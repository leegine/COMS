head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング訂正完了リクエストクラス(WEB3IPOBookBuildingChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 劉江涛(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 * IPOブックビルディング訂正完了リクエストクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IPOBookBuildingChangeCompleteRequest extends WEB3IPODemandCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171401L;
    
    /**
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * 確認時基準値
     */
    public String checkValue;
    
    /**
     * @@roseuid 4112EDC401C5
     */
    public WEB3IPOBookBuildingChangeCompleteRequest() 
    {
        
    }
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E79E019A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingChangeCompleteResponse(this);
    }    
}
@
