head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixTpCalcResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力計算結果リクエストクラス(WEB3TPOrixTpCalcResultRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (余力計算結果リクエスト)<BR>
 * 余力計算結果リクエストクラス。<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixTpCalcResultRequest extends WEB3BackRequest 
{

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "tradingpower_orix_tp_calc_result";

    /**
     * シリアルバージョンUID <BR>
     */
    public static final long serialVersionUId = 200503181330L;
   
    /**
     * From口座ID
     */
    public long fromAccountID;

    /**
     * To口座ID
     */
    public long toAccountID;

    /**
     * 会社コード
     */
    public long institutionCode;

    /**
     * 処理区分
     */
    public String procDiv;

    /**
     * (コンストラクタ)
     */
    public WEB3TPOrixTpCalcResultRequest() 
    {
    
    }
   
    /**
     * (createレスポンス)
     * @@return webbroker3.common.message.WEB3BackResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3TPOrixTpCalcResultResponse(this);
    }
}
@
