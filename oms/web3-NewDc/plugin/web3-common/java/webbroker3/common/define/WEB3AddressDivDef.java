head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AddressDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アドレス区分定数定義インタフェイス(WEB3AddressDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/23 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * アドレス区分定数定義インタフェイス<BR>
 * (顧客メールアドレスのアドレス区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AddressDivDef
{
    /**
     * 0：指定なし
     */
    public final static String DEFAULT = "0";

    /**
     * 1:PCメールアドレス
     */
    public final static String PC_MAIL_ADDRESS = "1";
    
    /**
     * 2:携帯メールアドレス 
     */
    public final static String MOBILE_MAIL_ADDRESS = "2";
}@
