head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SonarExecutionConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SONAR���s�����@@�萔��`�C���^�t�F�C�X(WEB3ExecutionCondition)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 �{���@@�瑐(SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * SONAR���s�����@@�萔��`�C���^�t�F�C�X
 *
 * @@author �{���瑐(SRA)
 * @@version 1.0
 */
public interface WEB3SonarExecutionConditionDef
{
    /**
     * ������
     */
    public static final String UNCONDITIONDNESS = "1";
    
    /**
     * �o��
     */
    public static final String COME_TO_TERMS = "2";
    
    /**
     * ��t
     */
    public static final String AT_MARKET_OPEN = "3";
    
    /**
     * ����
     */
    public static final String AT_MARKET_CLOSE = "4";
    
    /**
     * �o�����Έ���(�s��)
     */
    public static final String NO_EXECUTED_MARKET_ORDER = "7";

}
@
