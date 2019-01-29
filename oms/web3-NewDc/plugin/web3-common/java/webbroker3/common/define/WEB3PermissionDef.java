head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PermissionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 許可／不許可定数定義クラス(WEB3PermissionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/31 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 許可／不許可定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3PermissionDef
{
    /**
     * 許可
     */
    public static final String APPROVAL = "0";
    
    /**
     * 不許可
     */
    public static final String DISAPPROVAL = "1";
}
@
