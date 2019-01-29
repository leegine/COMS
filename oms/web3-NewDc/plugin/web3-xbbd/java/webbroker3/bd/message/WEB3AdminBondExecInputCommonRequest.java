head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecInputCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者新規約定入力共通リクエスト(WEB3AdminBondExecInputCommonRequest.java)
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
 * (管理者新規約定入力共通リクエスト)<BR>
 * 管理者新規約定入力共通リクエストクラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecInputCommonRequest extends WEB3GenRequest
{
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_input_common";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecInputCommonRequest.class);
    
    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード（WEB3）
     */
    public String productCode;
    
    /**
     * (顧客情報)<BR>
     * 顧客情報
     */
    public WEB3AdminBondAccountInfo accountInfo;
    
    /**
     * (注文情報)<BR>
     * 注文情報
     */
    public WEB3AdminBondOrderInfo orderInfo;
    
    /**
     * (約定情報)<BR>
     * 約定情報
     */
    public WEB3AdminBondOrderExecInfo execInfo;
    
    /**
     * (管理者約定入力共通リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 44BDD06F0155
     */
    public WEB3AdminBondExecInputCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １)　@顧客情報チェック <BR>
     * 　@this.顧客情報.validate()をコールする。 <BR>
     * <BR>
     * ２)　@注文情報チェック <BR>
     * 　@this.注文情報.validate()をコールする。 <BR>
     * <BR>
     * ３)　@約定情報チェック <BR>
     * 　@this.約定情報.validate()をコールする。 <BR>
     * <BR>
     * ４)　@銘柄コード（WEB3）チェック <BR>
     * 　@this.銘柄コード（WEB3）==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00079
     * @@throws WEB3BaseException
     * @@roseuid 44BDD06F0194
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //当リクエストデータの整合性チェックを行う。 
        //（ただし、当クラス内で完結する簡易チェックのみとする。） 
        //１)　@顧客情報チェック 
        //this.顧客情報.validate()をコールする。 
        this.accountInfo.validate();
        
        //２)　@注文情報チェック 
        //this.注文情報.validate()をコールする。 
        this.orderInfo.validate();
        
        //３)　@約定情報チェック
        //this.約定情報.validate()をコールする。 
        this.execInfo.validate();
        
        //４)　@銘柄コード（WEB3）チェック 
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

    public WEB3GenResponse createResponse()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
@
