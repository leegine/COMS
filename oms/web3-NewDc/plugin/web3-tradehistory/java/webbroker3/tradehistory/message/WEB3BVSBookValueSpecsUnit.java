head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 簿価単価明細情報(WEB3BVSBookValueSpecsUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/08 賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (簿価単価明細情報)<BR>
 * 簿価単価明細情報クラス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0 
 */
public class WEB3BVSBookValueSpecsUnit extends Message 
{
    /**
     * (簿価単価明細ＩＤ)<BR>
     * 簿価単価明細ＩＤ<BR>
     */
    public String bookValueSpecId = null;
    
    /**
     * (簿価単価明細レコード区分)<BR>
     * 簿価単価明細レコード区分<BR>
     * <BR>
     * 1：　@残高レコード<BR>
     * 2：　@明細レコード<BR>
     */
    public String bookvalRecDiv = null;
    
    /**
     * (計算日)<BR>
     * 計算日<BR>
     */
    public Date calcDate = null;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate = null;
    
    /**
     * (売買)<BR>
     * 売買<BR>
     * <BR>
     * 1：　@売( or 現渡 or 出庫)<BR>
     * 2：　@買( or 現引 or 入庫)<BR>
     * 上記以外：　@その他<BR>
     */
    public String buySellDiv = null;
    
    /**
     * (取引)<BR>
     * 取引<BR>
     * <BR>
     * ※定義値については、画面定義書<BR>
     * 「簿価単価明細照会(簿価単価明細照会).xls」<BR>
     * 参照<BR>
     */
    public String tradeType = null;
    
    /**
     * (数量)<BR>
     * 数量<BR>
     */
    public String quantity = null;
    
    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice = null;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     * <BR>
     * A0：　@USドル<BR>
     * A3：　@香港ドル<BR>
     * Z4：　@ユーロ<BR>
     * 上記以外：　@円<BR>
     */
    public String ccyCode = null;
    
    /**
     * (受渡金額)<BR>
     * 受渡金額<BR>
     */
    public String deliveryAmount = null;
    
    /**
     * (損益)<BR>
     * 損益<BR>
     */
    public String prolossAmount = null;
    
    /**
     * (簿価計上)<BR>
     * 簿価計上<BR>
     */
    public String calcAmount = null;
    
    /**
     * (簿価金額)<BR>
     * 簿価金額<BR>
     */
    public String bookAmount = null;
    
    /**
     * (残数量)<BR>
     * 残数量<BR>
     */
    public String balQuantity = null;
    
    /**
     * (簿価単価)<BR>
     * 簿価単価<BR>
     */
    public String bookPrice = null;
    
    /**
     * (仮残区分)<BR>
     * 仮残区分<BR>
     * <BR>
     * 0：　@非仮残高<BR>
     * 1：　@仮残高<BR>
     */
    public String debitBalDiv = null;
    
    /**
     * (簿価単価明細情報)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsUnit
     * @@roseuid 416E6E94015F
     */
    public WEB3BVSBookValueSpecsUnit() 
    {
     
    }
}
@
