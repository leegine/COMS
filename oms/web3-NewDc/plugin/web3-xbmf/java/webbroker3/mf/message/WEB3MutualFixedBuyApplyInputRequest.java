head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込入力リクエスト(WEB3MutualFixedBuyApplyInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (投信定時定額買付新規申込入力リクエスト)<BR>
 * 投信定時定額買付新規申込入力リクエスト<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261701L;
  
    /**
     * (投信定時定額買付新規申込入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     */   
    public WEB3MutualFixedBuyApplyInputRequest()
    {
    }
   
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信定時定額買付新規申込入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualFixedBuyApplyInputResponse(this);
    } 
}
@
