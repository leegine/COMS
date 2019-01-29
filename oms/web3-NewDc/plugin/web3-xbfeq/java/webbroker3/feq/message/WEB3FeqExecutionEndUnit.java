head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionEndUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来終了明細(WEB3FeqExecutionEndUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
                 : 2005/08/02 王煜 (中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (外国株式出来終了明細)<BR>
 * 外国株式出来終了明細クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqExecutionEndUnit extends Message 
{
    
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (市場名)<BR>
     * 市場名
     */
    public String marketName;
    
    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0：　@未処理<BR>
     * 1：　@処理済<BR>
     * 9：　@エラー
     */
    public String executionEndDiv;
    
    /**
     * (前回発注日)<BR>
     * 前回発注日
     */
    public Date previousOrderBizDate;
    
    /**
     * (前回出来終了日時)<BR>
     * 前回出来終了日時
     */
    public Date previousExecutionEndTime;
    
    /**
     * (外国株式出来終了明細)<BR>
     * コンストラクタ
     * @@roseuid 4202079102E4
     */
    public WEB3FeqExecutionEndUnit() 
    {
     
    }
}
@
