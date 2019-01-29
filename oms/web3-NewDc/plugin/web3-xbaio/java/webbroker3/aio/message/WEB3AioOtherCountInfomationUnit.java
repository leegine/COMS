head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOtherCountInfomationUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : その他件数情報(WEB3AioOtherCountInfomationUnit)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/11 韋念瓊(中訊) 新規作成
 */

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (その他件数情報) <BR>
 * その他件数情報クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AioOtherCountInfomationUnit extends Message
{
    /**
     * (発注日) <BR>
     * 発注日=YYYYMMDD <BR>
     * ※月次合計の場合はYYYYMM
     */
    public String orderBizDate;

    /**
     * (件数１) <BR>
     * 金融機@関決済連携の場合、入金件数 <BR>
     * 為替保証金、外国株式（外部連携）の場合、振替出金
     */
    public String buyOrderCount;

    /**
     * (件数２) <BR>
     * 金融機@関連携の場合、null <BR>
     * 為替保証金、外国株式（外部連携）の場合、振替入金
     */
    public String sellOrderCount;

    /**
     * (件数３) <BR>
     * 金融機@関連携の場合、null 
     * 為替保証金、外国株式（外部連携）の場合、口座開設数
     */
    public String executeCount;

    /**
     * (その他件数情報) <BR>
     * コンストラクタ。
     * 
     * @@roseuid 41B042CE02BD
     */
    public WEB3AioOtherCountInfomationUnit()
    {
    }
}@
