head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文銘柄選択レスポンス(WEB3EquityProductSelectResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 岡村和明(SRA) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式買付注文銘柄選択レスポンス）。<br>
 * <br>
 * 現物株式買付注文銘柄選択応答　@レスポンスデータクラス
 * @@author 岡村和明(SRA) 
 * @@version 1.0
 */
public class WEB3EquityProductSelectResponse extends WEB3GenResponse {

    /**
     * ポリモルフィックタイプ<BR>
     */
    public static final String PTYPE = "equity_productSelect";

    /**
     * シリアルバージョンUID<BR>
     */
    public static final long serialVersionUID = 200412100000L;

    /**
     * (市場コード一覧)<BR>
     * 選択可能市場一覧<BR>
     * 1:東京　@2:大阪　@3:名古屋　@6:福岡　@8:札幌　@9:NNM　@10:JASDAQ<BR>
     */
    public String[] marketList;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (コンストラクタ)<BR>
     * <BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3EquityProductSelectResponse(WEB3EquityProductSelectRequest l_request)
    {
        super(l_request);
    }
}
@
