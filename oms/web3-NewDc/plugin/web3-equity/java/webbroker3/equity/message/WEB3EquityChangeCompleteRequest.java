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
filename	WEB3EquityChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正完了要求リクエストデータクラス(WEB3EquityChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/11 周玲玲 (中訊) 新規作成
Revesion History : 2004/12/08 岡村和明(SRA) 残案件対応 Ｎｏ.０５７
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文訂正完了リクエスト）。<br>
 * <br>
 * 現物株式注文訂正完了要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityChangeCompleteRequest extends WEB3EquityCommonRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeCompleteRequest.class);

    /**
     * (ID)<BR>
     * 注文ID<BR>
     */
    public String id;

    /**
     * (確認時単価)<BR>
     * 確認時の単価<BR>
     */
    public String checkPrice;

    /**
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;

    /**
     * (暗証番号)<BR>
     * 暗証番号（取引パスワード）入力値<BR>
     */
    public String password;

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_change_complete";

    /**
     * シリアルバージョンUID。<BR>
     */
    public static final long serialVersionUID = 200402241355L;

    /**
     * コンストラクタ
     * @@roseuid 4061210601D1
     */
    public WEB3EquityChangeCompleteRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityChangeCompleteResponse
     * @@roseuid 406120C90396
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityChangeCompleteResponse();
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
     * 　@　@　@tag   : BUSINESS_ERROR_00600<BR>
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
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
