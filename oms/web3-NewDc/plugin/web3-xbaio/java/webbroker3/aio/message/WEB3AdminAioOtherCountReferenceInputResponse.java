head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : その他件数照会入力レスポンス(WEB3AdminAioOtherCountReferenceInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 韋念瓊(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (その他件数照会入力レスポンス)<BR>
 * その他件数照会入力レスポンスクラス<BR>
 * 件数照会を行う対象商品一覧及び<BR>
 * 商品区分が金融機@関連携の場合、決済機@関情報を返却する。<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (商品一覧) <BR>
     * 照会対象商品の商品コードを返却する。<BR>
     * <BR>
     * ・商品コード <BR>
     * 1:オンライン入金 <BR>
     * 2:為替保証金 <BR>
     * 3:外国株式（外部連携）<BR>
     */
    public String[] commodityDivList;
    
    /**
     * (決済機@関一覧) <BR>
     * 商品区分が金融機@関連携の場合、提携決済機@関情報を返却する。<BR>
     */
    public WEB3AioSettleInstitutionUnit[] settleInstitutionUnits;
    
    /**
     * @@roseuid 423552AB00FA
     */
    public WEB3AdminAioOtherCountReferenceInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminAioOtherCountReferenceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
