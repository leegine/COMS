head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TriggerOrderTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3TriggerOrderTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/13 ������(���u)�@@�V�K�쐬
*/
package webbroker3.common.define;

/**
 * ����������� �萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author ������
 * @@version 1.0
 */
public interface WEB3TriggerOrderTypeDef
{
    /**
     * 1�F�A������
     */
    public static final String SUCC = "1";
    
    /**
     * 2�FOCO����
     */
	public static final String OCO = "2";
	
	/**
	 * 3�FIFD����
	 */
	public static final String IFD = "3";
	
	/**
	 * 4�F�t�w�l����
	 */
	public static final String STOP = "4";

    /**
     * 5�FW�w�l����
     */
    public static final String W_LlIMIT = "5";
}
@
