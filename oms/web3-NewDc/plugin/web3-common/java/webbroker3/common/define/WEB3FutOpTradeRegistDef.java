head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FutOpTradeRegistDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �敨OP����o�^�@@�萔��`�C���^�t�F�C�X(WEB3FutOpTradeRegistDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ����� (���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �敨OP����o�^�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3FutOpTradeRegistDef
{
    /**
      *0�F�@@�o�^�Ȃ� 
      */
    public static final String NOT_REGIST = "0";
    
    /**
      *1�F�@@�o�^�ς݁iOP��������j 
      */
    public static final String OP_REGIST = "1";
    
    /**
      *2�F�@@�o�^�ς݁i�敨����j
      */
    public static final String FUT_REGIST = "2";
    
    /**
      *3�F�@@�o�^�ς݁i�敨�^OP����j
      */
    public static final String FUT_OP_REGIST = "3";

}
@
