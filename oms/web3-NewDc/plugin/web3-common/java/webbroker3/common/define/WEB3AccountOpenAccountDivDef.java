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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�敪�萔��`�C���^�t�F�C�X(WEB3AccountOpenAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/31 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �ڋq�敪 �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3AccountOpenAccountDivDef
{
    /**
     * 10�F��ʌڋq(100001�`599999)
     */
    public final static String NORMAL_CUSTORMER = "10";

    /**
     * 60�F�Ј��ڋq�E����(600001�`609999)
     */
    public final static String  EMPLOYEE_CUSTORMER_OFFICER = "60";

    /**
     * 61�F�Ј��ڋq�E�j���Ј�(610001�`629999)
     */
    public final static String  EMPLOYEE_CUSTORMER_MALE = "61";

    /**
     * 63�F�Ј��ڋq�E�����Ј�(630001�`649999)
     */
    public final static String  EMPLOYEE_CUSTORMER_FEMALE = "63";

    /**
     * 68�F�Ј��ڋq�E�c��(680001�`699999)
     */
    public final static String  EMPLOYEE_CUSTORMER_SALES = "68";

    /**
     * 70�F��ꎖ�Ɩ@@�l(700001�`709999)
     */
    public final static String  PRESENTATION_ENTERPRISE_CORPORATION = "70";

    /**
     * 71�F���ꎖ�Ɩ@@�l(710001�`719999)
     */
    public final static String  NOT_PRESENTATION_ENTERPRISE_CORPORATION = "71";

    /**
     * 73�F�񋏏Z�O���l(730001�`789999)
     */
    public final static String  NOT_RESIDENCE_FOREIGNER = "73";

    /**
     * 79�F���Z�O���l(790001�`799999)
     */
    public final static String  RESIDENCE_FOREIGNER = "79";

    /**
     * 80�F�،����
     */
    public final static String  INSTITUTION = "80";

    /**
     * 81�F��s�E�M����
     */
    public final static String  BANK_CREDITCASH = "81";

    /**
     * 83�F�_�ьn��
     */
    public final static String  AGRICULTURE_FORESTRY_SYSTEM = "83";

    /**
     * 84�F�ی����
     */
    public final static String  INSUTANCE_CORPORATION = "84";

    /**
     * 85�F���̑����Z�@@��
     */
    public final static String  OTHER_FINANCE_ORGANIZATION = "85";

    /**
     * 86�F�����@@�l�E���v�@@�l��
     */
    public final static String  COMMON_CORPORATION = "86";

    /**
     * 87�F���ꎖ�Ɩ@@�l�E�M����s
     */
    public final static String  DIVINE_MESSAGE_BANK = "87";

    /**
     * 88�F�،����
     */
    public final static String  SECURITIES_CORPORATION = "88";

    /**
     * 89�F���̑��Вc��
     */
    public final static String  OTHER_CORPORATION = "89";

    /**
     * 90�FSST�ڋq
     */
    public final static String  SST_CUSTORMER = "90";

    /**
     * 00�FCOD�ڋq
     */
    public final static String  COD_CUSTORMER = "00";
}
@
