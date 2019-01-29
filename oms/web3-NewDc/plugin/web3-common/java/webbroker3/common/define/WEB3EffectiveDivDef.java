head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EffectiveDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  有効区分　@定数定義インタフェイス(WEB3EffectiveDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス申込登録テーブル.有効区分　@
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3EffectiveDivDef
{
    /**
     * 0：有効（default）
     */
    public static final String EFFECTIVE = "0";
    
    /**
     * 1：無効
     */
    public static final String INEFFECTIVE = "1";
}

@
