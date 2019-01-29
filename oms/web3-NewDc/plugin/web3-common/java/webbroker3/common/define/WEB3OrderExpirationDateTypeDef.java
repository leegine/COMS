head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExpirationDateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  注文期限区分　@定数定義インタフェイス(WEB3OrderExpirationDateTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 鄒政 (中訊) 新規作成
Revesion History : 2007/06/12 栄イ(中訊) 夕場まで注文を追加
*/
package webbroker3.common.define;

/**
 * 注文期限区分　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3OrderExpirationDateTypeDef
{
    /**
      * 1：当日限り 
      */
    public static final String DAY_LIMIT = "1";

    /**
      * 2：出来るまで注文
      */
    public static final String CARRIED_ORDER = "2";

    /**
      * 3：夕場まで注文
      */
    public static final String EVENING_SESSION_ORDER = "3";
}@
