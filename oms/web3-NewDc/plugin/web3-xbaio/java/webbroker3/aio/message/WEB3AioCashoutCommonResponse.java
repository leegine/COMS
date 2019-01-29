head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込共通レスポンス(WEB3AioCashoutCommonResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込共通レスポンス)<BR>
 * (出金申込共通レスポンスクラス)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCommonResponse extends WEB3GenResponse 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101125L;    
    
    /**
     * (振込先登録区分)<BR>
     * 振込先口座が登録されているかどうかの区分<BR>
     * <BR>
     * 0：未登録<BR>
     * 1：登録済<BR>
     */
    public String transRegistDiv;

    /**
     * (銀行コード)<BR>
     * 振込先となる銀行のコード
     */
    public String bankCode;
    
    /**
     * (銀行名)<BR>
     * 振込先となる銀行の名称
     */
    public String bankName;
    
    /**
     * (支店名)<BR>
     * 振込先となる銀行の支店の名称
     */
    public String branchBankName;
    
    /**
     * (預金区分)<BR>
     * 振込先口座の区分<BR>
     * <BR>
     * 1：普通<BR>
     * 2：当座<BR>
     * 3：その他<BR>
     * 4：貯蓄
     */
    public String depositDiv;
    
    /**
     * (口座番号)<BR>
     * 振込先となる口座番号
     */
    public String accountID;
    
    /**
     * @@roseuid 4158EB61018B
     */
    public WEB3AioCashoutCommonResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }   
}
@
