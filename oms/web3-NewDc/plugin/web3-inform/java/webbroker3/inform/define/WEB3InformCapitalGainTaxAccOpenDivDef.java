head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCapitalGainTaxAccOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定管理口座開設区分(WEB3InformCapitalGainTaxAccOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/20 孫洪江 (中訊) 新規作成
*/

package webbroker3.inform.define;

/**
 * 特定管理口座開設区分 定数定義インタフェイス
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public interface WEB3InformCapitalGainTaxAccOpenDivDef
{
    /**
     * 0：未開設
     */
    public final static String DEFAULT = "0";

    /**
     * 1：開設
     */
    public final static String OPEN = "1";
}@
