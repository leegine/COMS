head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqActionStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������ԋ敪�@@�萔��`�C���^�t�F�C�X(WEB3FeqActionStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 �Г�(���u) �V�K�쐬
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.466
*/
package webbroker3.feq.define;

/**
 * ������ԋ敪�@@�萔��`�C���^�t�F�C�X
 * 
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqActionStatusDivDef
{
    /**
     * 00�F�V�K����
     */
    public static final String NEW_ORDER = "00";

    /**
     * 01�F������t
     */
    public static final String NEW_ORDER_ACCEPT = "01";

    /**
     * 02�F�V�K����(���s)
     */
    public static final String NEW_ORDER_REJECT = "02";

    /**
     * 03�F��������
     */
    public static final String CHANGE = "03";

    /**
     * 04�F������t
     */
    public static final String CHANGE_ACCEPT = "04";

    /**
     * 05�F��������
     */
    public static final String CHANGE_COMPLETE = "05";

    /**
     * 06�F��������(���s)
     */
    public static final String CHANGE_REJECT = "06";

    /**
     * 07�F�������
     */
    public static final String CANCEL = "07";

    /**
     * 08�F�����t
     */
    public static final String CANCEL_ACCEPT = "08";

    /**
     * 09�F�������
     */
    public static final String CANCEL_COMPLETE = "09";

    /**
     * 10�F�������(���s)
     */
    public static final String CANCEL_REJECT = "10";

    /**
     * 11�F�ꕔ���
     */
    public static final String PART_EXECUTE = "11";

    /**
     * 12�F�S�����
     */
    public static final String FULL_EXECUTE = "12";

    /**
     * 13�F�����
     */
    public static final String UNDO_EXECUTE = "13";

    /**
     * 14�F����
     */
    public static final String EXPIRE = "14";

    /**
     * 15�F�������
     */
    public static final String UNDO_EXPIRE = "15";

    /**
     * 16�F����
     */
    public static final String INVALID = "16";

    /**
     * 17�F�����J�z
     */
    public static final String ORDER_CARRY_OVER = "17";

    /**
     * 18�F�����J�z(���s)
     */
    public static final String ORDER_CARRY_OVER_REJECT = "18";
    
    /**
     * 19�F�����(�������n)
     */
    public static final String UNDO_EXECUTE_SWAP = "19";
    
    /**
     * 20�F������
     */
    public static final String NEW_ORDER_ORDERING = "20";
    
    /**
     * 21�F�����x��
     */
    public static final String ORDER_DELAY = "21";

    /**
     * 22�F�ؑ֒x��
     */
    public static final String SWITCH_DELAY = "22";

    /**
     * 23�F�ؑ֒���
     */
    public static final String SWITCH_ORDER = "23";

    /**
     * 24�F�ؑ֎�t
     */
    public static final String SWITCH_ACCEPT = "24";

    /**
     * 25�F�ؑ֊���
     */
    public static final String SWITCH_OVER = "25";

    /**
     * 26�F�ؑ֒���(���s)
     */
    public static final String SWITCH_ORDER_FAIL = "26";

    /**
     * 27�F�X�g�b�v��������
     */
    public static final String STOP_ORDER_EXPIRE = "27";

    /**
     * 28�F������t���
     */
    public static final String ORDER_ACCEPT_CANCEL = "28";

    /**
     * 29�F��������
     */
    public static final String FORCED_EXPIRE = "29";

    /**
     * 30�F���F��
     */
    public static final String APPROVED = "30";
    
    /**
     * 31�F��菈����(�ꕔ���)
     */
    public static final String PROCESSING_PART_EXECUTE = "31";

    /**
     * 32�F��菈����(�S�����)
     */
    public static final String PROCESSING_FULL_EXECUTE = "32";
    
    /**
     * 99�F���̑�
     */
    public static final String OTHER = "99";
}
@
