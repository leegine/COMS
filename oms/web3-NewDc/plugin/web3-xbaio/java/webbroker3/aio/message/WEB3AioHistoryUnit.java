head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴明細(WEB3AioHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (入出庫履歴明細)<BR>
 * 入出庫履歴明細クラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3AioHistoryUnit extends Message
{
    
    /**
     * (受渡日)<BR>
     * 受渡日
     */
    public Date deliveryDate;
    
    /**
     * (商品グループ)<BR>
     * 商品グループ<BR>
     * <BR>
     * A： 株式<BR>
     * B： 外国株式<BR>
     * C： 投信<BR>
     * D： 債券
     */
    public String productGroup;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName;
    
    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 0： 一般<BR>
     * 1： 特定
     */
    public String taxType;
    
    /**
     * (入出庫グループ)<BR>
     * 入出庫グループ<BR>
     * <BR>
     * A： 出庫<BR>
     * B： 入庫<BR>
     * C： 出庫(株式分割)<BR>
     * D： 入庫(株式分割)<BR>
     * E： 出庫(有償増資)<BR>
     * F： 入庫(有償増資)<BR>
     * G： 出庫(額面引下)<BR>
     * H： 入庫(額面引下)<BR>
     * I： 出庫(株式併合)<BR>
     * J： 入庫(株式併合)<BR>
     * K： 出庫(会社合併)<BR>
     * L： 入庫(会社合併)<BR>
     * M： 出庫(一斉転換)<BR>
     * N： 入庫(一斉転換)
     */
    public String inputOutputDetailGroup;
    
    /**
     * (数量)<BR>
     * 数量
     */
    public String quantity;
    
    /**
     * (数量単位)<BR>
     * 数量単位<BR>
     * <BR>
     * A： 株<BR>
     * B： 口
     */
    public String quantityUnit;
    
    /**
     * (単価)<BR>
     * 単価
     */
    public String price;
    
    /**
     * (入出庫履歴明細)<BR>
     * コンストラクタ
     * @@roseuid 41B6DB89013F
     */
    public WEB3AioHistoryUnit() 
    {
     
    }
}
@
