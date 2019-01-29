head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FinInstitutionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金融機@関区分　@定数定義(WEB3FinInstitutionDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/25 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 金融機@関区分　@定数定義インタフェイス                             
 * @@author 栄イ
 * @@version 1.0
 */
public interface WEB3FinInstitutionDivDef 
{
	/**
     * 1：銀行
     */
    public static final String BANK = "1";

    /**
     * 2：郵便局
     */
    public static final String POST_OFFICE = "2";
}
@
