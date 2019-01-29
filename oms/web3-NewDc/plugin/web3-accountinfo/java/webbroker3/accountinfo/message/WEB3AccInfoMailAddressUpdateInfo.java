head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressUpdateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メールアドレス変更情報（WEB3AccInfoMailAddressUpdateInfo.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 武波 (中訊) 新規作成 モデルNo.257
*/
package webbroker3.accountinfo.message;

/**
 * (メールアドレス変更情報)<BR>
 * メールアドレス変更情報<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressUpdateInfo extends WEB3AccInfoMailAddressInfoUnit
{

    /**
     * (メールアドレス更新者コード)<BR>
     * メールアドレス更新者コード<BR>
     */
    public String mailAddressUpdaterCode;

    /**
     * (メールアドレス更新日時)<BR>
     * メールアドレス更新日時<BR>
     */
    public String mailAddressUpdateDate;

    /**
     * (メールアドレス変更情報)<BR>
     * メールアドレス変更情報<BR>
     */
    public WEB3AccInfoMailAddressUpdateInfo()
    {
    }
}
@
