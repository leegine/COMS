head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資買付注文入力レスポンス(WEB3MstkBuyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
                   2004/12/09 桑原 (SRA) 残案件対応 No.281
                   2004/04/05 岡村 (SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資買付注文入力レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資買付注文入力レスポンスクラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3MstkBuyInputResponse extends WEB3GenResponse 
{
    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "mstk_buyInput";

    /**
     * （SerialVersionUID）。
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * （買付可能額）。
     */
    public String tradingPower;
    
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
     * （取引終了警告）。<BR>
     * <BR>
     * true：警告文を表示する<BR>
     * false：警告文を表示しない
     */
    public boolean messageSuspensionFlag;
    
	/**
	 * （インサイダー警告表示フラグ）。<BR>
     * <BR>
	 * true：警告表示要<BR>
	 * false：警告表示不要
	 */
	public boolean insiderWarningFlag;
    
    /**
     * （株式ミニ投資買付注文入力レスポンス）。<BR>
     * <BR>
     * デフォルトコンストラクタ
     */
	public WEB3MstkBuyInputResponse() 
	{
	}

    /**
     * （株式ミニ投資買付注文入力レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     * @@param l_request 株式ミニ投資買付注文入力リクエスト
     */
    public WEB3MstkBuyInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
