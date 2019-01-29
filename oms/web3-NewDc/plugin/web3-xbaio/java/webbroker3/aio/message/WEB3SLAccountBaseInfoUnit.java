head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLAccountBaseInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客基本情報(WEB3SLAccountBaseInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.754
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (顧客基本情報)<BR>
 * 顧客基本情報<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLAccountBaseInfoUnit extends Message
{
    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;

    /**
     * (顧客名（漢字）)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;

    /**
     * (生年月日年号)<BR>
     * 生年月日年号<BR>
     */
    public String eraBorn;

    /**
     * (生年月日)<BR>
     * 生年月日<BR>
     */
    public String bornDate;

    /**
     * (性別)<BR>
     * 性別<BR>
     */
    public String sex;

    /**
     * (郵便番号)<BR>
     * 郵便番号<BR>
     */
    public String zipCode;

    /**
     * (住所１（漢字）)<BR>
     * 住所１（漢字）<BR>
     */
    public String address1;

    /**
     * (住所２（漢字）)<BR>
     * 住所２（漢字）<BR>
     */
    public String address2;

    /**
     * (住所３（漢字）)<BR>
     * 住所３（漢字）<BR>
     */
    public String address3;

    /**
     * (emailアドレス)<BR>
     * emailアドレス<BR>
     */
    public String mailAddress;

    /**
     * @@roseuid 46E0BE480000
     */
    public WEB3SLAccountBaseInfoUnit()
    {

    }
}
@
