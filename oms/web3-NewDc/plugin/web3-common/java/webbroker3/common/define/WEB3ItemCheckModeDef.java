head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ItemCheckModeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ڃ`�F�b�N����(WEB3ItemCheckModeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 ����� (���u) �V�K�쐬
Revision History : 2007/06/05 �h�C(���u) �c�a���C�A�E�g(�A���Ǘ����ڃ}�X�^)�ɂ��
*/

package webbroker3.common.define;

/**
 * ���ڃ`�F�b�N���� �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3ItemCheckModeDef
{

    /**
     *  00�FDEFAULT�i�`�F�b�N�Ȃ��j
     */
    public final static String DEFAULT = "00";

    /**
     * 01�F�L���R�[�h�l�`�F�b�N(*1)
     */
    public final static String VALID_CODE_CHECK = "01";

    /**
     * 02�F���p�����̂�
     */
    public final static String HALF_NUMBER = "02";

    /**
     * 03�F���p�p����
     */
    public final static String HALF_ALPHABET_NUMBER = "03";

    /**
     * 04�F���p�p��
     */
    public final static String HALF_ALPHABET = "04";

    /**
     * 05�F���p�J�i
     */
    public final static String HALF_KANA = "05";

    /**
     * 06�F�S�p����
     */
    public final static String FULL_CHARACTER = "06";

    /**
     * 07�F�Z���^�����J�i(*2)
     */
    public final static String ADDRESS_GIVEN_NAME_KANA = "07";

    /**
     * 08�F���[���A�h���X
     */
    public final static String MAIL_ADDRESS = "08";

    /**
     * 10�F�X�֔ԍ�
     */
    public final static String ZIP_CODE = "10";

    /**
     * 11�F�d�b�^�g�єԍ�
     */
    public final static String TELEPHONE_MOBILE_NUMBER = "11";

    /**
     * 12�F�N��i20�Έȏ�j
     */
    public final static String AGE = "12";

    /**
     * 13�F�t���O�iBooleanEnum.TRUE/FALSE�j
     */
    public final static String BOOLEAN_FLAG = "13";

    /**
     * 14�F�����`�F�b�N
     */
    public final static String PRODUCT_CHECK = "14";

    /**
     * 15�F���M�����`�F�b�N
     */
    public final static String MUTUALFUND_PRODUCT_CHECK = "15";

    /**
     * 16�F�������`�F�b�N
     */
    public final static String BOND_PRODUCT_CHECK = "16";
}@
