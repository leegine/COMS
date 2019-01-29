head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CarriedOrderDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  出来るまで注文　@定数定義インタフェイス(WEB3CarriedOrderDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 出来るまで注文　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3CarriedOrderDef
{
    /**
      * 出来るまで注文   0：取扱不可 
      */
    public static final String CAN_NOT_DEALT = "0";
    
    /**
      * 出来るまで注文   1：取扱可能（週末営業日まで) 
      */
    public static final String DEALT_TO_WEEK_END = "1";
    
    /**
      * 出来るまで注文   2：取扱可能（月末営業日まで） 
      */
    public static final String DEALT_TO_MONTH_END = "2";
    
    /**
      * 出来るまで注文   3：取扱可能（3ヶ月後） 
      */
    public static final String DEALT_TO_THREE_MONTH = "3";
    
    /**
      * 出来るまで注文   9：取扱可能（日数指定） 
      */
    public static final String DEALT_DESIGNATA_DAYS = "9";
    
}@
