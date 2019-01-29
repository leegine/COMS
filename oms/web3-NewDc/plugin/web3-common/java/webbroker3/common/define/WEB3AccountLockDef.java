head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountLockDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客ロック（リアル連携）定数定義インタフェイス(WEB3AccountLockDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 凌建平(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客ロック（リアル連携）定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3AccountLockDef
{

    /**
     * 0：顧客ロック情報のリアル連携を行わない  　@　@　@　@　@  　@　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：顧客ロック情報のリアル連携を行う　@
     */
    public final static String LOCK_REAL = "1";  
} @
