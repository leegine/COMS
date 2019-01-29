head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税区分(WEB3InformTaxTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/24 孫洪江 (中訊) 新規作成
*/

package webbroker3.inform.define;

/**
 * 税区分 定数定義インタフェイス
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public interface WEB3InformTaxTypeDef
{
    /**
     * 0： その他
     */
    public final static String OTHER = "0";

    /**
     * 1： 一般
     */
    public final static String NORMAL = "1";

    /**
     * 2： 特定
     */
    public final static String SPECIAL = "2";

    /**
     * 3： 特定口座かつ源泉徴収
     */
    public final static String SPECIAL_WITHHOLD = "3";
}@
