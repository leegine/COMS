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
filename	WEB3MstkBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資買付注文確認レスポンス(WEB3MstkBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
                   2004/12/10 桑原 (SRA) 修正
                   2005/01/05 岡村 (SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * （株式ミニ投資買付注文確認レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資買付注文確認レスポンスクラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3MstkBuyConfirmResponse extends WEB3MstkConfirmCommonResponse 
{
    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "mstk_buyConfirm";

    /**
     * （SerialVersionUID）。
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * （銘柄名）。
     */
    public String productName;
    
    /**
     * （市場コード）。<BR>
     * <BR>
     * 1：東京 2：大阪 3：名古屋 6：福岡 8：札幌 9：NNM 10：JASDAQ<BR>
     */
    public String marketCode;
    
    /**
     * （株式ミニ投資買付注文確認レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     */
	public WEB3MstkBuyConfirmResponse() 
	{
	}
    
    /**
     * （株式ミニ投資買付注文確認レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     * @@param l_request 株式ミニ投資買付注文確認リクエスト
     */
    public WEB3MstkBuyConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
