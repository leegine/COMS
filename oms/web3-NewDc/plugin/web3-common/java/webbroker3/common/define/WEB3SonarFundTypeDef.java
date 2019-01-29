head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SonarFundTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品区分定数定義インタフェイス(WEB3SonarFundTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 商品区分 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3SonarFundTypeDef
{

    /**
     * 60：国内投信　@　@　@　@　@　@  　@　@
     */
    public final static String MUTUAL_FUND_DOMESTIC = "60";
    
    /**
     * 61：外国投信　@　@　@　@　@  　@　@
     */
    public final static String MUTUAL_FUND_FOREIGN = "61";    

} @
