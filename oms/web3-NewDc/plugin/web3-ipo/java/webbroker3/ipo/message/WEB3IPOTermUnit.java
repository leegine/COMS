head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOTermUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO期間指定メッセージデータ(WEB3IPOTermUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO期間指定メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPOTermUnit extends Message
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOTermUnit.class);
        
    /**
     * 営業日上限
     */
    public String bizDateUpper;
    
    /**
     * 営業日下限
     */
    public String bizDateLower;
    
    /**
     * 開始日時
     */
    public Date startDate;
    
    /**
     * 終了日時
     */
    public Date endDate;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40C6763F030A
     */
    public WEB3IPOTermUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@this.営業日上限のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00433<BR>
     * <BR>
     * ２）　@this.営業日下限のチェック<BR>
     * 　@※ 入力がある場合、以下のチェックを行う。<BR>
     * 　@１－１）　@数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00490<BR>
     * @@roseuid 40C865D3036E
     */
    public void validate()  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("リクエストデータの整合性チェックを行う。: ENTER");
        
        log.debug("１）　@this.営業日上限のチェック: ENTER");
        //１）　@this.営業日上限のチェック<BR>
        if (this.bizDateUpper != null & !"".equals(this.bizDateUpper))
        {
            if (!WEB3StringTypeUtility.isNumber(this.bizDateUpper))
            {
                //　@１－１）　@数値でない場合、例外をスローする。<BR>
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00433, getClass().getName() + "validate");
            }
        }
        log.debug("１）　@this.営業日上限のチェック: END");
        
        log.debug("２）　@this.営業日下限のチェック: ENTER");
        if (this.bizDateLower != null & !"".equals(this.bizDateLower))
        {
            if (!WEB3StringTypeUtility.isNumber(this.bizDateLower))
            {
                //　@１－１）　@数値でない場合、例外をスローする。<BR>
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00490, getClass().getName() + "validate");
            }
        }
        log.debug("２）　@this.営業日下限のチェック: END");
        
        log.debug("リクエストデータの整合性チェックを行う。: END");
        log.exiting(STR_METHOD_NAME);
    }
}
@
