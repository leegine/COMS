head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMenuDisplayResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メニュー画面表示レスポンス(WEB3GentradeMenuDisplayResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * メニュー画面表示レスポンスクラス
 */
public class WEB3GentradeMenuDisplayResponse 
    extends WEB3GentradeBatoDisplayCommonResponse 
{
   
    /**
     * PTYPE
     */
    public static final String PTYPE = "gentrade_menu_display";
   
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200503151416L;
   
    /**
     * @@roseuid 4236987103D5
     */
    public WEB3GentradeMenuDisplayResponse() 
    {
    }
   
    /**
     * コンストラクタ<br />
     * @@param WEB3GenRequest - リクエストデータ<br />
     * <br />
     * @@return webbroker3.gentrade.message.WEB3GentradeMenuDisplayResponse<br />
     * @@roseuid 423670A9017C
     */
    public WEB3GentradeMenuDisplayResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
