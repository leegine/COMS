head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設確認リクエスト(WEB3FEqConAccountOpenConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座開設確認リクエスト)<BR>
 * 外株口座開設確認リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_account_open_confirm";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;
    
    /**
     * (外株用暗証番号)<BR>
     * 外株用暗証番号
     */
    public String feqPassword1;
    
    /**
     * (外株用暗証番号（確認用）)<BR>
     * 外株用暗証番号（確認用）
     */
    public String feqPassword2;
    
    /**
     * @@roseuid 423552A9030D
     */
    public WEB3FEqConAccountOpenConfirmRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座開設確認レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FEqConAccountOpenConfirmResponse(this);
    }
}
@
