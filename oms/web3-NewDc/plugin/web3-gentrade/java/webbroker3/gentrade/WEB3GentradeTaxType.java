head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTaxType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ŏ�ޒ萔��`�N���X(WEB3GentradeTaxType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 �����@@���F(SRA) �V�K�쐬
*/
package webbroker3.gentrade;

/**
 * �i�Ŏ�ށj<BR>
 *<BR>
 * �Ŏ�ނ̒萔���`����B<BR>
 *<BR>
 * @@author �����@@���F(SRA)
 * @@version 1.0
 */
public interface WEB3GentradeTaxType
{

    /**
     * ����ł������R�[�h�l<BR>
     */
    public final static String CONSUMPTION_TAX = "10";

    /**
     * ���n�v�ł������R�[�h�l<BR>
     */
    public final static String CAPITAL_GAIN_TAX = "20";

    /**
     * ���M���򒥎��i�����^�E���Łj�������R�[�h�l<BR>
     */
    public final static String MF_WITHHOLDING_STOCK_NATIONAL_TAX = "30";

    /**
     * ���M���򒥎��i���^�E���Łj�������R�[�h�l<BR>
     */
    public final static String MF_WITHHOLDING_BOND__NATIONAL_TAX = "40";

    /**
     * ���M���򒥎��i�����^�E�n���Łj�������R�[�h�l<BR>
     */
    public final static String MF_WITHHOLDING_STOCK_LOCAL_TAX = "50";

    /**
     * ���M���򒥎��i���^�E�n���Łj�������R�[�h�l<BR>
     */
    public final static String MF_WITHHOLDING_BOND_LOCAL_TAX = "60";
}
@
