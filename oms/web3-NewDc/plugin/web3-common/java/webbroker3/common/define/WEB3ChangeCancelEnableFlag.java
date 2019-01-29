head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ChangeCancelEnableFlag.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  訂正取消可能フラグ　@定数定義インタフェイス(WEB3ChangeCancelEnableFlag)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/29 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 発注先切替テーブル.訂正取消可能フラグ　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3ChangeCancelEnableFlag
{
    /**
      * 0：不可　@
      */
    public static final String DISABLE = "0";
    
    /**
      * 1：可能　@
      */
    public static final String ENABLE = "1";

}
@
