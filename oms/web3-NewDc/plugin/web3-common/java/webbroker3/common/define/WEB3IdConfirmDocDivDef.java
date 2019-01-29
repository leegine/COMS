head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IdConfirmDocDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 本人確認書類区分(WEB3IdConfirmDocDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 本人確認書類区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3IdConfirmDocDivDef
{

    /**
     *  01：運転免許証
     */
    public final static String DRIVING_LICENSE  = "01";

    /**
     * 02：健康保険証
     */
    public final static String HEALTH_INSURANCE_CARD  = "02";

    /**
     * 03：住民票
     */
    public final static String RESIDENT_CARD  = "03";

    /**
     * 04：印鑑証明
     */
    public final static String SEAL_CERTIFICATE  = "04";

    /**
     * 05：外国人登録証明書
     */
    public final static String ALIEN_REGISTRATION_CERTIFICATE  = "05";

    /**
     * 99：その他
     */
    public final static String OTHER  = "99";

}@
