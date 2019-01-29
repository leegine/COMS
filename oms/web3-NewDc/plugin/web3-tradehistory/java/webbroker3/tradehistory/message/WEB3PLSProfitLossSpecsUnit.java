head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細情報(WEB3PLSProfitLossSpecsUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 範慧琴 (中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (損益明細情報)<BR>
 * 損益明細情報クラス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsUnit extends Message 
{
    /**
     * (損益明細ID)<BR>
     */
    public String profitLossSpecId = null;
    
    /**
     * (作業年月日)<BR>
     * 作業年月日<BR>
     * <BR>
     * ※画面非表示項目<BR>
     */
    public Date workDate = null;
    
    /**
     * (損益明細レコード区分)<BR>
     * 損益明細レコード区分<BR>
     * <BR>
     * 00：　@繰越残高レコード<BR>
     * 10：　@残高レコード<BR>
     * 20：　@明細レコード<BR>
     * 21：　@入出金レコード<BR>
     * <BR>
     * ※00：繰越残高レコードについては、PR層でのみ使用。<BR>
     * 　@(DBには登録されない。)<BR>
     */
    public String prolossRecDiv = null;
    
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
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName = null;
    
    /**
     * (商品)<BR>
     * 商品<BR>
     * <BR>
     * 10：　@日株売買<BR>
     * 11：　@ミニ株売買<BR>
     * 12：　@ミニ株権利<BR>
     * 15：　@信用取引<BR>
     * 30：　@日債売買<BR>
     * 40：　@外株売買<BR>
     * 42：　@外株権利<BR>
     */
    public String fundType = null;
    
    /**
     * (適用コード)<BR>
     * 適用コード<BR>
     * <BR>
     * 10：　@信用決済<BR>
     * 11：　@確定配当<BR>
     * 12：　@預り配当<BR>
     * 13：　@権利受払金<BR>
     * 20：　@ミニ株売買<BR>
     * 21：　@ミニ端株売却<BR>
     * 22：　@ミニ株有償増資<BR>
     * 31：　@外株権利売却<BR>
     * 上記以外：　@その他<BR>
     */
    public String applicationCode = null;
    
    /**
     * (長短等区分)<BR>
     * 長短等区分<BR>
     * <BR>
     * 1：　@一般上場<BR>
     * 2：　@特定信用<BR>
     * 3：　@長期上場<BR>
     * 4：　@長期特定<BR>
     */
    public String termDiv = null;
    
    /**
     * (返還金区分)<BR>
     * 返還金区分<BR>
     * <BR>
     * 1：　@返還金<BR>
     * 1以外：　@その他<BR>
     */
    public String returnDiv = null;
    
    /**
     * (備考)<BR>
     * 備考<BR>
     * <BR>
     * 1：　@計算済<BR>
     * 1以外：　@その他<BR>
     */
    public String remark = null;
    
    /**
     * (数量)<BR>
     * 数量<BR>
     */
    public String quantity = null;
    
    /**
     * (譲渡日)<BR>
     * 譲渡日<BR>
     */
    public Date passDate = null;
    
    /**
     * (譲渡金額)<BR>
     * 譲渡金額<BR>
     */
    public String passAmount = null;
    
    /**
     * (取得日)<BR>
     * 取得日<BR>
     */
    public Date getDate = null;
    
    /**
     * (取得費等)<BR>
     * 取得費等<BR>
     */
    public String getAmount = null;
    
    /**
     * (損益)<BR>
     * 損益<BR>
     */
    public String prolossAmount = null;
    
    /**
     * (累計損益)<BR>
     * 累計損益<BR>
     */
    public String totalProlossAmount = null;
    
    /**
     * (課税対象額)<BR>
     * 課税対象額<BR>
     */
    public String taxableAmount = null;
    
    /**
     * (徴収税額)<BR>
     * 徴収税額<BR>
     */
    public String collectTaxAmount = null;
    
    /**
     * (徴収税額(国税))<BR>
     * 徴収税額(国税)<BR>
     */
    public String collectTaxNAmount = null;
    
    /**
     * (徴収税額(地方税))<BR>
     * 徴収税額(地方税)<BR>
     */
    public String collectTaxLAmount = null;
    
    /**
     * (損益明細情報)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsUnit
     * @@roseuid 416E02580312
     */
    public WEB3PLSProfitLossSpecsUnit() 
    {
     
    }
}
@
