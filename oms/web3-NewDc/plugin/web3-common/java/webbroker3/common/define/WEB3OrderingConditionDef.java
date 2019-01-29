head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderingConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  発注条件　@定数定義インタフェイス(WEB3OrderingConditionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/15 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 発注条件　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3OrderingConditionDef
{
    /**
      * 0： DEFAULT 
      */
    public static final String DEFAULT = "0";
    
    /**
      * 1： 逆指値 
      */
    public static final String STOP_LIMIT_PRICE = "1";
    
    /**
      *  2： W指値 
      */
    public static final String W_LIMIT_PRICE = "2";
    
}@
