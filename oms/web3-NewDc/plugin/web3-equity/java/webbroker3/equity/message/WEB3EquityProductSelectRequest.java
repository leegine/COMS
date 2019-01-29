head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文銘柄選択リクエスト(WEB3EquityProductSelectRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 岡村和明(SRA) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式買付注文銘柄選択リクエスト）。<br>
 * <br>
 * 現物株式買付注文銘柄選択要求　@リクエストデータクラス
 * @@author 岡村和明(SRA) 
 * @@version 1.0
 */
public class WEB3EquityProductSelectRequest extends WEB3GenRequest {

    /**
     * ポリモルフィックタイプ<BR>
     */
    public static final String PTYPE = "equity_productSelect";

    /**
     * シリアルバージョンUID<BR>
     */
    public static final long serialVersionUID = 200412100000L;

    /**
     * レスポンスデータを作成する。<BR>
     */
    public WEB3GenResponse createResponse() {
        return new WEB3EquityProductSelectResponse(this);
    }
    
    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3EquityProductSelectRequest() {
    }

}
@
