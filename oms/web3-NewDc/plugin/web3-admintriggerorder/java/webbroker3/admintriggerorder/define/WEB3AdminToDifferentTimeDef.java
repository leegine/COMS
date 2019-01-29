head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToDifferentTimeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 乖離時間定数定義インタフェイス(WEB3AdminToDifferentTimeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/22 余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.define;

/**
 * 乖離時間　@定数定義インタフェイス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToDifferentTimeDef
{
    /**
     * 1：　@時価受信時間
     */
    public static final String CURRENTPRICE_ACCEPT_TIME = "1";
    
    /**
     * 2：　@トリガー起動時間
     */
    public static final String TRIGGER_START_TIME = "2";
    
    /**
     * 3：　@発注完了時間
     */
    public static final String ORDER_COMPLETE_TIME = "3";
}
@
