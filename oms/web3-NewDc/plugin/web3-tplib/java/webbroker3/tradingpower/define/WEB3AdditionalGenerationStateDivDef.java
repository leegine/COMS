head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdditionalGenerationStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 追証発生状況の定数定義インターフェース(WEB3AdditionalGenerationStateDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/10/20 陸文静 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * (追証発生状況の定数定義インターフェース)
 */
public interface WEB3AdditionalGenerationStateDivDef
{
    /**
     *  0 : 追証未発生<BR>
     */
    public final static String ADDITIONAL_NOT_GENERATION = "0";

    /**
     *  1 : 第一水準追証発生<BR>
     */
    public final static String FIRST_ADDITIONAL_GENERATION = "1";

    /**
     *  2 : 第二水準追証発生<BR>
     */
    public final static String SECOND_ADDITIONAL_GENERATION = "2";
    
}
@
