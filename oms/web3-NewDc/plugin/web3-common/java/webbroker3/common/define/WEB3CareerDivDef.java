head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CareerDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キャリア別加盟店ＩＤテーブル･キャリア区分 定数定義クラス(WEB3CareerDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/13 凌建平(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * キャリア区分 定数定義クラス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3CareerDivDef
{
     /**
      * 4：i-mode
      */
     public static final String I_MODE = "4";
     
     /**
      * 5：vodafone
      */
     public static final String VODAFONE = "5";
     
     /**
      * 6：au
      */
     public static final String AU = "6";
     
     /**
      * 2：モバイル以外（PC）
      */
     public static final String MOBILE_OTHER_PC = "2";
}
@
