head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3ControlCodeListDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ControlCodeListDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/18  ���o��(sinocom)�@@�V�K�쐬
Revesion History : 2005/12/20  杊��] (���u) �d�l�ύXNo.101�C��
*/
package webbroker3.ipo.define;

/**
 * ����R�[�h�ꗗ
 *                                                                     
 * @@author ���o��
 * @@version 1.0
 */
public interface WEB3ControlCodeListDef
{

    /**
     * 01�F�@@����
     */
    public static final String CHANGE = "01";

    /**
     * 02�F�@@�폜
     */
    public static final String CANCEL = "02";
    
    /**
     * 11�F�@@�u�b�N�r���f�B���O�󋵃_�E�����[�h
     */
    public static final String BOOK_BUILDING_SITUATION_DOWNLOAD = "11";
    
    /**
     * 12�F�@@���I���ʁ^�w���\���󋵃_�E�����[�h
     */
    public static final String LOT_RESULT_OFFER_STATE_DOWNLOAD = "12";
    
    /**
     * 21�F�@@���I���ʃA�b�v���[�h
     */
    public static final String LOT_RESULT_UPLOAD = "21";
    
    /**
     * 31�F�@@IPO��W��~�^�ĊJ
     */
    public static final String IPO_RECRUIT_STOP_RESUMPTION = "31";
    
    /**
     * 32�F�@@IPO���~
     */
    public static final String IPO_DISCONTINUATION = "32";

    /**
     * 41�F�@@IPO���I��������
     */
    public static final String IPO_LOT_INPUT = "41";

    /**
     * 42�F�@@IPO���I�������ʊm�F
     */
    public static final String IPO_LOT_RESULT_CONFIRM = "42";

    /**
     * 43�F�@@IPO���I�������ʊ���
     */
    public static final String IPO_LOT_RESULT_COMPLETE = "43";
    
}
@
