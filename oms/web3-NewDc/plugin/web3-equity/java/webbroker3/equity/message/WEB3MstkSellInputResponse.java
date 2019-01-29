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
filename	WEB3MstkSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付注文入力レスポンスクラス(WEB3MstkSellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
                   2004/12/09 桑原 (SRA) 残案件対応 No.281
                   2005/01/05 岡村 (SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資売付注文入力レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資売付注文入力レスポンスクラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellInputResponse extends WEB3GenResponse 
{
    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "mstk_sellInput";

    /**
     * （SerialVersionUID）。
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * （注文株数）。
     */
    public String orderQuantity;
    
    /**
     * （銘柄名）。
     */
    public String productName;
    
    /**
     * （市場コード）。<BR>
     * <BR>
     * 1：東京 2：大阪 3：名古屋 6：福岡 8：札幌 9：NNM 10：JASDAQ
     */
    public String marketCode;
    
    /**
     * （取引終了警）。<BR>
     * true：警告文を表示する　@　@false：警告文を表示しない
     */
    public boolean messageSuspensionFlag;
    
	/**
	 * （インサイダー警告表示フラグ）。<BR>
	 * true：警告表示要<BR>
	 * false：警告表示不要
	 */
	public boolean insiderWarningFlag;
	
    
    /**
     * （株式ミニ投資売付注文入力レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     */
	public WEB3MstkSellInputResponse() 
	{

	}

    /**
     * （株式ミニ投資売付注文入力レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     * @@param l_request 株式ミニ投資売付注文入力リクエスト
     */
    public WEB3MstkSellInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
