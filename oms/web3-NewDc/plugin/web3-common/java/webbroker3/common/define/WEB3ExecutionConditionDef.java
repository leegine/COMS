head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExecutionConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ExecutionConditionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * �����㎷�s�����@@�萔��`�C���^�t�F�C�X
 *                                                                       
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ExecutionConditionDef
{
    /**
     * 1 : ������
     */
    public static final String NO_CONDITION = "1";

    /**
     * 2 : �o��
     */
    public static final String COME_TO_TERMS = "2";

    /**
     * 3 : ��t
     */
    public static final String AT_MARKET_OPEN = "3";

    /**
     * 4 : ����
     */
    public static final String AT_MARKET_CLOSE = "4";
    
    /**
     * 7 : �o�����Έ���(�s��)
     */
    public static final String AT_MARKET_CLOSE_NOT_EXECUTED = "7";    


}
@
