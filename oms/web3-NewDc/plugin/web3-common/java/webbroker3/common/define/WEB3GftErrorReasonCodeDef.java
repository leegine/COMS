head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftErrorReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3GftErrorReasonCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/24 �����(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * GFT�G���[���R�R�[�h�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3GftErrorReasonCodeDef
{
    /**
     *  ����
     */
    public static final String NORMAL = "0000";

    /**
     *  ����M�敪�`�F�b�N�G���[
     */
    public static final String SENDRCV_ERROR = "0001";    

    /**
     *  �p�����[�^�Ó����`�F�b�N�G���[
     */
    public static final String PARAM_VALIDITY_ERROR = "0002";    

    /**
     *  �p�����[�^��v�`�F�b�N�G���[
     */
    public static final String PARAM_MISMATCH_ERROR = "0003";    

    /**
     *  ��t���ʃR�[�h�`�F�b�N�G���[
     */
    public static final String RESULT_CODE_ERROR = "0004";    

    /**
     *  ���̑��G���[
     */
    public static final String OTHER_ERROR = "9001";    
}
@
