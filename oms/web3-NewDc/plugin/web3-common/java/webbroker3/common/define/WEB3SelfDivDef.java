head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SelfDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 自己区分定数定義インタフェイス(WEB3SelfDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 自己区分定数定義インタフェイス<BR>
 * （上場外株・株主登録伝票（G8610）キューの自己区分の参考用）<BR>
 * <BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public interface WEB3SelfDivDef
{
    /**
     * BLANK：顧客
     */
    public final static String CUSTOMER = " ";
}@
