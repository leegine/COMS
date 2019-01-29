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
filename	WEB3MstkCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  株式ミニ投資注文取消完了レスポンス(WEB3MstkCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 カク寛新 (中訊) 新規作成
                   2004/12/10 桑原 (SRA) 修正
                   2005/01/05 岡村 (SRA) JavaDocの修正
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （株式ミニ投資注文取消確認レスポンス）。<BR>
 * <BR>
 * 株式ミニ投資注文取消確認レスポンスクラス
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * （PTYPE）。
     */
    public final static  String PTYPE = "mstk_cancelConfirm";
        
    /**
     * （SerialVersionUID）。
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * （銘柄コード）。
     */
    public String productCode;
    
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
     * （売買区分）。<BR>
     * <BR>
     * 1:売付　@2:買付
     */
    public String dealingType;
    
    /**
     * （注文株数）。
     */
    public String orderQuantity;
    
    /**
     * （確認時発注日）。<BR>
     * <BR>
     * ※注文執行日
     */
    public Date checkDate;
    
    /**
     * （取引終了警告）。<BR>
     * true：警告文を表示する　@<BR>　@
     * false：警告文を表示しない
     */
    public boolean messageSuspensionFlag;
    
    /**
     * （株式ミニ投資注文取消確認レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     */
	public WEB3MstkCancelConfirmResponse() 
	{

	} 
    
    /**
     * （株式ミニ投資注文取消確認レスポンス）。<BR>
     * <BR>
     * コンストラクタ
     * @@param l_request 株式ミニ投資注文取消確認リクエスト
     */
    public WEB3MstkCancelConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
