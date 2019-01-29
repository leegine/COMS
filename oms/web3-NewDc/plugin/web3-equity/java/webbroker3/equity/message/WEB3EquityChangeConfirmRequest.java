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
filename	WEB3EquityChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正確認要求リクエストデータクラス(WEB3EquityChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 周玲玲 (中訊) 新規作成
Revesion History : 2004/12/08 岡村和明(SRA) 残案件対応 Ｎｏ.０５７
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文訂正確認リクエスト）。<br>
 * <br>
 * 現物株式注文訂正確認要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityChangeConfirmRequest extends WEB3EquityCommonRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeConfirmRequest.class);

    /**
     * (ID)<BR>
     * 訂正対象とする注文データを一意に特定するもの<BR>
     */
    public String id;

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_change_confirm";

    /**
     * シリアルバージョンUID。<BR>
     */
    public static final long serialVersionUID = 200402241455L;

    /**
     * コンストラクタ
     * @@roseuid 406120FA03D4
     */
    public WEB3EquityChangeConfirmRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityChangeConfirmResponse
     * @@roseuid 4061202402BB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityChangeConfirmResponse();
    }
    
    /**
     * (validate)<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@IDチェック<BR>
     * 　@this.ID＝nullの場合、<BR>
     * 　@「IDがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00953<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();
        
        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
