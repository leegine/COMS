head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AgreementDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 同意非同意FLAG定数定義クラス(WEB3AgreementDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/06 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 同意非同意FLAG定数定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3AgreementDef
{

    /**
     * 非同意の場合<BR>
     */
    public final static String UNACCEPT = "0";

    /**
     * 同意の場合<BR>
     */
    public final static String ACCEPT = "1";
}
@
