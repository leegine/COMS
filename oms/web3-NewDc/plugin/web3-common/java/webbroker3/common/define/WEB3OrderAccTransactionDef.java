head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderAccTransactionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3OrderAccTransactionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2007/09/26 �h�C(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo541
Revesion History : 2008/07/31 ��іQ(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo638
*/
package webbroker3.common.define;

/**
 * ������t�X�e�C�^�X�e�[�u��.������t�g�����U�N�V�����@@�萔��`�C���^�t�F�C�X<BR>                                                             
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderAccTransactionDef
{
    /**
     * 00 : DEFAULT�i���ׂāj
     */
    public static final String DEFAULT = "00";

    /**
     * 01 : ���t <BR>    
     *   �i�V�K�����j<BR>
     *    (�T�[�r�X���p�\��)<BR>
     *    (�ב֕ۏ؋������J��)<BR>
     *    (�O�����������J��)<BR>
     *    (�V���O���T�C���I�����ב֏؋���)<BR>
     *    (�،��S�ۃ��[�������J��)<BR>
     */
    public static final String OPEN_LONG_MARGIN = "01";

    /**
     * 02 : ���t�i�V�K�����j
     */
    public static final String OPEN_SHORT_MARGIN = "02";

    /**
     * 03 : �ԍ�
     */
    public static final String CLOSE_MARGIN = "03";

    /**
     * 04 : �����E���n
     */
    public static final String SWAP_MARGIN = "04";

    /**
     * 05 : ����
     */
    public static final String CHANGE = "05";

    /**
     * 06 : ���
     */
    public static final String CANCEL = "06";

    /**
     * 07 : �Ɖ�
     */
    public static final String REFERENCE = "07";

    /**
     * 08 : �o��
     */
    public static final String PAYMENT = "08";

    /**
     * 09 : �U��
     */
    public static final String TRANSFER = "09";

    /**
     * 10 : ����
     */
    public static final String CASH_IN = "10";
    
    /**
     * 11 : ��W
     */
    public static final String RECRUIT = "11";

    /**
     * 12�F�莞��z���t
     */
    public static final String FIXED_BUYING = "12";
}
@
