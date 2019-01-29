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
filename	WEB3GentradeBatoDisplayCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩表示共通レスポンス(WEB3GentradeBatoDisplayCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 電子鳩表示共通レスポンス
 */
public class WEB3GentradeBatoDisplayCommonResponse
    extends WEB3GenResponse
{

    /**
     * 稼動チェック結果<br />
     */
    public boolean isWorking;

    /**
     * URL<br />
     */
    public String url;

    /**
     * ハッシュ値<br />
     */
    public String hashValue;


    /**
     * デフォルトコンストラクタ
     */
    public WEB3GentradeBatoDisplayCommonResponse() 
    {
    }
   
    /**
     * コンストラクタ<br />
     * @@param WEB3GenRequest - リクエストデータ<br />
     * <br />
     * @@return WEB3GentradeBatoDisplayCommonResponse<br />
     * @@roseuid 423670A9017C
     */
    public WEB3GentradeBatoDisplayCommonResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }

}
@
