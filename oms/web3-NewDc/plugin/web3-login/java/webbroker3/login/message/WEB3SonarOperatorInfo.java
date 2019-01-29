head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SonarOperatorInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : SONAR扱者情報(WEB3SonarOperatorInfo.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/10/31 柴双紅(中訊) 新規作成 モデル048
*/

package webbroker3.login.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (SONAR扱者情報)<BR>
 * SONAR扱者情報<BR>
 * <BR>
 * @@author      柴双紅
 * @@version     1.0
 */
public class WEB3SonarOperatorInfo extends Message
{
    /**
     * 扱者コード
     */
    public String operatorCode;

    /**
     * 扱者名(カナ)
     */
    public String nameKana;

    /**
     * 扱者名(漢字)
     */
    public String nameKanji;
}@
