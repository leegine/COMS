head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMonthlyOrderCountUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 発注月別件数情報(WEB3FXAccOpenApplyUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/11 韋念瓊(中訊) 新規作成
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (発注月別件数情報) <BR>
 * 発注月別件数情報クラス 
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AioMonthlyOrderCountUnit extends Message
{
    /**
     * (件数情報一覧) <BR>
     * 件数情報一覧
     */
    public WEB3AioOtherCountInfomationUnit[] otherCountInfomationUnits;

    /**
     * (発注月別件数情報) <BR>
     * コンストラクタ。
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3AioMonthlyOrderCountUnit()
    {
    }
}@
