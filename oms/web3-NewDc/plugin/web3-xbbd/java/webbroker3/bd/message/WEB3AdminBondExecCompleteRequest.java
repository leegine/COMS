head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.52.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者新規約定完了リクエスト(WEB3AdminBondExecCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者新規約定完了リクエスト)<BR>
 * 管理者新規約定完了リクエストクラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecCompleteRequest extends WEB3AdminBondExecInputCommonRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecCompleteRequest.class);
   
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_complete";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String id;
    
    /**
     * (入力時発注日)<BR>
     * 入力時発注日
     */
    public Date inpOrderDate;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 44E3421B034B
     */
    public WEB3AdminBondExecCompleteRequest() 
    {
     
    }
      
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドをコールする。<BR>
     * 　@　@<BR>
     * ２)　@注文IDチェック <BR>
     * 　@this.注文ID==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00600<BR>
     * <BR>
     * 　@this.注文IDが数値でない場合、例外をスローす<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01476<BR>
     * <BR>
     * ３)　@入力時発注日チェック <BR>
     * 　@this.入力時発注日==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00406<BR>
     * <BR>
     * ４)　@暗証番号チェック <BR>
     * 　@this.暗証番号==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01090
     * @@throws WEB3BaseException 
     * @@roseuid 44BDD80C00D2
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //当リクエストデータの整合性チェックを行う。 
        //（ただし、当クラス内で完結する簡易チェックのみとする。） 
        //１）スーパークラスのvalidate()メソッドをコールする。
        super.validate();

        //２)　@注文IDチェック 
        //this.注文ID==nullの場合、例外をスローする。 
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        
        //this.注文IDが数値でない場合、例外をスローす
        if (!WEB3StringTypeUtility.isNumber(this.id))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01476,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが数字以外です。");
        }

        //３)　@入力時発注日チェック 
        //this.入力時発注日==nullの場合、例外をスローする。 
        if (this.inpOrderDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }

        //４)　@暗証番号チェック 
        //this.暗証番号==nullの場合、例外をスローする。
        if (this.password == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "暗証番号が未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者新規約定完了レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     * @@roseuid 44BED9580271
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondExecCompleteResponse(this);
    }
    
}
@
