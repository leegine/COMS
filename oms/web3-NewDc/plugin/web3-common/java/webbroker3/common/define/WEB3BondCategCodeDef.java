head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondCategCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ʃR�[�h�萔��`�C���^�t�F�C�X(WEB3BondCategCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 �h�C(���u) �V�K�쐬
Revesion History : 2006/10/06 �h�C(���u) �d�l�ύX�E�c�a���C�A�E�gNo020
*/
package webbroker3.common.define;

/**
 * ��ʃR�[�h�萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3BondCategCodeDef
{
    /**
     * 1011�F���̑����i��.�������j
     */
    public final static String OTHER_GOVERNMENT_BOND = "1011";

    /**
     * 1012�F��������
     */
    public final static String LONG_TERM_GOVERNMENT_BOND = "1012";

    /**
     * 1013�F�Z������
     */
    public final static String SHORT_TERM_GOVERNMENT_BOND = "1013";

    /**
     * 1014�F��������
     */
    public final static String DISCOUNT_GOVERNMENT_BOND = "1014";

    /**
     * 1021�F����n����, �����n����
     */
    public final static String REGION_BOND = "1021";

    /**
     * 1031�F���ۍ�
     */
    public final static String GOVERNMENT_WARRANT_BOND = "1031";

    /**
     * 1032�F���t�d�X��
     */
    public final static String INTEREST_BEARING_ELETRICITY_BOND = "1032";

    /**
     * 1033�F�����d�X��
     */
    public final static String DISCOUNT_ELETRICITY_BOND = "1033";

    /**
     * 1034�F���ʓd�X��
     */
    public final static String SPECIAL_ELETRICITY_BOND = "1034";

    /**
     * 1035�F���t���Z��
     */
    public final static String INTEREST_BEARING_FINANCE_BOND = "1035";

    /**
     * 1036�F�������Z��
     */
    public final static String DISCOUNT_FINANCE_BOND = "1036";

    /**
     * 1041�F�d�͍�
     */
    public final static String ELECTRICITY_BOND = "1041";

    /**
     * 1042�F��ʎ��ƍi��.�r�a�j, ����厖�ƍ�
     */
    public final static String NORMAL_BUSINESS_BOND = "1042";

    /**
     * 1043�F�]���ЍE�������g��
     */
    public final static String CONVERSION_COMPANY_BOND_WARRANT_BOND = "1043";

    /**
     * 1091�F���̑�����
     */
    public final static String OTHER_BOND = "1091";

    /**
     * 2010�F���t����
     */
    public final static String INTEREST_BEARING_GOVERNMENT_BOND = "2010";

    /**
     * 2011�F�g���W�����[�r��
     */
    public final static String TO_RE_JIA_RI_BII_RU = "2011";

    /**
     * 2012�F���̑���������
     */
    public final static String OTHER_DISCOUNT_GOVERNMENT_BOND = "2012";

    /**
     * 2013�F�C���f�b�N�X����
     */
    public final static String INDEX_GOVERNMENT_BOND = "2013";

    /**
     * 2020�F���t���{�@@�֍�
     */
    public final static String INTEREST_BEARING_GOVERNMENT_ORGANIZATION_BOND = "2020";

    /**
     * 2021�F�������{�@@�֍�
     */
    public final static String DISCOUNT_GOVERNMENT_ORGANIZATION_BOND = "2021";

    /**
     * 2022�F�C���f�b�N�X���{�@@�֍�
     */
    public final static String INDEX_GOVERNMENT_ORGANIZATION_BOND = "2022";

    /**
     * 2030�F���t�n����
     */
    public final static String INTEREST_BEARING_REGION_BOND = "2030";

    /**
     * 2031�F�����n����
     */
    public final static String DISCOUNT_REGION_BOND = "2031";

    /**
     * 2032�F�C���f�b�N�X�n����
     */
    public final static String INDEX_REGION_BOND = "2032";

    /**
     * 2040�F���t���v�Ѝ�
     */
    public final static String INTEREST_BEARING_INTEREST_COMPANY_BOND = "2040";

    /**
     * 2041�F���t��ʎЍ�
     */
    public final static String INTEREST_BEARING_NOTMAL_COMPANY_BOND = "2041";

    /**
     * 2042�F���t���Z��
     */
    public final static String FOREIGN_INTEREST_BEARING_FINANCE_BOND = "2042";

    /**
     * 2043�F�������Z��
     */
    public final static String FOREIGN_DISCOUNT_FINANCE_BOND = "2043";

    /**
     * 2044�F�����Ѝ�
     */
    public final static String DISCOUNT_COMPANY_BOND = "2044";

    /**
     * 2050�F�t���[�e�B���O���[�g��
     */
    public final static String FLOATING_RATE_BOND = "2050";

    /**
     * 2053�F�i�K���t��
     */
    public final static String STEP_INTEREST_BEARING_BOND = "2053";

    /**
     * 2056�F�C���f�b�N�X�Ѝ�
     */
    public final static String INDEX_COMPANY_BOND = "2056";

    /**
     * 2060�F�]���Ѝ�
     */
    public final static String CONVERSION_COMPANY_BOND = "2060";

    /**
     * 2063�F�������g�t�Ѝ�
     */
    public final static String WARRANT_FOLLOW_COMPANY_BOND = "2063";

    /**
     * 2064�F�������g���Ѝ�
     */
    public final static String WARRANT_FALL_COMPANY_BOND = "2064";

    /**
     * 2070�F���t���ۋ@@�֍�
     */
    public final static String INTEREST_BEARING_INTERNATIONAL_ORGANIZATION_BOND = "2070";

    /**
     * 2071�F�������ۋ@@�֍�
     */
    public final static String DISCOUNT_INTERNATIONAL_ORGANIZATION_BOND = "2071";

    /**
     * 2072�F�C���f�b�N�X���ۋ@@�֍�
     */
    public final static String INDEX_INTERNATIONAL_ORGANIZATION_BOND = "2072";

    /**
     * 2090�F�b�o
     */
    public final static String CP = "2090";

    /**
     * 2091�F�b�c
     */
    public final static String CD = "2091";

    /**
     * 2097�F�����������g�i�X�C�X�t�������j
     */
    public final static String STOCK_WARRANT_SWITZERLAND_FRANC_BOND = "2097";

    /**
     * 2098�F�����������g
     */
    public final static String STOCK_WARRANT = "2098";

    /**
     * 2099�F���̑��L���،�
     */
    public final static String OTHER_VALUE_SECURITIES = "2099";
}
@
