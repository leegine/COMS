head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BatchFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : BATCH処理FLAG  定数定義インタフェイス(WEB3BatchFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/22　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * BATCH処理FLAG　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3BatchFlagDef
{

    /**
     *  NULL：初期値　@
     */
    public static final String INITIALIZE_PRICE = "NULL";

    /**
     *  1：更新済み
     */
    public static final String UPDATED_COMPLETE = "1";

     
}
@
