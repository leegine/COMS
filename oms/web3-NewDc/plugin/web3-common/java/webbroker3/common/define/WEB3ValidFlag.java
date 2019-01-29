head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ValidFlag.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  有効フラグ　@定数定義インタフェイス(WEB3ValidFlag)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/29 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 発注先切替テーブル.有効フラグ　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3ValidFlag
{
    /**
      * 0：OFF　@
      */
    public static final String OFF = "0";

    /**
      * 1：ON
      */
    public static final String ON = "1";
}
@
