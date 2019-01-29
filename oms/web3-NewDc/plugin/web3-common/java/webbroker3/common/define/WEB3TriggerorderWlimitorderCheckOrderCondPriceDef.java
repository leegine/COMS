head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TriggerorderWlimitorderCheckOrderCondPriceDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TriggerorderWlimitorderCheckOrderCondPriceDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/28 栄イ (中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * W指値注文・発注条件単価チェック実施区分　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3TriggerorderWlimitorderCheckOrderCondPriceDef
{
    /**
     * 0：チェックしない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：チェックする
     */
    public final static String CHECK = "1";
}
@
