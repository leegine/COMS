head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOBookBuildingDemandInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告入力レスポンスクラス(WEB3IPOBookBuildingDemandInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;

/**
 * IPOブックビルディング申告入力レスポンスクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IPOBookBuildingDemandInputResponse extends WEB3IPODemandCommonResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_bookBuildingDemandInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408171832L;
    
    /**
     * 出金余力
     */
    public String paymentPower;
    
    /**
     * 目論見書閲覧チェック結果<BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;
    
    /**
     * @@roseuid 4112E79E0244
     */
    public WEB3IPOBookBuildingDemandInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。
     * @@param l_request - リクエストオブジェクト
     * @@roseuid 40D1681D00E2
     */
    public WEB3IPOBookBuildingDemandInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);     
    }
}
@
