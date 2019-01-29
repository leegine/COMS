head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyTotalUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付金額合計(WEB3MutualFixedBuyTotalUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 徐宏偉 (中訊) 新規作成
Revesion History : 2008/07/08 王志葵 (中訊) 仕様変更 モデルNo.604
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (投信定時定額買付金額合計)<BR>
 * 投信定時定額買付金額合計<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyTotalUnit extends Message
{
    /**
     * (月々合計)<BR>
     * 月々合計<BR>
     */
    public String monthlyBATotal;
    
    /**
     * (積み増し合計)<BR>
     * 積み増し合計<BR>
     */
    public String increaseBATotal;
    
    /**
     * (口座引落年月)<BR>
     * 口座引落年月<BR>
     */
    public Date debitAccountYM;

    /**
     * (確定引落金額合計（積み増し）)<BR>
     * 確定引落金額合計（積み増し）<BR>
     */
    public  String definiteIncreaseBATotal;

    /**
     * (投信定時定額買付金額合計のインスタンスを生成する。)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyTotalUnit()
    {
    }
}
@
