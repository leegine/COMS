head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCfdAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : CFD口座情報（WEB3AccInfoCfdAccountInfo.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 武波 (中訊) 新規作成 モデルNo.250
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (CFD口座情報)<BR>
 * CFD口座情報<BR>
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCfdAccountInfo extends Message
{

    /**
     * (CFDログインID)<BR>
     * CFDログインID<BR>
     */
    public String cfdLoginId;

    /**
     * (CFD口座番号)<BR>
     * CFD口座番号（CFDコース）<BR>
     */
    public String cfdAccountCode;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード<BR>
     */
    public String fxSystemCode;

    /**
     * 
     */
    public WEB3AccInfoCfdAccountInfo()
    {

    }
}
@
