head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CarriedOrderDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �o����܂Œ����@@�萔��`�C���^�t�F�C�X(WEB3CarriedOrderDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 羐� (���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �o����܂Œ����@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author 羐�
 * @@version 1.0
 */
public interface WEB3CarriedOrderDef
{
    /**
      * �o����܂Œ���   0�F�戵�s�� 
      */
    public static final String CAN_NOT_DEALT = "0";
    
    /**
      * �o����܂Œ���   1�F�戵�\�i�T���c�Ɠ��܂�) 
      */
    public static final String DEALT_TO_WEEK_END = "1";
    
    /**
      * �o����܂Œ���   2�F�戵�\�i�����c�Ɠ��܂Łj 
      */
    public static final String DEALT_TO_MONTH_END = "2";
    
    /**
      * �o����܂Œ���   3�F�戵�\�i3������j 
      */
    public static final String DEALT_TO_THREE_MONTH = "3";
    
    /**
      * �o����܂Œ���   9�F�戵�\�i�����w��j 
      */
    public static final String DEALT_DESIGNATA_DAYS = "9";
    
}@
