head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.26.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座区分 定数定義インタフェイス(WEB3AccInfoTaxTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;


/**
 * 口座区分 定数定義インタフェイス
 * 現物株式口座区分、現物株式口座区分（次年）、信用取引口座区分、信用取引口座区分（次年）
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoTaxTypeDef
{
    
    /**
     * 1：未開設（一般）
     */
    public final static String DEFAULT = "1";
    
    /**
     * 2：開設済み源泉徴収なし（特定かつ源泉徴収なし）
     */
    public final static String OPEN_WITHOUT_SPECIAL_WITHHOLD = "2";
    
    /**
     * 3：開設済み源泉徴収あり（特定かつ源泉徴収）
     */
    public final static String OPEN_SPECIAL_WITHHOLD = "3";
}
@
