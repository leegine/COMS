head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告履歴明細メッセージデータ(WEB3IPODemandHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>049
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * IPO申告履歴明細メッセージデータクラス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IPODemandHistoryUnit extends Message
{
    
    /**
     * 申告履歴No
     */
    public String demandHistoryId;
    
    /**
     * 受付日時
     */
    public Date receptionDate;
    
    /**
     * IPO処理内容区分<BR>
     * <BR>
     * 　@1：　@申告<BR>
     * 　@2：　@訂正<BR>
     * 　@3：　@取消
     */
    public String ipoProcessingType;
    
    /**
     * 申告数量
     */
    public String demandQuantity;
    
    /**
     * 申告価格区分<BR>
     * 　@0：成行<BR>
     * 　@1：指値
     */
    public String demandPriceDiv;
    
    /**
     * 申告価格
     */
    public String demandPrice;
    
    /**
     * IPO受付結果区分<BR>
     * <BR>
     * 　@1：　@有効<BR>
     * 　@2：　@無効
     */
    public String ipoAcceptedResultDiv;
    
    /**
     * 最新有効履歴フラグ<BR>
     * <BR>
     * true：　@最新有効<BR>
     * false：　@最新有効でない
     */
    public boolean newHistoryFlag;
    
    /**
     * 表示用単位区分<BR>
     * <BR>
     * １： 株数（株）<BR>
     * ２： 口数（口）
     */
    public String displayUnitDiv;
    
    /**
     * 仮条件区分<BR>
     * <BR>
     * 1:円<BR>
     * 2:％
     */
    public String temporaryConditionDiv;
       
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40DC06380396
     */
    public WEB3IPODemandHistoryUnit() 
    {
     
    }
}
@
