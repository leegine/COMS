head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderStatusFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����FLAG�i�����j  �萔��`�C���^�t�F�C�X(WEB3OrderStatusFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23�@@�d�� (SRA) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * ����FLAG�i�����j�@@�萔��`�C���^�t�F�C�X
 *
 * @@author �d��(SRA)
 * @@version 1.0
 */
public interface WEB3OrderStatusFlagDef
{

    /**
     *  �O�F�������@@
     */
    public static final String NOT_DEAL = "0";

    /**
     *  �P�F�v����M�@@
     */
    public static final String REPUEST_RECEIPT = "1";

    /**
     *  �Q�F�������M�@@
     */
    public static final String RESPONSE_SEND = "2";
    
    /**
     *  �R�F�v���G���[�iNG�j 
     */
    public static final String REPUEST_ERROR_NG = "3";
    
    /**
     *  �S�F�v���G���[�iERROR�j�@@
     */
    public static final String REPUEST_ERROR_ERROR = "4";
    
    /**
     *  �T�F�Z�b�V�����G���[ 
     */
    public static final String SESSION_ERROR = "5";
}
@
