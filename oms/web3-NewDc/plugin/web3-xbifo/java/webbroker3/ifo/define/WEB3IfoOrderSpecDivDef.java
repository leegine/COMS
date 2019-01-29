head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderSpecDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������e�敪�C���^�t�F�C�X(WEB3IfoOrderSpecDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/30 ������(���u) �V�K�쐬
                 : 2006/07/16 ���G��(���u) �d�l�ύXNo.472
*/
package webbroker3.ifo.define;

/**
 * �������e�敪 �萔��`�C���^�t�F�C�X<BR>
 * 00�F�V�K���� 01�F������t 02�F�V�K����(���s) 03�F�������� 04�F������t <BR>
 * 05�F�������� 06�F��������(���s) 07�F������� 08�F�����t  09�F�������<BR>
 * 10�F�������(���s)11�F�ꕔ��� 12�F�S����� 13�F����� 14�F���� 15�F������� <BR>
 * 16�F���� 17�F�����J�z 18�F�����J�z(���s) 20�F������ 21�F�����x�� 22:�ؑ֒x�� <BR>
 * 23�F�ؑ֒��� 24�F�ؑ֎�t 25�F�ؑ֊��� 26�F�ؑ֒���(���s)<BR>
 * 27�F�X�g�b�v�������� 99�F���̑�<BR>
 * @@author ������
 * @@version 1.0
 */
public interface WEB3IfoOrderSpecDivDef
{
    /**
     * 00�F�V�K�����@@�@@  �@@�@@�@@�@@�@@  �@@�@@
     */
    public final static String OPEN_ORDER = "00";

    /**
     * 01�F������t�@@
     */ 
    public final static String ORDER_ACCEPT = "01";
    
    /**
     * 02�F�V�K����(���s)�@@
     */ 
    public final static String OPEN_ORDER_FAIL = "02";
    
    /**
     * 03�F���������@@
     */ 
    public final static String CHANGE_ORDER = "03";
    
    /**
     * 04�F������t�@@
     */ 
    public final static String CHANGE_ACCEPT = "04";
    
    /**
     * 05�F���������@@
     */ 
    public final static String CHANGE_FINISH = "05";
    
    /**
     * 06�F��������(���s)�@@
     */ 
    public final static String CHANGE_ORDER_FAIL = "06";
      
    /**
     * 07�F��������@@
     */ 
    public final static String CANCEL_ORDER = "07";
    
    /**
     * 08�F�����t
     */ 
    public final static String CANCEL_ACCEPT = "08";

    /**
     * 09�F�������
     */ 
    public final static String CANCEL_FINISH = "09";

    /**
     * 10�F�������(���s)
     */ 
    public final static String CANCEL_ORDER_FAIL = "10";

    /**
     * 11�F�ꕔ���
     */ 
    public final static String PARTIALLY_EXECUTED = "11";

    /**
     * 12�F�S�����
     */ 
    public final static String FULLY_EXECUTED = "12";
    
    /**
     * 13�F�����
     */ 
    public final static String EXCUTED_CANCEL = "13";
    
    /**
     * 14�F����
     */ 
    public final static String CLOSE = "14";
    
    /**
     * 15�F�������
     */ 
    public final static String CLOSE_FAIL = "15";

    /**
     * 16�F����
     */ 
    public final static String INEFFECTIVE = "16";
    
    /**
     * 17�F�����J�z
     */ 
    public final static String ORDER_CARRYOVER = "17";
    
    /**
     * 18�F�����J�z(���s)
     */ 
    public final static String ORDER_CARRYOVER_FAIL = "18";
    
    /**
     * 20�F������
     */ 
    public final static String ORDERING = "20";

    /**
     * 21�F�����x��
     */ 
    public final static String ORDER_DELAY = "21";
    
    /**
     * 22:�ؑ֒x��
     */
    public final static String SWITCH_DELAY = "22";
    
    /**
     * 23�F�ؑ֒��� 
     */
    public final static String SWITCH_ORDER = "23";
    
    /**
     * 24�F�ؑ֎�t 
     */
    public final static String SWITCH_ACCEPT = "24";
    
    /**
     * 25�F�ؑ֊��� 
     */
    public final static String SWITCH_OVER = "25";
    
    /**
     * 26�F�ؑ֒���(���s)
     */
    public final static String SWITCH_ORDER_FAIL = "26";
    
    /**
     * 27�F�X�g�b�v��������
     */
    public final static String STOP_ORDER_EXPIRE = "27";

    /**
     * 99�F���̑�
     */ 
    public final static String OTHER = "99";
    
} @
