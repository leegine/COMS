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
filename	WEB3MarginOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文通知リクエストクラス(WEB3MarginOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 李松峰 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３８
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
 * （信用取引注文通知リクエスト）。<br>
 * <br>
 * 信用取引注文通知リクエストクラス
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyRequest extends WEB3BackRequest 
{
    /**
     * <p>（PTYPE）。</p>
     */
    public static final String PTYPE = "margin_orderNotify";

    /**
     * <p>（SerialVersionUID）。</p>
     */
    public static final long serialVersionUID = 200409101617L;

    /**
     * <p>（識別コードプレフィクス一覧）。</p>
     * <p>識別コードプレフィクス一覧。</p>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderNotifyRequest.class);
    
    /**
     * <p>（信用取引注文通知リクエスト）。</p>
     * <p>コンストラクタ。</p>
     */
    public WEB3MarginOrderNotifyRequest() 
    {
    }
    
    /**
     * <p>（createレスポンス）。</p>
     * <p>信用取引注文通知レスポンスを生成して返す。</p>
     * @@return 信用取引注文通知レスポンス
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MarginOrderNotifyResponse(this);
    }
    
    /**
     * <p>（validate）。</p>
     * <p>当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@識別コードプレフィクス一覧のチェック<BR>
     * 　@this.識別コードプレフィクス一覧＝nullまたは要素数が0の場合、<BR>
     * 　@「識別コードプレフィクス一覧の指定なし」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_01291</p>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        if(this.orderRequestNumberPrefixGroup == null || this.orderRequestNumberPrefixGroup.length == 0)
        {
            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
