head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CompanyFormationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 法@人の設立形態区分　@定数定義インタフェイス(WEB3CompanyFormationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 法@人の設立形態区分 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3CompanyFormationDivDef
{
    /**
     * 法@人名に含まれる設立形態の区分配列
     * カ：株式会社
     * ユ：有限会社
     * シ：合資会社
     */
    public static final String[] COMPANY_FORMATION_DIV_LIST = {"（カ","カ）","（ユ","ユ）","（シ","シ）"};

    /**
     * 法@人名に含まれる設立形態の区分＋全角スペース＋半角スペースの配列
     * ※法@人名の重複チェック等に使用
     * カ：株式会社
     * ユ：有限会社
     * シ：合資会社
     * "　@"：全角スペース
     * " "：半角スペース
     */
    public static final String[] COMPANY_FORMATION_DIV_AND_SPACE_LIST = {"（カ","カ）","（ユ","ユ）","（シ","シ）","　@"," "};
}
@
