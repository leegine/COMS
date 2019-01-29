head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資買付注文入力リクエスト(WEB3MstkBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
                   2004/12/13 岡村和明(SRA) 残案件対応 Ｎｏ.４０７
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資買付注文入力リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資買付注文入力リクエストクラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3MstkBuyInputRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ      
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyInputRequest.class);
    /**
     * PTYPE
     */
    public static final String PTYPE = "mstk_buyInput";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * (銘柄コード)
     */
    public String productCode;
    
    /**
     * @@roseuid 4167B04D017D
     */
    public WEB3MstkBuyInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * 何もせずにそのままreturnする。<BR>
     * @@roseuid 411194020269
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B04D0191
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkBuyInputResponse(this);
    }
}
@
