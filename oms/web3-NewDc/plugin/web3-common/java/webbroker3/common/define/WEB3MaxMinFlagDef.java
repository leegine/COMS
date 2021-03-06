head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MaxMinFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 上限／下限 定数定義インタフェイス(WEB3MaxMinFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/23 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 上限／下限 定数定義インタフェイス
 *
 * @@author ��橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3MaxMinFlagDef
{
    /**
     * 1:上限
     */
    public static final int MAXIMUM = 1;

    /**
     * 2:下限
     */
    public static final int MINIMUM = 2;
}
@
