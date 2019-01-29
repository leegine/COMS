head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SubmitMqTriggerDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQトリガ発行定数定義インタフェイス(WEB3SubmitMqTriggerDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * MQトリガ発行 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3SubmitMqTriggerDef
{
    /**
     * 0：MQトリガを実施しない
     */
    public static final String NOT_TRIGGER = "0";

    /**
     * 1：MQトリガを実施する
     */
    public static final String TRIGGER = "1";
}
@
