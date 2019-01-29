head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告完了リクエスト(WEB3IPOBookBuildingDemandCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング申告完了リクエスト
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandCompleteRequest extends WEB3IPODemandCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171840L;
    
    /**
     * IPO銘柄ＩＤ
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
     * @@roseuid 4112EC1B003E
     */
    public WEB3IPOBookBuildingDemandCompleteRequest() 
    {
     
    }
    
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPOBookBuildingDemandCompleteResponse(this);
    }
}
@
