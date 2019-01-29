head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MfRecruitMqSendDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文一括送信区分定数定義インタフェイス(WEB3MfRecruitMqSendDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/31 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 投信募集注文一括送信区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3MfRecruitMqSendDivDef
{
    /**
     * 0：一括送信する。
     */
    public final static String DEFAULT = "0";
    /**
     * 1：一括送信しない。(リアル)
     */
    public final static String EXCEPT = "1";
}
@
