head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BatoFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  電子鳩実施FLAG　@定数定義インタフェイス(WEB3BatoFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/22 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 会社部店商品テーブル.電子鳩実施FLAG　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3BatoFlagDef
{
    /**
      * 0：電子鳩実施しない 
      */
    public static final String NOT_ENFORCEMENT = "0";
    
    /**
      * 1：電子鳩実施する
      */
    public static final String ENFORCEMENT = "1";

}
@
