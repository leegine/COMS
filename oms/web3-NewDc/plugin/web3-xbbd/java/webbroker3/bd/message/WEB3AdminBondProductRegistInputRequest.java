head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄登録入力リクエスト(WEB3AdminBondProductRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者債券銘柄登録入力リクエスト)<BR>
 * 管理者債券銘柄登録リクエストクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistInputRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegistInputRequest.class);
    
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_input";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;
    
    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3AdminBondProductRegistInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １)　@IDチェック <BR>
     * 　@this.銘柄コード（WEB3）==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00079
     * @@throws WEB3BaseException
     * @@roseuid 44BB31D601C9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う。
        //ただし、当クラス内で完結する簡易チェックのみとする。） 
        //１)　@IDチェック 
        //this.銘柄コード（WEB3）==nullの場合、例外をスローする。
        if (this.productCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 債券銘柄条件登録入力レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     * @@roseuid 44BB30ED0126
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondProductRegistInputResponse(this);
    }
}
@
