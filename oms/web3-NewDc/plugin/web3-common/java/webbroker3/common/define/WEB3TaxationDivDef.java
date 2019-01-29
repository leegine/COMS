head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TaxationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TaxStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 李海波 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * ”無関係”、”申告分離”、”源泉分離”区分　@定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3TaxationDivDef
{
    
    /**
     * 無関係 
     */
    public static final String IRRELEVENT = "0";
    
    /**
     * 申告分離 
     */
    public static final String SEPARATE_SELF_ACCESSMENT = "1";
    
    /**
     * 源泉分離 
     */
    public static final String SEPARATE_AT_SOURCE = "2";
    
}
@
