head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BranchLockDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  支店ロック　@定数定義インタフェイス(WEB3BranchLockDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/15 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 支店ロック　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3BranchLockDef
{
    /**
      * 0:認可  
      */
    public static final String BRANCH_UNLOCK = "0";
    
    /**
     *  1:非認可 
     */
    public static final String BRANCH_LOCK = "1";
    
}@
