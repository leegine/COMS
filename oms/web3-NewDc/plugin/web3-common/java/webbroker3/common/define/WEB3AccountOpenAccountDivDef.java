head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客区分定数定義インタフェイス(WEB3AccountOpenAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/31 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 顧客区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenAccountDivDef
{
    /**
     * 10：一般顧客(100001～599999)
     */
    public final static String NORMAL_CUSTORMER = "10";

    /**
     * 60：社員顧客・役員(600001～609999)
     */
    public final static String  EMPLOYEE_CUSTORMER_OFFICER = "60";

    /**
     * 61：社員顧客・男性社員(610001～629999)
     */
    public final static String  EMPLOYEE_CUSTORMER_MALE = "61";

    /**
     * 63：社員顧客・女性社員(630001～649999)
     */
    public final static String  EMPLOYEE_CUSTORMER_FEMALE = "63";

    /**
     * 68：社員顧客・営業(680001～699999)
     */
    public final static String  EMPLOYEE_CUSTORMER_SALES = "68";

    /**
     * 70：上場事業法@人(700001～709999)
     */
    public final static String  PRESENTATION_ENTERPRISE_CORPORATION = "70";

    /**
     * 71：非上場事業法@人(710001～719999)
     */
    public final static String  NOT_PRESENTATION_ENTERPRISE_CORPORATION = "71";

    /**
     * 73：非居住外国人(730001～789999)
     */
    public final static String  NOT_RESIDENCE_FOREIGNER = "73";

    /**
     * 79：居住外国人(790001～799999)
     */
    public final static String  RESIDENCE_FOREIGNER = "79";

    /**
     * 80：証券会社
     */
    public final static String  INSTITUTION = "80";

    /**
     * 81：銀行・信金等
     */
    public final static String  BANK_CREDITCASH = "81";

    /**
     * 83：農林系統
     */
    public final static String  AGRICULTURE_FORESTRY_SYSTEM = "83";

    /**
     * 84：保険会社
     */
    public final static String  INSUTANCE_CORPORATION = "84";

    /**
     * 85：その他金融機@関
     */
    public final static String  OTHER_FINANCE_ORGANIZATION = "85";

    /**
     * 86：公共法@人・公益法@人等
     */
    public final static String  COMMON_CORPORATION = "86";

    /**
     * 87：非上場事業法@人・信託銀行
     */
    public final static String  DIVINE_MESSAGE_BANK = "87";

    /**
     * 88：証券会社
     */
    public final static String  SECURITIES_CORPORATION = "88";

    /**
     * 89：その他社団等
     */
    public final static String  OTHER_CORPORATION = "89";

    /**
     * 90：SST顧客
     */
    public final static String  SST_CUSTORMER = "90";

    /**
     * 00：COD顧客
     */
    public final static String  COD_CUSTORMER = "00";
}
@
