head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3OrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 ma-zt(sinocom)�@@�V�K�쐬
Revesion History : 2006/06/07 ������(���u) �C���^�[�t�F���X�\���ENo082
Revesion History : 2006/06/07 �h�C(���u) �敨OP�\��
Revesion History : 2006/11/08 �h�C(���u) ү���ޒ�`��_�����w���敨(���Ɖ�)
*/
package webbroker3.common.define;

/**
 * ������ԁ@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ma-zt
 * @@version 1.0
 */
public interface WEB3OrderStatusDef
{
    /**
     *  0�F���̑� 
     */
    public static final String OTHER = "0";
    /**
     * 1�F��t�ρi�V�K�����j
     */
    public static final String ACCEPTED_OPENORDER= "1";
    /**
     *  2�F�������i�V�K�����j 
     */
    public static final String MODIFYING = "2";
    /**
     * 3�F�����ρi�V�K�����j
     */
    public static final String MODIFYED = "3";
    /**
    * 6�F�������s�i�V�K�����j
    */
    public static final String  MODIFY_FAIL_OPENORDER = "6";
    /**
     *  7�F��t�ρi�ύX�����j 
     */
    public static final String ACCEPTED_CHANGEORDER = "7";
    /**
     * 8�F�������i�ύX�����j 
     */
    public static final String MODIFYING_CHANGEORDER = "8";
    /**
     * 10�F�����ρi�ύX�����j
     */
    public static final String MODIFYED_CHANGEORDER = "10";
    /**
     *  11�F�������s�i�ύX�����j
     */
    public static final String MODIFY_FAIL_CHANGEORDER = "11";
    /**
     *  12�F��t�ρi��������j 
     */
    public static final String  ACCEPTED_CANCELORDER = "12";
    /**
     * 13�F�������i��������j
     */
    public static final String MODIFYING_CANCELORDER = "13";
    /**
     *  14�F�����ρi��������j 
     */
    public static final String MODIFYED_CANCELORDER = "14";
    /**
     * 15�F�������s�i��������j 
     */
    public static final String MODIFY_FAIL_CANCELORDER = "15";
    /**
     *20�F�ꕔ���� 
     */
    public static final String PART_INAFFECTED = "20";
    /**
     *21�F�S������ 
     */
    public static final String FULL_INAFFECTED = "21";
    /**
     *22�F����
     */
    public static final String CLOSED = "22";

    /**
     * 23�F�蓮����
     */
    public static final String MANUAL_EXPIRED = "23";

    /**
     * 24�F�ؑ֒���
     */
    public static final String TRANSFER_ORDER = "24";

    /**
     * 25�F�ؑ֎�t
     */
    public static final String TRANSFER_ACCEPT = "25";

    /**
     * 26�F�ؑ֊���
     */
    public static final String TRANSFERRED = "26";

    /**
     * 27�F�ؑ֒���(���s)
     */
    public static final String TRANSFER_ORDER_FAIL = "27";

    /**
     * 50 : �J�z��
     */
    public static final String TRANSFERED = "50";

    /**
     * 51 : �J�z���s
     */
    public static final String NOT_TRANSFERED = "51";

}
@
