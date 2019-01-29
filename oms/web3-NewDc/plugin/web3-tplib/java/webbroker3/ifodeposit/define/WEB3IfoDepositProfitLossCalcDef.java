head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositProfitLossCalcDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金評価損益計算方法@　@定数定義インターフェース(WEB3IfoDepositProfitLossCalcDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/03 有山 祥子(SRA) 新規作成
*/

package webbroker3.ifodeposit.define;

/**
 * 証拠金評価損益計算方法@　@定数定義インターフェース
 * 
 * @@author 有山 祥子
 * @@version 1.0
 */
public interface WEB3IfoDepositProfitLossCalcDef
{
    
    /**
     * 0：（評価損益計算に当日建の建玉を含む） <BR>
     */
    public static final String DEFAULT = "0";
    
    /**
     * 1：（評価損益計算に当日建の建玉を含まない） <BR>
     */
    public static final String EXCEPT_TODAY_CONTRACT = "1";

}
@
