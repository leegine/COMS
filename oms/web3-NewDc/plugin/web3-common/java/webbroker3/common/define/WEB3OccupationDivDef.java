head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OccupationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 職業区分(WEB3OccupationDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 職業区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3OccupationDivDef
{

    /**
     * 01：農林水産業
     */
    public final static String AGRICULTURE_FORESTRY_FISHERY  = "01";

    /**
     * 10：自営業
     */
    public final static String INDEPENDENT_BUSINESS  = "10";

    /**
     * 50：医者
     */
    public final static String DOCTOR  = "50";

    /**
     * 54：弁護士・会計士
     */
    public final static String LAWYER_ACCOUNTANT  = "54";

    /**
     * 70：上場会社役員
     */
    public final static String LISTED_COMPANY_OFFICER  = "70";

    /**
     * 71：上場会社管理職
     */
    public final static String LISTED_COMPANY_MANAGER  = "71";

    /**
     * 72：上場会社一般職員
     */
    public final static String LISTED_COMPANY_STAFF  = "72";

    /**
     * 75：その他給与生活
     */
    public final static String OTHER_SALARY_EARNER  = "75";

    /**
     * 94：無職（学生）
     */
    public final static String JOBLESSNESS_STUDENT  = "94";

    /**
     * 95：無職（主婦）
     */
    public final static String JOBLESSNESS_HOUSEWIFE  = "95";

    /**
     * 97：無職（定年退職）
     */
    public final static String JOBLESSNESS_MANDATORY_RETIREMENT  = "97";

    /**
     * 98：無職（その他）
     */
    public final static String JOBLESSNESS_OTHER  = "98";

    /**
     * 99：その他
     */
    public final static String OTHER  = "99";

}@
