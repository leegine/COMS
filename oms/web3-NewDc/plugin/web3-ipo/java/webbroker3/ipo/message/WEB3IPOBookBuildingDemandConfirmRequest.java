head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告確認リクエストクラス(WEB3IPOBookBuildingDemandConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング申告確認リクエストクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandConfirmRequest extends WEB3IPODemandCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171842L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112E79E030C
     */
    public WEB3IPOBookBuildingDemandConfirmRequest() 
    {
     
    }

    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingDemandConfirmResponse(this);
    }    
}
@
