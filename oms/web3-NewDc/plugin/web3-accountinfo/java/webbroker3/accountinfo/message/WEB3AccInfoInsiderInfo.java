head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoInsiderInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 内部者情報メッセージ(WEB3AccInfoInsiderInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.accountinfo.define.WEB3RegStateDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (内部者情報)<BR>
 * 内部者情報メッセージ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoInsiderInfo extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoInsiderInfo.class);
        
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (関係コード)<BR>
     * 関係コード<BR>
     * <BR>
     * 01：役員<BR>
     * 02：役員配偶者<BR>
     * 03：役員血族<BR>
     * 04：主要株主<BR>
     * 05：大株主<BR>
     * 06：関係会社<BR>
     * 07：幹部職員<BR>
     * 08：関係役員配偶者<BR>
     * 09：上場会社等の親会社の役職員<BR>
     * 10：上場会社等の子会社の役職員<BR>
     * <BR>
     */
    public String relationCode;
    
    /**
     * (役員名)<BR>
     * 役員名<BR>
     */
    public String executive;
    
    /**
     * (役職名)<BR>
     * 役職名<BR>
     */
    public String position;
    
    /**
     * (登録状況区分)<BR>
     * 登録状況区分<BR>
     * <BR>
     * 0：　@チェックしない<BR>
     * 1：　@警告のみ<BR>
     * 2：　@注文停止<BR>
     * <BR>
     * ※内部者マスターテーブル.登録状況区分<BR>
     * <BR>
     */
    public String registStateDiv;
    
    /**
     * @@roseuid 418F39F2035B
     */
    public WEB3AccInfoInsiderInfo() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@銘柄コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00079<BR>
     * <BR>
     * ２）　@登録状況区分のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01137<BR>
     * 　@２－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01138<BR>  
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415D045C02BC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
       /* 
        * １）　@銘柄コードのチェック<BR>
        * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_00079<BR>
        */
        if(productCode == null || "".equals(productCode))
        {
            log.debug("[銘柄コード] = " + productCode);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00079, 
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コードが未指定です");
        }
        
       /* 
        * ２）　@登録状況区分のチェック<BR>
        * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01137<BR>
        */
        if(registStateDiv == null || "".equals(registStateDiv))
        {
            log.debug("[登録状況区分] = " + registStateDiv);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01137, 
                this.getClass().getName() + STR_METHOD_NAME,
                "登録状況区分未入力");
        }
        
       /* 
        * 　@２－２）　@不正なコード値の場合、例外をスローする。 <BR>
        *         class: WEB3BusinessLayerException<BR>
        *         tag:   BUSINESS_ERROR_01138<BR>  
        */
        if(!(WEB3RegStateDivDef.CHECK).equals(registStateDiv)       &&
            !(WEB3RegStateDivDef.STOP_ORDER).equals(registStateDiv) &&
            !(WEB3RegStateDivDef.WARNING).equals(registStateDiv))
        {
            log.debug("[登録状況区分] = " + registStateDiv);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01138, 
                this.getClass().getName() + STR_METHOD_NAME,
                "登録状況区分不正なコード値");
        }
    }
}
@
