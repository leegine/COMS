head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3OrderAppStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\���\���󋵋敪�萔��`�C���^�t�F�C�X(WEB3OrderAppStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10  ���C�g(sinocom)�@@�V�K�쐬
*/
package webbroker3.ipo.define;

/**
 * �\���\���󋵋敪
 *                                                                     
 * @@author ���C�g
 * @@version 1.0
 */
public interface WEB3OrderAppStatusDef
{

    /**
     * 01�F�u�b�N�r���f�B���O�\����<BR>
     */
    public static final String BOOK_BUILDING_DEMAND_COMPLETE = "01";

    /**
     * 02�F�u�b�N�r���f�B���O�L�����Z��<BR>
     */
    public static final String BOOK_BUILDING_DEL = "02";
    
    /**
     * 03�F���I
     */
    public static final String ELECTION = "03";
    
    /**
     * 04�F���I�L�����Z��
     */
    public static final String ELECTION_DEL = "04";
    
    /**
     * 05�F�\����
     */
    public static final String APPLICATION = "05";
    
    /**
     * 06�F����
     */
    public static final String EXECUTED = "06";
    
    /**
     * 07�F�⌇
     */
    public static final String SUPPLEMENT = "07";
    
    /**
     * 08�F�⌇�L�����Z��
     */
    public static final String SUPPLEMENT_DEL = "08";
    
    /**
     * 09�F�⌇�\����
     */
    public static final String SUPPLEMENT_APPLICATION = "09";
    
    /**
     * 10�F�⌇����
     */
    public static final String SUPPLEMENT_EXECUTED = "10";
    
    /**
     * 11�F�⌇���I
     */
    public static final String SUPPLEMENT_DEFEAT = "11";
    
    /**
     * 12�F���I
     */
    public static final String DEFEAT = "12";
    
    
    
    
    
    
}@
