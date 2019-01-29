head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ContMrgDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 契約書徴収区分(WEB3ContMrgDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 契約書徴収区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ContMrgDivDef
{

    /**
     * 0：未徴収
     */
    public final static String NOT_COLLECT  = "0";

    /**
     * 1：徴収
     */
    public final static String COLLECT  = "1";

    /**
     * 9：未徴収取消し
     */
    public final static String COLLECT_CANCEL  = "9";

}@
