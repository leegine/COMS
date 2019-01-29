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
filename	WEB3EquityExecEndNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出来終了通知リクエストクラス(WEB3EquityExecEndNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３５
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式出来終了通知リクエスト）。<br>
 * <br>
 * 株式出来終了通知リクエストクラス
 * @@version 1.0
 */
public class WEB3EquityExecEndNotifyRequest extends WEB3BackRequest
{

    /**
     * <p>（serialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200405211030L;

    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "equity_exec_end_notify";

    /**
     * <p>(証券会社コード)。</p>
     * <p>繰越対象の証券会社コード。</p>
     */
    public String institutionCode;
    
    /**
     * <p>(From口座ID)。</p>
     * <p>From口座ID。</p>
     */
    public long rangeFrom;
    
    /**
     * <p>(To口座ID)。</p>
     * <p>To口座ID。</p>
     */
    public long rangeTo;
    
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecEndNotifyRequest.class);
    
    /**
     * <p>（株式出来終了通知リクエスト）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3EquityExecEndNotifyRequest()
    {

    }

    /**
     * <p>（createレスポンス）。</p>
     * <p>株式出来終了通知レスポンスを生成して返す。</p>
     * @@return 株式出来終了通知レスポンス
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityExecEndNotifyResponse(this);
    }
    
    /**
     * <p>（validate）。</p>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@this.証券会社コード＝nullの場合、<BR>
     * 　@「証券会社コードがnull」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_00827</p>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (institutionCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
