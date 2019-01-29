head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToOrderStopStateUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文停止状況(WEB3AdminToOrderStopStateUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 張　@芳(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文停止状況)<BR>
 * 注文停止状況クラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToOrderStopStateUnit extends Message 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToOrderStopStateUnit.class);
    
    /**
     * (条件注文種別)<BR>
     * 条件注文種別<BR>
     * <BR>
     * 1：　@連続注文<BR>
     * 2：　@OCO注文<BR>
     * 3：　@IFD注文<BR>
     * 4：　@逆指値注文<BR>
     * 5：　@W指値注文<BR>
     */
    public String triggerOrderType;
    
    /**
     * (停止フラグ)<BR>
     * 停止フラグ<BR>
     * <BR>
     * false：　@取扱可能<BR>
     * true：　@停止中<BR>
     */
    public boolean stopFlag = false;
    
    /**
     * (変更後停止フラグ)<BR>
     * 変更後停止フラグ<BR>
     * <BR>
     * false：　@取扱可能<BR>
     * true：　@停止中<BR>
     * <BR>
     * ※変更入力後の停止フラグをセットする。<BR>
     * 　@変更なしの場合は、this.停止フラグと同じ値をセットする。<BR>
     */
    public boolean aftChangeStopFlag = false;
    
    /**
     * (注文停止状況)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 440FD84C013B
     */
    public WEB3AdminToOrderStopStateUnit() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@条件注文種別チェック<BR>
     * 　@１－１）　@this.条件注文種別に下記の項目以外が<BR>
     * 　@　@　@　@設定されていた場合、<BR>
     * 　@　@　@　@「条件注文種別が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・"連続注文" <BR>
     * 　@　@　@　@・"OCO注文"<BR>
     * 　@　@　@　@・"IFD注文"<BR>
     * 　@　@　@　@・"逆指値注文" <BR>
     * 　@　@　@　@・"W指値注文"<BR>
     *         class : WEB3BusinessLayerException<BR>
     *         tag : BUSINESS_ERROR_02397<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44113A0C003C
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@条件注文種別チェック
        // 　@１－１）this.条件注文種別に下記の項目以外が設定されていた場合、
        // 　@　@　@　@ 「条件注文種別が未定義の値」の例外をスローする。 
        // 　@　@　@　@  ・"連続注文" 
        // 　@　@　@　@  ・"OCO注文"
        // 　@　@　@　@  ・"IFD注文"
        // 　@　@　@　@  ・"逆指値注文" 
        // 　@　@　@　@  ・"W指値注文"
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_02397
        if (!(WEB3TriggerOrderTypeDef.SUCC.equals(this.triggerOrderType)
                || WEB3TriggerOrderTypeDef.OCO.equals(this.triggerOrderType)
                || WEB3TriggerOrderTypeDef.IFD.equals(this.triggerOrderType)
                || WEB3TriggerOrderTypeDef.STOP.equals(this.triggerOrderType)
                || WEB3TriggerOrderTypeDef.W_LlIMIT.equals(this.triggerOrderType)))
        {
            log.debug("条件注文種別が未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02397,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件注文種別が未定義の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
