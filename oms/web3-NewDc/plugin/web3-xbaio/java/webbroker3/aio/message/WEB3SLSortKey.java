head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン用ソートキー(WEB3SLSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 金傑（中訊）新規作成 モデルNo.756
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (担保ローン用ソートキー)<BR>
 * 担保ローンソートキークラスクラス<BR>
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3SLSortKey extends Message
{
    /**
     * (キー項目)<BR>
     * キー項目<BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * 昇順／降順<BR>
     * A：昇順<BR>
     * D：降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 46E0BE47070C
     */
    public WEB3SLSortKey()
    {

    }
}
@
