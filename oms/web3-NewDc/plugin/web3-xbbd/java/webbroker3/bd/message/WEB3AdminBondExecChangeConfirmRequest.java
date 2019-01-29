head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.54.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者約定変更確認リクエスト(WEB3AdminBondExecChangeConfirmRequest.java)
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

/**
 * (管理者約定変更確認リクエスト)<BR>
 * 管理者約定変更確認リクエスト
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondExecChangeConfirmRequest extends WEB3AdminBondExecChangeCommonRequest 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecChangeConfirmRequest.class);
    
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_change_confirm";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (入力時発注日)<BR>
     * 入力時発注日
     */
    public Date inpOrderDate;
    
    /**
     * @@roseuid 44E3363500FA
     */
    public WEB3AdminBondExecChangeConfirmRequest() 
    {
     
    }
    
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * 　@　@<BR>
     * １）スーパークラスのvalidate()メソッドをコールする。<BR>
     * <BR>
     * ２)　@入力時発注日チェック <BR>
     * 　@this.入力時発注日==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00406<BR>
     * <BR>
     * ３)約定数量チェック <BR>
     * 　@this.約定情報.約定数量 == nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02634<BR>
     * <BR>
     * ４)約定単価チェック <BR>
     * 　@this.約定情報.約定単価 == nullの場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02021
     * @@throws WEB3BaseException 
     * @@roseuid 44BDE0850249
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //当リクエストデータの整合性チェックを行う。
        //（ただし、当クラス内で完結する簡易チェックのみとする。） 
        //１）スーパークラスのvalidate()メソッドをコールする。
        super.validate();
        
        //２)　@入力時発注日チェック
        //this.入力時発注日==nullの場合、例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_00406
        if (this.inpOrderDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注日が未指定です。");
        }
        
        //３)約定数量チェック 
        //this.約定情報.約定数量 == nullの場合、例外をスローする。
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_02025
        if (this.execInfo.execFaceAmount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02634,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が未指定です。");
        }
        
        //４)約定単価チェック 
        //this.約定情報.約定単価 == nullの場合、例外をスローする。 
        //class:　@WEB3BusinessLayerException
        //tag:　@　@BUSINESS_ERROR_02021
        if (this.execInfo.execPrice == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が未入力です。");
        }
        
    }
    
    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者約定変更確認レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     * @@roseuid 44BEDBBF00BB
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminBondExecChangeConfirmResponse(this);
    }
}
@
