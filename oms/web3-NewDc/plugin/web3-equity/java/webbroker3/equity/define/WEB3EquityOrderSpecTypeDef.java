head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderSpecTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������e�敪�@@�萔��`�C���^�t�F�C�X(WEB3EquityOrderSpecTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �������F(SRA) �V�K�쐬
Revesion History : 2006/11/07 ������@@(���u)���f�� No.990
Revesion History : 2007/06/05 �����q (���u) �d�l�ύX�E���f��1164
Revesion History : 2007/07/31 �����q (���u) �d�l�ύX�E���f��1188
Revesion History : 2007/10/10 �����q (���u) �d�l�ύX���f��No.1194
*/
package webbroker3.equity.define;

/**
 * �������e�敪�@@�萔��`�C���^�t�F�C�X
 * 
 * @@author �������F
 * @@version 1.0
 */
public interface WEB3EquityOrderSpecTypeDef {
    
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
     * 29�F��������
     */
    public static final String FORCED_EXPIRE = "29";

    /**
     * 30�F���F��
     */
    public static final String APPROVED = "30";

    /**
     * 99�F���̑�
     */
    public static final String OTHER = "99";
}
@
