head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLProductSearchConditions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄検索情報クラス(WEB3SLProductSearchConditions.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 張騰宇 (中訊) 新規作成 モデル760
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (担保銘柄検索情報)<BR>
 * 担保銘柄検索情報<BR>
 * 
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3SLProductSearchConditions extends Message
{
    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public long productId;

    /**
     * (適用期間from)<BR>
     * 適用期間from<BR>
     */
    public Date targetPeriodFrom;
    
    /**
     * (担保銘柄検索情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46DBBB800130
     */
    public WEB3SLProductSearchConditions()
    {

    }
}
@
