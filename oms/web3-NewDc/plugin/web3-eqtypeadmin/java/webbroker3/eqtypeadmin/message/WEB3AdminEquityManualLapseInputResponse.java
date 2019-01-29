head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualLapseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効入力レスポンス(WEB3AdminEquityManualLapseInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式手動失効入力レスポンス)<BR>
 * 管理者・株式手動失効入力レスポンスクラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public class WEB3AdminEquityManualLapseInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminEquity_manualLapseInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605291315L;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コード一覧
     */
    public String[] marketList;
    
    /**
     * @@roseuid 447AB8F3032C
     */
    public WEB3AdminEquityManualLapseInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request - リクエストオブジェクト
     */
    protected WEB3AdminEquityManualLapseInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
