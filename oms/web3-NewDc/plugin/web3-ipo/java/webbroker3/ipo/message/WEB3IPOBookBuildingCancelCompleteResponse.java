head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング取消完了レスポンスクラス(WEB3IPOBookBuildingCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング取消完了レスポンスクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingCancelCompleteResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171843L;
    
    /**
     * 更新時間
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * 識別番号
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4112EDC40374
     */
    public WEB3IPOBookBuildingCancelCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D26DC4013F
     */
    public WEB3IPOBookBuildingCancelCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
