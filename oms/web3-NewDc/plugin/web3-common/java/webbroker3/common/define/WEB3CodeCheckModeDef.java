head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CodeCheckModeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コードチェックモード定数定義クラス(WEB3CodeCheckModeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/26 菊地(SRA) WEB3PwdCheckModeDefを名称変更
*/
package webbroker3.common.define;

/**
 * コードチェックモード定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3CodeCheckModeDef
{
    /**
     * 数字のみ
     */
    public static final String NUMERIC = "1";
    
    /**
     * 英数字（数のみ、英のみ可）
     */
    public static final String ALPHA_OR_NUMERIC = "2";
    
    /**
     * 英数字混在
     */
    public static final String ALPHA_AND_NUMERIC = "3";
}@
