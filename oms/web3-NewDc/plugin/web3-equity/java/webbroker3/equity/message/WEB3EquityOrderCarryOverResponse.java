head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越レスポンスクラス(WEB3EquityOrderCarryOverResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３５
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式注文繰越レスポンス）。<br>
 * <br>
 * 株式注文繰越レスポンスクラス
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverResponse extends WEB3BackResponse
{

    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "equity_carry_over";

    /**
     * <p>（serialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200405211030L;

    /**
     * <p>（株式注文繰越レスポンス）。</p>
     * <p>コンストラクタ。</p>
     * @@param l_request 株式注文繰越リクエスト
     */
    public WEB3EquityOrderCarryOverResponse(WEB3EquityOrderCarryOverRequest l_request)
    {
        super(l_request);
    }

    /**
     * <p>（株式注文繰越レスポンス）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3EquityOrderCarryOverResponse()
    {

    }    
}
@
