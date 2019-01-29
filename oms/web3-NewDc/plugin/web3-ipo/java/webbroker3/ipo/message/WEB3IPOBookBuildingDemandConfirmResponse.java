head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告確認レスポンスクラス(WEB3IPOBookBuildingDemandConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * IPOブックビルディング申告確認レスポンスクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171841L;
    
    /**
     * 申告相当額
     */
    public String demandEquivalentPrice;
    
    /**
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * 確認時基準値
     */
    public String checkValue;
    
    /**
     * @@roseuid 4112EA850239
     */
    public WEB3IPOBookBuildingDemandConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D16A0701DC
     */
    public WEB3IPOBookBuildingDemandConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
