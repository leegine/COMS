head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualLapseMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインリクエスト(WEB3AdminIfoManualLapseMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効メインリクエスト)<BR>
 * 管理者・先物OP手動失効メインリクエストクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */

public class WEB3AdminIfoManualLapseMainRequest extends WEB3BackRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualLapseMainRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "adminIfo_manualLapseMain";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200701311315L;
    
    /**
     * (証券会社コード)<BR>
     * 証券会社コード
     */
    public String institutionCode;
    
    /**
     * (スレッドNo)<BR>
     * スレッドNo
     */
    public Long threadNo;
    
    /**
     * (From口座ID)<BR>
     * From口座ID
     */
    public Long accountIdFrom;
    
    /**
     * (To口座ID)<BR>
     * To口座ID
     */
    public Long accountIdTo;
    
    /**
     * (失効対象注文条件)
     */
    public WEB3AdminIfoLapseTargetOrderCondition ifoLapseTargetOrderCondition;
    
    /**
     * (管理者・先物OP手動失効メインリクエスト)<BR>
     * コンストラクタ
     * @@return WEB3AdminIfoManualLapseMainRequest
     * @@roseuid 446967550206
     */
    public WEB3AdminIfoManualLapseMainRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@１－１）　@this.証券会社コード == nullの場合、<BR>
     * 　@　@「証券会社コードが未指定です。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
     * ２）　@スレッドNoチェック<BR>
     * 　@２－１）　@this.スレッドNo == nullの場合、<BR>
     * 　@　@「スレッド番号の指定なし。」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01974<BR>
     * <BR>
     * ３）　@From口座IDチェック<BR>
     * 　@３－１）　@this.From口座ID == nullの場合、<BR>
     * 　@　@「From口座IDが未入力」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02421<BR>
     * <BR>
     * ４）　@To口座IDチェック<BR>
     * 　@４－１）　@this.To口座ID == nullの場合、<BR>
     * 　@　@「To口座ID（至）が未入力」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02422<BR>
     * <BR>
     * ５）　@失効対象注文条件チェック<BR>
     * 　@５－１）　@this.失効対象注文条件 == nullの場合、<BR>
     * 　@　@「失効対象注文条件が未入力」の例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02420<BR>
     * <BR>
     * 　@５－２）　@this.失効対象注文条件.validate()をコールする。<BR>
     *<BR> 
     * @@throws WEB3BaseException
     * @@roseuid 4469235C0167
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@証券会社コードチェック 
        //　@１－１）　@this.証券会社コード == nullの場合、 
        //　@　@「証券会社コードが未指定です。」の例外をスローする。 
        if (this.institutionCode == null) 
        {
            log.debug("証券会社コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }
        
        //２）　@スレッドNoチェック 
        //　@２－１）　@this.スレッドNo == nullの場合、 
        //　@　@「スレッド番号の指定なし。」の例外をスローする。 
        if (this.threadNo == null) 
        {
            log.debug("スレッド番号の指定なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "スレッド番号の指定なし。");
        }
        
        //３）　@From口座IDチェック 
        //　@３－１）　@this.From口座ID == nullの場合、 
        //　@　@「From口座IDが未入力」の例外をスローする。 
        if (this.accountIdFrom == null) 
        {
            log.debug("From口座IDが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02421,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "From口座IDが未入力です。");
        }
        
        //４）　@To口座IDチェック 
        //　@４－１）　@this.To口座ID == nullの場合、 
        //　@　@「To口座ID（至）が未入力」の例外をスローする。 
        if (this.accountIdTo == null) 
        {
            log.debug("To口座ID（至）が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02422,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "To口座ID（至）が未入力です。");
        }
        
        //５）　@失効対象注文条件チェック 
        //　@５－１）　@this.失効対象注文条件 == nullの場合、 
        //　@　@「失効対象注文条件が未入力」の例外をスローする。 
        if (this.ifoLapseTargetOrderCondition == null) 
        {
            log.debug("失効対象注文条件が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02420,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "失効対象注文条件が未入力です。");
        }
        
        //　@５－２）　@this.失効対象注文条件.validate()をコールする。
        this.ifoLapseTargetOrderCondition.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 管理者・株式手動失効メインレスポンオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6301A2
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AdminIfoManualLapseMainResponse(this);
    }
}
@
