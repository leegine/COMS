head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当詳細(WEB3IPOLotDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 譚漢江 (中訊) 新規作成
*/

package webbroker3.ipo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (管理者IPO抽選割当詳細)<BR>
 * 管理者IPO抽選割当詳細データクラス。
 *                                                                
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3IPOLotDetailUnit extends Message
{
    
    /**
     * (抽選シーケンス)<BR>
     * IPO抽選シーケンス。<BR>
     */
    public String lotSequence;
    
    /**
     * 部店コード
     */
    public String branchCode;
    
    /**
     * 抽選割当総枠数量
     */
    public String lotAllotTotalQuantity;
    
    /**
     * (割当上限数量)<BR>
     * 割当上限数量（１ループあたり）
     */
    public String allotLimitQuantity;
    
    /**
     * 割当済み数量
     */
    public String allotFinLimitQuantity;
    
    /**
     * 割当対象顧客数
     */
    public String allotTargetNumber;
    
    /**
     * (割当最大値)<BR>
     * １顧客への割当最大値。
     */
    public String allotMax;
    
    /**
     * (割当最小値)<BR>
     * １顧客への割当最小値。
     */
    public String allotMin;
    
    /**
     * 補欠割当総枠数量
     */
    public String waitingAllotTotalQuantity;
    
    /**
     * (補欠割当上限数量)<BR>
     * 補欠割当上限数量（１ループあたり）。
     */
    public String waitingAllotLimitQuantity;
    
    /**
     * 補欠割当済み数量
     */
    public String waitingAllotFinLimitQuantity;
    
    /**
     * 補欠割当対象顧客数
     */
    public String waitingAllotTargetNumber;
    
    /**
     * (補欠割当最大値)<BR>
     * １顧客への補欠割当最大値。
     */
    public String waitingAllotMax;
    
    /**
     * (補欠割当最小値)<BR>
     * １顧客への補欠割当最小値。
     */
    public String waitingAllotMin;
    
    /**
     * (処理日時)<BR>
     * 抽選確定処理日時。
     */
    public Date transactionDate;
    
    /**
     * (処理状態)<BR>
     * 抽選処理状態。<BR>
     * <BR>
     * １：確定処理中<BR>
     * ２：確定完了<BR>
     * ９：異常終了
     */
    public String transactionState;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40EE26B50009
     */
    public WEB3IPOLotDetailUnit() 
    {
     
    }
}
@
