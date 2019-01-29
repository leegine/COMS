head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SkipRegistTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  スキップ登録区分　@定数定義インタフェイス(WEB3SkipRegistTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * スキップ登録区分(株式注文繰越スキップ銘柄通知キューテーブル)　@
 *  定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3SkipRegistTypeDef
{
    /**
      *  1:登録 
      */
    public static final String REGISTRATION = "1";
    
    /**
      *   3:抹消 
      */
    public static final String DELETE = "3";
    
}@
