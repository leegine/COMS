head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング取消完了リクエスト(WEB3IPOBookBuildingCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング取消完了リクエスト
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingCancelCompleteRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171844L;
    
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
     * @@roseuid 4112EDC50018
     */
    public WEB3IPOBookBuildingCancelCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112EDC5004A
     */
    public WEB3GenResponse createResponse() 
    {
       return new WEB3IPOBookBuildingCancelCompleteResponse(this);
    }
}
@
