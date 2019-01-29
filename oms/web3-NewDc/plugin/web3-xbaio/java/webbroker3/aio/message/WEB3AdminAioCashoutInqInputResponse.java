head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ入力レスポンスクラス(WEB3AdminAioCashoutInqInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2006/09/04 車進 (中訊) 式樣變更 モデルNo.629
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込問合せ入力レスポンス)<BR>
 * 出金申込問合せ入力レスポンスクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;     
        
    /**
     * (部店コード)<BR>
     * デフォルト表示となる部店コード
     */
    public String branchCode;
    
    /**
     * (外貨コード一覧)<BR>
     * 外貨コード一覧
     */
    public String[] foreignCurrencyCodeList;
      
    /**
     * @@roseuid 4158EB66008E
     */
    public WEB3AdminAioCashoutInqInputResponse(WEB3AdminAioCashoutInqInputRequest l_request) 
    {
        super(l_request);
    }
}
@
