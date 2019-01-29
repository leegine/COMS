head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MngLockDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  ロックフラグ(管理ロック&考査ロック)　@定数定義インタフェイス(WEB3MngLockDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/15 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客マスター.考査ロック &  顧客マスター.管理ロック　@
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3MngLockDef
{
    /**
      * 0:普通 
      */
    public static final String UNLOCK = "0";
    
    /**
      *  1:ロック 
      */
    public static final String LOCK = "1";
    
}@
