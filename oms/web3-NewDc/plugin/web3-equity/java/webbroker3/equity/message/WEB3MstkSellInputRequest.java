head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付注文入力リクエストクラス(WEB3MstkSellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
                   2004/12/13 岡村和明 (SRA) 残案件対応 Ｎｏ.４０７
                   2005/01/05 岡村和明 (SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資売付注文入力リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資売付注文入力リクエストクラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellInputRequest extends WEB3GenRequest 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellInputRequest.class);
    
    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "mstk_sellInput";

    /**
     * （SerialVersionUID）。
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * （銘柄コード）。
     */
    public String productCode;
    
    /**
     * （株式ミニ投資売付注文入力リクエスト）。<BR>
     * <BR>
     * コンストラクタ
     */
    public WEB3MstkSellInputRequest() 
    {
    }
    
    /**
     * （validate）。<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@　@銘柄コードチェック<BR>
     * 　@　@　@　@this.銘柄コードが、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * <BR>
     * 　@　@　@・this.銘柄コード＝null(「銘柄コードがnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079
     */
    public void validate() throws WEB3BaseException
    {
     
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        log.debug("銘柄コードチェック");
        if(productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME);            
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createレスポンス）。<BR>
     * <BR>
     * @@return WEB3GenResponse 株式ミニ投資売付注文入力レスポンス
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellInputResponse(this);
    }
}
@
