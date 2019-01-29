head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMultiMailAddressList.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 複数アドレスリスト（WEB3AccInfoMultiMailAddressList.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 武波 (中訊) 新規作成 モデルNo.257
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (複数アドレスリスト)<BR>
 * 複数アドレスリストメッセージ<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccInfoMultiMailAddressList extends Message
{

    /**
     * メールアドレス情報<BR>
     */
    public WEB3AccInfoMailAddressInfoUnit[] mailAddressInfoList;

    /**
     * メール種別情報<BR>
     */
    public WEB3AccInfoMailAddressTypeUnit[] mailTypeInfoList;

    /**
     * (複数アドレスリスト)<BR>
     * 複数アドレスリスト<BR>
     */
    public WEB3AccInfoMultiMailAddressList()
    {
    }
}
@
