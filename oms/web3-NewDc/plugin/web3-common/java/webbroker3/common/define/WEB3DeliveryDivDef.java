head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DeliveryDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴テーブルの積送区分インタフェイス(WEB3DeliveryDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/19 凌建平(中訊) 作成
*/
package webbroker3.common.define;

/**
 * 入出庫履歴テーブルの積送区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3DeliveryDivDef 
{

    /**
     * 0（無関係）
     */
    public static final String IRRELEVENT = "0";

    /**
     * 1（積送中）
     */
    public static final String RESERVE_SENDING = "1";
    
    /**
     * 2（積送完了）
     */
    public static final String RESERVE_SENT = "2";
}
@
