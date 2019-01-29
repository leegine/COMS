head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者約定変更入力リクエスト(WEB3AdminBondExecChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者約定変更入力リクエスト)<BR>
 * 管理者約定変更入力リクエスト
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecChangeInputRequest extends WEB3AdminBondExecChangeCommonRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecChangeInputRequest.class);
    
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_change_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * @@roseuid 44E33635031C
     */
    public WEB3AdminBondExecChangeInputRequest() 
    {
     
    }
       
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １)　@注文IDチェック <BR>
     * 　@this.注文ID==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00600<BR>
     * <BR>
     * 　@this.注文IDが数値でない場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01476
     * @@throws WEB3BaseException 
     * @@roseuid 44BDDEFC0060
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //this.注文ID==nullの場合、例外をスローする。
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        
        //this.注文IDが数値でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.id))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが数字以外です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者約定変更入力レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     * @@roseuid 44BDDEFC0041
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondExecChangeInputResponse(this);
    }
}
@
