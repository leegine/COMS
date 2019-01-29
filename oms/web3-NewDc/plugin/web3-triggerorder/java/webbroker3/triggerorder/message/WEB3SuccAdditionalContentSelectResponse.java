head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccAdditionalContentSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追加内容選択レスポンス(WEB3SuccAdditionalContentSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (追加内容選択レスポンス)<BR>
 * 追加内容選択レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccAdditionalContentSelectResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_additionalContentSelect";
    
    /**
     * (（親注文）注文ID)<BR>
     * （親注文）注文ID。<BR>
     */
    public String parentOrderId;
    
    /**
     * (連続注文取引一覧)<BR>
     * 連続注文取引情報の配列<BR>
     */
    public WEB3SuccTradingInfo[] succTradingList;
    
    /**
     * @@roseuid 43489604034B
     */
    public WEB3SuccAdditionalContentSelectResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccAdditionalContentSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
