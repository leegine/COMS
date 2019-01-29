head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.38.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告入力リクエストクラス(WEB3IPOBookBuildingDemandInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング申告入力リクエストクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171831L;
    
    /**
     * IPO銘柄ＩＤ
     */
    public String id;
    
    /**
     * 電子鳩チェックフラグ<BR>
     * <BR>
     * true：チェックする <BR>
     * false：チェックしない <BR>
     */
    public boolean batoCheckFlag;

    /**
     * (種別コード)<BR>
     * 種別コード（IPO目論見書）<BR>
     */
    public String typeCode;
    
    /**
     * @@roseuid 4112EA850184
     */
    public WEB3IPOBookBuildingDemandInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112EA8501B6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingDemandInputResponse(this);
    }
}
@
