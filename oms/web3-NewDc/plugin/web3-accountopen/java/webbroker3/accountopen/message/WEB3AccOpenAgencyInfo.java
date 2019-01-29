head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAgencyInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 機@構通知情報(WEB3AccOpenAgencyInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/08/11 張騰宇(中訊) 新規作成 モデル168
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (機@構通知情報)<BR>
 * 機@構通知情報<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccOpenAgencyInfo extends Message
{
    /**
     * (フリガナ1)<BR>
     * フリガナ1<BR>
     */
    public String agencyAccNameKana1;

    /**
     * (フリガナ2)<BR>
     * フリガナ2<BR>
     */
    public String agencyAccNameKana2;

    /**
     * (名称1)<BR>
     * 名称1<BR>
     */
    public String agencyAccName1;

    /**
     * (名称2)<BR>
     * 名称2<BR>
     */
    public String agencyAccName2;

    /**
     * (住所1)<BR>
     * 住所1<BR>
     */
    public String agencyAddress1;

    /**
     * (住所2)<BR>
     * 住所2<BR>
     */
    public String agencyAddress2;

    /**
     * (代表者の役職)<BR>
     * 代表者の役職<BR>
     */
    public String agencyRepPost;

    /**
     * (代表者のフリガナ1)<BR>
     * 代表者のフリガナ1<BR>
     */
    public String agencyRepNameKana1;

    /**
     * (代表者のフリガナ2)<BR>
     * 代表者のフリガナ2<BR>
     */
    public String agencyRepNameKana2;

    /**
     * (代表者の氏名1)<BR>
     * 代表者の氏名1<BR>
     */
    public String agencyRepName1;

    /**
     * (代表者の氏名2)<BR>
     * 代表者の氏名2<BR>
     */
    public String agencyRepName2;
}
@
