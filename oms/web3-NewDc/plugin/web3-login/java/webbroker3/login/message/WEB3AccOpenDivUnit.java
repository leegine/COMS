head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AccOpenDivUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設区分(WEB3AccOpenDivUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/12 車進 (中訊) 新規作成 モデル057
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (口座開設区分)<BR>
 * 口座開設区分クラス<BR>
 * <BR>
 * @@author 車進
 * @@version 1.0
 */
public class WEB3AccOpenDivUnit extends Message
{
    /**
    * SerialVersionUID
    */
    public static  final long serialVersionUID = 200903121110L;

    /**
     * (口座種別)<BR>
     * 口座種別<BR>
     * 01：FX<BR>
     * 02：CFD<BR>
     * 03：大証FX<BR>
     */
    public String accType;

    /**
     * (口座開設区分)<BR>
     * 口座開設区分<BR>
     * 0：未開設　@<BR>
     * 1：開設済<BR>
     * 2：抹消<BR>
     * 3：振替停止<BR>
     */
    public String accOpenDiv;

    /**
     * @@roseuid 4021A07F0167
     */
    public WEB3AccOpenDivUnit()
    {

    }
}@
