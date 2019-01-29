head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualDisplayOrderChangeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序更新値(WEB3MutualDisplayOrderChangeUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (投信管理者銘柄表示順序更新値)<BR>
 * 投資信託管理者銘柄表示順序更新値データクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualDisplayOrderChangeUnit extends Message 
{
    /**
     * (表示順)<BR>
     *  表示順
     */
    public String displayOrder;
    
    /**
     * (銘柄コード)<BR>
     *  銘柄コード
     */
    public String mutualProductCode;
    
    /**
     * (投信管理者銘柄表示順序更新値)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 4153CDE60382
     */
    public WEB3MutualDisplayOrderChangeUnit()
    {
    }
}
@
